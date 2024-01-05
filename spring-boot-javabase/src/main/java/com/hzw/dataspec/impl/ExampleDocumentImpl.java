/*
 * An XML document type.
 * Localname: example
 * Namespace: http://www.hzw.com/dataspec/
 * Java type: com.hzw.dataspec.ExampleDocument
 *
 * Automatically generated - do not modify.
 */
package com.hzw.dataspec.impl;
/**
 * A document containing one example(@http://www.hzw.com/dataspec/) element.
 *
 * This is a complex type.
 */
public class ExampleDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.hzw.dataspec.ExampleDocument
{
    private static final long serialVersionUID = 1L;
    
    public ExampleDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXAMPLE$0 = 
        new javax.xml.namespace.QName("http://www.hzw.com/dataspec/", "example");
    
    
    /**
     * Gets the "example" element
     */
    public com.hzw.dataspec.ExampleDocument.Example getExample()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.hzw.dataspec.ExampleDocument.Example target = null;
            target = (com.hzw.dataspec.ExampleDocument.Example)get_store().find_element_user(EXAMPLE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "example" element
     */
    public void setExample(com.hzw.dataspec.ExampleDocument.Example example)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.hzw.dataspec.ExampleDocument.Example target = null;
            target = (com.hzw.dataspec.ExampleDocument.Example)get_store().find_element_user(EXAMPLE$0, 0);
            if (target == null)
            {
                target = (com.hzw.dataspec.ExampleDocument.Example)get_store().add_element_user(EXAMPLE$0);
            }
            target.set(example);
        }
    }
    
    /**
     * Appends and returns a new empty "example" element
     */
    public com.hzw.dataspec.ExampleDocument.Example addNewExample()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.hzw.dataspec.ExampleDocument.Example target = null;
            target = (com.hzw.dataspec.ExampleDocument.Example)get_store().add_element_user(EXAMPLE$0);
            return target;
        }
    }
    /**
     * An XML example(@http://www.hzw.com/dataspec/).
     *
     * This is a complex type.
     */
    public static class ExampleImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.hzw.dataspec.ExampleDocument.Example
    {
        private static final long serialVersionUID = 1L;
        
        public ExampleImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName NAME$0 = 
            new javax.xml.namespace.QName("http://www.hzw.com/dataspec/", "name");
        
        
        /**
         * Gets the "name" element
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" element
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "name" element
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAME$0);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" element
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAME$0);
                }
                target.set(name);
            }
        }
    }
}
