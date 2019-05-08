import java.util.ArrayList;


public class Discard {
	private final ArrayList<Card> discard;
	
	public Discard() {
		discard = new ArrayList<Card>();
	}

	public void addCardToDiscard(Card card)
	{
		discard.add(card);
	}

	@Override
	public String toString()
	{
		StringBuilder discardStr = new StringBuilder();

		for(Card card : discard)
		{
			discardStr.append(card);
		}

		return discardStr.toString();
	}
}
