<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/_include/head.jsp"%>
</head>

<body class="container">
	<%@ include file="/WEB-INF/jsp/_include/header.jsp"%>
	
	<h1>Connexion</h1>
	
	<div class="row">
		<div class="jumbotron col-lg-offset-2 col-lg-8 col-md-12 marge">
			<s:form action="login" class="formClassiq" method="POST" enctype="multipart/form-data">
				<s:textfield id = "email" name="email" label="E-mail" requiredLabel="true" type="email" maxlength="100" class="form-control miniMarge"/>
				<s:password id = "mdp" name="mdp" label="Mot de passe" requiredLabel="true" class="form-control miniMarge"/>
				
				<s:submit class="col-sm-offset-3 col-sm-6 col-xs-12 marge btn btn-primary" value="Valider" name=""/>
			</s:form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/_include/footer.jsp"%>
</body>
</html>


