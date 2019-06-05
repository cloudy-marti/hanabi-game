package Hanabi.Main;

import Hanabi.Board;
import Hanabi.Game;
import Hanabi.Player;

import java.util.Scanner;

/**
 * Main class which calls the board's constructor and the main game's loop.
 */
public class Main {

	public static void main(String[] args) {

		System.out.println("how many players ?");

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
			if(Integer.parseInt(input) > 5)
			{
				continue;
			}
			if(Integer.parseInt(input) < 2)
			{
				continue;
			}

			isValid = true;
		}
		while(!isValid);

		int x = Integer.parseInt(input);

		Board board = new Board();
		board.setNumberOfPlayers(x);

		System.out.println("players names :");

		for(int i=0; i<x; i++){
			String name= s.next();
			board.getPlayers().add(new Player(name, board));
		}

		boolean game = true;
		int turnsLeft = board.getNumberOfPlayers();

		/**
		 * Game loop
		 */
		while(game)
		{
			System.out.println("\n--------- Current status of board ---------" + board + "-------------------------------------------");

			Game.turn(board);
			board.nextTurn();

			/**
			 * Game ending's condition (1) = The main board's deck is empty.
			 * Let the players play one more time each before finishing the game.
			 */
			if(board.getDeck().size() == 0)
			{
				System.out.println("woops deck is empty");
				turnsLeft--;

				if(turnsLeft <= 0)
				{
					game = false;
				}
			}

			/**
			 * Game ending's condition (2) = 3 red tokens earned.
			 * Losing condition.
			 */
			if(board.getRedToken() >= 3)
			{
				System.out.println("woops too many errors you are dead");
				game = false;
			}
		}

	}
}
