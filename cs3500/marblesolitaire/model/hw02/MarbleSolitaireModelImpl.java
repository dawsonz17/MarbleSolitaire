package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

/**
 * This class represents the implementation of the MarbleSolitaireModel Interface. Each
 * instantiation of a MarbleSolitaireModelImpl represents one game of marble solitatire.
 */

public class MarbleSolitaireModelImpl extends AbstractMarbleSolitaireModel {

  /**
   * This constructor makes a model with arm length of 3 and empty slot in the middle.
   */
  public MarbleSolitaireModelImpl() {
    super(3, 3, 3);
  }

  /**
   * This constructor makes a model with arm length of 3 and empty slot in the given row and
   * column.
   *
   * @param sRow Start Row of empty Slot.
   * @param sCol Start Col of Empty Slot.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * This Constructor makes a model with the given arm length and empty slot in the center.
   *
   * @param armLength ArmLength of board.
   */
  public MarbleSolitaireModelImpl(int armLength) {
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
  public MarbleSolitaireModelImpl(int armLength, int sRow, int sCol) {
    super(armLength, sRow, sCol);
  }

  /**
   * Makes a fake model that is already finished.
   * @param s String to show this should be fake
   */
  public MarbleSolitaireModelImpl(String s) {
    super(s, 3, 3, 3);
  }
}
