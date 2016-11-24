/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package jchess.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;

import jchess.JChessApp;
import jchess.game.MovesTable;
import jchess.game.MovesTable.castling;
import jchess.game.Player;
import jchess.game.Settings;
import jchess.gui.GUI;
import jchess.pieces.Bishop;
import jchess.pieces.King;
import jchess.pieces.Knight;
import jchess.pieces.Move;
import jchess.pieces.Pawn;
import jchess.pieces.Piece;
import jchess.pieces.Queen;
import jchess.pieces.Rook;

/**
 * Class to represent chessboard. Chessboard is made from squares. It is setting
 * the squars of chessboard and sets the pieces(pawns) witch the owner is
 * current player on it.
 */
public class Chessboard extends JPanel {

	public Square squares[][];// squares of chessboard

	public Square activeSquare;

	protected int active_x_square;
	protected int active_y_square;

	// public Graphics graph;
	public static final int img_x = 5;// image x position (used in JChessView
										// class!)
	public static final int img_y = img_x;// image y position (used in
											// JChessView class!)
	public static final int img_widht = 480;// image width
	public static final int img_height = img_widht;// image height

	private ArrayList moves;
	private Settings settings;

	public King kingWhite;
	public King kingBlack;
	// -------- for undo ----------
	private Square undo1_sq_begin = null;
	private Square undo1_sq_end = null;
	private Piece undo1_piece_begin = null;
	private Piece undo1_piece_end = null;
	private Piece ifWasEnPassant = null;
	private Piece ifWasCastling = null;
	private boolean breakCastling = false; // if last move break castling
	// ----------------------------
	// For En passant:
	// |-> Pawn whose in last turn moved two square
	public static Pawn twoSquareMovedPawn = null;
	public static Pawn twoSquareMovedPawn2 = null;
	private MovesTable moves_history;

	/**
	 * Chessboard class constructor
	 * 
	 * @param settings
	 *            reference to Settings class object for this chessboard
	 * @param moves_history
	 *            reference to Moves class object for this chessboard
	 */
	public Chessboard(Settings settings, MovesTable moves_history) {
		this.setSettings(settings);

		this.activeSquare = null;
		this.active_x_square = -1;
		this.active_y_square = -1;

		this.moves_history = moves_history;
		this.setDoubleBuffered(true);

	}/*--endOf-Chessboard--*/

	/**
	 * Method selecting piece in chessboard
	 * 
	 * @param sq
	 *            square to select (when clicked))
	 */
	public void select(Square sq) {
		this.activeSquare = sq;
		this.active_x_square = sq.getPozX();
		this.active_y_square = sq.getPozY();
		System.out.println("active_x: " + this.active_x_square + " active_y: " + this.active_y_square);// 4tests
		repaint();

	}/*--endOf-select--*/

