<%@ page import="servlets.DataBaseServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="employee.Employee" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="checker.DigitChecker" %>
<%@ page import="exception.IllegalParameter" %>
<%@ page import="java.util.Collection" %>
<%@ page import="entity.Person" %>
<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 26.10.2015
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html>
<%
  HttpSession jspSession = request.getSession();

  List<Person> employees = (List<Person>)jspSession.getAttribute("employees");

  if (employees == null) throw new IllegalParameter("Employee not found!");
%>
<head>
  <link href="style.css" rel="stylesheet">
  <script language="JavaScript">
  function deleteValue() {
    document.forms[0].value.value = "SMITH";
  }

  function setValue() {
    document.forms[0].value.value = "";
  }

  function isNumber()
  {
    if (isNaN(document.forms[0].number.value)) alert("Enter number!");
  }

  function deleteRow(element)
  {
    if (confirm("Are you sure&")) element.parentNode.parentNode.style.display = "none";
  }

  function send(){
    document.forms[1].sub.value = oldValue;
  }


  </script>
</head>
<form action="http://localhost:8081/web_war_exploded/emp" METHOD="GET">
  <input type="text" name="value" value="<%=employees.get(0).getEname()%>" onfocus=setValue() />
  <br>
  <br>
  <input type="submit" name="show" value="show"/>
  <br>
  <br>
  <input type="submit" name="showAll" value="show all"/>
</form>

</br>
</br>
</br>
<div>
  <table>
    <tr>
      <td>CheckBox</td>
      <td>Number of employee</td>
      <td>Name</td>
      <td>Position</td>
      <td>Date</td>
      <td>Department</td>
      <td>Delete</td>
    </tr>

    <%

      for (int i = 0; i < employees.size(); i++) {

    %>

    <tr>
      <td><input type="checkbox"/></td>
      <td><a href=#><%=employees.get(i).getEmpno()%></a></td>
      <td><%=employees.get(i).getEname()%></td>
      <td><%=employees.get(i).getJob()%></td>
      <td><%=employees.get(i).getHiredate()%></td>
      <td><%=employees.get(i).getDeptno()%></td>
      <td><a href=# onclick="deleteRow(this)">Delete</a></td>
    </tr>
<%
  }
%>
  </table>


</div>
</html>
