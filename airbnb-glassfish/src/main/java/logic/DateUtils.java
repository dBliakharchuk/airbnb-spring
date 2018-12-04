package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * DateUtils is a utility class which handles help methods on Date objects. 
 * 
 * @author Mateusz Kobierski, Piotr Szylar
 *
 */
public class DateUtils {
	public static List<Date> getDatesBetween(Date startDate, Date endDate) {
		if (startDate.after(endDate)) {
			Date temp = endDate;
			endDate = startDate;
			startDate = temp;
		}
	    List<Date> datesInRange = new ArrayList<>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startDate);
	     
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(endDate);
	 
	    while (calendar.before(endCalendar)) {
	        Date result = calendar.getTime();
	        datesInRange.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return datesInRange;
	}
	
	public static boolean isDateBetweenTwoDates(Date dateStart, Date dateEnd, Date examinedDate) {
		
		return dateStart.compareTo(examinedDate) * dateEnd.compareTo(examinedDate) <= 0;
	}
	public static boolean isExpirationDateCorrect(String expirationDate){
		
		Date tempDate = null;
		
		try {
			tempDate = new Date(new SimpleDateFormat("MM/yyyy").parse(expirationDate).getTime());
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(tempDate == null) {
			
			return false;
		}
		else {
			
			Date otherTempDate = new Date(tempDate.getYear(), tempDate.getMonth() + 1, 1);
			Date today = new Date();
			
			return today.before(otherTempDate);
		}
	}
}
