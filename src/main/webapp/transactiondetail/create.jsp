<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07-Apr-22
  Time: 3:52 PM
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
        <a href="/transactiondetail?action=transactiondetail">List All Transaction</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Transaction Detail</h2>
            </caption>
            <tr>
                <th>Money Amount:</th>
                <td>
                    <input type="number" name="amount" id="amount" size="45"/>
                </td>
            </tr><tr>
                <th>Transaction Date:</th>
                <td>
                    <input type="number" name="day" id="day" placeholder="day" size="45"/>
                    <input type="number" name="month" id="month" placeholder="month" size="45"/>
                    <input type="number" name="year" id="year" placeholder="year" size="45"/>
                </td>
            </tr><tr>
                <th>Transaction:</th>
                <td>
                    <select name="transaction">
                        <c:forEach items="${listTransaction}" var="transaction" >
                            <option value="${transaction.id}">${transaction.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr><tr>
                <th>Category:</th>
                <td>
                    <select name="category">
                        <c:forEach items="${listCategory}" var="category" >
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr><tr>
                <th>Wallet:</th>
                <td>
                    <select name="wallet">
                        <c:forEach items="${listWallet}" var="wallet" >
                            <option value="${wallet.id}">${wallet.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr><tr>
                <th>NOTE:</th>
                <td>
                    <input type="text" name="notes" id="notes" size="45"/>
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

