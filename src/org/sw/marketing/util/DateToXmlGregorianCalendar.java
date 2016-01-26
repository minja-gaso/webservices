package org.sw.marketing.util;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateToXmlGregorianCalendar
{
	public static XMLGregorianCalendar convert(java.util.Date date, boolean timezone)
	{
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		XMLGregorianCalendar xmlGregorianCalendar = null;
		try
		{
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			if(!timezone)
			{
				xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			}
		}
		catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
		}
		
		return xmlGregorianCalendar;
	}
}
