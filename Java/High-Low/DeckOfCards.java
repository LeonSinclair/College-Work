import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class DeckOfCards implements DeckOfCardsI
{
	
	private PlayingCardI.Suit[] suits = new PlayingCardI.Suit[4];
	
	private PlayingCardI.Rank[] ranks;
	
	private PlayingCardI[] cards;
	
	private int topCardIndex;
	
	private boolean hasKnights = false;
	
	public DeckOfCards(boolean hasKnightPlayingCards)
	{
		hasKnights = hasKnightPlayingCards;
		initialiseSuits();
		initialiseRanks(hasKnights);
		cards = new PlayingCardI[suits.length*ranks.length];
		initialiseCards();
		shuffle();
	}
	public void initialiseCards()
	{
		for(int suitCounter = 0; suitCounter<suits.length; suitCounter++)
		{
			for(int rankCounter = 0; rankCounter<ranks.length; rankCounter++)
			{
				cards[topCardIndex] = new playingCard (suits[suitCounter], ranks[rankCounter]);
				topCardIndex++;
			}
		}
		topCardIndex = 0;
	}
	public void initialiseSuits()
	{
		PlayingCardI.Suit[] suitValue = PlayingCardI.Suit.values();
		for(int i = 0; i<suits.length; i++)
		{
			suits[i] = suitValue[i];	
		}
	}
	
	public void initialiseRanks(boolean hasKnightPlayingCards)
	{
		PlayingCardI.Rank[] rankValue = PlayingCardI.Rank.values();
		if(hasKnightPlayingCards)
		{
			ranks = new PlayingCardI.Rank[14];
			for(int i = 0; i<ranks.length; i++)
			{
				ranks[i] = rankValue[i];
			}
		}
		else
		{
			ranks = new PlayingCardI.Rank[13];
			for(int i = 0; i<ranks.length; i++)
			{
				String knightCheck = rankValue.toString();
				if(knightCheck!="KNIGHT")
				{
					if(i>=11)
					{
						ranks[i] = rankValue[i+1];
					}
					else
					{
						ranks[i] = rankValue[i];
					}
					
				}
			}
		}
		
	}
	public boolean isEmpty() {
		if (topCardIndex == cards.length)
		{
			return true;
		}
		return false;
	}

	public PlayingCardI deal() {
		boolean empty;
		empty = isEmpty();
		if(empty)
		{
			initialiseCards();
			shuffle();
			topCardIndex+=1;
		}
		else
		{
			topCardIndex+=1;
		}
		return cards[topCardIndex];
	}

	public void makeRemainingFaceDown() {
		for(int i = topCardIndex+1; i<cards.length; i++)
		{
			boolean currentlyFaceUp = cards[i].isFaceUp();
			if(currentlyFaceUp)
			{
				cards[i].makeFaceDown();
			}
		}
		
	}

	public void shuffle() {
		Collections.shuffle(Arrays.asList(cards));
	}

	public String toPictographs() {
		String cardPictograph = "";
		for(int i = 0; i<cards.length; i++)
		{
			String nextCardPictograph = cards[i].toPictograph();
			cardPictograph = cardPictograph + "\n " + nextCardPictograph;
		}
		return cardPictograph;
	}

}
