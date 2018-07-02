package org.biblioWebapp.services.generated.utilisateurservice;

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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ancienMdp" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nouveauNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nnouveauPrenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nouveauEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nouveauMdp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "id",
    "ancienMdp",
    "nouveauNom",
    "nnouveauPrenom",
    "nouveauEmail",
    "nouveauMdp"
})
@XmlRootElement(name = "modifierUtilisateur")
public class ModifierUtilisateur {

    protected int id;
    @XmlElement(required = true)
    protected String ancienMdp;
    protected String nouveauNom;
    protected String nnouveauPrenom;
    protected String nouveauEmail;
    protected String nouveauMdp;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété ancienMdp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAncienMdp() {
        return ancienMdp;
    }

    /**
     * Définit la valeur de la propriété ancienMdp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAncienMdp(String value) {
        this.ancienMdp = value;
    }

    /**
     * Obtient la valeur de la propriété nouveauNom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNouveauNom() {
        return nouveauNom;
    }

    /**
     * Définit la valeur de la propriété nouveauNom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNouveauNom(String value) {
        this.nouveauNom = value;
    }

    /**
     * Obtient la valeur de la propriété nnouveauPrenom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNnouveauPrenom() {
        return nnouveauPrenom;
    }

    /**
     * Définit la valeur de la propriété nnouveauPrenom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNnouveauPrenom(String value) {
        this.nnouveauPrenom = value;
    }

    /**
     * Obtient la valeur de la propriété nouveauEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNouveauEmail() {
        return nouveauEmail;
    }

    /**
     * Définit la valeur de la propriété nouveauEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNouveauEmail(String value) {
        this.nouveauEmail = value;
    }

    /**
     * Obtient la valeur de la propriété nouveauMdp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNouveauMdp() {
        return nouveauMdp;
    }

    /**
     * Définit la valeur de la propriété nouveauMdp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNouveauMdp(String value) {
        this.nouveauMdp = value;
    }

}
