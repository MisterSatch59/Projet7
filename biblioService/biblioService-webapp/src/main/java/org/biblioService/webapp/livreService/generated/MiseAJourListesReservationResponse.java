package org.biblioService.webapp.livreService.generated;

import java.util.ArrayList;
import java.util.List;

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
 *         &lt;element name="listeNouvellePremiereReservation" type="{http://www.example.org/types}Reservation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "listeNouvellePremiereReservation"
})
@XmlRootElement(name = "miseAJourListesReservationResponse")
public class MiseAJourListesReservationResponse {

    protected List<Reservation> listeNouvellePremiereReservation;

    /**
     * Gets the value of the listeNouvellePremiereReservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listeNouvellePremiereReservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListeNouvellePremiereReservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     * 
     * 
     */
    public List<Reservation> getListeNouvellePremiereReservation() {
        if (listeNouvellePremiereReservation == null) {
            listeNouvellePremiereReservation = new ArrayList<Reservation>();
        }
        return this.listeNouvellePremiereReservation;
    }

}
