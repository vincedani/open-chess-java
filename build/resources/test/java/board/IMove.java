package main.java.board;

import java.util.ArrayList;

import main.java.pieces.Piece;

public interface IMove {
	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing);
}
