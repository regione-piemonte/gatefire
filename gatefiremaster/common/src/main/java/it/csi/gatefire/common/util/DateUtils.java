package it.csi.gatefire.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {

	private DateUtils() {
		super();

	}

	public static Date getDataAMezzanotte(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)).getTime();

	}

	public static Date addDay(Date date, int value) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(date);
		c1.add(Calendar.DATE, value);
		date = c1.getTime();
		return date;
	}

	public static XMLGregorianCalendar getXMLGregorianCalendarNow() throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

	}

	/**
	 * formato yyMMddHHmmssZ
	 * 
	 * @param zDate
	 * @return
	 */
	public static Date getDateShortWithZ(String zDate) {
		Date ret = null;

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssX");
			ret = format.parse(zDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return ret;
	}

	public static String getDataPerXds(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssZ");
		format.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		// SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	public static String getDataPerXdsShort(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	public static Date dateToUTC(Date date) {
		return new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
	}

	public static String getDataPerXdsNotimeZone(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		// format.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		// SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return format.format(new Date());

	}
}
