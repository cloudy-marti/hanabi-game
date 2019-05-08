import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Deck
{
	private final ArrayList<Card> deck;
	
	public Deck()
	{
		this.deck = new ArrayList<>();

		int i;
		for(i = 0; i < 3; ++i)
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

	public ArrayList<Card> getDeck()
	{
		return this.deck;
	}

	public Card draw()
	{
		return deck.remove(0);
	}

	public int size()
	{
		return deck.size();
	}

	@Override
	public String toString()
	{
		StringBuilder deckStr = new StringBuilder();

		for(Card card : deck)
		{
			deckStr.append(card);
		}

		return deckStr.toString();
	}
}
