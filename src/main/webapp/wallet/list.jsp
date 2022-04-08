<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 4/3/2022
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Wallet</title>
</head>
<body>
<center>
    <h1>List Wallet</h1>
    <h2>
        <a href="wallets?action=create">Add new wallet</a>
    </h2>
</center>

<div align="center">
    <table border="1px" cellpadding="5">
        <caption><h2>List of account</h2></caption>
        <tr>
            <th>Name user</th>
            <th>Name wallet</th>
            <th>Current amount</th>
            <th>Currency</th>
            <th>Action</th>
        </tr>
        <c:forEach var="w" items="${wallets}">
            <tr>
                <td>   <%
                    String username = (String) session.getAttribute("loginuser");
                    out.println(username);
                %></td>
                <td><c:out value="${w.name}"/></td>
                <td><c:out value="${w.balance}"/></td>
                <td><c:out value="${w.currencyWallet.name}"/></td>
                <td>
                    <a href="wallets?action=edit&id=${w.id}">Edit</a>
                    <a href="wallets?action=delete&id=${w.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
