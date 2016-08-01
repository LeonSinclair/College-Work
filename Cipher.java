import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cipher {
	public static final int CHARACTERS_IN_ALPHABET = 27;
	public static void main(String[] args)
	{
		char[] cipherArray = new char[CHARACTERS_IN_ALPHABET];
		String alphabet = "abcdefghijklmnopqrstuvwxyz ";
		char[] alphabetArray = alphabet.toCharArray();
		createCipher(alphabetArray, cipherArray);
		System.out.println("Please enter 'encrypt', 'decrypt' or 'exit'.");
		Scanner inputScanner = new Scanner(System.in);
		if(inputScanner.hasNext("encrypt"))
		{
			inputScanner.nextLine();
			System.out.println("Please enter the string you would like to encrypt.");
			if(inputScanner.hasNextLine())
			{
				String input = inputScanner.nextLine();
				String output = encrypt(input, alphabetArray, cipherArray);
				System.out.println("The given input encrypts to " + output + ".");
			}
			else
			{
				System.out.println("Please try again and enter a valid input as defined originally.");
			}
			
			
		}
		else if(inputScanner.hasNext("decrypt"))
		{
			inputScanner.nextLine();
			System.out.println("Please enter the string you would like to decrypt.");
			if(inputScanner.hasNextLine())
			{
				String input = inputScanner.nextLine();
				String output = decrypt(input, alphabetArray, cipherArray);
				System.out.println("The given input decrypts to " + output + ".");
			}
			else
			{
				System.out.println("Please try again and enter a valid input as defined originally.");
			}
			
		}
		else if(inputScanner.hasNext("exit"))
		{
			System.out.println("Thank you come again.");
		}
		else
		{
			System.out.println("Please try again and enter a valid input as defined originally.");
		}
		inputScanner.close();
	}

	
	public static void createCipher(char[]alphabetArray, char[] cipherArray) 
	{
		for(int indexCounter=0; indexCounter<CHARACTERS_IN_ALPHABET; indexCounter++)
		{
			cipherArray[indexCounter] = alphabetArray[indexCounter];
		}
		Random generator = new Random();
		for (int count=0; count<CHARACTERS_IN_ALPHABET; count++)
		{
		    int swapPlace = generator.nextInt(CHARACTERS_IN_ALPHABET);
		    char temp = cipherArray[count];
		    cipherArray[count] = cipherArray[swapPlace];
		    cipherArray[swapPlace] = temp;   
		}
		
		System.out.println(Arrays.toString(alphabetArray) + " = Alphabet which maps to");
		System.out.println(Arrays.toString(cipherArray) + " = Cipher");
	}
	
	public static String encrypt(String input, char[]array, char[] cipherArray)
	{
		String lowercaseInput = input.toLowerCase();
		char[] inputArray = lowercaseInput.toCharArray();
		char[] outputArray = new char[inputArray.length];
		for(int countOfCharacters=0;countOfCharacters<inputArray.length;countOfCharacters++)
		{
			for(int alphabetCounter=0; alphabetCounter<CHARACTERS_IN_ALPHABET; alphabetCounter++)
			{
				if(inputArray[countOfCharacters]==array[alphabetCounter])
				{
					outputArray[countOfCharacters]= cipherArray[alphabetCounter];
				}
			}
		}
		String encryptedString = new String(outputArray);
		return encryptedString;
		
	}
	public static String decrypt(String input, char[]array, char[] cipherArray)
	{
		String lowercaseInput = input.toLowerCase();
		char[] inputArray = lowercaseInput.toCharArray();
		char[] outputArray = new char[inputArray.length];
		for(int countOfCharacters=0;countOfCharacters<inputArray.length;countOfCharacters++)
		{
			for(int alphabetCounter=0; alphabetCounter<CHARACTERS_IN_ALPHABET; alphabetCounter++)
			{
				if(inputArray[countOfCharacters]==cipherArray[alphabetCounter])
				{
					outputArray[countOfCharacters]= array[alphabetCounter];
				}
			}
				
				
		}
		String decryptedString = new String(outputArray);
		return decryptedString;
	
		
	}
}