	/**
	 * Method set variables active_x_square & active_y_square to 0 values.
	 */
	public void unselect() {
		this.active_x_square = -1;
		this.active_y_square = -1;
		this.activeSquare = null;
		repaint();
	}/*--endOf-unselect--*/
	/*
	public void move(Square begin, Square end) {
		move(begin, end, true);
	}

	/**
	 * Method to move piece over chessboard
	 * 
	 * @param xFrom
	 *            from which x move piece
	 * @param yFrom
	 *            from which y move piece
	 * @param xTo
	 *            to which x move piece
	 * @param yTo
	 *            to which y move piece
	 */
	/*
	public void move(int xFrom, int yFrom, int xTo, int yTo) {
		Square fromSQ = null;
		Square toSQ = null;
		try {
			fromSQ = this.squares[xFrom][yFrom];
			toSQ = this.squares[xTo][yTo];
		} catch (java.lang.IndexOutOfBoundsException exc) {
			System.out.println("error moving piece: " + exc);
			return;
		}
		this.move(this.squares[xFrom][yFrom], this.squares[xTo][yTo], true);
	}
	/*
	public void move(Square begin, Square end, boolean refresh) {
		this.move(begin, end, refresh, true);
	}

	/**
	 * Method move piece from square to square
	 * 
	 * @param begin
	 *            square from which move piece
	 * @param end
	 *            square where we want to move piece *
	 * @param refresh
	 *            chessboard, default: true
	 */
	/*
	public void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory) {

		castling wasCastling = MovesTable.castling.none;
		Piece promotedPiece = null;
		boolean wasEnPassant = false;
		if (end.piece != null) {
			end.piece.setSquare(null);
		}

		Square tempBegin = new Square(begin);// 4 moves history
		Square tempEnd = new Square(end); // 4 moves history
		// for undo
		undo1_piece_begin = begin.piece;
		undo1_sq_begin = begin;
		undo1_piece_end = end.piece;
		undo1_sq_end = end;
		ifWasEnPassant = null;
		ifWasCastling = null;
		breakCastling = false;
		// ---

		twoSquareMovedPawn2 = twoSquareMovedPawn;

		begin.piece.setSquare(end);// set square of piece to ending
		end.piece = begin.piece;// for ending square set piece from beginin
								// square
		begin.piece = null;// make null piece for begining square

		if (end.piece.getName().equals("King")) {
			if (!((King) end.piece).wasMotion) {
				breakCastling = true;
				((King) end.piece).wasMotion = true;
			}

			// Castling
			if (begin.getPozX() + 2 == end.getPozX()) {
				move(squares[7][begin.getPozY()], squares[end.getPozX() - 1][begin.getPozY()], false, false);
				ifWasCastling = end.piece; // for undo
				wasCastling = MovesTable.castling.shortCastling;
				// this.moves_history.addMove(tempBegin, tempEnd,
				// clearForwardHistory, wasCastling, wasEnPassant);
				// return;
			} else if (begin.getPozX() - 2 == end.getPozX()) {
				move(squares[0][begin.getPozY()], squares[end.getPozX() + 1][begin.getPozY()], false, false);
				ifWasCastling = end.piece; // for undo
				wasCastling = MovesTable.castling.longCastling;
				// this.moves_history.addMove(tempBegin, tempEnd,
				// clearForwardHistory, wasCastling, wasEnPassant);
				// return;
			}
			// endOf Castling
		} else if (end.piece.getName().equals("Rook")) {
			if (!((Rook) end.piece).wasMotion()) {
				breakCastling = true;
				((Rook) end.piece).setWasMotion(true);
			}
		} else if (end.piece.getName().equals("Pawn")) {
			if (twoSquareMovedPawn != null && squares[end.getPozX()][begin.getPozY()] == twoSquareMovedPawn.getSquare()) // en
																															// passant
			{
				ifWasEnPassant = squares[end.getPozX()][begin.getPozY()].piece; // for
																				// undo

				tempEnd.piece = squares[end.getPozX()][begin.getPozY()].piece; // ugly
																				// hack
																				// -
																				// put
																				// taken
																				// pawn
																				// in
																				// en
																				// passant
																				// plasty
																				// do
																				// end
																				// square

				squares[end.getPozX()][begin.getPozY()].piece = null;
				wasEnPassant = true;
			}

			if (begin.getPozY() - end.getPozY() == 2 || end.getPozY() - begin.getPozY() == 2) // moved
																								// two
																								// square
			{
				breakCastling = true;
				twoSquareMovedPawn = (Pawn) end.piece;
			} else {
				twoSquareMovedPawn = null; // erase last saved move (for En
											// passant)
			}

			if (end.piece.getSquare().getPozY() == 0 || end.piece.getSquare().getPozY() == 7) // promote
																								// Pawn
			{
				if (clearForwardHistory) {
					String color;
					if (end.piece.getPlayer().getColor() == Player.colors.white) {
						color = "W"; // promotionWindow was show with pieces in
										// this color
					} else {
						color = "B";
					}

					String newPiece = JChessApp.getJcv().showPawnPromotionBox(color); // return
																						// name
																						// of
																						// new
																						// piece

					if (newPiece.equals("Queen")) // transform pawn to queen
					{
						Queen queen = new Queen(this, end.piece.getPlayer());
						queen.setChessboard(end.piece.getChessboard());
						queen.setPlayer(end.piece.getPlayer());
						queen.setSquare(end.piece.getSquare());
						end.piece = queen;
					} else if (newPiece.equals("Rook")) // transform pawn to
														// rook
					{
						Rook rook = new Rook(this, end.piece.getPlayer());
						rook.setChessboard(end.piece.getChessboard());
						rook.setPlayer(end.piece.getPlayer());
						rook.setSquare(end.piece.getSquare());
						end.piece = rook;
					} else if (newPiece.equals("Bishop")) // transform pawn to
															// bishop
					{
						Bishop bishop = new Bishop(this, end.piece.getPlayer());
						bishop.setChessboard(end.piece.getChessboard());
						bishop.setPlayer(end.piece.getPlayer());
						bishop.setSquare(end.piece.getSquare());
						end.piece = bishop;
					} else // transform pawn to knight
					{
						Knight knight = new Knight(this, end.piece.getPlayer());
						knight.setChessboard(end.piece.getChessboard());
						knight.setPlayer(end.piece.getPlayer());
						knight.setSquare(end.piece.getSquare());
						end.piece = knight;
					}
					promotedPiece = end.piece;
				}
			}
		} else if (!end.piece.getName().equals("Pawn")) {
			twoSquareMovedPawn = null; // erase last saved move (for En passant)
		}
		// }

		if (refresh) {
			this.unselect();// unselect square
			repaint();
		}

		if (clearForwardHistory) {
			this.moves_history.clearMoveForwardStack();
			this.moves_history.addMove(tempBegin, tempEnd, true, wasCastling, wasEnPassant, promotedPiece);
		} else {
			this.moves_history.addMove(tempBegin, tempEnd, false, wasCastling, wasEnPassant, promotedPiece);
		}
	}/* endOf-move()- 
	
	public boolean redo() {
		return redo(true);
	}

	public boolean redo(boolean refresh) {
		if (this.getSettings().gameType == Settings.gameTypes.local) // redo
																		// only
																		// for
																		// local
																		// game
		{
			Move first = this.moves_history.redo();

			Square from = null;
			Square to = null;

			if (first != null) {
				from = first.getFrom();
				to = first.getTo();

				this.move(this.squares[from.getPozX()][from.getPozY()], this.squares[to.getPozX()][to.getPozY()], true,
						false);
				if (first.getPromotedPiece() != null) {
					Pawn pawn = (Pawn) this.squares[to.getPozX()][to.getPozY()].piece;
					pawn.setSquare(null);

					this.squares[to.getPozX()][to.getPozY()].piece = first.getPromotedPiece();
					Piece promoted = this.squares[to.getPozX()][to.getPozY()].piece;
					promoted.setSquare(this.squares[to.getPozX()][to.getPozY()]);
				}
				return true;
			}

		}
		return false;
	}

	public boolean undo() {
		return undo(true);
	}

	public synchronized boolean undo(boolean refresh) // undo last move
	{
		Move last = this.moves_history.undo();

		if (last != null && last.getFrom() != null) {
			Square begin = last.getFrom();
			Square end = last.getTo();
			try {
				Piece moved = last.getMovedPiece();
				this.squares[begin.getPozX()][begin.getPozY()].piece = moved;

				moved.setSquare(this.squares[begin.getPozX()][begin.getPozY()]);

				Piece taken = last.getTakenPiece();
				if (last.getCastlingMove() != castling.none) {
					Piece rook = null;
					if (last.getCastlingMove() == castling.shortCastling) {
						rook = this.squares[end.getPozX() - 1][end.getPozY()].piece;
						this.squares[7][begin.getPozY()].piece = rook;
						rook.setSquare(this.squares[7][begin.getPozY()]);
						this.squares[end.getPozX() - 1][end.getPozY()].piece = null;
					} else {
						rook = this.squares[end.getPozX() + 1][end.getPozY()].piece;
						this.squares[0][begin.getPozY()].piece = rook;
						rook.setSquare(this.squares[0][begin.getPozY()]);
						this.squares[end.getPozX() + 1][end.getPozY()].piece = null;
					}
					((King) moved).wasMotion = false;
					((Rook) rook).setWasMotion(false);
					this.breakCastling = false;
				} else if (moved.getName().equals("Rook")) {
					((Rook) moved).setWasMotion(false);
				} else if (moved.getName().equals("Pawn") && last.wasEnPassant()) {
					Pawn pawn = (Pawn) last.getTakenPiece();
					this.squares[end.getPozX()][begin.getPozY()].piece = pawn;
					pawn.setSquare(this.squares[end.getPozX()][begin.getPozY()]);

				} else if (moved.getName().equals("Pawn") && last.getPromotedPiece() != null) {
					Piece promoted = this.squares[end.getPozX()][end.getPozY()].piece;
					promoted.setSquare(null);
					this.squares[end.getPozX()][end.getPozY()].piece = null;
				}

				// check one more move back for en passant
				Move oneMoveEarlier = this.moves_history.getLastMoveFromHistory();
				if (oneMoveEarlier != null && oneMoveEarlier.wasPawnTwoFieldsMove()) {
					Piece canBeTakenEnPassant = this.squares[oneMoveEarlier.getTo().getPozX()][oneMoveEarlier.getTo()
							.getPozY()].piece;
					if (canBeTakenEnPassant.getName().equals("Pawn")) {
						this.twoSquareMovedPawn = (Pawn) canBeTakenEnPassant;
					}
				}

				if (taken != null && !last.wasEnPassant()) {
					this.squares[end.getPozX()][end.getPozY()].piece = taken;
					taken.setSquare(this.squares[end.getPozX()][end.getPozY()]);
				} else {
					this.squares[end.getPozX()][end.getPozY()].piece = null;
				}

				if (refresh) {
					this.unselect();// unselect square
					repaint();
				}

			} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
				return false;
			} catch (java.lang.NullPointerException exc) {
				return false;
			}

			return true;
		} else {
			return false;
		}
	}
*/
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
