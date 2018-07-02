<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/_include/head.jsp"%>
</head>

<body class="container">
	<%@ include file="/WEB-INF/jsp/_include/header.jsp"%>
	
	<h1>Information du compte utilisateur</h1>
	
	<div class="row">
		<div class="jumbotron col-lg-offset-2 col-lg-8 col-md-12 marge">
			
				<h3>Nom : <s:property value="#session.utilisateur.nom" /></h3>
				<h3>Prenom : <s:property value="#session.utilisateur.prenom" /></h3>
				<h3>E-mail : <s:property value="#session.utilisateur.email" /></h3>
			
			<s:a class="col-sm-offset-3 col-sm-6 col-xs-12 marge btn btn-primary" action ="modifierUtilisateur">Modifier le compte</s:a>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/_include/footer.jsp"%>
</body>
</html>


