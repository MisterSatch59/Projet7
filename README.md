# Projet4
Projet 4 du parcours "Expert Java EE" d'OpenClassrooms


Ressources : 
RealTimeBord : https://realtimeboard.com/app/board/o9J_kzCPMuQ=/
GitHub : https://github.com/Oltenos/Projet4

# Créer la base de données PostgreSQL (10)

1/ Créer dans PgAdmin la base de données
2/ Au choix :
	a/ Restaurer la base de données à partir du fichier de "Backup" de pgAdmin suivant : livrables/BaseDeDonnees/backup.sql (clic droit > Restore > filename puis chercher le fichier backup.sql)
	b/ utiliser le script de création des tables et des données de démonstration livrables/BaseDeDonnees/creationBD.sql

# Déploiement du Web Service (sur serveur Tomcat 9 seul)

1/ Configurer les ressources JNDI dans le fichier apache-tomcat-9.X.X/conf/context.xml en ajoutant les ressources suivantes (voir l'exemple Projet4/livrables/WebServices/context.xml à copier/coller entre les balises <Context> puis à modifier) :
	a/ DataSource pour configurer l'accès à la base de données (url, username, password)
	b/ Durée maximale d'un prêt (fixée à 28 jours si non reconfiguré)

2/ Déployer sur Tomcat le fichier war suivant : Projet4/livrables/WebServices/biblioService.war

