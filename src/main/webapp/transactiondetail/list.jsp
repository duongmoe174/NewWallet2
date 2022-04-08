<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07-Apr-22
  Time: 2:37 PM
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
    <h1>Transaction Detail Management</h1>
    <h2>
        <a href="/transactiondetail?action=create">Add New Transaction Detail</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Transaction Detail</h2></caption>
        <tr>
            <th>ID</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Transaction</th>
            <th>Category</th>
            <th>Wallet</th>
            <th>Note</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="transactiondetail" items="${listTransactionDetail}">
            <tr>
                <td><c:out value="${transactiondetail.getId()}"/></td>
                <td><c:out value="${transactiondetail.getAmount()}"/></td>
                <td><c:out value="${transactiondetail.getDate()}"/></td>
                <td><c:out value="${transactiondetail.transaction.getName()}"/></td>
                <td><c:out value="${transactiondetail.category.getName()}"/></td>
                <td><c:out value="${transactiondetail.wallet.getName()}"/></td>
                <td><c:out value="${transactiondetail.getNotes()}"/></td>
                <td>
                    <a href="/transactiondetail?action=edit&id=${transactiondetail.id}">Edit</a>
                    <a href="/transactiondetail?action=delete&id=${transactiondetail.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
