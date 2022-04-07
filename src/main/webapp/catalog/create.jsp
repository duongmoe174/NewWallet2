<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/6/2022
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Catalog Management Application</title>
</head>
<body>
<center>
    <h1>Catalog Management</h1>
    <h2>
        <a href="/Catalog?action=Catalog">List All Users</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Catalog</h2>
            </caption>
            <tr>
                <th>Catalog Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Note Catalog:</th>
                <td>
                    <input type="text" name="note" id="note" size="45"/>
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
