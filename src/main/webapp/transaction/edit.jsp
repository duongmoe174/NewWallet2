<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07-Apr-22
  Time: 8:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Wallet Management Application</title>
</head>
<body>
<center>
    <h1>Transaction Management</h1>
    <h2>
        <a href="/transaction?action=transaction">List All Transaction</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Transaction
                </h2>
            </caption>
            <c:if test="${transaction != null}">
                <input type="hidden" name="id" value="<c:out value='${transaction.id}' />"/>
            </c:if>
            <tr>
                <th>Transaction Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${transaction.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
