import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsageTesting {
	static BufferedReader br;
	static String cmdLogin = "wget --directory-prefix /home/utambe/bookstore/wget/UC --keep-session-cookies --save-cookies cookies.txt --post-data ";
	static String cmdPOST = "wget --directory-prefix /home/utambe/bookstore/wget/UC  --load-cookies cookies.txt --post-data ";
	static String URL = " http://localhost:8080";
	static String restartDB = "mysql --user=root --password=root < /home/utambe/bookstore/database/bookstore.sql\n";
	static String makeDir = "rm -r /home/utambe/bookstore/wget/UC \n mkdir /home/utambe/bookstore/wget/UC\n";
	public static void main(String[] args) {
		// Read file
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(
					"/var/lib/tomcat6/logs/mylogs.2013-10-21.log"));
			FileWriter fw = new FileWriter(
					"/home/utambe/bookstore/scripts/scriptUsageWgets.sh", false);
			fw.write("#! /bin/bash\n");
			fw.write(restartDB);
			int i = 1;
			fw.write(makeDir.replace("UC", "UC"+i));
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains("<<end>>"))
				{
					break;
				}
				else if (sCurrentLine.contains("<<end of use case>>")) {
					System.out.println("Success in writing Wgets for UseCase: "
							+ (i++));
					fw.write(restartDB+ makeDir.replace("UC", "UC"+i));
				} else {
					String[] params = sCurrentLine.split(" ");
					if (params[0].contains("302") && params[1].contains("Login.jsp")) 
					{	fw.write(cmdLogin.replace("UC", "UC"+i) + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n");
					} 
					else
					{	fw.write(cmdPOST.replace("UC", "UC"+i) + "'"
								+ params[2].replace("[", "").replace("]", "")
								+ "'" + URL + params[1] + "\n");
					}
				}
			}
			fw.close();
			System.out.println("Success in writing Wgets!");
			// Run WGET Script
			Process proc = Runtime.getRuntime().exec(
					"sh /home/utambe/bookstore/scripts/scriptUsageWgets.sh");
			if (proc.waitFor() == 0)
				System.out.println("Success Replaying!");
			// Verify
			/* User Registration */
			String strContent = "";
			br = new BufferedReader(new FileReader(
					"/home/utambe/bookstore/wget/UC1/Login.jsp.1"));
			while ((sCurrentLine = br.readLine()) != null) {
				strContent += sCurrentLine;
			}
			Pattern TAG_REGEX = Pattern
					.compile("Email</font></td><td style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">guest1@noweb.com&nbsp;</font>");
			Matcher matcher1 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("First Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">guest1&nbsp;</font></td>");
			Matcher matcher2 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("Last Name</font></td><td style=\"background-color: #FFFFFF; border-width: 1\"><font style=\"font-size: 10pt; color: #000000\">guest1&nbsp;</font></td>");
			Matcher matcher3 = TAG_REGEX.matcher(strContent);
			if (matcher1.find() && matcher2.find() && matcher3.find()) {
				System.out.println("Verified Registration!");
			}
	               /*Search Book*/
			strContent = "";
			br = new BufferedReader(new FileReader(
					"/home/utambe/bookstore/wget/UC4/Books.jsp"));
			while ((sCurrentLine = br.readLine()) != null) {
				strContent += sCurrentLine;
			}
			TAG_REGEX = Pattern
					.compile("Beginning ASP Databases");
			matcher1 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("Black Belt Web Programming Methods; Servers, Security, Databases and Sites");
			matcher2 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("Web Database Development : Step by Step");
			matcher3 = TAG_REGEX.matcher(strContent);
			
			if (matcher1.find() && matcher2.find() && matcher3.find()) {
				System.out.println("Verified Searching Books!");
			}
			/* Delete Book*/
			strContent = "";
			br = new BufferedReader(new FileReader(
					"/home/utambe/bookstore/wget/UC3/ShoppingCart.jsp.2"));
			while ((sCurrentLine = br.readLine()) != null) {
				strContent += sCurrentLine;
			}
			TAG_REGEX = Pattern
					.compile("C# - Programming with the Public Beta");
			matcher1 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("Web Database Development : Step by Step");
			matcher2 = TAG_REGEX.matcher(strContent);
			
			if (matcher1.find() && matcher2.find()) {
				System.out.println("Verified Adding Books!");
			}
			strContent = "";
			br = new BufferedReader(new FileReader(
					"/home/utambe/bookstore/wget/UC3/ShoppingCartRecord.jsp.1"));
			while ((sCurrentLine = br.readLine()) != null) {
				strContent += sCurrentLine;
			}
			TAG_REGEX = Pattern
					.compile("C# - Programming with the Public Beta");
			matcher1 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("Web Database Development : Step by Step");
			matcher2 = TAG_REGEX.matcher(strContent);
			
			if (matcher1.find() && !matcher2.find()) {
				System.out.println("Verified Deleting Books!");
			}
			/*Guest Login*/
			strContent = "";
			br = new BufferedReader(new FileReader(
					"/home/utambe/bookstore/wget/UC5/AdminMenu.jsp"));
			while ((sCurrentLine = br.readLine()) != null) {
				strContent += sCurrentLine;
			}
			TAG_REGEX = Pattern
					.compile("guest");
			matcher1 = TAG_REGEX.matcher(strContent);
			TAG_REGEX = Pattern
					.compile("<input type=\"hidden\" name=\"FormAction\" value=\"logout\"/><input type=\"submit\" value=\"Logout\"/>");
			matcher2 = TAG_REGEX.matcher(strContent);
			if (matcher1.find() && matcher2.find()) {
				System.out.println("Verified Admin Login for Guest!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
