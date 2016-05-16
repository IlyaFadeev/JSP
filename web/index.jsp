<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <form action="http://localhost:8081/web_war_exploded/calc" METHOD="GET">
  <input type="text" name="firstNumber" value="${f}"/>
  <br>
  <br>
  <input type="text" name="secondNumber" value="${s}"/>
  <br>
  <br>
  <input type="submit" name="operation" value="+"/>
  <br>
  <br>
  <input type="submit" name="operation" value="-"/>
  <br>
  <br>
  <input type="submit" name="operation" value="*"/>
  <br>
  <br>
  <input type="submit" name="operation" value="/"/>
    <br>
    <br>
    <input type="submit" name="operation" value="save"/>
    <br>
    <br>
    <input type="submit" name="operation" value="extract"/>
    <br>
    <br>
    <input type="submit" name="operation" value="reset"/>


    </form>
  <br>
  <br>
  <br>
  <br>
  <p>Result = ${res}</p>
  <p>Memory status:${status}</p>
  </body>
</html>