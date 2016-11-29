import java.lang.String;
import java.util.Arrays;
import java.lang.Character;
public class playingCard implements PlayingCardI
{		
	Rank cardRank;
	Suit cardSuit;
	boolean isFaceUp;
	public playingCard(Suit suit, Rank rank)
	{
		cardRank = rank;
		cardSuit = suit;
		isFaceUp = false;
	}
	
	
	public Suit suit() {
		return cardSuit;
	}

	@Override
	public Rank rank() {
		return cardRank;
	}

	@Override
	public boolean isFaceUp() {
		return isFaceUp;
	}

	@Override
	public PlayingCardI flip() {
		if(isFaceUp)
		{
			isFaceUp = false;
		}
		else
		{
			isFaceUp = true;
		}
		return null;
	}

	@Override
	public void makeFaceDown() {
		isFaceUp = false;
		
	}

	
	static String backOfPlayingCard;
	static String[][] pictographs;
	static int numberOfPlayingCardObjects;
	public String toPictograph() 
	{
	 return isFaceUp() ? pictographs[suit().ordinal()][rank().ordinal()]
                       : backOfPlayingCard;
	}
	
	static 
	{
		int backOfPlayingCardUnicodePoint = 0x1F0A0;
		int[] unicodePoints = { backOfPlayingCardUnicodePoint };
		backOfPlayingCard = new String(unicodePoints, 0, 1);		
		pictographs = new String [Suit.values().length][Rank.values().length];
		int unicodePoint = 0x1F0A1;
		for (Suit suit : Suit.values())
		{
				for (Rank rank : Rank.values()) 
					{
						unicodePoints[0] = unicodePoint + rank.ordinal();
						pictographs[suit.ordinal()][rank.ordinal()] = new String(unicodePoints, 0, 1);
					}
				unicodePoint += 0x10; 
		}
		numberOfPlayingCardObjects = 0;
	}
	@Override
	public int compareTo(PlayingCardI card) {
		if((this.rank().ordinal()<card.rank().ordinal()))
		{
			return 1;
		}
		else if((this.rank().ordinal())>(card.rank().ordinal()))
		{
			return -1;
		}
		return 0;
	}

}
