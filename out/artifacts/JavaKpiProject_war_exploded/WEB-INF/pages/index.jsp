<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.12.2019
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Conference</title>
</head>
<style>
  input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
  input[type=password], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  input[type=submit]:hover {
    background-color: #45a049;
  }

  div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
  }
</style>
<body>
<div>
  <form action="login" method="post" >
    <label for="login">Enter login</label>
    <input type="text" id="login" name="login" placeholder="login">

    <label for="password">Enter password</label>
    <input type="password" id="password" name="password" placeholder="password">

    <input type="submit" value="Submit">
  </form>

  <a href="/registrate">Registrate in the system</a>

</div>

</body>
</html>
