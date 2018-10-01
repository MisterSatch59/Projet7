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
			<h1>Pret en cours</h1>
		</div>
	</div>
	
	<!-- Cadre pret en cours -->
	<div class="row">
		<div class="jumbotron col-xs-12 marge">
			<ul class="list-unstyled">
				<s:iterator value="mapPret">
					<li class="cadrePerso marge">Emprunté depuis le : <s:property value="key.dateDebut" /> - Date de retour prévue : <span id="key.dateRetourPrevue_${id}"><s:property value="key.dateRetourPrevue" /></span>
						<ul class="list-unstyled">
							<li>Titre : <s:property value="key.exemplaire.livre.titre"/></li>
							<li>Aux éditions <s:property value="key.exemplaire.livre.editeur.nom"/></li>
							<li>De : 
								<s:iterator value="key.exemplaire.livre.auteur">
									<s:property value="key.nom"/> <s:property value="key.prenom"/>
								</s:iterator>
							</li>
							<li>Description : <s:property value="key.exemplaire.livre.description.titre"/></li>
							<s:iterator value="key.exemplaire.livre.description.paragraphes">
								<li><s:property/></li>
							</s:iterator>
							<li>
								<s:if test="!value">
									<button onclick="prolongerPret(this)" id="${id}" disabled class="marge btn btn-primary ">Prolonger le prêt</button>
								</s:if>
								<s:else>
									<button onclick="prolongerPret(this)" id="${id}" class="marge btn btn-primary ">Prolonger le prêt</button>
								</s:else>
							</li>
						</ul>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>

	
	<%@ include file="/WEB-INF/jsp/_include/footer.jsp"%>
	<script>
		function prolongerPret(that) {
			// URL de l'action AJAX
			var url = "<s:url action="prolongerPret_ajax"/>";

			var buttonId = that.id;

			// Paramètres de la requête AJAX
			var params = {
				id : buttonId,
			};

			// Action AJAX en POST
			jQuery.post(url, params, function(data) {
				var newDateRetourPrevue = $("#dateRetourPrevue_"+buttonId);
				newDateRetourPrevue.empty();
				
				var result ='';
				if(data.day<10) result+= '0';
				result+= data.day+'/';
				if(data.month<10) result+= '0';
				result+= data.month+'/'+ data.year;
				
				newDateRetourPrevue.append(result);

			}).fail(function(data) {
				alert("Une erreur s'est produite.");
			});
			
			alert("La prolongation du prêt a été prise en compte.");
			$(that).prop("disabled",true);

		}
	
	</script>
</body>

</html>