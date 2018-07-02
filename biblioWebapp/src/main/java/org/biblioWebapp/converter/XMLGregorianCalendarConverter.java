package org.biblioWebapp.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;


public class XMLGregorianCalendarConverter extends StrutsTypeConverter {
	private static final Logger LOGGER = LogManager.getLogger(XMLGregorianCalendarConverter.class);

	@Override
	public Object convertFromString(Map pContext, String[] pValues, Class pToClass) {
		LOGGER.traceEntry("pValues[0] = " + pValues[0]);
		Object vRetour = null;

		LOGGER.traceExit(vRetour);
		return vRetour;
	}

	@Override
	public String convertToString(Map pContext, Object pObject) {
		LOGGER.traceEntry("pObject = " + pObject);
		
		String vString;
		if (pObject instanceof XMLGregorianCalendar) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        GregorianCalendar gCalendar = ((XMLGregorianCalendar) pObject).toGregorianCalendar();
	 
	        //Converted to date object
	        Date date = gCalendar.getTime();
	 
	        //Formatted to String value
	        vString = df.format(date);
		} else {
			vString = "";
		}
		
		LOGGER.traceExit(vString);
		return vString;
	}

}
