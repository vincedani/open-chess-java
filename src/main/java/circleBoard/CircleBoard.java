package main.java.circleBoard;

import java.awt.Point;

import javax.swing.JOptionPane;

import main.java.LogToFile;
import main.java.board.ChessboardDisplay;
import main.java.board.ChessboardLayout;
import main.java.board.IChessboard;
import main.java.board.Square;
import main.java.game.Player;
import main.java.movesInCircleBoard.DragonMovesInCircleBoard;
import main.java.movesInCircleBoard.PawnMovesInCircleBoard;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory;
import main.java.pieces.PieceFactory.PieceType;

/**
 * Class to represent a Circle Chessboard for a three player Chess game. It
 * contains 24 x 6 Squares
 */

public class CircleBoard implements IChessboard {

	ChessboardLayout board_layout = new ChessboardLayout("circle_chessboard.png", "sel_circle.png", "able_circle.png");
	CircleBoardInitialization initial;
	private CircleBoardDisplay display;

	public CircleBoard() {

		initial = new CircleBoardInitialization(this);
		display = new CircleBoardDisplay(null, null, new Point(0, 0), this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Use transformation of x and y to polar coordinates to obtain the x and y
	 * indexes of the Square. 
	 */

	public Square getSquareFromCoordinates(int x, int y) {

		if (x > 2 * getRadius() || y > 2 * getRadius()) {
			LogToFile.log(null, "INFO", "click out of chessboard.");
			return null;
		}

		int cx = getRadius();
		int cy = getRadius();
		int hi = getSquareHeight();
		double ri = Math.sqrt(Math.pow(cx - x, 2) + Math.pow(cy - y, 2));

		// Calculate the angle depending on the quadrant
		double ai = calculateAngle(x, y, cx, cy, ri);

		double squareX = ai / 15;

		double squareY = (cy - ri) / hi;

		Square result;
		try {
			result = getSquareFromIndexes((int) squareX,(int) squareY);
			return result;

		} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
			LogToFile.log(exc, "ERROR", "Array out of bounds when getting Square with Chessboard.getSquare(int,int)");
			return null;
		}
	}

	private double calculateAngle(int x, int y, int cx, int cy, double ri) {
		if (x > cx && y < cy) {
			return  Math.toDegrees(Math.asin((x - cx) / ri));
		} else if (x > cx && y > cy) {
			return 90 + Math.toDegrees(Math.acos((x - cx) / ri));
		} else if (x < cx && y > cy) {
			return 180 + Math.toDegrees(Math.asin((cx - x) / ri));
		} else if (x < cx && y < cy) {
			return 270 + Math.toDegrees(Math.acos((cx - x) / ri));
		}
		return 0;

	}

	public void select(Square sq) {
		this.display.setActiveSquare(sq);
		LogToFile.log(null, "INFO",
				"active_x: " + this.display.getActiveSquare().getPosX() + " active_y: " + this.display.getActiveSquare().getPosY());
		display.repaint();
	}

	public void unselect() {
		this.display.setActiveSquare(null);
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

		// Check if pawn passed the center
		if (begin.getPiece().getType().equals(PieceType.Pawn)) {
			Piece movedPawn = begin.getPiece();
			if (movedPawn.getPosY() == 5 && end.getPosY() == 5) {
				PawnMovesInCircleBoard pawnBeh = (PawnMovesInCircleBoard) movedPawn.getMoveBehaviour();
				pawnBeh.passCenter();
			}
		}

		// Check if first move
		if (!begin.getPiece().wasMoved()) {
			begin.getPiece().setWasMoved(true);
		}

		LogToFile.log(null, "INFO", begin.getPiece().getType().toString() + " moved from " + begin.getPosX() + ","
				+ begin.getPosY() + " to " + end.getPosX() + " , " + end.getPosY());

		if (end.getPiece() != null) {
			LogToFile.log(null, "INFO", begin.getPiece().getType().toString() + " " + begin.getPiece().getPlayer().getColor()
					+ " taked " + end.getPiece().getType().toString() + " " + end.getPiece().getPlayer().getColor());

		}

		if (end.getPiece() != null && begin.getPiece().getType().equals(PieceType.Rook)) {
			// Create Dragon if a rook captures a piece
			JOptionPane.showMessageDialog(null, "The princess " + end.getPiece().getType().toString() + " "
					+ end.getPiece().getPlayer().getColor()
					+ " is captured, if the dragon is defeated, you will get a queen. Be careful with the dragon, although he can only be used 3 times, he can throw fire to all the pieces around him, and with each move, his power grows. Good luck! ");
			end.setPiece(PieceFactory.createSpecificPieceForCircleBoard(this, begin.getPiece().getPlayer(), PieceType.Dragon));
			end.getPiece().setSquare(end);
			begin.setPiece(null);
		} else if (begin.getPiece().getType().equals(PieceType.Dragon) && end.getPiece() != null) {
			// The Dragon throws fire to the captured piece, but remains in his
			// original place
			Piece dragon = begin.getPiece();
			DragonMovesInCircleBoard dragonBeh = (DragonMovesInCircleBoard) dragon.getMoveBehaviour();
			dragonBeh.increaseFireLoader();

			end.setPiece(null);

			// Check if the dragon has made all his moves, if so bring the rook
			// back
			if (dragonBeh.getFireLoader() == 4) {
				JOptionPane.showMessageDialog(null, "Nobody defeated the dragon!");
				begin.setPiece(PieceFactory.createSpecificPieceForCircleBoard(this, begin.getPiece().getPlayer(),
						PieceType.Rook));
				begin.getPiece().setSquare(begin);
			}

		} else if (end.getPiece() != null && end.getPiece().getType().equals(PieceType.Dragon)) {
			// The active player defeated the dragon, and earn a queen
			Piece warrior = begin.getPiece();
			JOptionPane.showMessageDialog(null,
					begin.getPiece().getPlayer().getName() + " defeated the dragon! Your have earned a queen");
			warrior.setSquare(end);
			end.setPiece(warrior);
			begin.setPiece(PieceFactory.createSpecificPieceForCircleBoard(this, begin.getPiece().getPlayer(),
					PieceType.Queen));
			begin.getPiece().setSquare(begin);

		} else {
			// Regular move
			begin.getPiece().setSquare(end);
			end.setPiece(begin.getPiece());
			begin.setPiece(null);
		}
		this.unselect();
		display.repaint();

	}

	@Override
	public Square getActiveSquare() {
		return display.getActiveSquare();
	}

	@Override
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
	public int getSquareHeight() {
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

	@Override
	public Square getSquareFromIndexes(int i, int j) {
		return initial.getSquares()[i][j];
	}

	@Override
	public Piece getKing(Player player) {
		if (player.getColor().equals(Player.colors.white)) {
			return initial.kingWhite;
		} else if (player.getColor().equals(Player.colors.black)) {
			return initial.kingBlack;
		} else if (player.getColor().equals(Player.colors.blue)) {
			return initial.kingBlue;
		}
		return null;
	}

	/*
	 * public IKing getKing(Player player) { if
	 * (player.getColor().equals(Player.colors.white)) { return (IKing)
	 * initial.kingWhite.pieceBehaviour; } else if
	 * (player.getColor().equals(Player.colors.black)) { return (IKing)
	 * initial.kingBlack.pieceBehaviour; } else if
	 * (player.getColor().equals(Player.colors.blue)) { return (IKing)
	 * initial.kingBlue.pieceBehaviour; } return null; }
	 */
}
