# Projet4
Projet 4 du parcours "Expert Java EE" d'OpenClassrooms

Ressources : 
RealTimeBord : https://realtimeboard.com/app/board/o9J_kzCPMuQ=/
GitHub : https://github.com/Oltenos/Projet4

# Contenu
Le dossier Projet4 contient les éléments suivant : 
 - analyse : éléments de la phase d'analyse du projet (également disponible en présentation sur le RealTimeBord)
 - biblioBatch : sources (projet Eclipse) du batch d'envoi de mail de relance
 - BiblioBD : éléments de la base de données : diagramme UML des données et MPD (également disponible en présentation sur le RealTimeBord) et scripts sql
 - biblioService : sources (projet Eclipse) du WebService
 - livrables : regroupe l'ensemble des livrables
 - service-soapui-project.xml : projet SoapUI de test du webservice

# Installation des livrables : 
## Créer la base de données PostgreSQL (10)

1/ Créer dans PgAdmin la base de données
2/ Au choix :
	a/ Restaurer la base de données à partir du fichier de "Backup" de pgAdmin suivant : livrables/BaseDeDonnees/backup.sql (clic droit > Restore > filename puis chercher le fichier backup.sql)
	b/ utiliser le script de création des tables et des données de démonstration livrables/BaseDeDonnees/creationBD.sql

## Déploiement du Web Service (sur serveur Tomcat 9)

1/ Configurer les ressources JNDI dans le fichier apache-tomcat-9.X.X/conf/context.xml en ajoutant les ressources suivantes (voir l'exemple Projet4/livrables/WebServices/context.xml à copier/coller entre les balises <Context> puis à modifier) :
	a/ DataSource pour configurer l'accès à la base de données (url, username, password)
	b/ Durée maximale d'un prêt

2/ Déployer sur Tomcat le fichier war suivant : Projet4/livrables/WebServices/biblioService.war

## Déploiement du batch d'envoi de mail de relance

1/ Décompresser le fichier Projet4\livrables\Batch\biblioBatch-1.1.zip
2/ Créer une variable d'environnement nommée "biblioBatch_Home" et contenant le chemin d'accès au dossier "biblioBatch-1.0" contenu dans le dossier décompressé.
3/ Modofier le fichier biblioBatch-1.1\conf\config.properties pour configurer le Batch (Message et périodicité des mails de relance, SMTP et adresse du Web Service LivreService).
4/ Exécuter biblioBatch-1.1.jar

## Déploiement de l'application  web (sur serveur Tomcat 9)

1/ Configurer la ressources JNDI dans le fichier apache-tomcat-9.X.X/conf/context.xml en ajoutant les ressources suivantes (voir l'exemple Projet4/livrables/WebApp/context.xml à copier/coller entre les balises <Context> puis à modifier) avec l'adresse du Webservice
2/ Déployer le fichier Projet4\livrables\WebApp\biblioWebapp.war
