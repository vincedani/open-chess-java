package main.java.circleBoard;

import java.awt.Point;

import main.java.Constants;
import main.java.LogToFile;
import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInCircleBoard.DragonMovesInCircleBoard;
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.movesInCircleBoard.RookMovesInCircleBoard;
import main.java.pieces.ConcretePieceFactory;
import main.java.pieces.King;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;

/**
 * Class to represent a Circle Chessboard for a three player Chess game. It
 * contains 24 x 6 Squares
 */

public class CircleBoard implements IChessboard {

	ChessboardLayout board_layout = new ChessboardLayout("circle_chessboard.png", "sel_circle.png", "able_circle.png");
	public CircleBoardInitialization initial;
	private CircleBoardDisplay display;

	public CircleBoard() {

		initial = new CircleBoardInitialization(this);
		display = new CircleBoardDisplay(null, null, new Point(0, 0), this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Use transformation of x and y to polar coordinates to obtain the x and y
	 * indexes of the Square. The angle is calculated based on the corresponding
	 * quadrant
	 */

	public Square getSquare(int x, int y) {

		if (x > 2 * getRadius() || y > 2 * getRadius()) // test if click is out
														// of chessboard
		{
			LogToFile.log(null, "INFO", "click out of chessboard.");
			return null;
		}

		int cx = getRadius();
		int cy = getRadius();
		int hi = get_square_height();
		double ri = Math.sqrt(Math.pow((cx - x), 2) + Math.pow((cy - y), 2));

		double ai = 0;
		// Calculate the angle depending on the quadrant
		if (x > cx && y < cy) {
			ai = Math.toDegrees(Math.asin((x - cx) / ri));
		} else if (x > cx && y > cy) {
			ai = 90 + Math.toDegrees(Math.acos((x - cx) / ri));
		} else if (x < cx && y > cy) {
			ai = 180 + Math.toDegrees(Math.asin((cx - x) / ri));
		} else if (x < cx && y < cy) {
			ai = 270 + Math.toDegrees(Math.acos((cx - x) / ri));
		}

		double square_x = (ai / 15);

		double square_y = (cy - ri) / hi;

		Square result;
		try {
			result = initial.squares[(int) square_x][(int) square_y];
			return result;

		} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
			LogToFile.log(exc, "ERROR", "Array out of bounds when getting Square with Chessboard.getSquare(int,int)");
			return null;
		}
	}

	public void select(Square sq) {
		this.display.activeSquare = sq;
		this.display.active_x_square = sq.getPozX();
		this.display.active_y_square = sq.getPozY();
		LogToFile.log(null, "INFO",
				"active_x: " + this.display.active_x_square + " active_y: " + this.display.active_y_square);
		display.repaint();
	}

	public void unselect() {
		this.display.active_x_square = -1;
		this.display.active_y_square = -1;
		this.display.activeSquare = null;
		display.repaint();
	}

	public void setPieces(Player[] players) {

		initial.setPieces(players);

	}

	public int get_height() {

		return board_layout.image.getHeight(null);
	}

	public Square[][] getSquares() {
		return initial.squares;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * If the piece is a pawn checks if it has passed the center so that the
	 * regular move is changed from forward to backward in the y axis.
	 * 
	 */
	public void move(Square begin, Square end) {

		ConcretePieceFactory piceFac = new ConcretePieceFactory();
		
		// Check if pawn passed the center
		if (begin.piece.getName().equals("Pawn")) {
			Piece movedPawn = begin.piece;
			if (movedPawn.getPozY() == 5 && end.getPozY() == 5) {
				PawnMovesInCircleBoard pawnBeh = (PawnMovesInCircleBoard) movedPawn.getMoveBehaviour();
				pawnBeh.passCenter();
			}
		}

		// Check if first move
		if (!begin.piece.wasMoved()) {
			begin.piece.setWasMoved(true);
		}

		LogToFile.log(null, "INFO", begin.piece.getName() + " moved from " + begin.getPozX() + "," + begin.getPozY()
				+ " to " + end.getPozX() + " , " + end.getPozY());

		if (end.piece != null) {
			LogToFile.log(null, "INFO", begin.piece.getName() + " " + begin.piece.getPlayer().getColor() + " taked "
					+ end.piece.getName() + " " + end.piece.getPlayer().getColor());

		}

		if (end.piece != null && begin.piece.getName().equals("Rook")) {
			System.out.println(
					"The princess " + end.piece.getName() + " " + end.piece.getPlayer().getColor() + " is captured");
			//end.piece = PieceFactory.releaseTheDragon(this, begin.piece.getPlayer());			
			end.piece = piceFac.GetPieceForCircleBoard(Constants.Symbols.Dragon.toString(),Constants.Pieces.Dragon.toString(), this, begin.piece.getPlayer());
			end.piece.setSquare(end);
			begin.piece = null;
		} else if (begin.piece.getName().equals("Dragon") && end.piece != null) {
			Piece dragon = begin.piece;
			DragonMovesInCircleBoard dragonBeh = (DragonMovesInCircleBoard) dragon.getMoveBehaviour();
			dragonBeh.increaseFireLoader();

			end.piece = null;

			if (dragonBeh.getFireLoader() == 4) {
				System.out.println("Nobody defeated the dragon!");
				//begin.piece = PieceFactory.createRookInCircleBoard(this, begin.piece.getPlayer());
				begin.piece = piceFac.GetPieceForCircleBoard(Constants.Symbols.Rook.toString(),Constants.Pieces.Rook.toString(), this, begin.piece.getPlayer());
				begin.piece.setSquare(begin);
			}
		} else if (end.piece != null && end.piece.getName().equals("Dragon")) {
			Piece warrior = begin.piece;
			System.out.println("YOU defeated the dragon! Your princess is now a queen");
			warrior.setSquare(end);
			end.piece = warrior;

			//begin.piece = PieceFactory.createQueenInCircleBoard(this, begin.piece.getPlayer());
			begin.piece = piceFac.GetPieceForCircleBoard(Constants.Symbols.Queen.toString(),Constants.Pieces.Queen.toString(), this, begin.piece.getPlayer());
			begin.piece.setSquare(begin);
		} else {
			begin.piece.setSquare(end);// set square of piece to ending
			end.piece = begin.piece;// for ending square set piece from
			// beginning square
			begin.piece = null;// make null piece for beginning square
		}
		this.unselect();// unselect square
		display.repaint();

	}

	/**
	 * {@inheritDoc}
	 * 
	 * Not implemented
	 * 
	 */
	@Override
	public boolean undo() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Not implemented
	 * 
	 */
	@Override
	public boolean redo() {
		// TODO Auto-generated method stub
		return false;
	}

	public Square getActiveSquare() {
		return display.activeSquare;
	}

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

	public ChessboardDisplay getDisplay() {
		return display;
	}

	/**
	 * Method calculating the square height of the circle board, based on the
	 * actual radius.
	 * 
	 * @return the square height of the CircleBoard
	 * 
	 */
	public int get_square_height() {
		return (getRadius() - getRadius() / 3) / 6;
	}

	/**
	 * Method calculating the radius of the circle board, based on the board
	 * layout height.
	 * 
	 * @return the radius of the CircleBoard
	 * 
	 */
	public int getRadius() {
		return board_layout.image.getHeight(null) / 2;
	}

}
