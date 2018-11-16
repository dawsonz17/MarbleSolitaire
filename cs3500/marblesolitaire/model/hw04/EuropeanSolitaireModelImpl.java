package cs3500.marblesolitaire.model.hw04;

public class EuropeanSolitaireModelImpl extends AbstractMarbleSolitaireModel {


  /**
   * This constructor makes a model with arm length of 3 and empty slot in the middle.
   */
  public EuropeanSolitaireModelImpl() {
    super(3, 3, 3);
  }

  /**
   * This constructor makes a model with arm length of 3 and empty slot in the given row and
   * column.
   *
   * @param sRow Start Row of empty Slot.
   * @param sCol Start Col of Empty Slot.
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * This Constructor makes a model with the given arm length and empty slot in the center.
   *
   * @param armLength ArmLength of board.
   */
  public EuropeanSolitaireModelImpl(int armLength) {
    super(armLength, armLength + ((armLength - 1) / 2) - 1,
            armLength + ((armLength - 1) / 2) - 1);
  }

  /**
   * This constrctor makes a model with the given arm lenght and start row and column for the empty
   * slot.
   *
   * @param armLength Arm length of board.
   * @param sRow      Start row of empty slot.
   * @param sCol      Start column of empty slot.
   */
  public EuropeanSolitaireModelImpl(int armLength, int sRow, int sCol) {
    super(armLength, sRow, sCol);
  }

  /**
   * Makes a fake model that is already finished.
   * @param s String to show this should be fake
   */
  public EuropeanSolitaireModelImpl(String s) {
    super(s, 3, 3, 3);
  }


  /**
   * Is the given slot on the board.
   * @param sideLength Length of an arm
   * @param r the row of the slot being checked
   * @param c the column of the slot being checked
   * @return Is the given slot on the board?
   */
  boolean isOnBoard(int sideLength, int r, int c) {
    return (Math.abs((sideLength + ((sideLength - 1) / 2) - 1) - r)
            + Math.abs((sideLength + ((sideLength - 1) / 2) - 1) - c)
            <= (sideLength + ((sideLength - 1) / 2) - 1)
            + (sideLength / 2));
  }
}
