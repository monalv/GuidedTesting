<%
try {
  String className = "net.sourceforge.cobertura.coveragedata.ProjectData";
  String methodName = "saveGlobalProjectData";
  Class saveClass = Class.forName(className);
  java.lang.reflect.Method saveMethod = saveClass.getDeclaredMethod(methodName, new Class[0]);
  saveMethod.invoke(null, new Object[0]);
} 
catch(Throwable t) {
}
%>