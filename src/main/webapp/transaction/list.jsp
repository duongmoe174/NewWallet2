<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07-Apr-22
  Time: 6:17 AM
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
        <a href="/transaction?action=create">Add New Transaction</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Transaction</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="transaction" items="${listTransaction}">
            <tr>
                <td><c:out value="${transaction.id}"/></td>
                <td><c:out value="${transaction.name}"/></td>
                <td>
                    <a href="/transaction?action=edit&id=${transaction.id}">Edit</a>
                    <a href="/transaction?action=delete&id=${transaction.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
