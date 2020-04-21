<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.task.ntc_twoo.dao.OracleDAOConection" %>
<%@ page import="com.spring.task.ntc_twoo.model.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: olbe0615
  Date: 22.03.2020
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax Project</title>
</head>
<body>
<%
    String name = request.getParameter("val");
    List<Student> studentList = OracleDAOConection.getInstance().selectAllStudents();
%>
<table border='1' cellpadding='2' width='100%'>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Salary</th>
    </tr>
    <%for (Student student : studentList) {%>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSalary()%>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
