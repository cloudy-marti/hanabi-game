package Hanabi;

import java.util.ArrayList;

/**
 * SuperClass of all sort of decks
 */
public class Cards
{
	protected final ArrayList<Card> deck;

	protected Cards()
	{
		this.deck = new ArrayList<>();
	}

	public ArrayList<Card> getDeck()
	{
		return deck;
	}

	public int size()
	{
		return deck.size();
	}

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
