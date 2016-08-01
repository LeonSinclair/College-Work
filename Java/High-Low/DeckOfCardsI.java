
public interface DeckOfCardsI 
{

      public boolean isEmpty();
      public PlayingCardI deal();
      public void makeRemainingFaceDown();
      public void shuffle();
      public String toPictographs();
}