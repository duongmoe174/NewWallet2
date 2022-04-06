<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<center>
    <h1>Create New Account</h1>
    <h2><a href="users?action=users"></a></h2>
</center>
<div align="center">
    <form action="" method="post">
        <table border="1px" cellpadding="5px">
            <caption>
                <h2>Create new account</h2>
            </caption>
            <tr>
                <th>Account name</th>
                <td>
                    <input type="text" name="name", id="name", size="50"/>
                </td>
            </tr>
            <tr>
                <th>Account password</th>
                <td>
                    <input type="password" name="password" id="password" size="50"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Sign up"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>