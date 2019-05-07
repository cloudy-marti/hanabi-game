import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public final static ArrayList<Player> players;
	
	public static void turn() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		switch(x) {
			case 1:
				discard();
				break;
			
			case 2:
				play();
				break;
		}
	}
	

	private static void discard() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
	(players.get(board.turn%players.size()));
		
	}
	
	private static void play() {
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		for(int i=0; i<x; i++){
			String name= s.next();
			players.add(new Player(name));
		}
		
		while (true) {
			turn();
		}

	}
}
