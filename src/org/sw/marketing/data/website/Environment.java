//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.06 at 01:15:16 PM CDT 
//


package org.sw.marketing.data.website;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="componentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="screenName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="assets" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "componentId",
    "screenName",
    "serverName",
    "assets",
    "contactName",
    "contactEmail"
})
@XmlRootElement(name = "environment")
public class Environment {

    protected long componentId;
    @XmlElement(required = true)
    protected String screenName;
    @XmlElement(required = true)
    protected String serverName;
    @XmlElement(required = true)
    protected String assets;
    @XmlElement(required = true)
    protected String contactName;
    protected boolean contactEmail;

    /**
     * Gets the value of the componentId property.
     * 
     */
    public long getComponentId() {
        return componentId;
    }

    /**
     * Sets the value of the componentId property.
     * 
     */
    public void setComponentId(long value) {
        this.componentId = value;
    }

    /**
     * Gets the value of the screenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * Sets the value of the screenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScreenName(String value) {
        this.screenName = value;
    }

    /**
     * Gets the value of the serverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the value of the serverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName(String value) {
        this.serverName = value;
    }

    /**
     * Gets the value of the assets property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssets() {
        return assets;
    }

    /**
     * Sets the value of the assets property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssets(String value) {
        this.assets = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactEmail property.
     * 
     */
    public boolean isContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value of the contactEmail property.
     * 
     */
    public void setContactEmail(boolean value) {
        this.contactEmail = value;
    }

}
