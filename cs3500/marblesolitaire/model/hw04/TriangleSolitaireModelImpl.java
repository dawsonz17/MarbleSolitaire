package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.GameSlot;

public class TriangleSolitaireModelImpl extends AbstractMarbleSolitaireModel {


  /**
   * This constructor makes a model with arm length of 3 and empty slot in the middle.
   */
  public TriangleSolitaireModelImpl() {
    super(5, 0, 0);
  }

  /**
   * This constructor makes a model with arm length of 3 and empty slot in the given row and
   * column.
   *
   * @param sRow Start Row of empty Slot.
   * @param sCol Start Col of Empty Slot.
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    super(5, sRow, sCol);
  }

  /**
   * This Constructor makes a model with the given arm length and empty slot in the center.
   *
   * @param armLength ArmLength of board.
   */
  public TriangleSolitaireModelImpl(int armLength) {
    super(armLength, 0, 0);
  }

  /**
   * This constrctor makes a model with the given arm lenght and start row and column for the empty
   * slot.
   *
   * @param armLength Arm length of board.
   * @param sRow      Start row of empty slot.
   * @param sCol      Start column of empty slot.
   */
  public TriangleSolitaireModelImpl(int armLength, int sRow, int sCol) {
    super(armLength, sRow, sCol);
  }

  /**
   * Makes a fake model that is already finished.
   * @param s String to show this should be fake
   */
  public TriangleSolitaireModelImpl(String s) {
    super(s, 5, 0, 0);
  }



  /**
   * Makes the 2D array of the board for a model.
   * @param sideLength The length of the arm of the board
   * @param startR The starting row of the empty slot
   * @param startC The starting column of the empty slot
   */
  @Override
  void makeBoard(int sideLength, int startR, int startC) {

    if (sideLength < 1) {
      throw new IllegalArgumentException("Dimension must be positive");
    }


    if (!(isOnBoard(sideLength, startR, startC))) {
      throw new IllegalArgumentException("Invalid empty cell position (r,c)");
    }


    this.board = new ArrayList<ArrayList<GameSlot>>();

    for (int r = 0; r < sideLength; r++) {
      ArrayList<GameSlot> temp = new ArrayList<GameSlot>();
      for (int c = 0; c <= r; c++) {
        if ((r == startR) && (c == startC)) {
          temp.add(new GameSlot(false, false));
        } else {
          temp.add(new GameSlot(true, false));
        }
      }
      this.board.add(temp);
    }
  }


  /**
   * Makes the 2D array of the board for a model but for testing.
   * @param sideLength The length of the arm of the board
   * @param startR The starting row of the empty slot
   * @param startC The starting column of the empty slot
   */
  @Override
  void makeBoard2(int sideLength, int startR, int startC) {

    if (sideLength < 1) {
      throw new IllegalArgumentException("Dimension must be positive");
    }


    if (!(isOnBoard(sideLength, startR, startC))) {
      throw new IllegalArgumentException("Invalid empty cell position (r,c)");
    }


    this.board = new ArrayList<ArrayList<GameSlot>>();

    for (int r = 0; r < sideLength; r++) {
      ArrayList<GameSlot> temp = new ArrayList<GameSlot>();
      for (int c = 0; c <= r; c++) {
        if ((r == startR) && (c == startC)) {
          temp.add(new GameSlot(true, false));
        } else {
          temp.add(new GameSlot(false, false));
        }
      }
      this.board.add(temp);
    }
  }


  /**
   * Is the given slot on the board.
   * @param sideLength Length of an arm
   * @param r the row of the slot being checked
   * @param c the column of the slot being checked
   * @return Is the given slot on the board?
   */
  @Override
  boolean isOnBoard(int sideLength, int r, int c) {
    return ((r >= c) && (r >= 0) && (c >= 0) && (r < sideLength));
    //&& (r < sideLength) && (c < sideLength)
  }



