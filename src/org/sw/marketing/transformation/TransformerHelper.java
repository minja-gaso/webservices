package org.sw.marketing.transformation;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformerHelper
{
	public static String getXmlStr(String packageName, Object data)
	{
		StringWriter xmlWriter = new StringWriter();
		
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
			Marshaller marshaller = jaxbContext.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		    marshaller.marshal(data, xmlWriter);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}	
		
		String xmlStr = xmlWriter.toString();
		
		return xmlStr;
	}
	
	public static String getHtmlStr(String xmlStr, java.io.InputStream xslStream)
	{
		StringWriter htmlWriter = new StringWriter();
		try
		{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslStream));
			StreamResult streamResult = new StreamResult(htmlWriter);
			transformer.transform(new StreamSource(new StringReader(xmlStr)), streamResult);
		}
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (TransformerException e)
		{
			e.printStackTrace();
		}	
		
		String htmlStr = htmlWriter.toString();
		
		return htmlStr;
	}
}
