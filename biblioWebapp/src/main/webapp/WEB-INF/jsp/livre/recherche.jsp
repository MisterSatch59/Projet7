<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/_include/head.jsp"%>
</head>

<body class="container">
	<%@ include file="/WEB-INF/jsp/_include/header.jsp"%>

	<!-- Titre -->
	<div class="row aligneCentre">
		<div class="col-xs-12">
			<h1>Recherche</h1>
		</div>
	</div>
	
	<!-- Cadre recherche -->
	<div class="row marge cadrePerso">
		<s:form theme="css_xhtml">
			<div class="formInLinePerso">
				<s:textfield id = "titre" name="titre" label="Titre" requiredLabel="false" cssClass="form-control"/>
				<s:textfield id = "auteur" name="auteur" label="Auteur" requiredLabel="false" cssClass="form-control"/>
				<s:select id = "genre" name="genre" label="Genre" list="genres" emptyOption="true" requiredLabel="false" cssClass="form-control" />
				<s:select id = "langue" name="langue" label="Langue" list="langues" emptyOption="true" requiredLabel="false" cssClass="form-control" />
			</div>
		</s:form>
		<div class="row marge">
			<button onclick="recherche()"  class="btn btn-primary col-xs-offset-4 col-xs-4">Lancer la recherche</button>
		</div>
	</div>
	
	<!-- Résultats -->
	<div class="row marge">
		<div class="col-md-12" id="aucunResultat">
			<div class="jumbotron">
				<p>Aucun resultat...</p>
			</div>
		</div>
	
		<div id="rechercheListe" class="col-xs-12">
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/_include/footer.jsp"%>
	
	<script>
		//Masque le texte "aucun résultat" au chargement de la page
		$(document).ready(function() {
			var aucunResult = $("#aucunResultat");
			aucunResult.hide();
		});
	
		function recherche() {
			// URL de l'action AJAX
			var url = "<s:url action="lancerRecherche_ajax"/>";
	
			// Paramètres de la requête AJAX
			var params = {
					titre: jQuery("#titre").val(),
					auteur: jQuery("#auteur").val(),
					genre: jQuery("#genre").val(),
					langue: jQuery("#langue").val()
			};
			
			//Masque le texte "aucun résultat"
			var aucunResult = $("#aucunResultat");
			aucunResult.hide();
	
			// Action AJAX en POST
			jQuery.post(
				url,
				params,
				function (data) {
					var $rechercheListe = jQuery("#rechercheListe");
					$rechercheListe.empty();
					
					var detail = '';

					jQuery.each(data, function(key, val) {
						detail += '<ul id="rechercheListe" class="list-unstyled cadrePerso marge">';
						detail += '<li>' + 'Titre : ' + val.titre;
						detail += '<li>' + 'Publié le : ' + val.datePublication.day +'/'+ val.datePublication.month +'/'+ val.datePublication.year;
						detail += '<li>' + 'Aux éditions ' + val.editeur.nom;
						detail += '<li>' + 'De : ';
						$.each(val.auteur, function(i, obj) {
							detail += obj.prenom + ' ' + obj.nom + ' ';
						});
						if(val.description.titre == null){
							detail += '<li>' + 'Description : ';
						}else{
							detail += '<li>' + 'Description : ' + val.description.titre;
						}
						$.each(val.description.paragraphes, function(i, obj) {
							detail += '<li>' + obj;
						});
						detail += '<li>' + 'Genres : ';
						$.each(val.genre, function(i, obj) {
							detail += obj + ' ';
						});
						detail += '<li>' + 'Langue : ' + val.langue;
						detail += '</ul>'
						
					});

				if (detail == '') {
					//Affiche le texte "aucun résultat"
					aucunResult.show();
				} else {
					$rechercheListe.append(detail);
				}
			}).fail(function() {
				alert("Une erreur s'est produite.");
			});
		}
	</script>
</body>

</html>