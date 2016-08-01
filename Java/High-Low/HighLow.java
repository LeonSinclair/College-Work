import java.util.Arrays;
import java.util.Scanner;

public class HighLow 
{
	static PlayingCardI[] currentHand = new  playingCard[5];
	static DeckOfCardsI deck = new DeckOfCards(false);
	static String[] currentHandPictographs = new String[5];
	
	public static void main(String[] args) 
	{
		System.out.println("Welcome to High-Low	!");
		System.out.println("Please enter 'knights' to include Knights in the deck, otherwise enter anything you like.");
		Scanner knightScanner = new Scanner(System.in);
		if(knightScanner.hasNext("knights"))
		{
			deck = new DeckOfCards(true);
			System.out.println("Knights have been included!");
		}
		boolean takingInput = true;
		deck.makeRemainingFaceDown();
		int numberOfCorrectGuesses=0;
		for(int i = 0; i<currentHand.length; i ++)
		{
			currentHand[i] = deck.deal();
			if(i==0 && (currentHand[i].rank().ordinal()!=0 || currentHand[i].rank().ordinal()!=13))
			{
				if(currentHand[i].rank().ordinal()==0)
				{
					while(currentHand[i].rank().ordinal()==0)
					{
						deck.shuffle();
						deck.makeRemainingFaceDown();
						currentHand[i] = deck.deal();
						currentHand[i].flip();
					}
				}
				else if((currentHand[i].rank().ordinal()==13))
				{
					while(currentHand[i].rank().ordinal()==13)
					{
						deck.shuffle();
						deck.makeRemainingFaceDown();
						currentHand[i] = deck.deal();
						currentHand[i].flip(); 
					}
				}
				else
				{
					currentHand[i].flip();
				}
			}
		}
		displayCards();
		while(takingInput)
		{
			numberOfCorrectGuesses = input(numberOfCorrectGuesses);
			if(numberOfCorrectGuesses == -1)
			{
				System.out.println("You have guessed wrong. Game's over. Good job.");
				takingInput = false;
				return;
			}
			else if(numberOfCorrectGuesses == 4)
			{
				System.out.println("You have guessed all cards correctly, congratulations!");
				takingInput = false;
				return;
			}
			else if (numberOfCorrectGuesses == -2)
			{
				System.out.println("Thank you for playing. Come again!");
				takingInput = false;
				break;
			}
			else
			{
				System.out.println("You have guessed correctly, keep it up!");
			}
		}
		
	}
	static int input (int numberOfCorrectGuesses)
	{
		int correctGuesses = numberOfCorrectGuesses;
		int currentCard = numberOfCorrectGuesses;
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("A card will be shown to you, guess whether the next card will be higher, lower or equal. Type exit to exit.");
		boolean takingInput = true;
		while(takingInput)
		{
			if(inputScanner.hasNext())
			{
				String guess = inputScanner.nextLine();
				currentHand[currentCard+1].flip();
				switch (guess)
				{
					case "higher": if(currentHand[currentCard].compareTo(currentHand[currentCard+1])==1)
								   {
									 correctGuesses +=1;
								   }
								   else
								   {
									 correctGuesses =-1; 	 
								   }
								   break;
					case "lower": if(currentHand[currentCard].compareTo(currentHand[currentCard+1])==-1)
								  {
									correctGuesses +=1;	
								  }
								  else
							      {
							    	correctGuesses =-1; 
							      }
								  break;
					case "exit": correctGuesses = -2;
								 break;
					default: if(currentHand[currentCard].compareTo(currentHand[currentCard+1])==0)
							 {
							   correctGuesses +=1;	
							 }
						     else
						     {
						       correctGuesses =-1; 
						     }
							 break;
				}
				displayCards();
				takingInput = false;
			}
		}
		takingInput = true;
		return correctGuesses;
	}
	
	static void displayCards()
	{
		for(int i = 0; i<currentHand.length; i++)
		{
			currentHandPictographs[i] = currentHand[i].toPictograph();
			System.out.println(currentHandPictographs[i]);
		}
		
	}
}
