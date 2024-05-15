<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,javax.naming.*,javax.sql.DataSource,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
 DataSource ds = null;
 Connection con = null;
 Statement stmt = null;
 InitialContext ic;
  
 try {
 ic = new InitialContext();
 ds = (DataSource) ic.lookup("java:/postgresDS");
 con = ds.getConnection();
 stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery("select * from tabname");

 while (rs.next()) {
 out.println("<br> " + rs.getString("onecolumn") + " | "
 + rs.getString("othercolumn"));
 }
 rs.close();
 stmt.close();
 } catch (Exception e) {
 out.println("Exception thrown :/");
 e.printStackTrace();
 } finally {
 if (con != null) {
 con.close();
 }
 }
    %>
</body>
</html>
