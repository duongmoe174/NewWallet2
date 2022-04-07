<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 4/5/2022
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>Wallet Management</title>
</head>
<body>
<center>
<%
    String username = (String) session.getAttribute("loginuser");
    out.println("<h2>Welcome</h2>" + username);
%>
    <br>
    <br>
    <a href="users?action=logout"><button>CHECK VAN OUT</button></a>
    <a href="wallets?action=create"><button>Create new wallet</button></a>
    <a href="#"><button>Show my wallet</button></a>
</center>

</body>
</html>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>