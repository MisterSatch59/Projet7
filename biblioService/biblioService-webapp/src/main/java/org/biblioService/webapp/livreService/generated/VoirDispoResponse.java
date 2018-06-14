package org.biblioService.webapp.livreService.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *         &lt;sequence maxOccurs="unbounded"&gt;
 *           &lt;element name="biliotheque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *           &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;/sequence&gt;
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
    "biliothequeAndNombre"
})
@XmlRootElement(name = "voirDispoResponse")
public class VoirDispoResponse {

    @XmlElements({
        @XmlElement(name = "biliotheque", required = true, type = String.class),
        @XmlElement(name = "nombre", required = true, type = Integer.class)
    })
    protected List<Serializable> biliothequeAndNombre;

    /**
     * Gets the value of the biliothequeAndNombre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the biliothequeAndNombre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBiliothequeAndNombre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link Integer }
     * 
     * 
     */
    public List<Serializable> getBiliothequeAndNombre() {
        if (biliothequeAndNombre == null) {
            biliothequeAndNombre = new ArrayList<Serializable>();
        }
        return this.biliothequeAndNombre;
    }

}
