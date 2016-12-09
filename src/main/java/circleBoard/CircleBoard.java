package main.java.circleBoard;

import java.awt.Point;
import java.util.ArrayList;

import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.game.MovesTable;
import main.java.game.Player;
import main.java.game.Settings;
import main.java.game.MovesTable.castling;
import main.java.pieces.King;
import main.java.pieces.Pawn;
import main.java.pieces.Piece;

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
		settings.renderLabels = false;
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

		if (x > 2 * getRadius() || y > 2 * getRadius()) // test if click is out
														// of chessboard
		{
			System.out.println("click out of chessboard.");
			return null;
		}

		int cx = getRadius(), cy = getRadius(), hi = get_square_height();
		double ri = Math.sqrt(Math.pow((cx - x), 2) + Math.pow((cy - y), 2));

		double ai = 0;
		if (x > cx && y < cy) {

			ai = Math.toDegrees(Math.asin((x - cx) / ri));
		} else if (x > cx && y > cy) {
			ai = 90 + Math.toDegrees(Math.acos((x - cx) / ri));
		} else if (x < cx && y > cy) {
			ai = 180 + Math.toDegrees(Math.asin((cx - x) / ri));
		} else if (x < cx && y < cy) {
			ai = 270 + Math.toDegrees(Math.acos((cx - x) / ri));
		}

		double square_x = (ai / 15);// count which field in X was
									// clicked

		double square_y = (cy - ri) / hi;// count which field
											// in Y
											// was
		Square result;
		try {
			result = initial.squares[(int) square_x][(int) square_y];
			System.out.println("square_x: " + square_x + " square_y: " + square_y + " \n"); // 4tests
			return result;

		} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
			System.out.println("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc);
			return null;
		}
	}

	@Override
	public void select(Square sq) {
		this.display.activeSquare = sq;
		this.display.active_x_square = sq.getPozX();
		this.display.active_y_square = sq.getPozY();
		System.out.println("active_x: " + this.display.active_x_square + " active_y: " + this.display.active_y_square);// 4tests
		display.repaint();
	}

	@Override
	public void unselect() {
		this.display.active_x_square = -1;
		this.display.active_y_square = -1;
		this.display.activeSquare = null;
		display.repaint();
	}

	@Override
	public void setPieces(String places, Player[] players) {
		// plWhite.setGoDown(true);
		initial.setPieces(places, players);

	}

	@Override
	public int get_height(boolean b) {
		if (this.settings.renderLabels) {
			return board_layout.image.getHeight(null) + display.upDownLabel.getHeight(null);
		}
		return board_layout.image.getHeight(null);
	}

	@Override
	public Square[][] getSquares() {
		return initial.squares;
	}

	@Override
	public void move(Square begin, Square end) {
		// TODO Auto-generated method stub

		if (begin.piece instanceof Pawn) {
			Pawn movedPawn = (Pawn) begin.piece;
			if (movedPawn.getSquare().getPozY() == 5 && end.getPozY() == 5)
				movedPawn.passedCenter = true;
		}

		begin.piece.setSquare(end);// set square of piece to ending
		end.piece = begin.piece;// for ending square set piece from beginning
								// square
		// Check if pawn passed square

		begin.piece = null;// make null piece for begining square
		this.unselect();// unselect square
		display.repaint();

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
		return display.activeSquare;
	}

	@Override
	public void setActiveSquare(Square sq) {
		// TODO Auto-generated method stub
		display.activeSquare = sq;
	}

	@Override
	public King getKing(Player player) {
		if (player.getColor().equals(Player.colors.white)) {
			return initial.kingWhite;
		} else if (player.getColor().equals(Player.colors.black)) {
			return initial.kingBlack;
		} else if (player.getColor().equals(Player.colors.blue)) {
			return initial.kingBlue;
		}
		return null;
	}

	@Override
	public Piece getTwoSquareMovedPawn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChessboardDisplay getDisplay() {
		return display;
	}

	@Override
	public int get_square_height() {
		return (getRadius() - getRadius() / 3) / 6;
	}

	public int getRadius() {
		return board_layout.image.getHeight(null) / 2;
	}

}
