<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/6/2022
  Time: 11:19 AM
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
        <a href="/Catalog?action=create">Add New User</a>
    </h2>
    <%--    <h2>--%>
    <%--        <a href="/Catalog?action=search">Search</a>--%>
    <%--    </h2>--%>
    <%--    <h2>--%>
    <%--        <a href="/Catalog?action=sort">Sort By Name</a>--%>
    <%--    </h2>--%>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption>
            <h2>List of Catalog</h2>
        </caption>
        <tr>

            <th>ID</th>
            <th>Name</th>
            <th>Note</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="user" items="${listCatalog}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.note}"/></td>

                <td>
                    <a href="/Catalog?action=edit&id=${user.id}">Edit</a>
                    <a href="/Catalog?action=delete&id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
