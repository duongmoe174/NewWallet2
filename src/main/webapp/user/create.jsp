<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<center>
    <h1>Create New Account</h1>
    <p>
        <c:if test='${requestScope["message"] != null}'>
            <span class="message">${message}</span>
        </c:if>
    </p>
</center>
<div align="center">
    <form action="" method="post">
        <table border="1px" cellpadding="5px" onchange="confirm()">
            <caption>
                <h2>Create new account</h2>
            </caption>
            <tr>
                <th>Account name</th>
                <td>
                    <input type="text" name="name", id="name", size="50" required/>
                </td>
            </tr>
            <tr>
                <th>Account password</th>
                <td>
                    <input type="password" name="password" id="password" size="50" placeholder="password" required/>
                </td>
            </tr>
            <tr>
                <th>Confirm password</th>
                <td>
                    <input type="password" name="re_password" id="re_password" placeholder="confirm password" size="50" required/>
                    <p id="message"></p>
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
<script>
    function confirm() {
        let pw = document.getElementById("password").value;
        let re_pw = document.getElementById("re_password").value;
        let msg = document.getElementById("message");
        let greenColor = "#9cff9c";
        let redColor = "#ff6666";
        if(pw === re_pw) {
            msg.style.color = greenColor;
            msg.innerHTML = "password is matched!";
        } else {
            msg.style.color = redColor;
            msg.innerHTML = "password is not match!"
        }
    }
</script>