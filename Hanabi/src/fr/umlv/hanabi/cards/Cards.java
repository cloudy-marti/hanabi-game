package fr.umlv.hanabi.cards;

import java.util.ArrayList;

/**
 * SuperClass of all sort of decks containing zero to multiple cards
 */
public class Cards
{
	protected final ArrayList<Card> deck;

	/**
	 * Constructor
	 */
	protected Cards()
	{
		this.deck = new ArrayList<>();
	}

	/**
	 * Getter function - Card's arraylist of cards
	 * @return ArrayList
	 */
	public ArrayList<Card> getDeck()
	{
		return deck;
	}

	/**
	 * Return arraylist's size
	 * @return int
	 */
	public int size()
	{
		return deck.size();
	}

	/**
	 * Display all the cards of the arraylist
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder handStr = new StringBuilder();

		for(Card card : deck)
		{
			handStr.append(card);
		}

		return handStr.toString();
	}
}
