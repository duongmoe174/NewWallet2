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
            <th>Action</th>
        </tr>
        <c:forEach var="wallet" items="${wallets}">
            <tr>
                <td><c:out value="${wallet.id_wallet}"/></td>
                <td><c:out value="${wallet.name_wallet}"/></td>
                <td><c:out value="${wallet.balance}"/></td>
                <td><c:out value="${wallet.description}"/></td>
                <td>
                    <a href="wallets?action=edit">Edit</a>
                    <a href="wallets?action=delete">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
