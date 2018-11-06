package org.biblioService.model.bean;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Livre }
     * 
     */
    public Livre createLivre() {
        return new Livre();
    }

    /**
     * Create an instance of {@link Editeur }
     * 
     */
    public Editeur createEditeur() {
        return new Editeur();
    }

    /**
     * Create an instance of {@link Description }
     * 
     */
    public Description createDescription() {
        return new Description();
    }

    /**
     * Create an instance of {@link Auteur }
     * 
     */
    public Auteur createAuteur() {
        return new Auteur();
    }

    /**
     * Create an instance of {@link Exemplaire }
     * 
     */
    public Exemplaire createExemplaire() {
        return new Exemplaire();
    }

    /**
     * Create an instance of {@link UtilisateurBD }
     * 
     */
    public UtilisateurBD createUtilisateurBD() {
        return new UtilisateurBD();
    }

    /**
     * Create an instance of {@link Utilisateur }
     * 
     */
    public Utilisateur createUtilisateur() {
        return new Utilisateur();
    }

    /**
     * Create an instance of {@link Pret }
     * 
     */
    public Pret createPret() {
        return new Pret();
    }

    /**
     * Create an instance of {@link DispoParBibliotheque }
     * 
     */
    public DispoParBibliotheque createDispoParBibliotheque() {
        return new DispoParBibliotheque();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

}
