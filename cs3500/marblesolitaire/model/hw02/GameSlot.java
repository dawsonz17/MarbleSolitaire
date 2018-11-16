package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents a single slot on a board in a marble solitaire game.
 */

public class GameSlot {

  private boolean containsMarble;
  private boolean outOfBounds;

  /**
   * Makes a game slot that either contains a marble or doesn't or is out of bounds of not.
   *
   * @param containsMarble Does the slot have a marble?
   * @param outOfBounds    Is the slot out of bounds?
   */

  public GameSlot(boolean containsMarble, boolean outOfBounds) {

    this.containsMarble = containsMarble;
    this.outOfBounds = outOfBounds;
  }


  /**
   * Returns the String that represents the type of piece.
   *
   * @return Returns the String that corresponds to the GameSlots variable types.
   */

  public String image() {
    if (this.outOfBounds) {
      return " ";
    } else if (this.containsMarble) {
      return "O";
    } else {
      return "_";
    }
  }


}
