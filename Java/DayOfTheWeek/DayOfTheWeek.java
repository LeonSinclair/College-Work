import java.util.InputMismatchException;
import java.util.Scanner;


public class DayOfTheWeek 
{
	public static final int DAYS_IN_MOST_MONTHS = 31;
	public static final int DAYS_IN_APRIL_JUNE_SEPTEMBER_NOVEMBER = 30;
	public static final int DAYS_IN_FEB_NORMALLY = 28;
	public static final int DAYS_IN_FEB_LEAP_YEAR = 29;
	public static final int NUMBER_OF_MONTHS = 12;
	public static final String FIRST_ENDING = "st";
	public static final String SECOND_ENDING = "nd";
	public static final String THIRD_ENDING = "rd";
	public static final String FOURTH_ENDING = "th";
	
	public static void main(String[] args)
	{
		System.out.println("Please enter a date in the format DD/MM/YYYY/");
		Scanner inputScanner = new Scanner (System.in);
		inputScanner.useDelimiter("/");
		if(inputScanner.hasNextInt())
		{
			int day = inputScanner.nextInt();
			int month = inputScanner.nextInt();
			int year = inputScanner.nextInt();
			
			if(isValidDate(day,month,year))
			{
				System.out.println(dayOfTheWeek(day,month,year) + ", " + day + stringEnding(day) + " of " + monthName(month) + " " + year);
			}

			
		}
		else
		{
			System.out.println("Please enter a valid input.");
		}
		inputScanner.close();
		

	}
	public static boolean isLeapYear (int year)
	{
		if((year%4==0 && year%100!=0) || year%400==0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static int daysInMonth (int month, int year)
	{
		switch(month)
		{
			case 4:
			case 6:
			case 9:
			case 11: return DAYS_IN_APRIL_JUNE_SEPTEMBER_NOVEMBER;
			case 2: boolean leapYear = isLeapYear (year);
				if(leapYear)
				{
					return DAYS_IN_FEB_LEAP_YEAR;
				
				}
				else
				{
					return DAYS_IN_FEB_NORMALLY;
				}
			default: return DAYS_IN_MOST_MONTHS;
		}
	}
	public static boolean isValidDate (int day, int month, int year)
	{
		try
		{
			int daysInGivenMonth = 0;
			if(month>=1 && month<=12)
			{
				daysInGivenMonth = daysInMonth(month, year);
			}
			else
			{
				System.out.println("Please enter a valid input");
				return false;
			}
			if (day>0 && day<=daysInGivenMonth)
			{
				if(year>0)
				{
					return true;
				}
				else
				{
					System.out.println("Please enter a valid input");
					return false;
				}
		
			}
			else
			{
				System.out.println("Please enter a valid input");
				return false;
			}
		}
		catch(InputMismatchException exception)
		{
			System.out.println("Please enter a valid input");
			return false;
		}

	}
	public static String stringEnding (int day)
	{
		String ending ="";
		if(day>=10 && day<20)
		{
			ending = FOURTH_ENDING;
		}
		else
		{
			int dayLastDigit = day%10;
			switch(dayLastDigit)
			{
			case 1: ending = FIRST_ENDING;
					break;
			case 2: ending = SECOND_ENDING;
					break;
			case 3: ending = THIRD_ENDING;
					break;
			default: ending = FOURTH_ENDING;
					 break;
			}
			
		}

		return ending;
	}
	
	public static String monthName (int month)
	{
		String nameOfInputMonth ="";
		switch(month)
		{
		case 1: nameOfInputMonth = "January";
				break;
		case 2: nameOfInputMonth = "February";
				break;
		case 3: nameOfInputMonth = "March";
				break;
		case 4: nameOfInputMonth = "April";
				break;
		case 5:	nameOfInputMonth = "May";
				break;
		case 6: nameOfInputMonth = "June";
				break;
		case 7: nameOfInputMonth = "July";
				break;
		case 8: nameOfInputMonth = "August";
				break;
		case 9: nameOfInputMonth = "September";
				break;
		case 10: nameOfInputMonth = "October";
				break;
		case 11: nameOfInputMonth = "November";
				break;
		default: nameOfInputMonth = "December";
				break;
		}
		return nameOfInputMonth;
	}
	
	public static String dayOfTheWeek (int day, int month, int year)
	{
		int Y = 0;
		if(month == 1 || month == 2)
		{
			Y=year-1;
		}
		else
		{
			Y=year;
		}
		int y = Y%100;
		int c = Y/100;
		
		int dayOfWeek = (int) ((((day + Math.floor(2.6 * (((month + 9) % 12) + 1) - 0.2) + y + Math.floor(y/4) + Math.floor(c/4) - (2*c)))%7+7)%7);
		String dayOfWeekOutput = " ";
		switch(dayOfWeek)
		{
			case 0: dayOfWeekOutput = "Sunday";
					break;
			case 1: dayOfWeekOutput = "Monday";
					break;
			case 2: dayOfWeekOutput = "Tuesday";
					break;
			case 3: dayOfWeekOutput = "Wednesday";
			        break;
			case 4: dayOfWeekOutput = "Thursday";
					break;
			case 5: dayOfWeekOutput = "Friday";
					break;
			default: dayOfWeekOutput = "Saturday";
					break;
		}
		return dayOfWeekOutput;
	}

}
