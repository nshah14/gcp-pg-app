/**
 * 
 */
package com.revolut;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author navedshah
 *
 */
public class RevUtils {

	Logger LOGGER = LoggerFactory.getLogger(RevUtils.class);
	/**
	 * @return 
	 * 
	 */
	public  RevUtils() {}
		
		
		/**
		 * @param str
		 * @return
		 */
		public boolean isString(String str) {
			
			return str.matches("[a-zA-Z]+\\.?");
		}
		
		/**
		 * @param date
		 * @return
		 */
		private boolean isDate(String date) {
			LOGGER.info("IN:: isDate   ::"+date);
			boolean isMatched=date.matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
			LOGGER.info("OUTCOME:: matched date   ::"+isMatched);
			return isMatched;
		}
		
		
		public boolean isValidBirthDate(String date)
		{
			LOGGER.info("IN:: isValidBirthYear  birthday ::"+date);
			
			String delimeter = "-";	    
			LocalDate today = LocalDate.now();
		    String bDate[] = date.split(delimeter,3);
		    today.getYear();
		    
		    System.out.println("year "+Integer.parseInt(bDate[0])+"  "+today.getYear() + "status "+ (Integer.parseInt(bDate[0]) > today.getYear() ));
		    System.out.println("month "+Integer.parseInt(bDate[1])+" "+today.getMonthValue()+ "status "+ ( Integer.parseInt(bDate[1]) > today.getMonthValue()));
		    System.out.println("date "+Integer.parseInt(bDate[2])+" "+today.getDayOfMonth()+ "status " + (Integer.parseInt(bDate[2]) > today.getDayOfMonth()));
//		    if (today.getYear() == Integer.parseInt(bDate[0]) ) 		
//		    {
//		    	if(Integer.parseInt(bDate[1]) > today.getMonthValue()) {
//		    		if(Integer.parseInt(bDate[2]) > today.getDayOfMonth())
//					{
//						return true;
//					}
//		    		else {
//		    			return false;
//		    		}
//		    	}
//		    	else {
//			    		return false;
//		    	}
//		    }
//		    else if (today.getYear() < Integer.parseInt(bDate[0]) ){
//		    	
//		    	return false;
//		    }
		    
		    if (today.getYear() < Integer.parseInt(bDate[0]) ) 		
		    {
		    	return false;
		    	
		    }
		    else if(today.getYear() == Integer.parseInt(bDate[0]))
		    {
		    	if(Integer.parseInt(bDate[1]) > today.getMonthValue())
		    	{
		    		return false;
		    
		    	}
		    	else if(Integer.parseInt(bDate[1]) == today.getMonthValue())
		    	{
		    		
		    		if(Integer.parseInt(bDate[2]) > today.getDayOfMonth())
		    		{
		    			return false;
		    		}
		    		else
		    		{
		    			return true;
		    		}
		    	}
		    	
		    }
		    
		    return true;
		    
		    
		}
	
		
		/**
		 * @param strBDay
		 * @return
		 */
		private long getNumberOfDaysToBday(String strBDay)
		{
			LOGGER.info("IN:: getNumberOfDaysToBday  birthday ::"+strBDay);
			String delimeter = "-";	    
		    String bDate[] = strBDay.toString().split(delimeter,3);
			LocalDate today = LocalDate.now();
	        LocalDate birthday = LocalDate.of( Integer.parseInt(bDate[0]), getMonth(bDate[1]),  Integer.parseInt(bDate[2]));
	        
			LocalDate nextBDay = birthday.withYear(today.getYear());
	        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
	            nextBDay = nextBDay.plusYears(1);
	        }
	        Period period = Period.between(today, nextBDay);
	        long days = ChronoUnit.DAYS.between(today, nextBDay);
	        LOGGER.info("OUT:: getNumberOfDaysToBday  month to birthday  :: "+period.getMonths()+"& days to birthday :: "+period.getDays());
	        return (period.getDays() == 0 && period.getMonths() == 0) ? 0: days;

		}
		
		/**
		 * @param monthNumber
		 * @return
		 */
		private Month getMonth(String monthNumber){
			LOGGER.info("IN:: getMonth with month Number ::"+monthNumber);
			Month month = Month.JANUARY;
			
			switch (monthNumber) {
			case "02":
				month = Month.FEBRUARY;
				break;
			case "03":
				month = Month.MARCH;
				break;
			case "04":
				month = Month.APRIL;
				break;
			case "05":
				month = Month.MAY;
				break;
			case "06":
				month = Month.JUNE;
				break;
			case "07":
				month = Month.JULY;
				break;
			case "08":
				month = Month.AUGUST;
				break;
			case "09":
				month = Month.SEPTEMBER;
				break;
			case "10":
				month = Month.OCTOBER;
				break;
			case "11":
				month = Month.NOVEMBER;
				break;
			case "12":
				month = Month.DECEMBER;
				break;
			default:
				break;
			}
			LOGGER.info("OUT :: getMonth with month  ::"+month);
			return month;
		}

		/**
		 * @param birthday
		 * @return
		 */
		public String getBirthDayMsg(String birthday, String name)
		{
			 LOGGER.info("IN ::getBirthDayMsg ");
			 long days = getNumberOfDaysToBday(birthday);
			 final  String TODAY_BDAY_MSG = "Hello, "+name+"! Happy birthday";
			 final  String DAYS_TO_BDAY = "Hello, "+name+"! Your birthday is in "+String.valueOf(days)+" day(s)";
			 LOGGER.info("OUT ::number of days "+days);
			 return days == 0 ?TODAY_BDAY_MSG : DAYS_TO_BDAY;
		}
		
		public String validate(Person person) {
			LOGGER.info("IN ::validate name : "+person.getFirst_name()+" date of birth : "+person.getDateOfBirth());
			
			if (!isString(person.getFirst_name()))
				return "Accepts only string as name";
			
			if (!isDate(person.getDateOfBirth().toString()) || !isValidBirthDate(person.getDateOfBirth().toString())) {
				return "Not a valid birth date";
			}
			return "None";
			
		}
}
