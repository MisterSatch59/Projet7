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
	<div class="row">
		<div class="col-xs-12">
			<h1>Recherche</h1>
		</div>
	</div>
	
	<!-- Cadre recherche -->
	<div class="row">
		<div class="jumbotron col-xs-12 marge">
			<s:form theme="css_xhtml">
				<div class="formInLinePerso">
					<s:textfield id = "titre" name="titre" label="Titre" requiredLabel="false" cssClass="form-control"/>
					<s:textfield id = "auteur" name="auteur" label="Auteur" requiredLabel="false" cssClass="form-control"/>
					<s:select id = "genre" name="genre" label="Genre" list="genres" emptyOption="true" requiredLabel="false" cssClass="form-control" />
					<s:select id = "langue" name="langue" label="Langue" list="langues" emptyOption="true" requiredLabel="false" cssClass="form-control" />
				</div>
			</s:form>
			<div class="row marge">
				<button onclick="recherche()" class="btn btn-primary col-xs-offset-4 col-xs-4">Lancer la recherche</button>
			</div>
		</div>
	</div>
	
	<!-- Résultats -->
	<div class="row">
		<div class="jumbotron col-xs-12 marge" id="aucunResultat">
				<p>Aucun résultat...</p>
		</div>
	
		<div class="jumbotron col-xs-12 marge" id="rechercheListe">
		
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/_include/footer.jsp"%>
	
	<script>
		//Masque le texte "aucun résultat" au chargement de la page
		$(document).ready(function() {
			var aucunResult = $("#aucunResultat");
			aucunResult.hide();
			var rechercheListe = $("#rechercheListe");
			rechercheListe.hide();
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
			
			//Masque les jumbotron  aucunResultat et rechercheListe
			var aucunResult = $("#aucunResultat");
			aucunResult.hide();
			var rechercheListe = $("#rechercheListe");
			rechercheListe.hide();
			
			// Action AJAX en POST
			jQuery.post(
				url,
				params,
				function (data) {
					rechercheListe.empty();
					if(data==null){
						aucunResult.show();
					}else{
						jQuery.each(data, function(key, val) {
							//Affichage des éléments des livres
							var detail = '';
							detail += '<ul id="dispoListe'+ val.isbn +'" class="list-unstyled cadrePerso marge">';
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
							detail += '<li>' + '<button onclick="voirDispo(this)" id="'+ val.isbn +'" class="marge btn btn-primary ">Voir les disponibilités</button>'
							
							rechercheListe.append(detail);
							rechercheListe.show();
						});
					}
			}).fail(function() {
				alert("Une erreur s'est produite.");
			});
		}
		
		function voirDispo(that) {
			// URL de l'action AJAX
			var url = "<s:url action="voirDispo_ajax"/>";

			var buttonIsbn = that.id;

			// Paramètres de la requête AJAX
			var params = {
					isbn : buttonIsbn,
			};

			// Action AJAX en POST
			jQuery.post(url, params, function(data) {
				var nom = "#dispoListe"+buttonIsbn
				var $dispoListe = jQuery(nom);
				
				var detail = '<li><ul class="list-unstyled cadrePerso marge">';
				detail += '<li>Disponibilités : ';
				
				jQuery.each(data, function(key, val) {
					detail += '<li>  ' + val.bibliotheque + ' : ' + val.nombre;
				});
				$dispoListe.append(detail);
			}).fail(function(data) {
				alert("Une erreur s'est produite.");
			});
			
			$(that).prop("disabled",true);

		}
			
	</script>
</body>

</html>