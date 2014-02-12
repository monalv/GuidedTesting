package iris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.generic.GotoInstruction;
import org.apache.bcel.generic.IfInstruction;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ReturnInstruction;
import org.apache.bcel.generic.Select;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * @author utambe, mvora 
 * This class is used to create CFGs and find Guided Test
 *         Cases.
 */
public class CFG {

	/* Dotty File Resource */
	protected static final String[] dottyFileHeader = new String[] {
			"digraph control_flow_graph {", "",
			"	node [shape = rectangle]; entry exit;",
			"	node [shape = circle];", "" };
	protected static final String[] dottyFileFooter = new String[] { "}" };
	protected static final String dottyEntryNode = "entry";
	protected static final String dottyExitNode = "exit";
	protected static final String dottyLineFormat = "	%1$s -> %2$s;%n";
	protected static final String dottyLineLabelFormat = "	%1$s -> %2$s [label = \"%3$s\"];%n";
	protected static String dottyContent = "";

	/* IO members */
	static FileWriter fwriter;
	static BufferedReader br;

	/* Supporting Data Structures */
	static HashMap<Integer, String> TestCaseNumMapping = new HashMap<Integer, String>();
	static int TotalTests = 1;
	static HashMap<String, ArrayList<Integer>> TestCaseMapping = new HashMap<String, ArrayList<Integer>>();
	static HashMap<String, ArrayList<Integer>> ComboMapping = new HashMap<String, ArrayList<Integer>>();
	protected static LineNumberTable lineTable;
	protected static ArrayList<CFGNode> uncoveredNodes;
	static double ComboPercent = 0.0;
	static HashMap<Integer, UncoveredNode> uncoveredNodeList = new HashMap<Integer, UncoveredNode>();

	/* Resources */
	/*
	 * Test Command Line Arguments:
	 * /home/utambe/workspace/GuidedTesting/test_class/Login_jsp.class
	 * /home/utambe/workspace/GuidedTesting/dotty/dotty Login
	 * http://localhost:8080/bookstore/Login.jsp bookstore /home/utambe 5
	 */
	static String SERVLET_NAME;
	static String SERVLET_URL;
	static String APP_NAME;
	static String SRC_PATH;
	static int RANK_THRESHOLD;

	/* Shell Script Resource */
	static String cmdLogin = "wget --directory-prefix "
			+ SRC_PATH
			+ "/workspace/GuidedTesting/wget/olduc/UC --keep-session-cookies --save-cookies cookies.txt --post-data ";
	static String cmdPOST = "wget --directory-prefix "
			+ SRC_PATH
			+ "/workspace/GuidedTesting/wget/olduc/UC  --load-cookies cookies.txt --post-data ";
	static String URL = " http://localhost:8080";
	static String restartDB = "sh " + SRC_PATH
			+ "/workspace/GuidedTesting/scripts/scriptStart.sh\n";
	static String makeDir = "rm -r " + SRC_PATH
			+ "/workspace/GuidedTesting/wget/olduc/UC \n mkdir " + SRC_PATH
			+ "/workspace/GuidedTesting/wget/olduc/UC\n";
	static String coverageReport = "wget --directory-prefix "
			+ SRC_PATH
			+ "/workspace/GuidedTesting/restartwget http://localhost:8080/"
			+ APP_NAME
			+ "/Restart.jsp\n"
			+ "cd "
			+ SRC_PATH
			+ "/Downloads/cobertura\n"
			+ " ./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-"
			+ APP_NAME + ".ser --datafile " + SRC_PATH + "/" + APP_NAME
			+ "/cobertura.ser\n" + "./cobertura-report.sh --datafile "
			+ SRC_PATH + "/" + APP_NAME + "/cobertura.ser --destination  "
			+ SRC_PATH + "/workspace/GuidedTesting/usecases/olduc/reports-"
			+ APP_NAME + "/ " + SRC_PATH + "/" + APP_NAME + "/src\n"
			+ "./cobertura-report.sh --format xml --datafile " + SRC_PATH + "/"
			+ APP_NAME + "/cobertura.ser --destination  " + SRC_PATH
			+ "/workspace/GuidedTesting/usecases/olduc/reports-" + APP_NAME
			+ "/ " + SRC_PATH + "/" + APP_NAME + "/src\n" + "rm -r " + SRC_PATH
			+ "/workspace/GuidedTesting/restartwget \n mkdir " + SRC_PATH
			+ "/workspace/GuidedTesting/restartwget\n";

	static String coverageReportEnd = "wget --directory-prefix "
			+ SRC_PATH
			+ "/workspace/GuidedTesting/restartwget http://localhost:8080/"
			+ APP_NAME
			+ "/Restart.jsp\n"
			+ "cd "
			+ SRC_PATH
			+ "/Downloads/cobertura\n"
			+ " ./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-"
			+ APP_NAME + ".ser --datafile " + SRC_PATH + "/" + APP_NAME
			+ "/cobertura.ser\n" + "./cobertura-report.sh --datafile "
			+ SRC_PATH + "/" + APP_NAME + "/cobertura.ser --destination  "
			+ SRC_PATH + "/workspace/GuidedTesting/usecases/olduc/reports-"
			+ APP_NAME + "/ " + SRC_PATH + "/" + APP_NAME + "/src\n"
			+ "./cobertura-report.sh --format xml --datafile " + SRC_PATH + "/"
			+ APP_NAME + "/cobertura.ser --destination  " + SRC_PATH
			+ "/workspace/GuidedTesting/usecases/olduc/reports-" + APP_NAME
			+ "/ " + SRC_PATH + "/" + APP_NAME + "/src\n";
	static ArrayList<String> mapping = new ArrayList<>();
	static String ComboText = "";

