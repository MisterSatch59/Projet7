package org.biblioService.model.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour Reservation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Reservation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="livre" type="{http://www.example.org/types}Livre"/&gt;
 *         &lt;element name="bibliotheque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="utilisateur" type="{http://www.example.org/types}Utilisateur"/&gt;
 *         &lt;element name="dateResa" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dateMail" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pret" type="{http://www.example.org/types}Pret" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", propOrder = {
    "livre",
    "bibliotheque",
    "utilisateur",
    "dateResa",
    "dateMail",
    "pret"
})
public class Reservation {

    @XmlElement(required = true)
    protected Livre livre;
    @XmlElement(required = true)
    protected String bibliotheque;
    @XmlElement(required = true)
    protected Utilisateur utilisateur;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateResa;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateMail;
    protected Pret pret;

    /**
     * Obtient la valeur de la propriété livre.
     * 
     * @return
     *     possible object is
     *     {@link Livre }
     *     
     */
    public Livre getLivre() {
        return livre;
    }

    /**
     * Définit la valeur de la propriété livre.
     * 
     * @param value
     *     allowed object is
     *     {@link Livre }
     *     
     */
    public void setLivre(Livre value) {
        this.livre = value;
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
     * Obtient la valeur de la propriété utilisateur.
     * 
     * @return
     *     possible object is
     *     {@link Utilisateur }
     *     
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Définit la valeur de la propriété utilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link Utilisateur }
     *     
     */
    public void setUtilisateur(Utilisateur value) {
        this.utilisateur = value;
    }

    /**
     * Obtient la valeur de la propriété dateResa.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateResa() {
        return dateResa;
    }

    /**
     * Définit la valeur de la propriété dateResa.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateResa(XMLGregorianCalendar value) {
        this.dateResa = value;
    }

    /**
     * Obtient la valeur de la propriété dateMail.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateMail() {
        return dateMail;
    }

    /**
     * Définit la valeur de la propriété dateMail.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateMail(XMLGregorianCalendar value) {
        this.dateMail = value;
    }

    /**
     * Obtient la valeur de la propriété pret.
     * 
     * @return
     *     possible object is
     *     {@link Pret }
     *     
     */
    public Pret getPret() {
        return pret;
    }

    /**
     * Définit la valeur de la propriété pret.
     * 
     * @param value
     *     allowed object is
     *     {@link Pret }
     *     
     */
    public void setPret(Pret value) {
        this.pret = value;
    }

}
