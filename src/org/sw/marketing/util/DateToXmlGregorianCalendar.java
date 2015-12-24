package org.sw.marketing.util;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateToXmlGregorianCalendar
{
	public static XMLGregorianCalendar convert(java.util.Date date)
	{
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		XMLGregorianCalendar xmlGregorianCalendar = null;
		try
		{
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		}
		catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
		}
		
		return xmlGregorianCalendar;
	}
}
