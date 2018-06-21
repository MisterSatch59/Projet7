package org.biblioService.webapp.livreService.generated.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für NombreEtBibliotheque complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="NombreEtBibliotheque"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bibliotheque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "nombre"
})
public class NombreEtBibliotheque {

    @XmlElement(required = true)
    protected String bibliotheque;
    protected int nombre;

    /**
     * Ruft den Wert der bibliotheque-Eigenschaft ab.
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
     * Legt den Wert der bibliotheque-Eigenschaft fest.
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
     * Ruft den Wert der nombre-Eigenschaft ab.
     * 
     */
    public int getNombre() {
        return nombre;
    }

    /**
     * Legt den Wert der nombre-Eigenschaft fest.
     * 
     */
    public void setNombre(int value) {
        this.nombre = value;
    }

}
