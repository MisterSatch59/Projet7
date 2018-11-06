package org.biblio.biblioBatch.livre.generated.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Exemplaire complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Exemplaire"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="livre" type="{http://www.example.org/types}Livre"/&gt;
 *         &lt;element name="bibliotheque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exemplaire", propOrder = {
    "id",
    "livre",
    "bibliotheque"
})
public class Exemplaire {

    protected int id;
    @XmlElement(required = true)
    protected Livre livre;
    @XmlElement(required = true)
    protected String bibliotheque;

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

}
