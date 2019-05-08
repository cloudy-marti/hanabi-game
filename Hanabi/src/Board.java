import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;


public class Board {
	private final Deck deck;
	private final Discard discard;

	private int blueToken;
	private int redToken;

	private int turn;
	private final ArrayList<Player> players;
	private int numberOfPlayers;

	private final HashMap<Color,Integer> board;

	public Board() {
		this.deck = new Deck();
		this.discard = new Discard();
		this.blueToken = 8;
		this.redToken = 3;
		this.turn = 0;
		this.board = new HashMap<Color,Integer>();
		this.players = new ArrayList<Player>();
		this.numberOfPlayers = 0;
	}

	public Deck getDeck()
	{
		return this.deck;
	}

	public Discard getDiscard()
	{
		return this.discard;
	}

	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

	public int getBlueToken()
	{
		return this.blueToken;
	}

	public int getRedToken()
	{
		return this.redToken;
	}

	public int getTurn()
	{
		return this.turn;
	}

	public void nextTurn()
	{
		turn++;
	}

	public HashMap<Color, Integer> getBoard()
	{
		return this.board;
	}

	public int getNumberOfPlayers()
	{
		return this.numberOfPlayers;
	}

	public void setNumberOfPlayers(int number)
	{
		this.numberOfPlayers = number;
	}

	@Override
	public String toString() {
		return "Board [deck = " + deck.size() + " cards on deck\n" + deck + ",\nred token = " + redToken + ",\ndiscard = "
				+ discard + ",\nturn = " + turn + "]";
	}	
	
}