  /**
   * Is the proposed move possible for this board.
   * @param fromRow from row of move
   * @param fromCol from col of move
   * @param toRow to row of move
   * @param toCol to column of move
   * @param jumpR the row of the jumped slot
   * @param jumpC column of jumped slot
   * @return Is the proposed move possible for this board?
   * @throws IllegalArgumentException If the move is invalid
   */
  @Override
  boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, int jumpR, int jumpC)
          throws IllegalArgumentException {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0
            || fromRow >= this.board.size() || toRow >= this.board.size()
            || fromCol >= this.board.get(fromRow).size() || toCol >= this.board.get(toRow).size()
            || this.board.get(fromRow).get(fromCol).image().equals(" ")
            || this.board.get(toRow).get(toCol).image().equals(" ")) {
      throw new IllegalArgumentException("Both fromRow and toRow must be valid move locations");
    } else if (!((((fromRow + toRow) % 2) == 0 && ((fromCol + toCol) % 2) == 0)
            && ((fromRow - toRow) * (fromCol - toCol) >= 0))
            || ((Math.abs(fromRow - toRow) > 2)) || (Math.abs(fromCol - toCol) > 2)) {
      //allows to check for moves
      // diagonally as well as vertically and horizontally
      throw new IllegalArgumentException("From and to must be two spaces apart " +
              "in a valid move direction");
    } else if (!(this.board.get(fromRow).get(fromCol).image().equals("O"))) {
      throw new IllegalArgumentException("The from location must have a marble.");
    } else if (!(this.board.get(toRow).get(toCol).image().equals("_"))) {
      throw new IllegalArgumentException("The to location must be empty.");
    } else if (!(this.board.get(jumpR).get(jumpC).image().equals("O"))) {
      throw new IllegalArgumentException("You must jump over a marble");
    } else {
      return true;
    }
  }



  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {

    //iterate over rows
    for (int r = 0; r < this.board.size(); r++) {

      //iterate over columns
      for (int c = 0; c < this.board.get(r).size(); c++) {

        int fromRow = r;
        int fromCol = c;
        int toRow = 0;
        int toCol = 0;


        //iterate over all 6 possible moves from one space.
        for (int i = 0; i < 6; i++) {
          if (i == 0) {
            toRow = r + 2;
            toCol = c;
          } else if (i == 1) {
            toRow = r - 2;
            toCol = c;
          } else if (i == 2) {
            toRow = r;
            toCol = c + 2;
          } else if (i == 3) {
            toRow = r;
            toCol = c - 2;
          } else if (i == 4) {
            toRow = r - 2;
            toCol = c - 2;
          } else {
            toRow = r + 2;
            toCol = c + 2;
          }

          if (toRow < 0) {
            toRow = 0;
          }

          if (toCol < 0) {
            toCol = 0;
          }

          if (toRow >= this.board.size()) {
            toRow = this.board.size() - 1;
          }

          if (toCol >= this.board.get(r).size()) {
            toCol = this.board.get(r).size() - 1;
          }

          int jumpR = (fromRow + toRow) / 2;
          int jumpC = (fromCol + toCol) / 2;

          try {
            isValidMove(fromRow, fromCol, toRow, toCol, jumpR, jumpC);
            return false;
          } catch (IllegalArgumentException e) {
            //Illegal moves are expected and okay when checking all possible moves on a board
          }
        }
      }
    }
    return true;
  }


  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, X or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String getGameState() {
    String result = "";


    //iterate over rows
    for (int r = 0; r < this.board.size(); r++) {
      String temp1 = "";
      String temp2 = "";

      //make a padding of spaces for the beginning of each row
      for (int i = 0; i < board.size(); i++) {
        temp2 = temp2 + " ";
      }

      //iterate over columns
      for (int c = 0; c < this.board.get(r).size(); c++) {
        //if the cell is in the area that should be printed(not top left or right) add the image
        temp1 = temp1 + this.board.get(r).get(c).image();
        temp2 = temp2.substring(0, temp2.length() - 1);


        //if it is not the end of a column, add a space.
        if (c != this.board.get(r).size() - 1) {
          temp1 = temp1 + " ";
        }
      }
      //add a new line and padding of spaces
      result = result + temp2 + temp1.trim() + "\n";
    }

    //remove the last now line and return
    result = result.substring(0, result.length() - 1);
    return result;
  }
}
