<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Duty Roster Suite</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div align="center">
    <h1>Import Result</h1>

    <table id="importTable">
        <tr>
            <td>File</td>
            <td>${formDto.file.originalFilename}</td>
        </tr>
        <tr>
            <td>Year</td>
            <td>${formDto.year}</td>
        </tr>
        <tr>
            <td>Month</td>
            <td>${formDto.month}</td>
        </tr>
        <tr>
            <td>Dry Run?</td>
            <td>${formDto.dryRun}</td>
        </tr>
        <tr>
            <td>Create CSV?</td>
            <td>${formDto.createCsv}</td>
        </tr>

    </table>

    <h2 style="text-align: left">Result</h2>

    <p style="text-align: left">${importResult}</p>

    <br/>

    <a href="importForm.html">Back</a>

</div>

</body>

</html>
