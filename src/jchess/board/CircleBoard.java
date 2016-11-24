package jchess.board;

import javax.swing.JOptionPane;

import jchess.game.Player;
import jchess.game.Settings;

public class CircleBoard implements IChessboard {

	private BoardLayout board_layout;
	private CircleBoardDisplay board_display;
	private Square[][] squares;
	private Square activeSquare;
	

	public CircleBoard(Settings settings) {

		board_layout = new BoardLayout("circle_chessboard.png", "circle_sel_square.png", "circle_able_square.png");
		board_display = new CircleBoardDisplay(board_layout, settings.renderLabels);
		
	}

	public void initializeBoard(Player[] players, String places){
		
		squares = CircleBoardInitialization.initializeCircleBoard(this, players, places);
		board_display.setSquares(squares);
	}

	/**
	 * method to get reference to square from given x and y integers
	 * 
	 * @param x
	 *            x position on chessboard
	 * @param y
	 *            y position on chessboard
	 * @return reference to searched square
	 */

	public Square getSquare(int x, int y) {

		if (board_display.renderLabels) {
			x -= board_display.getUpDownLabelHeight();
			y -= board_display.getUpDownLabelHeight();
		}

		if (x > 2 * board_display.radius || y > 2 * board_display.radius) // test if click is out of chessboard
		{
			System.out.println("click out of chessboard.");
			return null;
		}
		
		int cx= board_display.radius, cy= board_display.radius;
		
		double ri = Math.sqrt(Math.pow((cx - x), 2) + Math.pow((cy - y), 2));
		double xi = (double) (x - cx);

		double ai = Math.toDegrees(Math.acos(xi / ri));

		double square_x = 6 - (ai / 15);// count which field in X was
										// clicked

		double square_y = (board_display.radius - ri) / board_display.square_height;// count which field in Y was
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
			result = this.squares[(int) square_x - 1][(int) square_y - 1];
			System.out.println("square_x: " + square_x + " square_y: " + square_y + " \n"); // 4tests
			return result;
			
		} catch (java.lang.ArrayIndexOutOfBoundsException exc) {
			System.out.println("!!Array out of bounds when getting Square with Chessboard.getSquare(int,int) : " + exc);
			return null;
		}

	}
	
    /** Method selecting piece in chessboard
     * @param  sq square to select (when clicked))
     */
    public void select(Square sq)
    {
        this.activeSquare = sq;
        board_display.setActiveSquare(sq);
        System.out.println("active_x: " + this.activeSquare.getPozX() + " active_y: " + this.activeSquare.getPozY());//4tests
        board_display.repaint();

    }/*--endOf-select--*/

    /** Method set variables active_x_square & active_y_square
     * to 0 values.
     */
    public void unselect()
    {
    	this.activeSquare = null;
    	board_display.setActiveSquare(this.activeSquare);
        board_display.repaint();
    }/*--endOf-unselect--*/

	@Override
	public JOptionPane getBoardDisplay() {
		// TODO Auto-generated method stub
		return board_display;
	}

	@Override
	public Square getActiveSquare() {
		// TODO Auto-generated method stub
		return activeSquare;
	}

	@Override
	public IBoardDisplay getBoardDisplay2() {
		// TODO Auto-generated method stub
		return board_display;
	}

	@Override
	public Square[][] getSquares() {
		// TODO Auto-generated method stub
		return squares;
	}

	@Override
	public CircleBoardDisplay getBoardDisplay3() {
		// TODO Auto-generated method stub
		return board_display;
	}


	

}