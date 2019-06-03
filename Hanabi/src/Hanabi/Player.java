package Hanabi;

import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * All variables and methods directly linked to the player
 */
public class Player
{
	private final String name;
	private final Hand hand;

	/**
	 * Constructor of Player with a given name.
	 * Board is needed to get the cards for the player's hand.
	 * @param name Name of the player
	 * @param board Main board
	 */
	public Player(String name, Board board)
	{
		this.name = name;
		this.hand = new Hand(board);
	}

	/**
	 * Getter function - Player's name.
	 * @return String
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Get index input from the player.
	 * @return input integer
	 */
	public int getIndexInput()
	{
		int x;

		do
		{
			Scanner s = new Scanner(System.in);
			x = s.nextInt();
		}
		while(x > hand.size() || x < 0);

		return x;
	}

	/**
	 * Get color input from the player.
	 * @return color string
	 */
	public String getColorInput()
	{
		String color;

		Scanner scan = new Scanner(System.in);
		color = scan.next();

		return color;
	}

	/**
	 * Drawing a card from the deck to the player's hand.
	 * Won't take any card if the deck is empty.
	 * @param board Hanabi.Board where the game is played on
	 */
	private void draw(Board board)
	{
		try
		{
			hand.draw(board.getDeck());
		}
		catch (Exception e)
		{
			System.out.println("it looks like you can't draw anymore !");
		}
	}

	/**
	 * Discards the card from the player's hand.
	 * Add this card to the discard deck.
	 * @param board Hanabi.Board where the game is played on
	 * @param index Index of the card to be discarded, chosen by the player
	 */
	public void discardAndDraw(Board board, int index)
	{
		board.getDiscard().addCardToDiscard(hand.getCard(index));

		draw(board);
	}

	/**
	 * Check whether the card played can be put on game or not
	 * @param board Hanabi.Board where the game is played on
	 * @param card Hanabi.Card played
	 * @param chosenColor Color where the card needs to be put, chosen by the player
	 * @return boolean
	 */
	private boolean isCardAllowed(Board board, Card card, Color chosenColor)
	{
		Color color = card.getColor();
		int number = card.getNumber();

		/**
		 * Check whether the board has already a card from the chosen color or not
		 */
		if(!board.getBoard().containsKey(chosenColor))
		{
			if(number != 1)
			{
				return false;
			}
			/**
			 * Game design choice : if the player gave a random color, not existing in board yet and the card played is 1
			 * We let them play the card anyway
			 *
			 * In real life, when the player has a 1 and wants to put it on game, he/she will put it in a empty random space
			 * Even without knowing the color yet (useless on first turns)
			 */
			else
			{
				return true;
			}
		}
		/**
		 * If the board has already the chosen color
		 */
		else
		{
			if(board.getBoard().containsValue(number) || board.getBoard().get(color) != (number - 1))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Translate the color from an input (string) to a color (enum)
	 * @param colorStr player's input
	 * @return Color
	 */
	private Color stringToColor(String colorStr)
	{
		Color color;

		switch (colorStr)
		{
			case "w":
				color = Color.WHITE;
				break;

			case "b":
				color = Color.BLUE;
				break;

			case "r":
				color = Color.RED;
				break;

			case "g":
				color = Color.GREEN;
				break;

			case "y":
				color = Color.YELLOW;
				break;

			default:
				color = Color.BLACK;
				break;
		}

		return color;
	}

	/**
	 * Put the card on game if allowed and draw a card from the deck
	 * @param board Hanabi.Board where the game is played on
	 * @param index Index of the card to be discarded, chosen by the player
	 * @param colorStr Hanabi.Player's input
	 */
	public void playAndDraw(Board board, int index, String colorStr)
	{
		Card chosenCard = hand.getCard(index);

		Color color = stringToColor(colorStr);

		if(!isCardAllowed(board, chosenCard, color))
		{
			System.out.println("wrong move !");

			board.punishWithRedToken();
			board.getDiscard().addCardToDiscard(chosenCard);
		}
		else
		{
			board.addCardToBoard(chosenCard.getColor(), chosenCard.getNumber());
		}

		draw(board);
	}

	public void giveAHint(Board board, Player chosenPlayer)
	{
		System.out.println();
	}

	/**
	 * Getter function of Player's hand
	 * @return Hand
	 */
	public Hand getHand()
	{
		return this.hand;
	}

	public void showOwnHand()
	{
		StringBuilder handStr = new StringBuilder();

		handStr.append("\t ");
		for(Card card : hand.getDeck())
		{
			handStr.append(card.showHint());
		}

		handStr.append("\n\t(");

		for(Card card : hand.getDeck())
		{
			handStr.append(card.hiddenCardToString());
		}
		handStr.append(")");

		System.out.println(handStr);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return Objects.equals(name.toLowerCase(), player.name.toLowerCase());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * Display the hand of the player
	 * toString() does only have the player's name, so this function gives the hand when needed
	 * @return String
	 */
	public String handToString()
	{
		StringBuilder string = new StringBuilder();

		string.append(" (");
		string.append(hand);
		string.append(")");

		return string.toString();
	}

	/**
	 * Display the player's name
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Player " + name;
	}

	/**
	 * Checks if the player has the same name
	 * @param player Player to be compared with this player
	 * @return boolean
	 */
	public boolean equals(Player player)
	{
		return name == player.getName();
	}
}