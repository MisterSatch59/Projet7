DROP TABLE public.auteur CASCADE;
DROP TABLE public.livre_auteur CASCADE;
DROP TABLE public.livre_genre CASCADE;
DROP TABLE public.bibliotheque CASCADE;
DROP TABLE public.description CASCADE;
DROP TABLE public.editeur CASCADE;
DROP TABLE public.exemplaire CASCADE;
DROP TABLE public.genre CASCADE;
DROP TABLE public.langue CASCADE;
DROP TABLE public.livre CASCADE;
DROP TABLE public.paragraphe CASCADE;
DROP TABLE public.pret CASCADE;
DROP TABLE public.utilisateur CASCADE;
DROP TABLE public.reservation CASCADE;

--Cr�ation des tables

CREATE TABLE public.langue (
                nom VARCHAR(50) NOT NULL,
                CONSTRAINT langue_pk PRIMARY KEY (nom)
);


CREATE SEQUENCE public.editeur_id_seq;

CREATE TABLE public.editeur (
                id INTEGER NOT NULL DEFAULT nextval('public.editeur_id_seq'),
                nom VARCHAR(100) NOT NULL,
                CONSTRAINT editeur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.editeur_id_seq OWNED BY public.editeur.id;

CREATE SEQUENCE public.auteur_id_seq;

CREATE TABLE public.auteur (
                id INTEGER NOT NULL DEFAULT nextval('public.auteur_id_seq'),
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100),
                CONSTRAINT auteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.auteur_id_seq OWNED BY public.auteur.id;

CREATE SEQUENCE public.utilisateur_id_seq;

CREATE TABLE public.utilisateur (
                id INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
                email VARCHAR(100) NOT NULL,
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100),
                mdp VARCHAR(100) NOT NULL,
                sel VARCHAR(20) NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.utilisateur.id;

CREATE UNIQUE INDEX utilisateur_idx
 ON public.utilisateur
 ( email );

CREATE TABLE public.bibliotheque (
                nom VARCHAR(50) NOT NULL,
                CONSTRAINT bibliotheque_pk PRIMARY KEY (nom)
);


CREATE TABLE public.genre (
                nom VARCHAR(20) NOT NULL,
                CONSTRAINT genre_pk PRIMARY KEY (nom)
);


CREATE SEQUENCE public.description_id_seq;

CREATE TABLE public.description (
                id INTEGER NOT NULL DEFAULT nextval('public.description_id_seq'),
                titre VARCHAR(100),
                CONSTRAINT description_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.description_id_seq OWNED BY public.description.id;

CREATE TABLE public.paragraphe (
                decription_id INTEGER NOT NULL,
                ordre INTEGER NOT NULL,
                texte VARCHAR NOT NULL,
                CONSTRAINT paragraphe_pk PRIMARY KEY (decription_id, ordre)
);


CREATE TABLE public.livre (
                isbn VARCHAR(20) NOT NULL,
                titre VARCHAR(100) NOT NULL,
                editeur_id INTEGER NOT NULL,
                date_publication DATE NOT NULL,
                description_id INTEGER NOT NULL,
                langue VARCHAR(50) NOT NULL,
                CONSTRAINT livre_pk PRIMARY KEY (isbn)
);

CREATE TABLE public.livre_genre (
                isbn VARCHAR(20) NOT NULL,
                nom VARCHAR(20) NOT NULL,
                CONSTRAINT livre_genre_pk PRIMARY KEY (isbn, nom)
);


CREATE TABLE public.livre_auteur (
                isbn VARCHAR(20) NOT NULL,
                auteur_id INTEGER NOT NULL,
                CONSTRAINT livre_auteur_pk PRIMARY KEY (isbn, auteur_id)
);


CREATE SEQUENCE public.exemplaire_id_seq;

CREATE TABLE public.exemplaire (
                id INTEGER NOT NULL DEFAULT nextval('public.exemplaire_id_seq'),
                isbn VARCHAR(20) NOT NULL,
                bibliotheque VARCHAR(50) NOT NULL,
                CONSTRAINT exemplaire_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.exemplaire_id_seq OWNED BY public.exemplaire.id;

CREATE SEQUENCE public.pret_id_seq;

CREATE TABLE public.pret (
                id INTEGER NOT NULL DEFAULT nextval('public.pret_id_seq'),
                date_debut DATE NOT NULL,
                date_retour_prevue DATE NOT NULL,
                date_fin DATE,
                renouvele BOOLEAN NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                exemplaire_id INTEGER NOT NULL,
                CONSTRAINT pret_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.pret_id_seq OWNED BY public.pret.id;

ALTER TABLE public.livre ADD CONSTRAINT langue_livre_fk
FOREIGN KEY (langue)
REFERENCES public.langue (nom)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre ADD CONSTRAINT editeur_livre_fk
FOREIGN KEY (editeur_id)
REFERENCES public.editeur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre_auteur ADD CONSTRAINT auteur_auteur_livre_fk
FOREIGN KEY (auteur_id)
REFERENCES public.auteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pret ADD CONSTRAINT utilisateur_pret_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.exemplaire ADD CONSTRAINT bibliotheque_exemplaire_fk
FOREIGN KEY (bibliotheque)
REFERENCES public.bibliotheque (nom)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre_genre ADD CONSTRAINT genre_livre_genre_fk
FOREIGN KEY (nom)
REFERENCES public.genre (nom)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre ADD CONSTRAINT description_livre_fk
FOREIGN KEY (description_id)
REFERENCES public.description (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.paragraphe ADD CONSTRAINT description_paragraphe_fk
FOREIGN KEY (decription_id)
REFERENCES public.description (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.exemplaire ADD CONSTRAINT livre_exemplaire_fk
FOREIGN KEY (isbn)
REFERENCES public.livre (isbn)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre_auteur ADD CONSTRAINT livre_auteur_livre_fk
FOREIGN KEY (isbn)
REFERENCES public.livre (isbn)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre_genre ADD CONSTRAINT livre_livre_genre_fk
FOREIGN KEY (isbn)
REFERENCES public.livre (isbn)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pret ADD CONSTRAINT exemplaire_pret_fk
FOREIGN KEY (exemplaire_id)
REFERENCES public.exemplaire (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- Cr�ation donn�es de demonstration

--Genres
INSERT INTO public.genre (nom) VALUES ('Autobiographies');
INSERT INTO public.genre (nom) VALUES ('Aventure');
INSERT INTO public.genre (nom) VALUES ('B.D.');
INSERT INTO public.genre (nom) VALUES ('Biographies');
INSERT INTO public.genre (nom) VALUES ('Classiques');
INSERT INTO public.genre (nom) VALUES ('Essais');
INSERT INTO public.genre (nom) VALUES ('Fantasy');
INSERT INTO public.genre (nom) VALUES ('Histoire');
INSERT INTO public.genre (nom) VALUES ('Jeunesse');
INSERT INTO public.genre (nom) VALUES ('Nouvelles');
INSERT INTO public.genre (nom) VALUES ('Po�sie');
INSERT INTO public.genre (nom) VALUES ('Polar historique');
INSERT INTO public.genre (nom) VALUES ('Policier');
INSERT INTO public.genre (nom) VALUES ('Romans');
INSERT INTO public.genre (nom) VALUES ('S.F.');
INSERT INTO public.genre (nom) VALUES ('Th��tre');
INSERT INTO public.genre (nom) VALUES ('Thriller');
INSERT INTO public.genre (nom) VALUES ('Voyages');

-- Langues
INSERT INTO public.langue (nom) VALUES ('Fran�ais');
INSERT INTO public.langue (nom) VALUES ('Anglais');
INSERT INTO public.langue (nom) VALUES ('Allemand');

-- Auteurs
INSERT INTO public.auteur (id,nom,prenom) VALUES (1,'Rowling' ,'J.K.');
INSERT INTO public.auteur (id,nom,prenom) VALUES (2,'Tolkien' ,'J.R.R.');
INSERT INTO public.auteur (id,nom,prenom) VALUES (3,'Moyes' ,'Jojo');
INSERT INTO public.auteur (id,nom,prenom) VALUES (4,'Asimov' ,'Isaac');

-- Editeurs
INSERT INTO public.editeur (id,nom) VALUES (1,'Gallimard Jeunesse');
INSERT INTO public.editeur (id,nom) VALUES (2,'Carlsen');
INSERT INTO public.editeur (id,nom) VALUES (3,'Bloomsbury Childrens Books');
INSERT INTO public.editeur (id,nom) VALUES (4,'Folio');
INSERT INTO public.editeur (id,nom) VALUES (5,'Milady');
INSERT INTO public.editeur (id,nom) VALUES (6,'J''AI LU');

-- Livres
--Harry Potter I
INSERT INTO public.description (id) VALUES (1);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (1,1,'Le jour de ses 11 ans, Harry Potter, un orphelin �lev� par un oncle et une tante qui le d�testent, voit son existence boulevers�e. Un g�ant vient le chercher pour l''amener � Poudlard, une �cole de sorcellerie ! Voler en balai, jeter des sorts, combattre les trolls : Harry Potter se r�v�le un sorcier dou�. Mais un myst�re entoure sa naissance et l''effroyable V..., le mage dont personne n''ose prononcer le nom.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070649693', 'Harry Potter, I : Harry Potter � l''�cole des sorciers' , 1 ,{d '2012-10-25'} , 1, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070649693','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070649693','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070649693' ,1);

--Harry Potter II
INSERT INTO public.description (id) VALUES (2);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (2,1,'Harry Potter fait une deuxi�me rentr�e fracassante en voiture volante � l''�cole des sorciers. Cette deuxi�me ann�e ne s''annonce pas de tout repos... surtout depuis qu''une �trange mal�diction s''est abattue sur les �l�ves. Entre les cours de potion magique, les matchs de Quidditch et les combats de mauvais sorts, Harry trouvera-t-il le temps de percer le myst�re de la Chambre des Secrets ? Un livre magique pour sorciers et sorci�res confirm�s !');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070624539', 'Harry Potter, II : Harry Potter et la Chambre des Secrets' , 1 ,{d '2016-10-3'} , 2, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624539','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624539','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070624539' ,1);

--Harry Potter III
INSERT INTO public.description (id) VALUES (3);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (3,1,'Harry Potter a treize ans. Apr�s des vacances insupportables chez les horribles Dursley, il retrouve ses fid�les amis, Ron et Hermione, pour prendre le train qui les ram�ne au coll�ge Poudlard. Le monde des gens ordinaires, les Moldus, comme celui des sorciers, est en �moi : aux derni�res nouvelles, Sirius Black, un dangereux criminel proche de Voldemort, s''est �chapp� de la prison d''Azkaban. Les redoutables gardiens de la prison assureront la s�curit� du coll�ge Poudlard, car le prisonnier �vad� recherche Harry Potter, responsable de l''�limination de son ma�tre. C''est donc sous bonne garde que l''apprenti sorcier fait sa troisi�me rentr�e. Au programme : des cours de divination, la fabrication d''une potion de ratatinage, le dressage des hippogriffes... Mais Harry est-il vraiment � l''abri du danger qui le menace ?');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070624546', 'Harry Potter, III : Harry Potter et le prisonnier d''Azkaban' , 1 ,{d '2016-10-3'} , 3, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624546','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624546','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070624546' ,1);

--Harry Potter IV
INSERT INTO public.description (id) VALUES (4);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (4,1,'Apr�s un horrible �t� chez les Dursley, Harry Potter entre en quatri�me ann�e au coll�ge de Poudlard. A quatorze ans, il voudrait simplement �tre un jeune sorcier comme les autres, retrouver ses amis Ron et Hermione, assister avec eux � la Coupe du Monde de Quidditch, apprendre de nouveaux sortil�ges et essayer des potions inconnues. Une grande nouvelle l''attend � son arriv�e : la tenue � Poudlard d''un tournoi de magie entre les plus c�l�bres �coles de sorcellerie. D�j� les spectaculaires d�l�gations �trang�res font leur entr�e... Harry se r�jouit. Trop vite. Il va se trouver plong� au coeur des �v�nements les plus dramatiques qu''il ait jamais eu � affronter. Envo�tant, dr�le, bouleversant, ce quatri�me tome est le pilier central des aventures de Harry Potter.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070624553', 'Harry Potter, IV : Harry Potter et la Coupe de Feu' , 1 ,{d '2016-10-3'} , 4, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624553','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624553','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070624553' ,1);

--Harry Potter V
INSERT INTO public.description (id) VALUES (5);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (5,1,'� quinze ans, Harry s''appr�te � entrer en cinqui�me ann�e � Poudlard. Et s''il est heureux de retrouver le monde des sorciers, il n''a jamais �t� aussi anxieux. L''adolescence, la perspective des examens importants en fin d''ann�e et ces �tranges cauchemars... Car Celui-Dont-On-Ne-Doit-Pas-Prononcer-Le-Nom est de retour et, plus que jamais, Harry sent peser sur lui une terrible menace. Une menace que le minist�re de la Magie ne semble pas prendre au s�rieux, contrairement � Dumbledore. Poudlard devient alors le terrain d''une v�ritable lutte de pouvoir. La r�sistance s''organise autour de Harry qui va devoir compter sur le courage et la fid�lit� de ses amis de toujours...');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070624560', 'Harry Potter, V : Harry Potter et l''Ordre du Ph�nix' , 1 ,{d '2016-10-3'} , 5, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624560','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624560','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070624560' ,1);

--Harry Potter VI
INSERT INTO public.description (id) VALUES (6);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (6,1,'Dans un monde de plus en plus inqui�tant, Harry se pr�pare � retrouver Ron et Hermione. Bient�t, ce sera la rentr�e � Poudlard, avec les autres �tudiants de sixi�me ann�e. Mais pourquoi le professeur Dumbledore vient-il en personne chercher Harry chez les Dursley ? Harry, Ron et Hermione entrent en sixi�me ann�e � Poudlard o� ils vont vivre leur derni�re ann�e avant la majorit� qui est fix�e, chez les sorciers, � l''�ge de dix-sept ans. Des �v�nements particuli�rement marquants vont contribuer � faire passer Harry du statut d''adolescent � celui d''homme. Ce tome, sur fond de guerre contre un Voldemort plus puissant que jamais, se r�v�le plus sombre que les pr�c�dents. Secrets, alliances et trahisons conduisent aux �v�nements les plus dramatiques qu''Harry ait eu � affronter. Mais, en d�pit de ces �pisodes tragiques, il �mane du texte un sentiment g�n�ral d''all�gresse et de joie de vivre d� � l''humour, aux preuves d''amiti�, aux sc�nes romantiques, � de nouvelles trouvailles po�tiques de J. K. Rowling, mais surtout � la s�r�nit� retrouv�e de Harry qui reprend confiance en lui. Ce dernier se plonge �galement dans les souvenirs d''enfance de Voldemort. Il va ainsi mieux comprendre la personnalit� de son adversaire car m�me cet �tre monstrueux poss�de une part d''humanit�. Le sens des responsabilit�s et du sacrifice rev�tent, ici encore, une dimension particuli�rement importante.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070624904', 'Harry Potter, VI : Harry Potter et le Prince de Sang-M�l�' , 1 ,{d '2016-10-3'} , 6, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624904','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624904','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070624904' ,1);

--Harry Potter VII
INSERT INTO public.description (id) VALUES (7);
INSERT INTO public.paragraphe (decription_id,ordre,texte) VALUES (7,1,'Cet �t�-l�, Harry atteint ses dix-sept ans, l''�ge de la majorit� pour un sorcier, et s''appr�te � faire face � son destin. Soutenu par Ron et Hermione, Harry se consacre pleinement � la mission confi�e par Dumbledore avant de mourir, la chasse aux Horcruxes. Mais le Seigneur des T�n�bres r�gne d�sormais en ma�tre absolu. Traqu�s, en exil, les trois fid�les amis vont conna�tre une solitude sans pr�c�dent, o� leur courage, leurs choix et leurs sacrifices seront d�terminants dans la lutte contre les forces du mal. Leur qu�te croisera celle des Reliques de la Mort, et fera surgir du pass� des r�v�lations capitales et parfois douloureuses. Ces �preuves conduiront Harry, sans d�tour, vers sa destin�e, l''affrontement final avec Lord Voldemort.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue) VALUES ('978-2070624911', 'Harry Potter, VII : Harry Potter et les Reliques de la Mort' , 1 ,{d '2016-10-3'} , 7, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624911','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070624911','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070624911' ,1);

--Harry Potter I Allemand
INSERT INTO public.description (id) VALUES (8);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (8,1,'Bis zu seinem elften Geburtstag glaubt Harry, er sei ein ganz normaler Junge. Doch dann erf�hrt er, dass er sich an der Schule f�r Hexerei und Zauberei einfinden soll � denn er ist ein Zauberer! In Hogwarts st�rzt Harry von einem Abenteuer ins n�chste und muss gegen Bestien, Mitsch�ler und Fabelwesen k�mpfen. Da ist es gut, dass er schon Freunde gefunden hat, die ihm im Kampf gegen die dunklen M�chte zur Seite stehen.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-3551551672', 'Harry Potter und der Stein der Weisen' , 2 ,{d '1998-07-21'} , 8, 'Allemand');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-3551551672','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-3551551672','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-3551551672' ,1);

--Harry Potter II Allemand
INSERT INTO public.description (id) VALUES (9);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (9,1,'Endlich wieder Schule! Einen solchen Seufzer kann nur der aussto�en, dessen Ferien scheu�lich waren: Harry Potter. Doch wie im vergangenen Schuljahr stehen nicht nur Zaubertrankunterricht und Verwandlung auf dem Programm. Ein grauenhaftes Etwas treibt sein Unwesen in der Schule. Wird Harry mit Hilfe seiner Freunde Ron und Hermine das R�tsel l�sen und Hogwarts von den dunklen M�chten befreien k�nnen?');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-3551551689', 'Harry Potter und die Kammer des Schreckens' , 2 ,{d '1999-03-21'} , 9, 'Allemand');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-3551551689','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-3551551689','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-3551551689' ,1);

--Harry Potter I Anglais
INSERT INTO public.description (id) VALUES (10);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (10,1,'Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry''s eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. An incredible adventure is about to begin! These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It''s time to PASS THE MAGIC ON.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-1408855652', 'Harry Potter and the Philosopher''s Stone' , 3 ,{d '2014-08-19'} , 10, 'Anglais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-1408855652','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-1408855652','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-1408855652' ,1);

--Harry Potter II Anglais
INSERT INTO public.description (id) VALUES (11);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (11,1,'Harry Potter''s summer has included the worst birthday ever, doomy warnings from a house-elf called Dobby, and rescue from the Dursleys by his friend Ron Weasley in a magical flying car! Back at Hogwarts School of Witchcraft and Wizardry for his second year, Harry hears strange whispers echo through empty corridors - and then the attacks start. Students are found as though turned to stone . Dobby''s sinister predictions seem to be coming true. These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It''s time to PASS THE MAGIC ON.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-1408855669', 'Harry Potter and the Chamber of Secrets' , 3 ,{d '2014-08-19'} , 11, 'Anglais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-1408855669','Fantasy');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-1408855669','Jeunesse');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-1408855669' ,1);

--Seigneur des Anneaux I
INSERT INTO public.description (id) VALUES (12);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (12,1,'Dans les vertes prairies de la Comt�, les Hobbits, ou Semi-hommes, vivaient en paix� Jusqu''au jour fatal o� l''un d''entre eux, au cours de ses voyages, entra en possession de l''Anneau Unique aux immenses pouvoirs. Pour le reconqu�rir, Sauron, le seigneur t�n�breux, va d�cha�ner toutes les forces du Mal. Frodon, le Porteur de l''Anneau, Gandalf, le magicien, et leurs intr�pides compagnons r�ussiront-ils � �carter la menace qui p�se sur la Terre du Milieu ?');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-2070612888', 'Le Seigneur des Anneaux (Tome 1-La Communaut� de l''Anneau)' , 4 ,{d '2007-08-23'} , 12, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070612888','Fantasy');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070612888' ,2);

--Seigneur des Anneaux II
INSERT INTO public.description (id) VALUES (13);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (13,1,'Dispers�e dans les terres de l''Ouest, la Communaut� de l''Anneau affronte les p�rils de la guerre, tandis que Frodon, accompagn� du fid�le Samsagace, poursuit une qu�te presque d�sesp�r�e : d�truire l''Anneau Unique en le jetant dans les crevasses d''Oradruir, la Montagne du destin. Mais aux fronti�res du royaume de Mordor, une myst�rieuse cr�ature les �pie� pour les perdre ou pour les sauver ?');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-2070612895', 'Le Seigneur des Anneaux (Tome 2-Les Deux Tours)' , 4 ,{d '2007-08-23'} , 13, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070612895','Fantasy');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070612895' ,2);

--Seigneur des Anneaux III
INSERT INTO public.description (id) VALUES (14);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (14,1,'Le royaume de Gondor s''arme contre Sauron, le seigneur des t�n�bres, qui veut asservir tous les peuples libres, hommes et elfes, nains et hobbits. Mais la vaillance des soldats de Minas Tirith ne peut rien d�sormais contre la puissance mal�fique de Mordor. Un fragile espoir, toutefois, demeure : le Porteur de l''Anneau, jour apr�s jour, s''approche de la montagne o� br�le le feu du destin, seul capable de d�truire l''Anneau Unique et de provoquer la chute de Sauron�');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-2070612901', 'Le Seigneur des Anneaux (Tome 3-Le Retour du Roi)' , 4 ,{d '2007-08-23'} , 14, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2070612901','Fantasy');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2070612901' ,2);

--Avant toi
INSERT INTO public.description (id) VALUES (15);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (15,1,'Lou est une fille ordinaire qui m�ne une vie monotone dans un trou paum� de l�Angleterre dont elle n�est jamais sortie. Quand elle se retrouve au ch�mage, elle accepte un contrat de six mois pour tenir compagnie � un handicap�. Malgr� l�accueil glacial qu�il lui r�serve, Lou va d�couvrir en lui un jeune homme exceptionnel, brillant dans les affaires, accro aux sensations fortes et voyageur inv�t�r�. Mais depuis l�accident qui l�a rendu t�trapl�gique, Will veut mettre fin � ses jours. Lou n�a que quelques mois pour le faire changer d�avis.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-2811215576', 'Avant toi' , 5 ,{d '2016-06-10'} , 15, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2811215576','Romans');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2811215576' ,3);

--Apr�s toi
INSERT INTO public.description (id) VALUES (16);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (16,1,'Un an et demi apr�s avoir exauc� le voeu de Will, qui souhaitait b�n�ficier du suicide assist�, Lou quitte sa ville natale o� elle est la cible de critiques. Elle commence une nouvelle vie � Londres, mais elle a du mal � se conformer aux derni�res volont�s de Will qui lui recommandait de profiter de la vie.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-2811219673', 'Apr�s toi' , 5 ,{d '2017-05-19'} , 16, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2811219673','Romans');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2811219673' ,3);

--Le cycle des robots, Tome 1 : Les robots I
INSERT INTO public.description (id) VALUES (17);
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (17,1,'Premi�re Loi : Un robot ne peut porter atteinte � un �tre humain ni, restant passif, laisser cet �tre humain expos� au danger.');
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (17,2,'Deuxi�me Loi : Un robot doit ob�ir aux ordres donn�s par les �tres humains, sauf si de tels ordres entrent en contradiction avec la Premi�re Loi.');
INSERT INTO public.paragraphe (decription_id,ordre,texte)
VALUES (17,3,'Troisi�me Loi : Un robot doit prot�ger son existence dans la mesure o� cette protection n''entre pas en contradiction avec la Premi�re ou la Deuxi�me Loi.');
INSERT INTO public.livre (isbn,titre,editeur_id,date_publication,description_id,langue)
VALUES ('978-2290055953', 'Le cycle des robots, Tome 1 : Les robots' , 6 ,{d '2012-06-23'} , 17, 'Fran�ais');
INSERT INTO public.livre_genre (isbn,nom) VALUES ('978-2290055953','S.F.');
INSERT INTO public.livre_auteur (isbn,auteur_id) VALUES ('978-2290055953' ,4);

--Biblioth�ques de la ville
INSERT INTO public.bibliotheque (nom) VALUES ('Biblioth�que centrale');
INSERT INTO public.bibliotheque (nom) VALUES ('Biblioth�que des oies');
INSERT INTO public.bibliotheque (nom) VALUES ('Biblioth�que des canards');

-- Exemplaires
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070649693','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624539','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624539','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624539','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624539','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624539','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624539','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624546','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624546','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624546','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624546','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624546','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624553','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624553','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624553','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624553','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624553','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624553','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624560','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624904','Biblioth�que des canards');


INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que des canards');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070624911','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-3551551672','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-3551551672','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-3551551672','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-3551551689','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-3551551689','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-3551551689','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-1408855652','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-1408855652','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-1408855652','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-1408855669','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-1408855669','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-1408855669','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612888','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612888','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612888','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612895','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612895','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612895','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612901','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612901','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2070612901','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2811215576','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2811215576','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2811215576','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2811219673','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2811219673','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2811219673','Biblioth�que des canards');

INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2290055953','Biblioth�que centrale');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2290055953','Biblioth�que des oies');
INSERT INTO public.exemplaire (isbn,bibliotheque) VALUES ('978-2290055953','Biblioth�que des canards');

