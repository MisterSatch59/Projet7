
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

CREATE TABLE public.utilisateur (
                email VARCHAR(100) NOT NULL,
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(50) NOT NULL,
                mdp VARCHAR(100) NOT NULL,
                mdp_1 VARCHAR(20) NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (email)
);


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
                titre VARCHAR(100) NOT NULL,
                CONSTRAINT description_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.description_id_seq OWNED BY public.description.id;

CREATE TABLE public.paragraphe (
                decription_id INTEGER NOT NULL,
                ordre INTEGER NOT NULL,
                texte LONGNVARCHAR NOT NULL,
                CONSTRAINT paragraphe_pk PRIMARY KEY (decription_id, ordre)
);


CREATE TABLE public.livre (
                isbn VARCHAR(20) NOT NULL,
                titre VARCHAR(100) NOT NULL,
                editeur_id INTEGER NOT NULL,
                date_publication DATE NOT NULL,
                description_id INTEGER NOT NULL,
                genre VARCHAR(20) NOT NULL,
                langue VARCHAR(50) NOT NULL,
                CONSTRAINT livre_pk PRIMARY KEY (isbn)
);


CREATE TABLE public.auteur_livre (
                isbn VARCHAR(20) NOT NULL,
                auteur_id INTEGER NOT NULL,
                CONSTRAINT auteur_livre_pk PRIMARY KEY (isbn, auteur_id)
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
                date_fin VARCHAR,
                renouvele BOOLEAN NOT NULL,
                email_utilisateur VARCHAR(100) NOT NULL,
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

ALTER TABLE public.auteur_livre ADD CONSTRAINT auteur_auteur_livre_fk
FOREIGN KEY (auteur_id)
REFERENCES public.auteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pret ADD CONSTRAINT utilisateur_pret_fk
FOREIGN KEY (email_utilisateur)
REFERENCES public.utilisateur (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.exemplaire ADD CONSTRAINT bibliotheque_exemplaire_fk
FOREIGN KEY (bibliotheque)
REFERENCES public.bibliotheque (nom)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livre ADD CONSTRAINT genre_livre_fk
FOREIGN KEY (genre)
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

ALTER TABLE public.auteur_livre ADD CONSTRAINT livre_auteur_livre_fk
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
