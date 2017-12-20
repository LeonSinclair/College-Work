import java.lang.Comparable;
public interface PlayingCardI extends Comparable<PlayingCardI>
{
	public static enum Suit
	{
		SPADES, HEARTS, DIAMONDS, CLUBS
	}
	
	public static enum Rank
	{
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, KNIGHT, QUEEN, KING,
	}
	
	public Suit suit();
	
	public Rank rank();
	
	public boolean isFaceUp();
	
	public PlayingCardI flip();
	
	public void makeFaceDown();
	
	public String toPictograph();
	
	public int compareTo(PlayingCardI card);
}
