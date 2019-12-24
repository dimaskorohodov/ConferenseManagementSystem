<%@ page import="model.Report" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2019
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
<center>You are on the user page</center>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
  <div class="w3-card-4">
    <div class="w3-container w3-light-blue">
      <h2>Reports</h2>
    </div>
    <%
      List<Report> reports = (List<Report>) request.getAttribute("report");
      out.println("<ul class=\"w3-ul\">");
      for (Report report : reports) {
        out.println("<li class=\"w3-hover-sand\">" + report.getReportTheme() + " " + report.getPlace()+ " " + report.getDate() + "</li>");
      }
      out.println("</ul>");
    %>
  </div>
</div>

<div>
  <form action="visitToReport" method="post" >
    <label for="reportToVisit">Report theme</label>
    <input type="text" id="reportToVisit" name="reportToVisit" placeholder="Enter theme to visit report">

    <input type="submit" value="Visit report">
  </form>
</div>

<div>
  <form action="registrateToReport" method="post">
    <label for="reportToRegistrate">Report theme</label>
    <input type="text" id="reportToRegistrate" name="reportToRegistrate" placeholder="Enter theme to registrate">

    <input type="submit" value="Registrate on  report">
  </form>
</div>



</body>
</html>
