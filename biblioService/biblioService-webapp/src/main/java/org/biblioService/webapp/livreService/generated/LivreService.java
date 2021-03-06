package org.biblioService.webapp.livreService.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.biblioService.model.bean.DispoParBibliotheque;
import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.bean.Reservation;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-10-29T09:49:04.123+01:00
 * Generated source version: 3.2.5
 *
 */
@WebService(targetNamespace = "http://www.example.org/LivreService/", name = "LivreService")
@XmlSeeAlso({org.biblioService.model.bean.ObjectFactory.class, ObjectFactory.class})
public interface LivreService {

    @WebMethod
    @RequestWrapper(localName = "nouveauPret", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.NouveauPret")
    @ResponseWrapper(localName = "nouveauPretResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.NouveauPretResponse")
    public void nouveauPret(
        @WebParam(name = "utilisateurId", targetNamespace = "")
        int utilisateurId,
        @WebParam(name = "exemplaireId", targetNamespace = "")
        int exemplaireId
    ) throws NouveauPretFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "infoMailRappel", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.InfoMailRappel")
    @ResponseWrapper(localName = "infoMailRappelResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.InfoMailRappelResponse")
    @WebResult(name = "pret", targetNamespace = "")
    public java.util.List<Pret> infoMailRappel();

    @WebMethod
    @RequestWrapper(localName = "getLangues", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.GetLangues")
    @ResponseWrapper(localName = "getLanguesResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.GetLanguesResponse")
    @WebResult(name = "langue", targetNamespace = "")
    public java.util.List<java.lang.String> getLangues();

    @WebMethod
    @RequestWrapper(localName = "supprimerReservation", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.SupprimerReservation")
    @ResponseWrapper(localName = "supprimerReservationResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.SupprimerReservationResponse")
    public void supprimerReservation(
        @WebParam(name = "isbn", targetNamespace = "")
        java.lang.String isbn,
        @WebParam(name = "bibliotheque", targetNamespace = "")
        java.lang.String bibliotheque,
        @WebParam(name = "utilisateurId", targetNamespace = "")
        int utilisateurId
    ) throws SupprimerReservationFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "prolongerPret", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ProlongerPret")
    @ResponseWrapper(localName = "prolongerPretResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ProlongerPretResponse")
    @WebResult(name = "newDateRetourPrevue", targetNamespace = "")
    public javax.xml.datatype.XMLGregorianCalendar prolongerPret(
        @WebParam(name = "pretId", targetNamespace = "")
        int pretId
    ) throws ProlongerPretFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "creerReservation", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.CreerReservation")
    @ResponseWrapper(localName = "creerReservationResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.CreerReservationResponse")
    public void creerReservation(
        @WebParam(name = "isbn", targetNamespace = "")
        java.lang.String isbn,
        @WebParam(name = "bibliotheque", targetNamespace = "")
        java.lang.String bibliotheque,
        @WebParam(name = "utilisateurId", targetNamespace = "")
        int utilisateurId
    ) throws CreerReservationFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "listerReservation", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ListerReservation")
    @ResponseWrapper(localName = "listerReservationResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ListerReservationResponse")
    @WebResult(name = "listReservation", targetNamespace = "")
    public java.util.List<Reservation> listerReservation(
        @WebParam(name = "utilisateurId", targetNamespace = "")
        int utilisateurId
    ) throws ListerReservationFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "listerPretEnRetard", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ListerPretEnRetard")
    @ResponseWrapper(localName = "listerPretEnRetardResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ListerPretEnRetardResponse")
    @WebResult(name = "pret", targetNamespace = "")
    public java.util.List<Pret> listerPretEnRetard();

    @WebMethod
    @RequestWrapper(localName = "getGenres", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.GetGenres")
    @ResponseWrapper(localName = "getGenresResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.GetGenresResponse")
    @WebResult(name = "genres", targetNamespace = "")
    public java.util.List<java.lang.String> getGenres();

    @WebMethod
    @RequestWrapper(localName = "voirDispo", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.VoirDispo")
    @ResponseWrapper(localName = "voirDispoResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.VoirDispoResponse")
    @WebResult(name = "dispoParBibliotheque", targetNamespace = "")
    public java.util.List<DispoParBibliotheque> voirDispo(
        @WebParam(name = "isbn", targetNamespace = "")
        java.lang.String isbn
    ) throws VoirDispoFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "rechercherLivre", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.RechercherLivre")
    @ResponseWrapper(localName = "rechercherLivreResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.RechercherLivreResponse")
    @WebResult(name = "livre", targetNamespace = "")
    public java.util.List<Livre> rechercherLivre(
        @WebParam(name = "titre", targetNamespace = "")
        java.lang.String titre,
        @WebParam(name = "auteur", targetNamespace = "")
        java.lang.String auteur,
        @WebParam(name = "genre", targetNamespace = "")
        java.lang.String genre,
        @WebParam(name = "langue", targetNamespace = "")
        java.lang.String langue
    );

    @WebMethod
    @RequestWrapper(localName = "retourPret", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.RetourPret")
    @ResponseWrapper(localName = "retourPretResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.RetourPretResponse")
    @WebResult(name = "premierSurListeAttente", targetNamespace = "")
    public Reservation retourPret(
        @WebParam(name = "id", targetNamespace = "")
        int id
    ) throws RetourPretFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "listerPretEnCours", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ListerPretEnCours")
    @ResponseWrapper(localName = "listerPretEnCoursResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.ListerPretEnCoursResponse")
    @WebResult(name = "pret", targetNamespace = "")
    public java.util.List<Pret> listerPretEnCours(
        @WebParam(name = "utilisateurId", targetNamespace = "")
        int utilisateurId
    ) throws ListerPretEnCoursFault_Exception;

    @WebMethod
    @RequestWrapper(localName = "miseAJourListesReservation", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.MiseAJourListesReservation")
    @ResponseWrapper(localName = "miseAJourListesReservationResponse", targetNamespace = "http://www.example.org/LivreService/", className = "org.example.livreservice.MiseAJourListesReservationResponse")
    @WebResult(name = "listeNouvellePremiereReservation", targetNamespace = "")
    public java.util.List<Reservation> miseAJourListesReservation() throws MiseAJourListesReservationFault_Exception;
}