	/**
	 * Constructor creates CFG for a given method and identifies uncovered
	 * nodes.
	 * 
	 * @param instructions
	 *            The Instructions of the method.
	 * @param methodName
	 *            The Method name whose CFG needs to be created.
	 **/
	public CFG(InstructionList instructions, String methodName) {

		InstructionHandle[] instHandles = instructions.getInstructionHandles();
		dottyContent = "\n";
		dottyContent += String.format(dottyLineFormat, dottyEntryNode,
				instHandles[0].getPosition());
		CFGNode node = new CFGNode();
		node.node = instHandles[0];
		node.parent = null;
		CFGNode prevnode = null;
		int i = 0;
		try {
			fwriter.write("Method Name: " + methodName + "\n");
			for (InstructionHandle instHandle : instHandles) {

				if (i != 0) {
					node = new CFGNode();
					node.parent = prevnode;
					node.node = instHandle;
				}
				String Tests = "";
				/* Print Line number to test case mapping. */
				if (TestCaseMapping.containsKey("." + SERVLET_NAME + "_jsp."
						+ lineTable.getSourceLine(instHandle.getPosition()))) {
					Tests = " [";
					for (Integer test : TestCaseMapping
							.get("."
									+ SERVLET_NAME
									+ "_jsp."
									+ lineTable.getSourceLine(instHandle
											.getPosition()))) {
						Tests += test.toString() + " ";
					}
					Tests += "]";

				}

				fwriter.write("parent:" + instHandle.getPosition() + ":"
						+ lineTable.getSourceLine(instHandle.getPosition())
						+ " Tests: " + Tests + "\n");

				Instruction inst = instHandle.getInstruction();
				/* Generate CFG. */
				if (inst instanceof GotoInstruction)
				/* GOTO, GOTO_W */
				{
					dottyContent += String.format(
							dottyLineFormat
									+ lineTable.getSourceLine(instHandle
											.getPosition()), instHandle
									.getPosition(), ((GotoInstruction) inst)
									.getTarget().getPosition())
							+ Tests;
					if (lineTable.getSourceLine(((GotoInstruction) inst)
							.getTarget().getPosition()) != lineTable
							.getSourceLine(instHandle.getPosition()))
						node.children.add(((GotoInstruction) inst).getTarget());
				} else if (inst instanceof IfInstruction)
				/*
				 * IF_ACMPEQ, IF_ACMPNE, IF_ICMPEQ, IF_ICMPGE, IF_ICMPGT,
				 * IF_ICMPLE, IF_ICMPLT, IF_ICMPNE, IFEQ, IFGE, IFGT,
				 * IfInstruction, IFLE, IFLT, IFNE, IFNONNULL, IFNULL
				 */
				{
					dottyContent += String.format(dottyLineFormat, instHandle
							.getPosition(), ((IfInstruction) inst).getTarget()
							.getPosition());
					dottyContent += String.format(dottyLineFormat, instHandle
							.getPosition(), instHandle.getNext().getPosition());

					if (lineTable.getSourceLine(((IfInstruction) inst)
							.getTarget().getPosition()) != lineTable
							.getSourceLine(instHandle.getPosition()))
						node.children.add(((IfInstruction) inst).getTarget());
					if (lineTable.getSourceLine(instHandle.getNext()
							.getPosition()) != lineTable
							.getSourceLine(instHandle.getPosition()))
						node.children.add(instHandle.getNext());
					GenerateFlowString(instHandle, "");
				} else if (inst instanceof Select)
				/* LOOKUPSWITCH, TABLESWITCH */
				{
					InstructionHandle[] targets = ((Select) inst).getTargets();
					for (InstructionHandle target : targets) {
						dottyContent += String.format(dottyLineFormat,
								instHandle.getPosition(), target.getPosition());
						if (target.getPosition() != instHandle.getPosition())
							node.children.add(target);
					}
					dottyContent += String.format(dottyLineFormat, instHandle
							.getPosition(), ((Select) inst).getTarget()
							.getPosition());
					if (lineTable.getSourceLine(((Select) inst).getTarget()
							.getPosition()) != lineTable
							.getSourceLine(instHandle.getPosition()))
						node.children.add(((Select) inst).getTarget());
				} else if (inst instanceof ReturnInstruction)
				/* ARETURN, DRETURN, FRETURN, IRETURN, LRETURN, RETURN */
				{
					GenerateFlowString(instHandle, null, "");
				} else {
					GenerateFlowString(instHandle, "");
				}

				/* Check and add uncovered nodes. */
				if (!ComboMapping.containsKey("." + SERVLET_NAME + "_jsp."
						+ lineTable.getSourceLine(instHandle.getPosition()))) {
					uncoveredNodes.add(node);
				}

				if (i != 0) {
					prevnode.next = node;
				}
				prevnode = node;
				i++;
			}

			prevnode.next = null;
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}

		getUncoverednodes();
		uncoveredNodes.clear();
	}

