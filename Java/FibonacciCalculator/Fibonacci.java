import java.util.Scanner;

public class Fibonacci
{
	public static void main (String[] args)
	{
		boolean takingInput = true;
		while(takingInput)
		{
			Scanner inputScanner = new Scanner(System.in);
			System.out.println("How many values of the Fibonacci sequence would you like to calculate?");
			System.out.println("If you would like to quit, please input the word 'exit'.");
			if(inputScanner.hasNext("exit"))
			{
				takingInput = false;
			}
			else if(inputScanner.hasNextInt())
			{
				double targetValue = inputScanner.nextInt();
				double answer = calculateFibonacci(targetValue);
				System.out.println(answer);
			}
			else
			{
				System.out.println("That was not a valid input, please input an integer value next time.");
				System.out.println("");
				inputScanner.next();
			}
		}
		
	}
	
	public static double calculateFibonacci (double endValue)
	{
		if(endValue <= 1)
		{
			return endValue;
		}
		else
		{
			endValue = calculateFibonacci(endValue-1) + calculateFibonacci(endValue-2);
		
		}
		return endValue;
	}
}