-- Utilisateur
INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES ('jeremylootens@gmail.com' ,'Lootens' ,'J�r�my' ,'9Gb+Cm0HWmVuF7zuSK4L4Pg+GOvPFNsbAWoU/cM2Ywg=' ,'t0P7IvDKzv36W2npOfR4' );
INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES ('test1@gmail.com' ,'Test1' ,'Test' ,'9Gb+Cm0HWmVuF7zuSK4L4Pg+GOvPFNsbAWoU/cM2Ywg=' ,'t0P7IvDKzv36W2npOfR4' );
INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES ('test2@gmail.com' ,'Test2' ,'Test' ,'9Gb+Cm0HWmVuF7zuSK4L4Pg+GOvPFNsbAWoU/cM2Ywg=' ,'t0P7IvDKzv36W2npOfR4' );
INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES ('test3@gmail.com' ,'Test3' ,'Test' ,'9Gb+Cm0HWmVuF7zuSK4L4Pg+GOvPFNsbAWoU/cM2Ywg=' ,'t0P7IvDKzv36W2npOfR4' );
INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES ('test4@gmail.com' ,'Test4' ,'Test' ,'9Gb+Cm0HWmVuF7zuSK4L4Pg+GOvPFNsbAWoU/cM2Ywg=' ,'t0P7IvDKzv36W2npOfR4' );
INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES ('test5@gmail.com' ,'Test5' ,'Test' ,'9Gb+Cm0HWmVuF7zuSK4L4Pg+GOvPFNsbAWoU/cM2Ywg=' ,'t0P7IvDKzv36W2npOfR4' );

