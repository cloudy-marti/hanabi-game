package Hanabi;

/**
 * Player's hand.
 */
public class Hand extends Cards
{
	private final int numberOfCards;

	/**
	 * Constructor of the player's hand that will draw as many cards as needed at the beginning of the game
	 * @param board Main board of the game
	 */
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

	/**
	 * Returns the card wanted by the player, who will give its index
	 * @param index Index of card of the hand
	 * @return Card
	 */
	public Card getCard(int index)
	{
		return deck.remove(index);
	}

	/**
	 * Draws a card from the board's main deck and adds it to the hand
	 * @param boardDeck Main deck of the game, containing the cards not given to the players yet
	 */
	public void draw(Deck boardDeck)
	{
		if(deck.size() == 0)
		{
			throw new IndexOutOfBoundsException("deck is empty !");
		}

		deck.add(boardDeck.draw());
	}
}
