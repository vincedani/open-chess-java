package main.java.pieces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import main.java.LogToFile;
import main.java.board.Square;
import main.java.circleBoard.CircleBoard;
import main.java.game.Settings.BoardType;
import main.java.squareBoard.SquareBoard;

public class PieceDisplay {
  IPieceDisplay display;

  public PieceDisplay(Piece piece) {
    if (piece.getChessboardType().equals(BoardType.circleBoard))
      display = new CirclePieceDisplay(piece);
    else if (piece.getChessboardType().equals(BoardType.squareBoard))
      display = new SquarePieceDisplay(piece);
  }

  public void draw(Graphics g) {
    display.draw(g);
  }

  @FunctionalInterface
  public interface IPieceDisplay {
    void draw(Graphics g);
  }

  private class CirclePieceDisplay implements IPieceDisplay {
    CircleBoard board;
    Square square;
    private PieceLayout layout;
    Piece piece;

    public CirclePieceDisplay(Piece piece) {
      board = (CircleBoard) piece.getChessboard();
      layout = piece.getLayout();
      this.piece = piece;

    }

    @Override
    public final void draw(Graphics g) {
      try {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        square = piece.getSquare();

        int r = board.getRadius();
        int hi = board.getSquareHeight();

        int rs = r - square.getPosY() * hi;
        int ri = rs - hi;
        int rm = (rs + ri) / 2;

        int ai = square.getPosX() * 15;
        int as = ai + 15;
        int am = (as + ai) / 2;

        int x = r + (int) (rm * Math.sin(Math.toRadians(am))) - hi / 2;
        int y = r - (int) (rm * Math.cos(Math.toRadians(am))) - hi / 2;

        if (layout.image != null) {
          Image tempImage = layout.orgImage;

          BufferedImage resized = new BufferedImage(hi, hi, BufferedImage.TYPE_INT_ARGB_PRE);
          Graphics2D imageGr = resized.createGraphics();
          imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          imageGr.drawImage(tempImage, 0, 0, hi, hi, null);
          imageGr.dispose();

          layout.setImage(resized.getScaledInstance(hi, hi, 0));
          g2d.drawImage(layout.image, x, y, null);
        } else {
          LogToFile.log(null, "Debug", "image is null!");
        }

      } catch (java.lang.NullPointerException exc) {
        System.out.println("Something wrong when painting piece: " + exc.getMessage());
        LogToFile.log(exc, "Error", "Something wrong when painting piece: " + exc.getMessage());
      }
    }
  }

  private class SquarePieceDisplay implements IPieceDisplay {
    SquareBoard board;
    Square square;
    private PieceLayout layout;
    Piece piece;

    public SquarePieceDisplay(Piece piece) {
      board = (SquareBoard) piece.getChessboard();
      this.piece = piece;
      layout = piece.getLayout();
    }

    /*
     * Method to draw piece on chessboard
     *
     * @graph : where to draw
     */
    @Override
    public final void draw(Graphics g) {
      try {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        square = piece.getSquare();
        Point topLeft = board.getDisplay().getTopLeftPoint();
        int height = board.get_square_height();

        int x = (square.getPosX() * height) + topLeft.x;
        int y = (square.getPosY() * height) + topLeft.y;

        if (layout.image != null) {
          Image tempImage = layout.orgImage;

          BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
          Graphics2D imageGr = resized.createGraphics();
          imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          imageGr.drawImage(tempImage, 0, 0, height, height, null);
          imageGr.dispose();

          layout.setImage(resized.getScaledInstance(height, height, 0));
          g2d.drawImage(layout.image, x, y, null);
        } else {
          LogToFile.log(null, "Debug", "image is null!");
        }

      } catch (java.lang.NullPointerException exc) {
        System.out.println("Something wrong when painting piece: " + exc.getMessage());
        LogToFile.log(exc, "Error", "Something wrong when painting piece: " + exc.getMessage());
      }
    }
  }
}
