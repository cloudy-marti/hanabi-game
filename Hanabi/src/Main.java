import java.util.Scanner;

public class Main {

	public static void turn(Board board) {

		Player currentPlayer = whoseTurn(board);

		System.out.println(currentPlayer + "'s turn");

		System.out.println("choose 1 if you want to discard, 2 if you want to play");

		Scanner s = new Scanner(System.in);
		int x = s.nextInt();

		switch(x) {
			case 1:
				System.out.println("you chose to discard");
				discard(board, currentPlayer);
				break;

			case 2:
				System.out.println("you chose to play");
				play(board, currentPlayer);
				break;
		}
	}

	private static Player whoseTurn(Board board)
	{
		return board.getPlayers().get(board.getTurn()%board.getPlayers().size());
	}

	private static void discard(Board board, Player player) {

		System.out.print("Choose which card you are going to discard :");
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();

		player.discardAndDraw(board, x);
	}

	private static void play(Board board, Player player)
	{
		System.out.println("doing nothing");
	}

	public static void main(String[] args) {

		System.out.println("how many players ?");
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();

		Board board = new Board();

		System.out.println("players names :");

		for(int i=0; i<x; i++){
			String name= s.next();
			board.getPlayers().add(new Player(name, board));
			board.setNumberOfPlayers(i);
		}

		System.out.println(board);

		boolean game = true;

		while (game) {
			turn(board);
			board.nextTurn();

			if(board.getDeck().size() == 0)
			{
				System.out.println("woops deck is empty");
				game = false;
			}
		}

	}
}
