<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="_include/head.jsp"%>
</head>

<body class="container">
    <h2>Une erreur s''est produite</h2>

    <s:actionerror />
	
	<%@ include file="/WEB-INF/jsp/_include/footer.jsp"%>
</body>
</html>
