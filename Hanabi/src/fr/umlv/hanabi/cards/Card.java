package fr.umlv.hanabi.cards;

import java.awt.Color;

/**
 * All variables and methods directly linked to a single card
 */
public class Card {
	private final Color color;
	private final int number;

	//private boolean hasHint;

	private Color colorHint;
	private int numberHint;

	/**
	 * Constructor
	 * @param color Card's color
	 * @param number Card's number
	 */
	public Card(Color color, int number)
	{
		this.color = color;
		this.number = number;

		this.colorHint = Color.BLACK;
		this.numberHint = 0;
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
	 * Return a string of the color, in upper case if it's the actual color of the Card, in lowerCase if it's only a hint
	 * @param color Color given as a hint
	 * @param isHint is the color we want to display or not
	 * @return String
	 */
	private String getColor(Color color, boolean isHint)
	{
		String myColor;

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

		if(!isHint)
		{
			return myColor;
		}
		else
		{
			return myColor.toLowerCase();
		}
	}

	public void setNumberHint(int numberHint)
	{
		this.numberHint = numberHint;
	}

	public void setColorHint(Color colorHint)
	{
		this.colorHint = colorHint;
	}

	/**
	 * Display an understandable representation of the card, with its color and its number
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder cardStr = new StringBuilder();

		cardStr.append("[");
		cardStr.append(getColor(color, false));
		cardStr.append(", ");
		cardStr.append(number);
		cardStr.append("]");

		return cardStr.toString();
	}

	/**
	 * Show the hints given to the card (nothing if there is no hint)
	 * @return String
	 */
	public String showHint()
	{
		StringBuilder hintStr = new StringBuilder();

		hintStr.append(" ");
		if(colorHint == Color.BLACK)
		{
			hintStr.append(" ");
		}
		else
		{
			hintStr.append(getColor(colorHint, true));
		}

		hintStr.append(" ");
		if(numberHint == 0)
		{
			hintStr.append(" ");
		}
		else
		{
			hintStr.append(numberHint);
		}

		hintStr.append(" ");

		return hintStr.toString();
	}

	/**
	 * Display a hidden card
	 * @return String
	 */
	public String hiddenCardToString()
	{
		return "[ H ]";
	}
}
