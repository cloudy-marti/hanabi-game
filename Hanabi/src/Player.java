
public class Player {
	private final String name;
	private final Hand hand;
	
	public Player(String name, Board board)
	{
		this.name = name;
		this.hand = new Hand(board);
	}

	public void discardAndDraw(Board board, int index)
	{
		board.getDiscard().addCardToDiscard(hand.discard(index));

		try
		{
			hand.draw(board.getDeck());
		}
		catch (Exception e)
		{
			System.out.println("it looks like you can't draw anymore !");
		}
	}

	@Override
	public String toString()
	{
		return "Player (" + name + " " + hand + ")";
	}
}
