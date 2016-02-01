package org.sw.marketing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.sw.marketing.data.form.Data.Form;

public class SurveyHelper
{	
	public static Form verifyStartAndEndDate(Form form)
	{
		/*
		 * validate dates to ensure form has started and has not ended
		 */
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDateStr = dateFormat.format(new Date());
			Date currentDate = dateFormat.parse(currentDateStr);
			XMLGregorianCalendar currentCalendar = DateToXmlGregorianCalendar.convert(currentDate, false);
			
			GregorianCalendar currentGregorianCalendar = currentCalendar.toGregorianCalendar();
			GregorianCalendar startDateGregorianCalendar = form.getStartDate().toGregorianCalendar();
			GregorianCalendar endDateGregorianCalendar = form.getEndDate().toGregorianCalendar();
			
			int startDateResult = startDateGregorianCalendar.compareTo(currentGregorianCalendar);
			boolean started = false;
			int endDateResult = endDateGregorianCalendar.compareTo(currentGregorianCalendar);
			boolean ended = false;
			
			if(startDateResult < 1)
			{
				started = true;
			}
			if(endDateResult == -1)
			{
				ended = true;
			}
			
			form.setEnded(ended);
			form.setStarted(started);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}		
		
		return form;
	}
}
