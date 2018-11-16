package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.GameSlot;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {

  ArrayList<ArrayList<GameSlot>> board = new ArrayList<ArrayList<GameSlot>>();

  /**
   * This constructor makes a model with arm length of 3 and empty slot in the middle.
   */
  public AbstractMarbleSolitaireModel(int armLength, int startR, int startC) {
    this.makeBoard(armLength, startR, startC);
  }

  /**
   * This Constructor makes a model that only has one marble.
   */
  public AbstractMarbleSolitaireModel(String s, int armLength, int r, int c) {
    this.makeBoard2(armLength, r, c);
  }

  /**
   * Makes the 2D array of the board for a model.
   *
   * @param armLength The length of the arm of the board
   * @param startR    The starting row of the empty slot
   * @param startC    The starting column of the empty slot
   */
  void makeBoard(int armLength, int startR, int startC) {

    if (armLength % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be even");
    }

    if (!(isOnBoard(armLength, startR, startC))) {
      throw new IllegalArgumentException("Invalid empty cell position (r,c)");
    }


    this.board = new ArrayList<ArrayList<GameSlot>>();

    for (int r = 0; r < (armLength * 3) - 2; r++) {
      ArrayList<GameSlot> temp = new ArrayList<GameSlot>();
      for (int c = 0; c < (armLength * 3) - 2; c++) {
        if ((r == startR) && (c == startC)) {
          temp.add(new GameSlot(false, false));
        } else if (isOnBoard(armLength, r, c)) {
          temp.add(new GameSlot(true, false));
        } else {
          temp.add(new GameSlot(false, true));
        }
      }
      this.board.add(temp);
    }
  }


  /**
   * Makes the 2D array of the board for a model that is finished and has only one marble left. This
   * method is used for testing purposes.
   *
   * @param armLength The length of the arm of the board
   * @param startR    The starting row of the empty slot
   * @param startC    The starting column of the empty slot
   */
  void makeBoard2(int armLength, int startR, int startC) {

    if (armLength % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be even");
    }

    if (!(isOnBoard(armLength, startR, startC))) {
      throw new IllegalArgumentException("Invalid empty cell position (r,c)");
    }

    this.board = new ArrayList<ArrayList<GameSlot>>();

    for (int r = 0; r < (armLength * 3) - 2; r++) {
      ArrayList<GameSlot> temp = new ArrayList<GameSlot>();
      for (int c = 0; c < (armLength * 3) - 2; c++) {
        if ((r == startR) && (c == startC)) {
          temp.add(new GameSlot(true, false));
        } else if (isOnBoard(armLength, r, c)) {
          temp.add(new GameSlot(false, false));
        } else {
          temp.add(new GameSlot(false, true));
        }
      }
      this.board.add(temp);
    }
  }


  /**
   * Is the given slot on the board.
   *
   * @param sideLength Length of an arm
   * @param r          the row of the slot being checked
   * @param c          the column of the slot being checked
   * @return Is the given slot on the board?
   */
  //is the given slot on the board
  boolean isOnBoard(int sideLength, int r, int c) {
    return (((r >= sideLength - 1)
            && (r <= (2 * sideLength) - 2)
            && (c >= 0)
            && (c <= sideLength * 3 - 3))
            || ((c >= sideLength - 1)
            && (c <= (2 * sideLength) - 2)
            && (r >= 0)
            && (r <= (3 * sideLength) - 3)));
  }


  /**
   * The move method will not work if you try to jump more than one space in a straight direction,
   * try to jump from a empty slot, try to jump a empty slot, or try to jump onto a marble.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException If move is illegal by the stated rules above.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    int jumpR = ((fromRow + toRow) / 2);
    int jumpC = ((fromCol + toCol) / 2);


    if (isValidMove(fromRow, fromCol, toRow, toCol, jumpR, jumpC)) {
      this.board.get(fromRow).set(fromCol, new GameSlot(false, false));
      this.board.get(toRow).set(toCol, new GameSlot(true, false));
      this.board.get(jumpR).set(jumpC, new GameSlot(false, false));
    }
  }


  /**
   * Is the proposed move possible for this board.
   *
   * @param fromRow from row of move
   * @param fromCol from col of move
   * @param toRow   to row of move
   * @param toCol   to column of move
   * @param jumpR   the row of the jumped slot
   * @param jumpC   column of jumped slot
   * @return Is the proposed move possible for this board?
   */
  boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, int jumpR, int jumpC)
          throws IllegalArgumentException {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0
            || fromRow >= this.board.size() || toRow >= this.board.size()
            || fromCol >= this.board.get(fromRow).size() || toCol >= this.board.get(toRow).size()
            || this.board.get(fromRow).get(fromCol).image().equals(" ")
            || this.board.get(toRow).get(toCol).image().equals(" ")) {
      throw new IllegalArgumentException("Both fromRow and toRow must be valid move locations");
    } else if (!((((fromRow + toRow) % 2) == 0 && ((fromCol + toCol) % 2) == 0)
            && (Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2)
            && (!(Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2)))) {
      throw new IllegalArgumentException("From and to must be two spaces apart, " +
              "either horizontally or vertically.");
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


        //iterate over all 4 possible moves from one space.
        for (int i = 0; i < 4; i++) {
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
            //do not need to use expection because an invalid move is okay
            //and expected when testingall moves for a given board until a valid one is found
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
      String temp = "";
      //iterate over columns
      for (int c = 0; c < this.board.get(r).size(); c++) {
        //if the cell is in the area that should be printed(not top left or right) add the image

        temp = temp + this.board.get(r).get(c).image() + " ";
      }

      String temp2;
      try {
        if ((temp.indexOf("_") < temp.indexOf("O") && temp.contains("_")) && temp.contains("O")) {
          temp2 = temp.substring(0, (temp.indexOf("_")));
          //System.out.println("made it 1");
        } else if (temp.contains("O")) {
          temp2 = temp.substring(0, temp.indexOf("O"));
        } else {
          temp2 = temp.substring(0, (temp.indexOf("_")));
          //System.out.println("made it 2");
        }
      } catch (IndexOutOfBoundsException e) {
        temp2 = "";
        //System.out.println("made it 3");
      }

      temp = temp.trim();
      temp = temp2 + temp;

      //add a new line
      result = result + temp + "\n";
    }

    //remove the last now line and return
    result = result.substring(0, result.length() - 1);
    return result;
  }


  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int r = 0; r < board.size(); r++) {
      for (int c = 0; c < board.get(r).size(); c++) {
        if (board.get(r).get(c).image().equals("O")) {
          score++;
        }
      }
    }
    return score;
  }

}
