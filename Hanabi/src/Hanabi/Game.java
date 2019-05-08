package Hanabi;

import java.util.Scanner;

public class Game
{
	public static void turn(Board board) {

		Player currentPlayer = whoseTurn(board);

		System.out.println(currentPlayer + "'s turn !\nCheck other players' hand :\n");
		board.showOtherPlayersHand(currentPlayer);

		System.out.println("1 = play a card\t\t2 = discard\t\t3 = give a hint");

		Scanner s = new Scanner(System.in);
		int x = s.nextInt();

		switch(x) {
			case 1:
				System.out.println("you chose to play a card!");
				play(board, currentPlayer);
				break;

			case 2:
				System.out.println("you chose to discard !");
				discard(board, currentPlayer);
				break;

			case 3:
				System.out.println("you chose to give a hint !");
				hint(board, currentPlayer);
				break;

			default:
				System.out.println("you tried to break the game, you chose to loose your turn ¯\\_(ツ)_/¯");
				break;
		}
	}

	private static Player whoseTurn(Board board)
	{
		return board.getPlayers().get(board.getTurn()%board.getPlayers().size());
	}

	private static void play(Board board, Player player)
	{
		System.out.println("Choose which card you are going to play :");
		int x = player.getIndexInput();

		System.out.println("Where are you going to put it ?\nb = blue\t\tr = red\t\tg = green\t\ty = yellow\t\tw = white\n");
		String color = player.getColorInput();

		player.playAndDraw(board, x, color);
	}

	private static void discard(Board board, Player player)
	{
		try
		{
			board.earnBlueToken();
		}
		catch (Exception e)
		{
			System.out.println("You've got already all the blue tokens !");
		}

		System.out.println("Choose which card you are going to discard :");
		int x = player.getIndexInput();

		player.discardAndDraw(board, x);
	}

	private static void hint(Board board, Player player)
	{
		try
		{
			board.payWithBlueToken();
		}
		catch (Exception e)
		{
			System.out.println("cannot give a hint ! try another move");
			board.previousTurn();
		}

		System.out.println("Hints are not implemented in code yet ! Just tell it for now");
		//player.giveAHint(board, playerWanted)
	}
}
