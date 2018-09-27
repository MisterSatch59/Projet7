<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<s:a action="index"><img src="img/Logo.png" class="miniLogo" /></s:a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><s:a action="parcourirBiblio">Parcourir la bibliothèque</s:a></li>
				<s:if test="#session.utilisateur">
					<li><s:a action="pretEnCours">Prêt en cours</s:a></li>
				</s:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<s:if test="#session.utilisateur">
					<li><s:a action="infoUtilisateur" id="infoUtilisateur"><s:property value="#session.utilisateur.prenom" /> <s:property value="#session.utilisateur.nom" /> - Informations</s:a></li>
					<li><s:a action="logout">Se déconnecter</s:a></li>
				</s:if>
				<s:else>
					<li><s:a action="login">Se Connecter</s:a></li>
					<li><s:a action="creerUtilisateur">Créer un compte</s:a></li>
				</s:else>
			</ul>
		</div>
	</div>
</nav>

<s:actionerror class="alert alert-danger list-unstyled"/>
<s:actionmessage class="alert alert-danger list-unstyled"/>

