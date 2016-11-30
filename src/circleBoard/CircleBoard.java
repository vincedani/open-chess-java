package circleBoard;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import jchess.board.ChessboardDisplay;
import jchess.board.ChessboardLayout;
import jchess.board.IChessboard;
import jchess.board.IChessboardDisplay;
import jchess.board.Square;
import main.java.game.MovesTable;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.pieces.King;
import main.java.pieces.Pawn;
import main.java.pieces.Piece;
import squareBoard.SquareBoardDisplay;
import squareBoard.SquareBoardInitialization;

public class CircleBoard implements IChessboard {

	public static final int top = 0;
	public static final int bottom = 7;

	// public Graphics graph;
	ArrayList<Square> moves;
	private Settings settings;
	// -------- for undo ----------
	private Square undo1_sq_begin = null;
	private Square undo1_sq_end = null;
	private Piece undo1_piece_begin = null;
	public Piece undo1_piece_end;
	public Piece ifWasEnPassant;
	public Piece ifWasCastling;
	public boolean breakCastling;
	public MovesTable moves_history;
	// ----------------------------
	// For En passant:
	// |-> Pawn whose in last turn moved two square
	public static Pawn twoSquareMovedPawn = null;
	public static Pawn twoSquareMovedPawn2 = null;

	ChessboardLayout board_layout = new ChessboardLayout("circle_chessboard.png", "sel_circle.png", "able_circle.png");
	public CircleBoardInitialization initial;
	private CircleBoardDisplay display;
	
	public CircleBoard(Settings settings, MovesTable moves_history) {
		
		this.settings = settings;
		initial = new CircleBoardInitialization(this);
		display = new CircleBoardDisplay(null, null, new Point(0, 0), settings.renderLabels, settings.upsideDown, this);
		this.moves_history = moves_history;
	}

	@Override
	public Square getSquare(int x, int y) {
		if (display.renderLabels) {
			x -= display.getUpDownLabelHeight();
			y -= display.getUpDownLabelHeight();
		}

		if (x > 2 * display.radius || y > 2 * display.radius) // test if click is out of chessboard
		{
			System.out.println("click out of chessboard.");
			return null;
		}
		
		int cx= display.radius, cy= display.radius;
		
		double ri = Math.sqrt(Math.pow((cx - x), 2) + Math.pow((cy - y), 2));
		double xi = (double) (x - cx);

		double ai = Math.toDegrees(Math.acos(xi / ri));

		double square_x = 6 - (ai / 15);// count which field in X was
										// clicked

		double square_y = (display.radius - ri) / display.square_height;// count which field in Y was
										// clicked

		
		if (square_x > (int) square_x) // if X is more than X parsed to Integer
		{
			square_x = (int) square_x + 1;// parse to integer and increment
		}
		if (square_y > (int) square_y) // if X is more than X parsed to Integer
		{
			square_y = (int) square_y + 1;// parse to integer and increment
		}

		Square result;
		try {
			result = initial.squares[(int) square_x - 1][(int) square_y - 1];
			System.out.println("square_x: " + square_x + " square_y: " + square_y + " \n"); // 4tests
			return result;
			
		} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
			System.out.println("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc);
			return null;
		}
	}

	@Override
	public void select(Square sq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unselect() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void setPieces(String places, Player plWhite, Player plBlack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_height(boolean b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Square[][] getSquares() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(Square square, Square square2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean undo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Square getActiveSquare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActiveSquare(Square sq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public King getKing(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece getTwoSquareMovedPawn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChessboardDisplay getDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int get_square_height() {
		// TODO Auto-generated method stub
		return 0;
	}

}
