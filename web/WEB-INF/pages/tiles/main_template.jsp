<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>

<div id="header">
    <tiles:insertAttribute name="header"/>
</div>

<div id="menu" style="float:left;padding:10px;width:15%;">
    <tiles:insertAttribute name="menu"/>
</div>

<div id="inputBox">
    <tiles:insertAttribute name="inputBox"/>
</div>

<div id="content" style="float:left;padding:10px;width:80%;border-left:1px solid pink;">
    <tiles:insertAttribute name="content"/>
</div>

<div id="footer" style="clear:both">
    <tiles:insertAttribute name="footer"/>
</div>

</body>
</html>