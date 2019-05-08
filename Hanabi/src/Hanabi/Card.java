package Hanabi;

import java.awt.Color;

public class Card {
	private final Color color;
	private final int number;
	
	public Card(Color color, int number)
	{
		this.color = color;
		this.number = number;
	}

	public Color getColor()
	{
		return color;
	}

	public int getNumber()
	{
		return number;
	}

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
