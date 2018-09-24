package org.biblioService.model.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour NombreEtBibliotheque complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NombreEtBibliotheque"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bibliotheque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="prochainRetour" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="personnesEnAttente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NombreEtBibliotheque", propOrder = {
    "bibliotheque",
    "nombre",
    "prochainRetour",
    "personnesEnAttente"
})
public class DispoParBibliotheque {

    @XmlElement(required = true)
    protected String bibliotheque;
    protected int nombre;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar prochainRetour;
    protected Integer personnesEnAttente;

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
     * Obtient la valeur de la propriété nombre.
     * 
     */
    public int getNombre() {
        return nombre;
    }

    /**
     * Définit la valeur de la propriété nombre.
     * 
     */
    public void setNombre(int value) {
        this.nombre = value;
    }

    /**
     * Obtient la valeur de la propriété prochainRetour.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProchainRetour() {
        return prochainRetour;
    }

    /**
     * Définit la valeur de la propriété prochainRetour.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProchainRetour(XMLGregorianCalendar value) {
        this.prochainRetour = value;
    }

    /**
     * Obtient la valeur de la propriété personnesEnAttente.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPersonnesEnAttente() {
        return personnesEnAttente;
    }

    /**
     * Définit la valeur de la propriété personnesEnAttente.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPersonnesEnAttente(Integer value) {
        this.personnesEnAttente = value;
    }

}
