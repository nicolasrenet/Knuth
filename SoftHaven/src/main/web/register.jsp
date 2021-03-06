<%--
  Created by IntelliJ IDEA.
  User: Clayton
  Date: 4/20/2020
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Register</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='./styles/basic_reset.css'>
</head>
<body>

<div class="content-container">
    <div id="content" class="content-container">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <h1>Register</h1>
        <form action="register" method="post">
            <label for="first_name">First Name:</label>
            <input name="first_name" size="30" id="first_name" required/>
            <br><br>
            <label for="last_name">Last Name:</label>
            <input name="last_name" size="30" id="last_name" required/>
            <br><br>
            <label for="email">Email:</label>
            <input name="email" size="30" id="email" required/>
            <br><br>
            <label for="position">Position:</label>
            <select name="position" id="position" required>
                <option value="master">Ship Master</option>
                <option value="agent">Ship Agent</option>
                <option value="customs">Customs Officer</option>
            </select>
            <br><br>
            <label for="password">Password:</label>
            <input name="password" id="password" required type="password"/>
            <br><br>
            <label for="confirm_password">Confirm Password:</label>
            <input name="confirm_password" id="confirm_password" required type="password"/>
            <br><br>
            <button type="submit">Register</button>
        </form>
        ${message}
    </div>
</div>
</body>
</html>
