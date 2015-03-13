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
    <h1>Import DOCX Duty Roster into Google Calendar</h1>

    <form:form method="post" action="importForm"
               modelAttribute="dutyRosterUploadForm" enctype="multipart/form-data">

        <p>Select a file (docx) to upload and import.</p>

        <table id="importTable">
            <tr>
                <td>File <img src="info.png" title="Must be a docx file."/></td>
                <td><form:input path="file" type="file"/></td>
                <td><form:errors path="file" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Year <img src="info.png" title="Format: yyyy, e.g. 2015"></td>
                <td><form:input path="year"/></td>
                <td><form:errors path="year" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Month</td>
                <td colspan="2">
                    <form:select path="month">
                        <form:option value="0" label="January"/>
                        <form:option value="1" label="February"/>
                        <form:option value="2" label="March"/>
                        <form:option value="3" label="April"/>
                        <form:option value="4" label="May"/>
                        <form:option value="5" label="June"/>
                        <form:option value="6" label="July"/>
                        <form:option value="7" label="August"/>
                        <form:option value="8" label="September"/>
                        <form:option value="9" label="October"/>
                        <form:option value="10" label="November"/>
                        <form:option value="11" label="December"/>
                    </form:select>
                </td>
                <td><form:errors path="month" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Dry Run <img src="info.png"
                                 title="If selected, the import will be tested only and the result will be displayed. No modification of the Google calendar will happen, no email will be sent.">
                </td>
                <td><form:checkbox path="dryRun"/></td>
            </tr>
            <tr>
                <td>Create CSV <img src="info.png"
                                    title="If selected, a CSV file will be created ready to be imported manually into a Google calendar via Google's web ui.">
                </td>
                <td><form:checkbox path="createCsv"/></td>
            </tr>

        </table>
        <br/>
        <input type="submit" value="Go"/>
    </form:form>

    <br/>
</div>
</body>
</html>
