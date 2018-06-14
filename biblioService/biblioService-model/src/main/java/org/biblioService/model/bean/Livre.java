
package org.biblioService.model.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour Livre complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Livre"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="editeur" type="{http://www.example.org/types}Editeur"/&gt;
 *         &lt;element name="datePublication" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="description" type="{http://www.example.org/types}Description"/&gt;
 *         &lt;element name="langue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="auteur" type="{http://www.example.org/types}Auteur" maxOccurs="unbounded"/&gt;
 *         &lt;element name="genre" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Livre", propOrder = {
    "isbn",
    "titre",
    "editeur",
    "datePublication",
    "description",
    "langue",
    "auteur",
    "genre"
})
public class Livre {

    @XmlElement(required = true)
    protected String isbn;
    @XmlElement(required = true)
    protected String titre;
    @XmlElement(required = true)
    protected Editeur editeur;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datePublication;
    @XmlElement(required = true)
    protected Description description;
    @XmlElement(required = true)
    protected String langue;
    @XmlElement(required = true)
    protected List<Auteur> auteur;
    @XmlElement(required = true)
    protected List<String> genre;

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
     * Obtient la valeur de la propriété titre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit la valeur de la propriété titre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitre(String value) {
        this.titre = value;
    }

    /**
     * Obtient la valeur de la propriété editeur.
     * 
     * @return
     *     possible object is
     *     {@link Editeur }
     *     
     */
    public Editeur getEditeur() {
        return editeur;
    }

    /**
     * Définit la valeur de la propriété editeur.
     * 
     * @param value
     *     allowed object is
     *     {@link Editeur }
     *     
     */
    public void setEditeur(Editeur value) {
        this.editeur = value;
    }

    /**
     * Obtient la valeur de la propriété datePublication.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatePublication() {
        return datePublication;
    }

    /**
     * Définit la valeur de la propriété datePublication.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatePublication(XMLGregorianCalendar value) {
        this.datePublication = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété langue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Définit la valeur de la propriété langue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangue(String value) {
        this.langue = value;
    }

    /**
     * Gets the value of the auteur property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auteur property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuteur().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Auteur }
     * 
     * 
     */
    public List<Auteur> getAuteur() {
        if (auteur == null) {
            auteur = new ArrayList<Auteur>();
        }
        return this.auteur;
    }

    /**
     * Gets the value of the genre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGenre() {
        if (genre == null) {
            genre = new ArrayList<String>();
        }
        return this.genre;
    }

}