	/**
	 * This method Generates Flow String for a CFG.
	 * 
	 * @param instHandle1
	 *            The Origin handler of flow.
	 * @param instHandle2
	 *            The Destination handler of flow
	 * @param label
	 *            Label for the flow arrow.
	 **/
	public void GenerateFlowString(InstructionHandle instHandle1,
			InstructionHandle instHandle2, String label) {
		if (instHandle2 == null) {
			dottyContent += String.format(dottyLineFormat,
					instHandle1.getPosition(), dottyExitNode);
		} else {
			dottyContent += String.format(dottyLineFormat, instHandle1
					.getPosition(), instHandle2.getNext().getPosition());
		}
	}

	/**
	 * This method Generates Flow String for a CFG.
	 * 
	 * @param instHandle
	 *            The Last handler of flow.
	 * @param label
	 *            Label for the flow arrow.
	 **/
	public void GenerateFlowString(InstructionHandle instHandle, String label) {
		if (instHandle.getNext() == null) {
			dottyContent += String.format(dottyLineFormat,
					instHandle.getPosition(), dottyExitNode);
		} else {
			dottyContent += String.format(dottyLineFormat, instHandle
					.getPosition(), instHandle.getNext().getPosition());
		}
	}

	/**
	 * This method is to add source code to byte code line number mappings.
	 * 
	 * @param SourceLine
	 *            Line Number in Source Code.
	 * @param ByteCodeLine
	 *            Corresponding Line Number in Byte Code.
	 * @param lineMappings
	 *            Line Mapping to be updated.
	 */
	static void addMapping(Integer SourceLine, InstructionHandle ByteCodeLine,
			HashMap<Integer, ArrayList<InstructionHandle>> lineMappings) {
		if (lineMappings.containsKey(SourceLine)) {
			if (!lineMappings.get(SourceLine).contains(ByteCodeLine))
				lineMappings.get(SourceLine).add(ByteCodeLine);
		} else {
			ArrayList<InstructionHandle> byteCodeLines = new ArrayList<InstructionHandle>();
			byteCodeLines.add(ByteCodeLine);
			lineMappings.put(SourceLine, byteCodeLines);
		}
	}

