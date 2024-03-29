//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.02 at 09:18:31 AM CDT 
//


package org.sw.marketing.data.blog;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sw.marketing.data.blog package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sw.marketing.data.blog
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
     * Create an instance of {@link Skin }
     * 
     */
    public Skin createSkin() {
        return new Skin();
    }

    /**
     * Create an instance of {@link Data.Blog }
     * 
     */
    public Data.Blog createDataBlog() {
        return new Data.Blog();
    }

    /**
     * Create an instance of {@link Data.Blog.Topic }
     * 
     */
    public Data.Blog.Topic createDataBlogTopic() {
        return new Data.Blog.Topic();
    }

    /**
     * Create an instance of {@link Environment }
     * 
     */
    public Environment createEnvironment() {
        return new Environment();
    }

    /**
     * Create an instance of {@link org.sw.marketing.data.blog.Role }
     * 
     */
    public org.sw.marketing.data.blog.Role createRole() {
        return new org.sw.marketing.data.blog.Role();
    }

    /**
     * Create an instance of {@link Skin.Role }
     * 
     */
    public Skin.Role createSkinRole() {
        return new Skin.Role();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Data.Blog.Category }
     * 
     */
    public Data.Blog.Category createDataBlogCategory() {
        return new Data.Blog.Category();
    }

    /**
     * Create an instance of {@link Data.Blog.Search }
     * 
     */
    public Data.Blog.Search createDataBlogSearch() {
        return new Data.Blog.Search();
    }

    /**
     * Create an instance of {@link Data.Blog.Topic.Tag }
     * 
     */
    public Data.Blog.Topic.Tag createDataBlogTopicTag() {
        return new Data.Blog.Topic.Tag();
    }

    /**
     * Create an instance of {@link Data.Blog.Topic.File }
     * 
     */
    public Data.Blog.Topic.File createDataBlogTopicFile() {
        return new Data.Blog.Topic.File();
    }

}
