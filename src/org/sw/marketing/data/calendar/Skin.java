//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.01 at 08:04:08 AM CST 
//


package org.sw.marketing.data.calendar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="creationTimestamp" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="skinUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="skinSelector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="skinCssOverrides" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="skinHtml" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="role" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fkId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="calendarCss" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formCss" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedApp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "creationTimestamp",
    "title",
    "editable",
    "skinUrl",
    "skinSelector",
    "skinCssOverrides",
    "skinHtml",
    "role",
    "calendarCss",
    "formCss",
    "selectedApp",
    "deleted"
})
@XmlRootElement(name = "skin")
public class Skin {

    protected long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationTimestamp;
    @XmlElement(required = true)
    protected String title;
    protected boolean editable;
    @XmlElement(required = true)
    protected String skinUrl;
    @XmlElement(required = true)
    protected String skinSelector;
    @XmlElement(required = true)
    protected String skinCssOverrides;
    @XmlElement(required = true)
    protected String skinHtml;
    protected List<Skin.Role> role;
    @XmlElement(required = true)
    protected String calendarCss;
    @XmlElement(required = true)
    protected String formCss;
    @XmlElement(required = true)
    protected String selectedApp;
    protected boolean deleted;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the creationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Sets the value of the creationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationTimestamp(XMLGregorianCalendar value) {
        this.creationTimestamp = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the editable property.
     * 
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     * 
     */
    public void setEditable(boolean value) {
        this.editable = value;
    }

    /**
     * Gets the value of the skinUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkinUrl() {
        return skinUrl;
    }

    /**
     * Sets the value of the skinUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkinUrl(String value) {
        this.skinUrl = value;
    }

    /**
     * Gets the value of the skinSelector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkinSelector() {
        return skinSelector;
    }

    /**
     * Sets the value of the skinSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkinSelector(String value) {
        this.skinSelector = value;
    }

    /**
     * Gets the value of the skinCssOverrides property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkinCssOverrides() {
        return skinCssOverrides;
    }

    /**
     * Sets the value of the skinCssOverrides property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkinCssOverrides(String value) {
        this.skinCssOverrides = value;
    }

    /**
     * Gets the value of the skinHtml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkinHtml() {
        return skinHtml;
    }

    /**
     * Sets the value of the skinHtml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkinHtml(String value) {
        this.skinHtml = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Skin.Role }
     * 
     * 
     */
    public List<Skin.Role> getRole() {
        if (role == null) {
            role = new ArrayList<Skin.Role>();
        }
        return this.role;
    }

    /**
     * Gets the value of the calendarCss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarCss() {
        return calendarCss;
    }

    /**
     * Sets the value of the calendarCss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarCss(String value) {
        this.calendarCss = value;
    }

    /**
     * Gets the value of the formCss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormCss() {
        return formCss;
    }

    /**
     * Sets the value of the formCss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormCss(String value) {
        this.formCss = value;
    }

    /**
     * Gets the value of the selectedApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedApp() {
        return selectedApp;
    }

    /**
     * Sets the value of the selectedApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedApp(String value) {
        this.selectedApp = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fkId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "type",
        "email",
        "fkId"
    })
    public static class Role {

        protected long id;
        @XmlElement(required = true)
        protected String type;
        @XmlElement(required = true)
        protected String email;
        protected long fkId;

        /**
         * Gets the value of the id property.
         * 
         */
        public long getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         */
        public void setId(long value) {
            this.id = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the email property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmail() {
            return email;
        }

        /**
         * Sets the value of the email property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmail(String value) {
            this.email = value;
        }

        /**
         * Gets the value of the fkId property.
         * 
         */
        public long getFkId() {
            return fkId;
        }

        /**
         * Sets the value of the fkId property.
         * 
         */
        public void setFkId(long value) {
            this.fkId = value;
        }

    }

}
