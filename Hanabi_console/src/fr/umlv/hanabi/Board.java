package fr.umlv.hanabi;

import fr.umlv.hanabi.cards.Deck;
import fr.umlv.hanabi.cards.Discard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Board manages the current status of the game on board and on player's hands from the beginning to the end
 */
public class Board {

	private final Deck deck;
	private final Discard discard;

	private int blueToken;
	private int redToken;

	private int turn;
	private final ArrayList<Player> players;
	private int numberOfPlayers;

	private final HashMap<Color, Integer> board;

	/**
	 * Constructor of the game's board
	 */
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

	/**
	 * Getter function - Board's main deck
	 * @return Deck
	 */
	public Deck getDeck()
	{
		return this.deck;
	}

	/**
	 * Displays the hidden deck and evolves when the deck is being emptied
	 * @return String
	 */
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

	/**
	 * Getter function - Board's discard
	 * @return Discard
	 */
	public Discard getDiscard()
	{
		return this.discard;
	}

	/**
	 * When needed (giving a hint by a player), this function decreases the number of blue tokens by one
	 */
	public void payWithBlueToken()
	{
		if(blueToken == 0)
		{
			throw new IllegalStateException("no more blue tokens !");
		}

		blueToken--;
	}

	/**
	 * When needed (discarding a card), this function increases the number of blue tokens by one
	 */
	public void earnBlueToken()
	{
		if(blueToken == 8)
		{
			throw new IllegalStateException("you have already all tokens !");
		}

		blueToken++;
	}

	/**
	 * Getter function - Return the number of red tokens already earned
	 * @return int
	 */
	public int getRedToken()
	{
		return this.redToken;
	}

	/**
	 * When an error is done (playing the wrong card or in the wrong place), this functions increases the number of red tokens by one
	 */
	public void punishWithRedToken()
	{
		redToken++;
	}

	/**
	 * Getter function - Returns the number of the current turn
	 * @return int
	 */
	public int getTurn()
	{
		return this.turn;
	}

	/**
	 * Increases the turn number by one each turn
	 */
	public void nextTurn()
	{
		turn++;
	}

	/**
	 * Decreases the turn number by one
	 * To be used ONLY when player wanted to give a hint and wasn't able to because of a lack of blue tokens
	 */
	public void previousTurn()
	{
		turn--;
	}

	/**
	 * Getter function - Returns the list of players
	 * @return ArrayList
	 */
	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

	/**
	 * Getter function - Board's status
	 * @return HashMap describing the status of the cards put in game
	 */
	public HashMap<Color, Integer> getBoard()
	{
		return this.board;
	}

	/**
	 * Puts the card into the hashmap of the board.
	 * Only the last allowed card of each color is saved into the hashmap.
	 * @param color Card's color
	 * @param value Card's number
	 */
	public void addCardToBoard(Color color, int value)
	{
		board.put(color, value);
	}

	/**
	 * Getter function - Board's players number
	 * @return int
	 */
	public int getNumberOfPlayers()
	{
		return this.numberOfPlayers;
	}

	/**
	 * Setter function - Sets the number of players
	 * @param number Number given by user
	 */
	public void setNumberOfPlayers(int number)
	{
		this.numberOfPlayers = number;
	}

	/**
	 * Ask the player the name of another player and return the player wanted
	 * @return Player
	 */
	public Player getPlayerByName(Player currentPlayer)
	{
		Player tmpPlayer = null;

		while(tmpPlayer == null)
		{
			Scanner scan = new Scanner(System.in);
			String playerName = scan.next();

			for(Player player : players)
			{
				if(playerName.equals(player.getName()))
				{
					tmpPlayer = player;
				}
			}

			if(tmpPlayer == null || tmpPlayer.equals(currentPlayer))
			{
				System.out.println("wrong name ! try again");
				tmpPlayer = null;
			}
		}

		return tmpPlayer;
	}

	/**
	 * Displays the hand of the other players when it's currentPlayer's turn.
	 * @param currentPlayer player whose turn is right now
	 */
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

	/**
	 * Called at the end, it sums all the highest scores on board.
	 * Displays a different message depending on the score.
	 */
	public void displayFinalScore()
	{
		int result = 0;

		for(int value : board.values())
		{
			result += value;
		}

		if(result > 24)
		{
			System.out.println("\nLegendary, everyone left speechless, stars in their eyes !");
		}
		else if(result > 20)
		{
			System.out.println("\nAmazing, will be remembered for a very long time !");
		}
		else if(result > 15)
		{
			System.out.println("\nExcellent, crowd is very pleased !");
		}
		else if(result > 10)
		{
			System.out.println("\nHonourable, but will not be remembered for long ...");
		}
		else if(result > 5)
		{
			System.out.println("\nMediocre, just a spattering of applause.");
		}
		else
		{
			System.out.println("\nHorrible, booed by the crowd ...");
		}
	}

	/**
	 * Displays the status of the cards played (and not discarded) in a understandable / human way
	 * @return String
	 */
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
				boardStr.append(" Blue = ");
				boardStr.append(board.get(Color.BLUE));
			}

			if(board.containsKey(Color.RED))
			{
				boardStr.append(" Red = ");
				boardStr.append(board.get(Color.RED));
			}

			if(board.containsKey(Color.GREEN))
			{
				boardStr.append(" Green = ");
				boardStr.append(board.get(Color.GREEN));
			}

			if(board.containsKey(Color.YELLOW))
			{
				boardStr.append(" Yellow = ");
				boardStr.append(board.get(Color.YELLOW));
			}

			if(board.containsKey(Color.WHITE))
			{
				boardStr.append(" White = ");
				boardStr.append(board.get(Color.WHITE));
			}

			boardStr.append("  }");
		}
		return boardStr.toString();
	}

	/**
	 * Display the current status of the board
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "\nturn = " + turn + "\n" + deck.size() + " cards on deck\t " + deckToString() + "\nred token = "
				+ redToken + "\tblue token = " + blueToken + "\n" + discard.size() + " cards on discard :\n" + discard
				+ "\nFireworks in progress : " + boardToString() + "\n";
	}
}
