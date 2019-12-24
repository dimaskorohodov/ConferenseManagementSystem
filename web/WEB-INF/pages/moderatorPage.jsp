<%@ page import="java.util.List" %>
<%@ page import="model.Report" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2019
  Time: 23:35
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
<center>You are on the moderator page</center>
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
  <form action="moderateReport" method="post">
    <label for="reportTheme">Report theme</label>
    <input type="text" id="reportTheme" name="reportTheme" placeholder="reportTheme">

    <label for="newTheme">New report theme</label>
    <input type="text" id="newTheme" name="newTheme" placeholder="newTheme">

    <label for="newPlace">New report place</label>
    <input type="text" id="newPlace" name="newPlace" placeholder="newPlace">

    <label for="newDate">New report date</label>
    <input type="text" id="newDate" name="newDate" placeholder="newDate">

    <input type="submit" value="Moderate report">
  </form>
</div>





</body>
</html>
