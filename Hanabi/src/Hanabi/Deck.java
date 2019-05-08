package Hanabi;

import java.awt.*;
import java.util.Collections;

public class Deck extends Cards
{
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

	private void addCards(int number)
	{
		deck.add(new Card(Color.BLUE, number));
		deck.add(new Card(Color.RED, number));
		deck.add(new Card(Color.WHITE, number));
		deck.add(new Card(Color.GREEN, number));
		deck.add(new Card(Color.YELLOW, number));
	}

	public Card draw()
	{
		return deck.remove(0);
	}
}
