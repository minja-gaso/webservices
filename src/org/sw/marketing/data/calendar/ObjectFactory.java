//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.22 at 12:12:27 PM CST 
//


package org.sw.marketing.data.calendar;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sw.marketing.data.calendar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sw.marketing.data.calendar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Data }
     * 
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link Data.Calendar }
     * 
     */
    public Data.Calendar createDataCalendar() {
        return new Data.Calendar();
    }

    /**
     * Create an instance of {@link Data.Calendar.Event }
     * 
     */
    public Data.Calendar.Event createDataCalendarEvent() {
        return new Data.Calendar.Event();
    }

    /**
     * Create an instance of {@link Data.Environment }
     * 
     */
    public Data.Environment createDataEnvironment() {
        return new Data.Environment();
    }

    /**
     * Create an instance of {@link Data.Message }
     * 
     */
    public Data.Message createDataMessage() {
        return new Data.Message();
    }

    /**
     * Create an instance of {@link Data.User }
     * 
     */
    public Data.User createDataUser() {
        return new Data.User();
    }

    /**
     * Create an instance of {@link Data.Calendar.Role }
     * 
     */
    public Data.Calendar.Role createDataCalendarRole() {
        return new Data.Calendar.Role();
    }

    /**
     * Create an instance of {@link Data.Calendar.Category }
     * 
     */
    public Data.Calendar.Category createDataCalendarCategory() {
        return new Data.Calendar.Category();
    }

    /**
     * Create an instance of {@link Data.Calendar.Search }
     * 
     */
    public Data.Calendar.Search createDataCalendarSearch() {
        return new Data.Calendar.Search();
    }

    /**
     * Create an instance of {@link Data.Calendar.CurrentView }
     * 
     */
    public Data.Calendar.CurrentView createDataCalendarCurrentView() {
        return new Data.Calendar.CurrentView();
    }

    /**
     * Create an instance of {@link Data.Calendar.Event.Tag }
     * 
     */
    public Data.Calendar.Event.Tag createDataCalendarEventTag() {
        return new Data.Calendar.Event.Tag();
    }

    /**
     * Create an instance of {@link Data.Calendar.Event.EventRecurrence }
     * 
     */
    public Data.Calendar.Event.EventRecurrence createDataCalendarEventEventRecurrence() {
        return new Data.Calendar.Event.EventRecurrence();
    }

}
