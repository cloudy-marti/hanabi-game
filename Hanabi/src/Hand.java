import java.util.ArrayList;


public class Hand
{
	private final ArrayList<Card> hand;
	private final int numberOfCards;
	
	public Hand(Board board)
	{
		hand = new ArrayList<>();

		if(board.getNumberOfPlayers() < 4)
		{
			this.numberOfCards = 5;
		}
		else
		{
			this.numberOfCards = 4;
		}

		for(int i = 0; i < numberOfCards; ++i)
		{
			hand.add(board.getDeck().draw());
		}
	}

	public ArrayList<Card> getHand()
	{
		return this.hand;
	}

	public Card discard(int index)
	{
		return hand.remove(index);
	}

	public void draw(Deck deck)
	{
		if(deck.size() == 0)
		{
			throw new IndexOutOfBoundsException("deck is empty !");
		}

		hand.add(deck.draw());
	}

	@Override
	public String toString()
	{
		StringBuilder handStr = new StringBuilder();

		for(Card card : hand)
		{
			handStr.append(card);
		}

		return handStr.toString();
	}
}