	/**
	 * Generates a Dotty file representing the CFG.
	 * 
	 * @param out
	 *            OutputStream to write the dotty file to.
	 */
	public void generateDotty(OutputStream _out) {
		try {

			for (String str : dottyFileHeader)
				_out.write(str.getBytes());

			_out.write(dottyContent.getBytes());
			for (String str : dottyFileFooter)
				_out.write(str.getBytes());
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	/**
	 * This method writes Wgets for the given test cases.
	 * 
	 * @param file
	 *            File name for writing wgets.
	 */
	static void WriteWgets(String file) {
		String sCurrentLine = "";
		try {
			br = new BufferedReader(new FileReader("" + SRC_PATH
					+ "/workspace/GuidedTesting/test.log"));
			/* For individual WGETS. */
			FileWriter fw = new FileWriter(file, false);
			/* For combined WGETS. */
			FileWriter fw1 = new FileWriter(file.replace("scriptUsageWgets",
					"scriptUsageComboWgets"), false);
			fw.write("#! /bin/bash\n");
			fw1.write("#! /bin/bash\n");
			fw.write(restartDB);
			fw1.write(restartDB);
			int i = 1;
			fw.write(makeDir.replace("UC", "UC" + i));
			fw1.write(makeDir.replace("UC", "Combo"));
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("<<end>>")) {
					fw.write(coverageReportEnd.replace("reports-" + APP_NAME
							+ "", "reports-" + APP_NAME + "-" + "UC" + i));
					fw1.write(coverageReportEnd.replace("reports-" + APP_NAME
							+ "", "reports-" + APP_NAME + "-Combo-" + "UC"));
					break;
				} else if (sCurrentLine.contains("<<end of use case>>")) {
					TotalTests++;
					fw.write(coverageReport.replace("reports-" + APP_NAME + "",
							"reports-" + APP_NAME + "-" + "UC" + i));

					System.out.println("Success in writing Wgets for UseCase: "
							+ (i++));
					fw.write(restartDB + makeDir.replace("UC", "UC" + i));
				} else {
					String[] params = sCurrentLine.split(" ");
					System.out.println(">> " + params[0] + " " + params[1]
							+ " " + params[2]);
					if (params[0].contains("302")
							&& params[1].contains("" + SERVLET_NAME + ".jsp")) {
						fw.write(cmdLogin.replace("UC", "UC" + i) + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n");
						fw1.write(cmdLogin.replace("UC", "Combo") + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n");
						TestCaseNumMapping.put(TotalTests,
								params[2].replace("[", "").replace("]", ""));
						ComboText += cmdLogin.replace("UC", "Combo") + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n";
					} else {
						fw.write(cmdPOST.replace("UC", "UC" + i) + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n");
						fw1.write(cmdPOST.replace("UC", "Combo") + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n");
						ComboText += cmdPOST.replace("UC", "Combo") + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n";
						if (params[1].contains("Login.jsp"))
							TestCaseNumMapping.put(TotalTests, params[2]
									.replace("[", "").replace("]", ""));
					}
				}
			}
			@SuppressWarnings("rawtypes")
			Iterator it = TestCaseNumMapping.entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("unchecked")
				Entry<Integer, String> entry = (Entry<Integer, String>) it
						.next();
				System.out.println("Test:" + entry.getKey() + ": "
						+ entry.getValue());
			}
			fw.close();
			fw1.close();
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		} catch (Exception e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
		}
	}

	/**
	 * This method is used to read coverage XML and perform source code line
	 * number to test case mapping.
	 * 
	 * @param fileName
	 *            The File name of the XML Coverage Report.
	 * @param TestNumber
	 *            The Current Test number.
	 * @param isCombo
	 *            If it is combined report send true else false.
	 */
	static void testCaseMapping(String fileName, Integer TestNumber,
			boolean isCombo) {
		File file = new File(fileName);
		DocumentBuilder dBuilder;

		try {
			dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			org.w3c.dom.NodeList classes = doc.getElementsByTagName("class");
			for (int count1 = 0; count1 < classes.getLength(); count1++) {
				org.w3c.dom.Node classNode = classes.item(count1);
				if (classNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE
						&& classNode.getNodeName() == "class") {
					String ServletName = classNode.getAttributes().item(4)
							.getNodeValue();
					ServletName = ServletName.replace("org.apache.jsp", "");
					org.w3c.dom.NodeList methods = classNode.getChildNodes();
					for (int count2 = 0; count2 < methods.getLength(); count2++) {
						org.w3c.dom.Node method = methods.item(count2);
						if (method.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE
								&& method.getNodeName() == "methods") {
							org.w3c.dom.NodeList methodlist = method
									.getChildNodes();
							for (int count3 = 0; count3 < methodlist
									.getLength(); count3++) {
								org.w3c.dom.Node methodNode = methodlist
										.item(count3);
								if (methodNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE
										&& methodNode.getNodeName() == "method") {
									org.w3c.dom.NodeList lines = methodNode
											.getChildNodes();
									for (int count4 = 0; count4 < lines
											.getLength(); count4++) {
										org.w3c.dom.Node linesNode = lines
												.item(count4);
										if (linesNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE
												&& linesNode.getNodeName() == "lines") {
											org.w3c.dom.NodeList line = linesNode
													.getChildNodes();
											for (int count5 = 0; count5 < line
													.getLength(); count5++) {
												org.w3c.dom.Node lineNode = line
														.item(count5);
												if (lineNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE
														&& lineNode
																.getNodeName() == "line") {
													NamedNodeMap map1 = lineNode
															.getAttributes();
													if (!map1.item(2)
															.getNodeValue()
															.toString().trim()
															.equals("0")) {

														String lineNum = map1
																.item(3)
																.getNodeValue();
														String currLine = ServletName
																.trim()
																+ "."
																+ lineNum
																		.trim();
														if (!isCombo) {
															if (!TestCaseMapping
																	.containsKey(currLine)) {
																TestCaseMapping
																		.put(currLine,
																				new ArrayList<Integer>());
															}
															ArrayList<Integer> tests = TestCaseMapping
																	.get(currLine);
															tests.add(TestNumber);
															TestCaseMapping
																	.put(currLine,
																			tests);
														} else {
															if (!ComboMapping
																	.containsKey(currLine)) {
																ComboMapping
																		.put(currLine,
																				new ArrayList<Integer>());
															}
															ArrayList<Integer> tests = ComboMapping
																	.get(currLine);
															tests.add(TestNumber);
															ComboMapping.put(
																	currLine,
																	tests);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to read WAM output and generate URL templates.
	 * 
	 * @param servletName
	 *            The name of the servlet which is under inspection.
	 */
	static void readWAM(String servletName) {
		File fXmlFile = new File("" + SRC_PATH + "/" + APP_NAME
				+ "/analysis/interfaces/" + APP_NAME
				+ "-wamai-pda-interfaces.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList compoList = doc.getElementsByTagName("component");

			/* For all the components */
			for (int compo = 0; compo < compoList.getLength(); compo++) {
				org.w3c.dom.Node compoNode = compoList.item(compo);

				if (compoNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
					Element compoElement = (Element) compoNode;
					String componame = compoElement.getAttribute("name");

					if (componame.contains(servletName)) {
						NodeList interfaceList = compoElement
								.getElementsByTagName("interface");

						/* For all the interfaces */
						for (int temp = 0; temp < interfaceList.getLength(); temp++) {
							org.w3c.dom.Node nNode = interfaceList.item(temp);

							if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								NodeList paramList = eElement
										.getElementsByTagName("parameter");
								String value = "";

								if (paramList.getLength() != 0) {
									/* For all the parameters */
									for (int param = 0; param < paramList
											.getLength(); param++) {
										org.w3c.dom.Node paramNode = paramList
												.item(param);

										if (paramNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
											Element paramElement = (Element) paramNode;

											/*
											 * For each parameter getting the
											 * name,values
											 */
											String nameString = paramElement
													.getAttribute("name");
											String valuesStringList = paramElement
													.getAttribute("values")
													.replace("[", "")
													.replace("]", "");
											String[] paramValues = valuesStringList
													.split(",");
											value = value + nameString + "=";
											for (int i = 0; i < paramValues.length - 1; i++) {
												value = value
														+ paramValues[i].trim()
														+ ",";
											}
											value += "&";

										}
									}
									value = value.replace(",&", "&");
									mapping.add(value.substring(0,
											value.lastIndexOf("&")));
								}
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to run input test suite.
	 */
	public static void runTestSuite() {
		WriteWgets("" + SRC_PATH
				+ "/workspace/GuidedTesting/scriptUsageWgets.sh");
		try {
			Process proc, proc1;
			proc = Runtime.getRuntime().exec(
					"sh " + SRC_PATH
							+ "/workspace/GuidedTesting/scriptUsageWgets.sh");

			if (proc.waitFor() == 0)
				System.out.println("Success in Running Tests!");
			proc1 = Runtime
					.getRuntime()
					.exec("sh "
							+ SRC_PATH
							+ "/workspace/GuidedTesting/scriptUsageComboWgets.sh");
			if (proc1.waitFor() == 0)
				System.out.println("Success in Running Combined Tests!");
		} catch (IOException | InterruptedException e1) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e1);
			e1.printStackTrace();
		}
		testCaseMapping("" + SRC_PATH
				+ "/workspace/GuidedTesting/usecases/olduc/reports-" + APP_NAME
				+ "-Combo-UC" + "/coverage.xml", 1, true);
		for (int i = 1; i <= TotalTests; i++) {
			testCaseMapping("" + SRC_PATH
					+ "/workspace/GuidedTesting/usecases/olduc/reports-"
					+ APP_NAME + "-UC" + i + "/coverage.xml", i, false);
		}
		ComboPercent = getCoveragePercent("" + SRC_PATH
				+ "/workspace/GuidedTesting/usecases/olduc/reports-" + APP_NAME
				+ "-Combo-UC/frame-summary.html");
	}

	/**
	 * This method is used to extract and return coverage percentage.
	 * 
	 * @param report
	 *            The path of the XML coverage report.
	 * @return The coverage percent.
	 */
	static double getCoveragePercent(String report) {

		String reportText = "";
		try {
			br = new BufferedReader(new FileReader(report));
			String text = "";
			while ((text = br.readLine()) != null) {
				reportText += text;
			}
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}

		double coverage = 0.0;
		Pattern p = Pattern
				.compile("<td align=\"right\" class=\"percentgraph\" width=\"40\">([0-9]+)%</td>");
		Matcher m = p.matcher(reportText);
		while (m.find()) {
			coverage = Double.parseDouble(m.group(1));
			System.out.println(coverage);
			break;
		}
		return coverage;
	}

	/**
	 * This method is used to create CFGs for all the methods for a given
	 * servlet.
	 * 
	 * @param inputClassFilename
	 *            The file name of input class file.
	 * @param outputDottyFilename
	 *            The file name of output Dotty file.
	 */
	public static void createCFGs(String inputClassFilename,
			String outputDottyFilename) {

		/* Parse class file. */
		System.out.println("Parsing " + inputClassFilename + ".");
		JavaClass cls = null;
		try {
			cls = (new ClassParser(inputClassFilename)).parse();
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			System.out.println("Error while parsing " + inputClassFilename
					+ ".");
			System.exit(1);
		}
		int i = 0;
		for (Method m : cls.getMethods()) {
			lineTable = m.getCode().getLineNumberTable();
			/* Create CFG. */
			CFG cfg = new CFG(new InstructionList(m.getCode().getCode()),
					m.getName());
			System.out.println("Created CFG object for: " + m.getName() + " "
					+ uncoveredNodes.size());

			try {
				++i;
				OutputStream output = new FileOutputStream(outputDottyFilename
						+ i);
				cfg.generateDotty(output);
				output.close();
			} catch (IOException e) {
				Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
				System.out.println("Error while writing to "
						+ outputDottyFilename + ".");
				System.exit(1);
			}
		}

		System.out.println("Done with creating CFGs..");
		try {
			fwriter.close();
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is to create hash map of uncovered node list. It also
	 * associates list of child and parent for a uncovered node.
	 */
	public static void getUncoverednodes() {
		System.out.println("Uncovered nodes:  ");
		for (CFGNode node : uncoveredNodes) {

			if (uncoveredNodeList.containsKey(lineTable.getSourceLine(node.node
					.getPosition()))) {
				if (node.parent != null)
					uncoveredNodeList.get(lineTable.getSourceLine(node.node
							.getPosition())).parent.add(node.parent.node);  //*?*
			} else {
				System.out.println(lineTable.getSourceLine(node.node
						.getPosition()));
				UncoveredNode ucNode = new UncoveredNode();
				ucNode.children = node.children;
				if (node.parent != null)
					ucNode.parent.add(node.parent.node);
				ucNode.instHandle = node.node;
				uncoveredNodeList.put(
						lineTable.getSourceLine(node.node.getPosition()),
						ucNode);
			}
		}
	}

	/**
	 * This method associates unique URL for each parent and child for the given
	 * uncovered node.
	 * 
	 * @param ucNode
	 *            Entry Set of Uncovered Node.
	 * @return Hash set of test cases.
	 */
	static HashSet<Integer> getRelationalURLs(
			Entry<Integer, UncoveredNode> ucNode) {
		HashSet<Integer> testCases = new HashSet<Integer>();
		for (InstructionHandle instHandle : ucNode.getValue().parent) {
			if (TestCaseMapping.containsKey("." + SERVLET_NAME + "_jsp."
					+ lineTable.getSourceLine(instHandle.getPosition()))) {
				for (Integer test : TestCaseMapping.get("." + SERVLET_NAME
						+ "_jsp."
						+ lineTable.getSourceLine(instHandle.getPosition())))
					testCases.add(test);
			}

		}

		for (InstructionHandle instHandle : ucNode.getValue().children) {
			if (TestCaseMapping.containsKey("." + SERVLET_NAME + "_jsp."
					+ lineTable.getSourceLine(instHandle.getPosition()))) {
				for (Integer test : TestCaseMapping.get("." + SERVLET_NAME
						+ "_jsp."
						+ lineTable.getSourceLine(instHandle.getPosition())))
					testCases.add(test);
			}

		}
		return testCases;
	}

	/**
	 * For each URL extract parameter and its associated values.
	 * 
	 * @param URL
	 *            URL under inspection.
	 * @return Hash map of parameter and its associated values.
	 */
	static HashMap<String, CopyOnWriteArrayList<String>> getParamValues( //*?*
			String URL) {
		String[] paramValue = URL.split("&");
		HashMap<String, CopyOnWriteArrayList<String>> paramValues = new HashMap<String, CopyOnWriteArrayList<String>>();
		for (String pv : paramValue) {
			String[] str = pv.split("=");
			if (str.length < 2) {
				paramValues.put(str[0].trim(),
						new CopyOnWriteArrayList<String>());
			} else {
				String[] values = str[1].split(",");
				CopyOnWriteArrayList<String> valueList = new CopyOnWriteArrayList<String>();
				for (String value : values) {
					if (!value.isEmpty()) {
						valueList.add(value.trim());
					}
				}

				paramValues.put(str[0].trim(), valueList);
			}
		}
		return paramValues;
	}

	/**
	 * Main method. Generate a Dotty file with the CFG representing a given
	 * class file.
	 * 
	 * @param args
	 *            Expects two arguments: <input-class-file> <output-dotty-file>
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		uncoveredNodes = new ArrayList<CFGNode>();
		try {
			fwriter = new FileWriter("trace.log", false);
		} catch (IOException e2) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e2);
			e2.printStackTrace();
		}

		/* Check arguments. */
		if (args.length != 7) {
			System.out.println("Wrong number of arguments.");
			System.out
					.println("Usage: CFG <input-class-file> <output-dotty-file>");
			System.exit(1);
		}
		String inputClassFilename = args[0];
		String outputDottyFilename = args[1];
		SERVLET_NAME = args[2];
		SERVLET_URL = " " + args[3];
		APP_NAME = args[4];
		SRC_PATH = args[5];
		RANK_THRESHOLD = Integer.parseInt(args[6]);

		System.out.println("Running TestSuite..");
		runTestSuite();
		System.out.println("Success in running TestSuite..");

		createCFGs(inputClassFilename, outputDottyFilename);

		/* Read WAM output. */
		System.out.println("Reading WAM output..");
		readWAM("" + SERVLET_NAME + "_jsp");
		System.out.println("Success in reading WAm output..");
		System.out.println("Uncovered Nodes: ");
		@SuppressWarnings("rawtypes")
		Iterator itv = uncoveredNodeList.entrySet().iterator();
		while (itv.hasNext()) {
			Entry<Integer, UncoveredNode> ucNode = (Entry<Integer, UncoveredNode>) itv
					.next();
			System.out.println(ucNode.getKey() + " ");
		}

		HashSet<String> GuidedURLs = getGuidedURLs();

		runGuidedTests(GuidedURLs);

		double newCoverage = getCoveragePercent("" + SRC_PATH
				+ "/workspace/GuidedTesting/usecases/olduc/reports-" + APP_NAME
				+ "-UCWget/frame-summary.html");
		System.out.println("Old Coverage: " + ComboPercent + " New Coverage: "
				+ newCoverage + " Improved Percentage: "
				+ (newCoverage - ComboPercent));
	}

	/**
	 * This method is to run heuristics and calculate guided URLs.
	 * 
	 * @return The guided URLs.
	 */
	@SuppressWarnings("unchecked")
	static HashSet<String> getGuidedURLs() {
		HashSet<String> GuidedURLs = new HashSet<String>();
		@SuppressWarnings("rawtypes")
		Iterator it = uncoveredNodeList.entrySet().iterator();

		/* For each uncovered node. */
		while (it.hasNext()) {
			Entry<Integer, UncoveredNode> ucNode = (Entry<Integer, UncoveredNode>) it
					.next();
			System.out.println("Processing " + ucNode.getKey() + " Node..");
			HashMap<String, Integer> URLRankings = new HashMap<String, Integer>();

			/* For each uncovered node get Parent and child unique URLs. */
			HashSet<Integer> testCases = getRelationalURLs(ucNode);

			/* For each WAM URL pattern. */
			for (String wamURL : mapping) {
				System.out.println("WAM URL:" + wamURL);
				HashMap<String, CopyOnWriteArrayList<String>> paramValues = getParamValues(wamURL);

				/* Compare for each Relational URL for current uncovered node. */
				for (Integer test : testCases) {
					HashMap<String, CopyOnWriteArrayList<String>> paramValuesWAM = new HashMap<String, CopyOnWriteArrayList<String>>();
					paramValuesWAM = (HashMap<String, CopyOnWriteArrayList<String>>) paramValues
							.clone();
					String testURL = TestCaseNumMapping.get(test);
					System.out.println("TEST URL" + testURL);
					HashMap<String, CopyOnWriteArrayList<String>> paramValuesTest = getParamValues(testURL);
					boolean paramsMatched = false;
					int matchParamCount = 0;
					int matchValueCount = 0;

					@SuppressWarnings("rawtypes")
					Iterator it2 = paramValues.entrySet().iterator();
					ArrayList<String> matchedParams = new ArrayList<String>();
					/* Compare parameters. */
					while (it2.hasNext()) {
						Entry<String, CopyOnWriteArrayList<String>> pv = (Entry<String, CopyOnWriteArrayList<String>>) it2
								.next();
						System.out.println("WAM PARAM: " + pv.getKey());
						if (paramValuesTest.containsKey(pv.getKey())) {
							matchParamCount++;
							System.out.println("Matched with Test Param!");
							matchedParams.add(pv.getKey());
						}
					}

					/*
					 * If parameters are all matched then check for values - if
					 * any missing.
					 */
					if (matchParamCount == paramValuesTest.size()) {
						paramsMatched = true;
						for (String param : matchedParams)
							for (String val : paramValues.get(param)) {
								String testValue = paramValuesTest.get(param)
										.toString();
								testValue = testValue.replace("[", "").replace(
										"]", "");
								System.out.println("WAM VALUE: " + val
										+ " TEST VALUE: "
										+ paramValuesTest.get(param));
								if (testValue.equals(val)) {
									CopyOnWriteArrayList<String> newValues = paramValuesWAM
											.get(param);
									newValues.remove(val);
									paramValuesWAM.put(param, newValues);
									matchValueCount++;
									System.out
											.println("Matched with Test Value!");
								}
							}
					} else {
						@SuppressWarnings("rawtypes")
						/* Compare Value. */
						Iterator it3 = paramValues.entrySet().iterator();
						while (it3.hasNext()) {
							Entry<String, CopyOnWriteArrayList<String>> pv1 = (Entry<String, CopyOnWriteArrayList<String>>) it3
									.next();
							String param = pv1.getKey();
							for (String val : paramValues.get(param)) {
								String testValue = paramValuesTest.get(param)
										.toString();
								testValue = testValue.replace("[", "").replace(
										"]", "");
								System.out.println("WAM VALUE: " + val
										+ " TEST VALUE: "
										+ paramValuesTest.get(param));
								if (testValue.equals(val)) {
									matchValueCount++;
									System.out
											.println("Matched with Test Value!");
								}
							}
						}
					}
					System.out.println("PARAMS MATCHED: " + matchParamCount
							+ "VALUES MATCHED" + matchValueCount);

					/* Check if all parameters were matched. */
					if (paramsMatched) {
						/*
						 * Check for missing values for each parameter in
						 * paramValuesWAM.
						 */
						@SuppressWarnings("rawtypes")
						Iterator it3 = paramValuesWAM.entrySet().iterator();
						while (it3.hasNext()) {
							Entry<String, CopyOnWriteArrayList<String>> pv = (Entry<String, CopyOnWriteArrayList<String>>) it3
									.next();
							System.out
									.println("-----------------------------------------------------------------------------------------");
							System.out.println("CASE1: Guided PARAM: "
									+ pv.getKey());
							CopyOnWriteArrayList<String> values = paramValuesWAM
									.get(pv.getKey());
							for (String value : values) {
								System.out.println(">> " + value);
							}
						}

						/*
						 * Create and add Guided URL in Hash set (defined as
						 * GuidedURLs).
						 */
						for (int k = 0; k < paramValuesWAM.get("FormAction")
								.size(); k++) {
							for (int j = 0; j < paramValuesWAM.get("FormName")
									.size(); j++) {
								String guidedURL = testURL.replaceAll(
										"FormAction=([a-zA-Z]+)",
										"FormAction="
												+ paramValuesWAM.get(
														"FormAction").get(k))
										.replaceAll(
												"FormName=([a-zA-Z]+)",
												"FormName="
														+ paramValuesWAM.get(
																"FormName")
																.get(j));
								System.out.println("GUIDED URL: " + guidedURL);
								URLRankings.put(guidedURL, matchParamCount
										+ matchValueCount);
							}
						}
					} else {
						@SuppressWarnings("rawtypes")
						Iterator it3 = paramValuesWAM.entrySet().iterator();
						while (it3.hasNext()) {
							Entry<String, CopyOnWriteArrayList<String>> pv = (Entry<String, CopyOnWriteArrayList<String>>) it3
									.next();
							System.out
									.println("-----------------------------------------------------------------------------------------");
							System.out.println("CASE2: Guided PARAM: "
									+ pv.getKey());
							CopyOnWriteArrayList<String> values = paramValuesWAM
									.get(pv.getKey());
							for (String value : values) {
								System.out.println(">> " + value);
							}
						}
						/*
						 * Create and add Guided URLs for each combination of
						 * parameters and values in paramValuesWAM.
						 */
						for (int k = 0; k < paramValuesWAM.get("FormAction")
								.size(); k++) {
							for (int j = 0; j < paramValuesWAM.get("FormName")
									.size(); j++) {
								String guidedURL = wamURL.replaceAll(
										"FormAction=([a-zA-Z,]+)",
										"FormAction="
												+ paramValuesWAM.get(
														"FormAction").get(k))
										.replaceAll(
												"FormName=([a-zA-Z,]+)",
												"FormName="
														+ paramValuesWAM.get(
																"FormName")
																.get(j));
								guidedURL = guidedURL.replaceAll(
										"Password=([a-zA-Z]+)",
										"Password=guest").replaceAll(
										"Login=([a-zA-Z]+)", "Login=guest");
								System.out.println("GUIDED URL: " + guidedURL);
								URLRankings.put(guidedURL, matchParamCount
										+ matchValueCount);
							}
						}
					}
				}
			}
			/* Sort by Rank. */
			HashMap<String, Integer> sortedMapAsc = sortByComparator(
					URLRankings, false);
			@SuppressWarnings("rawtypes")
			Iterator it3 = sortedMapAsc.entrySet().iterator();
			ArrayList<String> potentialGuidedURLs = new ArrayList<String>();
			while (it3.hasNext()) {
				Entry<String, Integer> url = (Entry<String, Integer>) it3
						.next();
				potentialGuidedURLs.add(url.getKey());
				if (url.getValue() >= RANK_THRESHOLD)
					GuidedURLs.add(url.getKey());
			}
		}

		return GuidedURLs;
	}

	/**
	 * This method runs combined tests along with guided tests.
	 * 
	 * @param GuidedURLs
	 *            The guided URLs.
	 */
	static void runGuidedTests(HashSet<String> GuidedURLs) {
		/* Print all Guided URLS. */
		System.out.println("Total Guided URLs generated by Iris: "
				+ GuidedURLs.size());

		try {
			FileWriter fwriter = new FileWriter("" + SRC_PATH
					+ "/workspace/GuidedTesting/scripts/testWget.sh", false);

			fwriter.write("#! /bin/bash\n");

			fwriter.write(restartDB);
			fwriter.write(makeDir.replace("UC", "UCWget"));
			fwriter.write(ComboText.replace("Combo", "UCWget"));
			Object[] urls = GuidedURLs.toArray();
			System.out.println("Guided URLs: ");
			for (int o = 0; o < GuidedURLs.size(); o++) {
				String url = urls[o].toString();
				System.out.println(url);

				fwriter.write(cmdLogin.replace("UC", "UCWget") + "'" + url
						+ "'" + URL + SERVLET_URL + "\n");
				fwriter.write(cmdPOST.replace("UC", "UCWget") + "'" + url + "'"
						+ URL + SERVLET_URL + "\n");
			}
			fwriter.write(coverageReport.replace("reports-" + APP_NAME + "",
					"reports-" + APP_NAME + "-" + "UCWget"));
			fwriter.close();

			try {
				Process proc;
				proc = Runtime
						.getRuntime()
						.exec("sh "
								+ SRC_PATH
								+ "/workspace/GuidedTesting/scripts/testWget.sh");

				if (proc.waitFor() == 0)
					System.out.println("Success in running Guided Tests!");
			} catch (InterruptedException e1) {
				Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e1);
				e1.printStackTrace();
			}
		} catch (IOException e) {
			Logger.getLogger(CFG.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to sort by comparator for HashMap<String, Integer>.
	 * 
	 * @param unsortMap
	 *            The Map to be sorted
	 * @param order
	 *            The Order of sorting.
	 * @return The sorted HashMap.
	 */
	private static HashMap<String, Integer> sortByComparator(
			HashMap<String, Integer> unsortMap, final boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(
				unsortMap.entrySet());

		/* Sorting the list based on values. */
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		/* Maintaining insertion order with the help of LinkedList. */
		HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
	}
}