package Hanabi;

import java.awt.*;
import java.util.Collections;

/**
 * Main deck of the board, containing the cards not given to the players.
 */
public class Deck extends Cards
{
	/**
	 * Constructor of Deck
	 * Called at the very beginning of the program, it will set all the existing cards of Hanabi Game
	 */
	public Deck()
	{
		super();

		for(int i = 0; i < 3; ++i)
		{
			addCards(1);

			if(i > 0)
			{
				addCards(2);
				addCards(3);
				addCards(4);
			}

			if(i > 1)
			{
				addCards(5);
			}
		}

		Collections.shuffle(deck);
	}

	/**
	 * Adds one card of each color to the deck
	 * @param number
	 */
	private void addCards(int number)
	{
		deck.add(new Card(Color.BLUE, number));
		deck.add(new Card(Color.RED, number));
		deck.add(new Card(Color.WHITE, number));
		deck.add(new Card(Color.GREEN, number));
		deck.add(new Card(Color.YELLOW, number));
	}

	/**
	 * Pick the first card (the one on the top) of the deck and returns it
	 * @return Card
	 */
	public Card draw()
	{
		return deck.remove(0);
	}
}