-- Pret
INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES ({d '2018-08-10'},{d '2018-09-07'} ,false , 2, 11);
INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES ({d '2018-09-03'},{d '2018-10-01'} ,false , 1, 77);


--Modification ticket #1
CREATE TABLE public.reservation (
                bibliotheque VARCHAR(50) NOT NULL,
                isbn VARCHAR(20) NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                date_resa TIMESTAMP NOT NULL,
                date_mail TIMESTAMP,
                pret_id INTEGER,
                CONSTRAINT reservation_pk PRIMARY KEY (bibliotheque, isbn, utilisateur_id)
);


CREATE INDEX reservation_idx
 ON public.reservation
 ( bibliotheque ASC, isbn ASC, date_resa ASC );

CLUSTER reservation_idx ON reservation;

CREATE UNIQUE INDEX reservation_idx1
 ON public.reservation
 ( isbn, utilisateur_id );

ALTER TABLE public.reservation ADD CONSTRAINT utilisateur_reservation_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.reservation ADD CONSTRAINT bibliotheque_reservation_fk
FOREIGN KEY (bibliotheque)
REFERENCES public.bibliotheque (nom)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.reservation ADD CONSTRAINT livre_reservation_fk
FOREIGN KEY (isbn)
REFERENCES public.livre (isbn)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.reservation ADD CONSTRAINT pret_reservation_fk
FOREIGN KEY (pret_id)
REFERENCES public.pret (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- Cr�ation donn�es de demonstration
--Pr�t
INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES ({d '2018-09-23'},{d '2018-10-21'} ,false , 2, 79);
INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES ({d '2018-09-23'},{d '2018-10-21'} ,false , 4, 75);

--Reservation
INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa,date_mail,pret_id) VALUES ('Biblioth�que des canards','978-2290055953',2,{ts '2018-09-01 00:00:00.000'},{ts '2018-09-23 00:00:00.000'},3);
INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa) VALUES ('Biblioth�que des canards','978-2290055953',3,{ts '2018-09-05 00:00:00.000'});

INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa,date_mail,pret_id) VALUES ('Biblioth�que des oies','978-2290055953',4,{ts '2018-09-01 00:00:00.000'},{ts '2018-09-23 00:00:00.000'},4);
