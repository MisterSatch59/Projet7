package org.biblioWebapp.services.generated.livreservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="bibliotheque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="utilisateurId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "isbn",
    "bibliotheque",
    "utilisateurId"
})
@XmlRootElement(name = "supprimerReservation")
public class SupprimerReservation {

    @XmlElement(required = true)
    protected String isbn;
    @XmlElement(required = true)
    protected String bibliotheque;
    protected int utilisateurId;

    /**
     * Obtient la valeur de la propriété isbn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Définit la valeur de la propriété isbn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbn(String value) {
        this.isbn = value;
    }

    /**
     * Obtient la valeur de la propriété bibliotheque.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBibliotheque() {
        return bibliotheque;
    }

    /**
     * Définit la valeur de la propriété bibliotheque.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBibliotheque(String value) {
        this.bibliotheque = value;
    }

    /**
     * Obtient la valeur de la propriété utilisateurId.
     * 
     */
    public int getUtilisateurId() {
        return utilisateurId;
    }

    /**
     * Définit la valeur de la propriété utilisateurId.
     * 
     */
    public void setUtilisateurId(int value) {
        this.utilisateurId = value;
    }

}
