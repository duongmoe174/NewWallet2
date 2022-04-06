<%--
  Created by IntelliJ IDEA.
  User: minhtuan
  Date: 06/04/2022
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Wallets</title>
</head>
<body>
<h1>Wallets</h1>
<h2>
    <a href="wallets?action=create">Add new wallet</a>
</h2>
<div>
    <table>
        <caption><h2>List of wallet</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Balance</th>
            <th>Description</th>
        </tr>
        <c:forEach var="wallets" items="${wallets}">
            <tr>
                <td><c:out value="${wallets.id}"/></td>
                <td><c:out value="${wallets.name}"/></td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
