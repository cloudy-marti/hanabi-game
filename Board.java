import java.awt.Color;
import java.util.HashMap;


public class Board {
	private final Deck deck;
	private int Token;
	private final Discard discard;
	private int turn;
	private final HashMap<Color,Integer> board;
	
	public Board() {
		this.deck = new Deck();
		Token = 3;
		this.discard = new Discard();
		this.turn = 0;
		this.board = new HashMap<Color,Integer>();
		
	}

	@Override
	public String toString() {
		return "Board [deck=" + deck + ", Token=" + Token + ", discard="
				+ discard + ", turn=" + turn + ", board=" + board + "]";
	}	
	
}
