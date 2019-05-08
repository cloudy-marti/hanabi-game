/*
import java.util.Scanner;
public class Game
{
	public void turn(Board board) {

		Player currentPlayer = whoseTurn(board);

		System.out.println("choose 1 if you want to discard, 2 if you want to play");
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();

		switch(x) {
			case 1:
				discard(board, currentPlayer);
				break;

			case 2:
				play(board, currentPlayer);
				break;
		}
	}

	private static Player whoseTurn(Board board)
	{
		return board.getPlayers().get(board.getTurn()%board.getPlayers().size());
	}

	private static void discard(Board board, Player player) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();

		player.discardAndDraw(board, x);
	}

	private static void play(Board board, Player player)
	{
		System.out.println("doin nothing");
	}
}
*/