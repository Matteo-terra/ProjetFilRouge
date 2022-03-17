//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.11.19 � 01:28:43 AM CET 
//


package com.filrouge.projet_filrouge.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="livre" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="auteur"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/&gt;
 *                   &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&gt;
 *                   &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "livre"
})
@XmlRootElement(name = "bibliotheque")
public class Bibliotheque {

    @XmlElement(required = true)
    protected List<Livre> livre;

    /**
     * Gets the value of the livre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the livre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLivre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Livre }
     * 
     * 
     */
    public List<Livre> getLivre() {
        if (livre == null) {
            livre = new ArrayList<Livre>();
        }
        return this.livre;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="auteur"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/&gt;
     *         &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&gt;
     *         &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/&gt;
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
        "titre",
        "auteur",
        "presentation",
        "parution",
        "colonne",
        "rangee"
    })
    public static class Livre {

        @XmlElement(required = true)
        protected String titre;
        @XmlElement(required = true)
        protected Auteur auteur;
        @XmlElement(required = true)
        protected String presentation;
        @XmlSchemaType(name = "unsignedShort")
        protected int parution;
        @XmlSchemaType(name = "unsignedByte")
        protected short colonne;
        @XmlSchemaType(name = "unsignedByte")
        protected short rangee;

        /**
         * Obtient la valeur de la propri�t� titre.
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
         * D�finit la valeur de la propri�t� titre.
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
         * Obtient la valeur de la propri�t� auteur.
         * 
         * @return
         *     possible object is
         *     {@link Auteur }
         *     
         */
        public Auteur getAuteur() {
            return auteur;
        }

        /**
         * D�finit la valeur de la propri�t� auteur.
         * 
         * @param value
         *     allowed object is
         *     {@link Auteur }
         *     
         */
        public void setAuteur(Auteur value) {
            this.auteur = value;
        }

        /**
         * Obtient la valeur de la propri�t� presentation.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPresentation() {
            return presentation;
        }

        /**
         * D�finit la valeur de la propri�t� presentation.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPresentation(String value) {
            this.presentation = value;
        }

        /**
         * Obtient la valeur de la propri�t� parution.
         * 
         */
        public int getParution() {
            return parution;
        }

        /**
         * D�finit la valeur de la propri�t� parution.
         * 
         */
        public void setParution(int value) {
            this.parution = value;
        }

        /**
         * Obtient la valeur de la propri�t� colonne.
         * 
         */
        public short getColonne() {
            return colonne;
        }

        /**
         * D�finit la valeur de la propri�t� colonne.
         * 
         */
        public void setColonne(short value) {
            this.colonne = value;
        }

        /**
         * Obtient la valeur de la propri�t� rangee.
         * 
         */
        public short getRangee() {
            return rangee;
        }

        /**
         * D�finit la valeur de la propri�t� rangee.
         * 
         */
        public void setRangee(short value) {
            this.rangee = value;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "nom",
            "prenom"
        })
        public static class Auteur {

            @XmlElement(required = true)
            protected String nom;
            @XmlElement(required = true)
            protected String prenom;

            /**
             * Obtient la valeur de la propri�t� nom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNom() {
                return nom;
            }

            /**
             * D�finit la valeur de la propri�t� nom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propri�t� prenom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * D�finit la valeur de la propri�t� prenom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }

        }

    }

}
