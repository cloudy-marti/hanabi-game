package fr.umlv.hanabi;

import fr.umlv.hanabi.cards.Card;
import fr.umlv.hanabi.cards.Hand;

import java.awt.Color;
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
	 * @param board main board
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
	public int getNumberInput(int min, int max)
	{
		String input;
		Scanner s;

		boolean isValid = false;

		do
		{
			s = new Scanner(System.in);
			input = s.next();

			if(!Game.isNumeric(input))
			{
				continue;
			}
			if(Integer.parseInt(input) > max)
			{
				continue;
			}
			if(Integer.parseInt(input) < min)
			{
				continue;
			}

			isValid = true;
		}
		while(!isValid);

		return Integer.parseInt(input);
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
	 * Check whether the card played can be put on game or not
	 * @param board fr.umlv.hanabi.Board where the game is played on
	 * @param card fr.umlv.hanabi.cards.Card played
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
	 * Drawing a card from the deck to the player's hand.
	 * Won't take any card if the deck is empty.
	 * @param board fr.umlv.hanabi.Board where the game is played on
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
	 * Put the card on game if allowed and draw a card from the deck
	 * @param board fr.umlv.hanabi.Board where the game is played on
	 * @param index Index of the card to be discarded, chosen by the player
	 * @param colorStr fr.umlv.hanabi.Player's input
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

	/**
	 * Discards the card from the player's hand.
	 * Add this card to the discard deck.
	 * @param board fr.umlv.hanabi.Board where the game is played on
	 * @param index Index of the card to be discarded, chosen by the player
	 */
	public void discardAndDraw(Board board, int index)
	{
		board.getDiscard().addCardToDiscard(hand.getCard(index));

		draw(board);
	}

	/**
	 * Give a hint to the chosenPlayer.
	 * hintType 0 if the player wants to give a numeric hint or 1 if the player wants to give a color hint.
	 * The player only has to give a number or a color (depending on the hintType) and they will be assigned to the right cards.	 *
	 * Let the player retry if the hint he wants to give cannot be put anywhere.
	 * @param chosenPlayer Player to be given the hint to
	 * @param hintType Color or Number (given as an int)
	 */
	public void giveAHint(Player chosenPlayer, int hintType)
	{
		boolean isHintValid = false;

		while(!isHintValid)
		{
			if(hintType == 0)
			{
				System.out.println("Which number do you want to give as a hint ?");

				int numberHint = getNumberInput(1, 5);
				for(Card card : chosenPlayer.getHand().getDeck())
				{
					if(card.getNumber() == numberHint)
					{
						card.setNumberHint(numberHint);
						isHintValid = true;
					}
				}
			}
			else
			{
				System.out.println("Which color do you want to give as a hint ?\n\tb = blue\t\tr = red\t\tg = green\t\ty = yellow\t\tw = white\n");
				String colorHint = getColorInput();

				for(Card card : chosenPlayer.getHand().getDeck())
				{
					if(card.getColor().equals(stringToColor(colorHint)))
					{
						card.setColorHint(stringToColor(colorHint));
						isHintValid = true;
					}
				}
			}
		}
	}

	/**
	 * Getter function of Player's hand
	 * @return Hand
	 */
	public Hand getHand()
	{
		return this.hand;
	}

	/**
	 * Show player's hand with hidden cards and their hints
	 */
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