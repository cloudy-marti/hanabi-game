package Hanabi.Main;

import Hanabi.Board;
import Hanabi.Game;
import Hanabi.Player;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("how many players ?");
		Scanner s;
		int x;

		do
		{
			s = new Scanner(System.in);
			x = s.nextInt();
		}
		while(x < 2 || x > 5);

		Board board = new Board();
		board.setNumberOfPlayers(x);

		System.out.println("players names :");

		for(int i=0; i<x; i++){
			String name= s.next();
			board.getPlayers().add(new Player(name, board));
		}

		boolean game = true;
		int turnsLeft = board.getNumberOfPlayers();

		while(game)
		{
			System.out.println("\n--------- Current status of board ---------" + board + "-------------------------------------------");

			Game.turn(board);
			board.nextTurn();

			if(board.getDeck().size() == 0)
			{
				System.out.println("woops deck is empty");
				turnsLeft--;

				/**
				 * Let the players play one more time before finishing the game.
				 */
				if(turnsLeft <= 0)
				{
					game = false;
				}
			}

			if(board.getRedToken() >= 3)
			{
				System.out.println("woops too many errors you ded");
				game = false;
			}
		}

	}
}
