package Hanabi;

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

	private final HashMap<Color, Integer> board;

	public Board() {

		this.deck = new Deck();
		this.discard = new Discard();

		this.blueToken = 8;
		this.redToken = 0;

		this.turn = 0;
		this.players = new ArrayList<>();
		this.numberOfPlayers = 0;

		this.board = new HashMap<>();
	}

	public Deck getDeck()
	{
		return this.deck;
	}

	private String deckToString()
	{
		if(deck.size() == 0)
		{
			return "--EMPTY--";
		}
		else if(deck.size() == 1)
		{
			return "[DECK]";
		}
		else if(deck.size() == 2)
		{
			return "[DECK]]";
		}
		else
		{
			return "[DECK]]]";
		}
	}

	public Discard getDiscard()
	{
		return this.discard;
	}

	public int getBlueToken()
	{
		return this.blueToken;
	}

	public void payWithBlueToken()
	{
		if(blueToken == 0)
		{
			throw new IllegalStateException("no more blue tokens !");
		}

		blueToken--;
	}

	public void earnBlueToken()
	{
		if(blueToken == 8)
		{
			throw new IllegalStateException("you have already all tokens !");
		}

		blueToken++;
	}

	public int getRedToken()
	{
		return this.redToken;
	}

	public void punishWithRedToken()
	{
		redToken++;
	}

	public int getTurn()
	{
		return this.turn;
	}

	public void nextTurn()
	{
		turn++;
	}

	/**
	 * To be used ONLY when player wanted to give a hint and wasn't able to
	 */
	public void previousTurn()
	{
		turn--;
	}

	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

	public void showOtherPlayersHand(Player currentPlayer)
	{
		StringBuilder playerStr = new StringBuilder();

		for(Player player : players)
		{
			if(!player.equals(currentPlayer))
			{
				playerStr.append("\t");
				playerStr.append(player);
				playerStr.append("\t->\t");
				playerStr.append(player.handToString());
				playerStr.append("\n");
			}
		}

		System.out.println(playerStr);
	}

	public int getNumberOfPlayers()
	{
		return this.numberOfPlayers;
	}

	public void setNumberOfPlayers(int number)
	{
		this.numberOfPlayers = number;
	}

	public HashMap<Color, Integer> getBoard()
	{
		return this.board;
	}

	public void addCardToBoard(Color color, int value)
	{
		board.put(color, value);
	}

	private String boardToString()
	{
		StringBuilder boardStr = new StringBuilder();

		if(board.isEmpty())
		{
			return "{ no fireworks for now ! }";
		}
		else
		{
			boardStr.append("{ ");
			if(board.containsKey(Color.BLUE))
			{
				boardStr.append("Blue = ");
				boardStr.append(board.get(Color.BLUE));
			}

			if(board.containsKey(Color.RED))
			{
				boardStr.append("Red = ");
				boardStr.append(board.get(Color.RED));
			}

			if(board.containsKey(Color.GREEN))
			{
				boardStr.append("Green = ");
				boardStr.append(board.get(Color.GREEN));
			}

			if(board.containsKey(Color.YELLOW))
			{
				boardStr.append("Yellow = ");
				boardStr.append(board.get(Color.YELLOW));
			}

			if(board.containsKey(Color.WHITE))
			{
				boardStr.append("White = ");
				boardStr.append(board.get(Color.WHITE));
			}

			boardStr.append(" }");
		}
		return boardStr.toString();
	}

	@Override
	public String toString()
	{
		return "\nturn = " + turn + "\n" + deck.size() + " cards on deck\t " + deckToString() + "\nred token = "
				+ redToken + "\tblue token = " + blueToken + "\n" + discard.size() + " cards on discard :\n" + discard
				+ "\nBoard in game : " + boardToString() + "\n";
	}	
	
}
