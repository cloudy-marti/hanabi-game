package Hanabi;

import java.awt.Color;

/**
 * All variables and methods directly linked to a single card
 */
public class Card {
	private final Color color;
	private final int number;

	/**
	 * Constructor
	 * @param color Card's color
	 * @param number Card's number
	 */
	public Card(Color color, int number)
	{
		this.color = color;
		this.number = number;
	}

	/**
	 * Getter function - Card's color
	 * @return Color
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * Getter function - Card's number
	 * @return int
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * Display an understandable representation of the card, with its color and its number
	 * @return String
	 */
	@Override
	public String toString()
	{
		String myColor = new String();

		if(color == Color.RED)
			myColor = "R";
		else if(color == Color.BLUE)
			myColor = "B";
		else if(color == Color.GREEN)
			myColor = "G";
		else if(color == Color.YELLOW)
			myColor = "Y";
		else
			myColor = "W";

		return "["+ myColor + ", " + number + "]";
	}
}
