package main.java.movesInCircleBoard;

import java.util.ArrayList;

import main.java.board.IMove;
import main.java.board.Square;
import main.java.pieces.Piece;
import main.java.pieces.PieceFactory.PieceType;

public class DragonMovesInCircleBoard implements IMove {

	private int fireLoader = 1;
	
	public int getFireLoader() {
		return fireLoader;
	}

	public void increaseFireLoader(){
		fireLoader++;
	}
	
	private void regularMove(Piece piece, ArrayList<Square> list) {
		int x = piece.getPosX(), y = piece.getPosY();
		for (int i = x - fireLoader; i <= x + fireLoader; i++) {
			for (int j = y - fireLoader; j <= y + fireLoader; j++) {
				int posi = i;
				if(posi<0){
					posi+=24;
				}else if(posi>23){
					posi-=24;
				}
				if (!piece.pieceBehaviour.isout(posi, j)) {// out of bounds
														// protection
					Square sq = piece.getSquare(posi, j);
					if (piece.getSquare() == sq) {// if we're checking square on
													// which is King
						continue;
					} else {
						Square moveSq = piece.getSquare(posi, j);
						if (moveSq.getPiece()!=null && piece.pieceBehaviour.otherOwner(posi, j)&& !moveSq.getPiece().getType().equals(PieceType.King) ) {
								list.add(sq);}
						}
					}
				}
			}
		}


	public ArrayList<Square> getMoves(Piece piece, boolean ignoreKing) {
		ArrayList<Square> list = new ArrayList<>();
		regularMove(piece, list);
		return list;
	}
}
