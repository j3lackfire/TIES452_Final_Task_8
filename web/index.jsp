<%--
  Created by IntelliJ IDEA.
  User: Le Pham Minh Duc
  Date: 12/11/2016
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<p>-SPARQL-</p>
<br/>
<form method="post" action="MainServlet">
  <h2> URL of the XHTML</h2>
  <input type="text" name="URL" width="600px"><br>
  <br/>
  <h2> Rules </h2>
  <textarea name="rule" rows="25" cols="100">
  </textarea>
  <br/>
  <%--<h2> The SPARQL Query</h2>--%>
  <%--<textarea name="query" rows="25" cols="100">--%>
    <%--</textarea>--%>
  <br/>
  <input type="submit" value="EXECUTE" size="30">
</form>
<br/>
<%--<button type="button">ExecuteQuery</button>--%>
</body>
</html>

