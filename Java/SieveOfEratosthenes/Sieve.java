import java.util.Arrays;
import java.util.Scanner;
public class Sieve{



	public static final int PRIME_NUMBERS_START = 2;
	public static final String START_BRACKETS = "[";
	public static final String CLOSE_BRACKETS = "]";	
	public static final String COMMA = ",";
	public static final String FULL_STOP = ".";
	public static void main(String[] args) 
	{
		System.out.println("Please enter a positive whole number greater than 1 "
				+ "as the limit you would like to go up to.");
		Scanner inputScanner = new Scanner(System.in);
		if(inputScanner.hasNextInt())
		{
			int limit =inputScanner.nextInt()-PRIME_NUMBERS_START;
			int[] primeNumbersArray = new int[limit];
			if(limit>=PRIME_NUMBERS_START)
			{
				String answer = sieve(limit, primeNumbersArray);
			    if(answer.equals("blah"))
			    {
			    }
			    else
			    {
			      System.out.println(answer);   
			    }
				
					
			}
			else
			{
				System.out.println("Please try again but enter a valid input this time.");
			}
		}
		else
		{
			System.out.println("Please try again but enter a valid input this time.");
		}
		inputScanner.close();
	}
	
	public static int[] createSequence (int limit)
	{
		int[]array = new int[limit];
		for(int count =0; count<limit; count++)
		{
			array[count] = count+PRIME_NUMBERS_START;
		}
		return array;
		
	}
	
	public static String nonCrossedOutSubseqToString (int[]array)
	{
	    
	    String nonCrossedOutNumbers = "";
	    for(int characterCount=0; characterCount<array.length; characterCount++)
	    {
	        String nextCharacter = Integer.toString(array[characterCount]);
	        if(array[characterCount]>0)
	        {
	            nonCrossedOutNumbers =nonCrossedOutNumbers+nextCharacter;
	            if(characterCount==(array.length)-1)
	            {
	               nonCrossedOutNumbers =nonCrossedOutNumbers+FULL_STOP; 
	            }
	            else
	            {
	                nonCrossedOutNumbers =nonCrossedOutNumbers+COMMA; 
	            }
	        }
	    }
	   
		return nonCrossedOutNumbers;
	}
	
	public static String sequenceToString (int[]array)
	{
	    String allNumbersString= "";
	    for(int characterCount=0; characterCount<array.length; characterCount++)
	    {
	     
	     if(array[characterCount]<0)
	        {
	            String nextCharacter = Integer.toString((array[characterCount]*-1));
    	        allNumbersString =allNumbersString+START_BRACKETS;
    	        allNumbersString =allNumbersString+nextCharacter;
    	        allNumbersString =allNumbersString+CLOSE_BRACKETS;
    	        allNumbersString =allNumbersString+COMMA;
	        }
	        else
	        {
	            String nextCharacter = Integer.toString(array[characterCount]);
	            allNumbersString =allNumbersString+nextCharacter;
	            allNumbersString =allNumbersString+COMMA;
	        }
	        
	    }
	    return allNumbersString;
	}
	
	
	public static void crossOutHigherMultiples (int limit, int[]array)
	{
		int count =0;
		boolean underSquarelimit = true;
		while(underSquarelimit)
		{
			
			int currentNumber = array[count];
			if(currentNumber>(int)(Math.sqrt(limit)))
			{
				underSquarelimit=false;
			}
			else if(currentNumber<0)
			{
				count++;
			}
			else
			{
				for(int crossOutCounter=(currentNumber+1);crossOutCounter<limit+PRIME_NUMBERS_START; crossOutCounter++ )
				{
					if(array[crossOutCounter-PRIME_NUMBERS_START]%currentNumber==0 && array[(crossOutCounter-PRIME_NUMBERS_START)]>0)
					{
						array[(crossOutCounter-PRIME_NUMBERS_START)] =
								(array[(crossOutCounter-PRIME_NUMBERS_START)] *-1);
					}
				}
				count++;
			}
		}
	}
	
	public static String sieve (int limit, int[]array)
	{
		
		array = createSequence(limit);
		crossOutHigherMultiples(limit, array);
		System.out.println("To see all numbers please enter 'numbers',to see all primes enter 'primes', to leave enter 'quit'");
		Scanner inputScanner = new Scanner(System.in);
		String input = inputScanner.next();
		if(input.equals("numbers"))
		{
		    String allNumbers = sequenceToString(array);
		    return allNumbers;
		}
		else if(input.equals("primes"))
		{
		    String primeString = nonCrossedOutSubseqToString(array);
		    return primeString;
		}
		else if(input.equals("quit"))
		{
		    System.out.println("Thank you come again!");
		}
		else
		{
		    System.out.println("Please try again with a valid input this time");
		}
		
	    return "blah";
		
	}
	

}

