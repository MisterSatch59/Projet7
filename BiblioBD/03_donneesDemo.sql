-- Cr�ation donn�es de demonstration
--Pr�t
INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES ({d '2018-09-23'},{d '2018-10-21'} ,false , 2, 79);
INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES ({d '2018-09-23'},{d '2018-10-21'} ,false , 4, 78);

--Reservation
INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa,date_mail,pret_id) VALUES ('Biblioth�que des canards','978-2290055953',2,{ts '2018-09-01 00:00:00.000'},{ts '2018-09-23 00:00:00.000'},3);
INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa) VALUES ('Biblioth�que des canards','978-2290055953',3,{ts '2018-09-05 00:00:00.000'});

INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa,date_mail,pret_id) VALUES ('Biblioth�que des oies','978-2290055953',4,{ts '2018-09-01 00:00:00.000'},{ts '2018-09-23 00:00:00.000'},4);