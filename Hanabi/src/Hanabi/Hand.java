package Hanabi;

public class Hand extends Cards
{
	private final int numberOfCards;
	
	public Hand(Board board)
	{
		super();

		if(board.getNumberOfPlayers() < 4)
		{
			numberOfCards = 5;
		}
		else
		{
			numberOfCards = 4;
		}

		for(int i = 0; i < numberOfCards; ++i)
		{
			deck.add(board.getDeck().draw());
		}
	}

	public Card getCard(int index)
	{
		return deck.remove(index);
	}

	public void draw(Deck boardDeck)
	{
		if(deck.size() == 0)
		{
			throw new IndexOutOfBoundsException("deck is empty !");
		}

		deck.add(boardDeck.draw());
	}
}
