package org.biblioService.webapp.livreService.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.biblioService.model.bean.Reservation;


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
 *         &lt;element name="premierSurListeAttente" type="{http://www.example.org/types}Reservation" minOccurs="0"/&gt;
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
    "premierSurListeAttente"
})
@XmlRootElement(name = "retourPretResponse")
public class RetourPretResponse {

    protected Reservation premierSurListeAttente;

    /**
     * Obtient la valeur de la propriété premierSurListeAttente.
     * 
     * @return
     *     possible object is
     *     {@link Reservation }
     *     
     */
    public Reservation getPremierSurListeAttente() {
        return premierSurListeAttente;
    }

    /**
     * Définit la valeur de la propriété premierSurListeAttente.
     * 
     * @param value
     *     allowed object is
     *     {@link Reservation }
     *     
     */
    public void setPremierSurListeAttente(Reservation value) {
        this.premierSurListeAttente = value;
    }

}
