CREATE TABLE public.reservation (
                bibliotheque VARCHAR(50) NOT NULL,
                isbn VARCHAR(20) NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                date_resa TIMESTAMP NOT NULL,
                date_mail TIMESTAMP,
                CONSTRAINT reservation_pk PRIMARY KEY (bibliotheque, isbn, utilisateur_id)
);


CREATE INDEX reservation_idx
 ON public.reservation
 ( bibliotheque ASC, isbn ASC, date_resa ASC );

CLUSTER reservation_idx ON reservation;

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