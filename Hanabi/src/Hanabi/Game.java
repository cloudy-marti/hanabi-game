package Hanabi;

import java.util.Scanner;

/**
 * Gameflow Manager - Will manage the turns and call the moves chosen by the players
 */
public class Game
{
	/**
	 * Main turn manager.
	 * This function allows the players to choose which move they want to do in their turn.
	 * @param board Main board of the game
	 */
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

	/**
	 * Calculates whose player is the current turn using the number of the turn.
	 * @param board
	 * @return Player
	 */
	private static Player whoseTurn(Board board)
	{
		return board.getPlayers().get(board.getTurn()%board.getPlayers().size());
	}

	/**
	 * When the player chooses to play a card and put it on the board
	 * @param board
	 * @param player
	 */
	private static void play(Board board, Player player)
	{
		System.out.println("Choose which card you are going to play :");
		player.showOwnHand();
		int x = player.getIndexInput();

		System.out.println("Where are you going to put it ?\nb = blue\t\tr = red\t\tg = green\t\ty = yellow\t\tw = white\n");
		String color = player.getColorInput();

		player.playAndDraw(board, x, color);
	}

	/**
	 * When the player chooses to discard a card
	 * @param board
	 * @param player
	 */
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
		player.showOwnHand();
		int x = player.getIndexInput();

		player.discardAndDraw(board, x);
	}

	/**
	 * When the player chooses to give a hint to another player.<p>
	 *     <i>Function not fully implemented yet.</i>
	 * </p>
	 * The hint is given only if there is at least one blue token.
	 * If there are not enough token, the function catches an exception and allows the player to choose another move by decreasing the turn number.
	 * @param board
	 * @param player
	 */
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

		//System.out.println("Hints are not implemented in code yet ! Just tell it for now");

		System.out.println("Who are you going to give a hint to ?");

		//String playerName = player.getNameOfPlayerInput();
		Player playerWanted = board.getPlayerByName();
		System.out.println("You selected " + playerWanted + " !");

		player.giveAHint(board, playerWanted);
	}
}
