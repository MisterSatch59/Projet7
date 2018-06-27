package org.biblio.biblioBatch.livre.generated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.biblio.biblioBatch.livre.generated.types.NombreEtBibliotheque;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nombreEtBibliotheque" type="{http://www.example.org/types}NombreEtBibliotheque" maxOccurs="unbounded"/&gt;
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
    "nombreEtBibliotheque"
})
@XmlRootElement(name = "voirDispoResponse")
public class VoirDispoResponse {

    @XmlElement(required = true)
    protected List<NombreEtBibliotheque> nombreEtBibliotheque;

    /**
     * Gets the value of the nombreEtBibliotheque property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nombreEtBibliotheque property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNombreEtBibliotheque().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NombreEtBibliotheque }
     * 
     * 
     */
    public List<NombreEtBibliotheque> getNombreEtBibliotheque() {
        if (nombreEtBibliotheque == null) {
            nombreEtBibliotheque = new ArrayList<NombreEtBibliotheque>();
        }
        return this.nombreEtBibliotheque;
    }

}
