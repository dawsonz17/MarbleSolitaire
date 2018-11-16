import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw02TypeChecks {

  public static void main(String[] args) {
    helper(new MarbleSolitaireModelImpl());
    helper(new MarbleSolitaireModelImpl
            (2, 2));
    helper(new MarbleSolitaireModelImpl(5));
    helper(new MarbleSolitaireModelImpl(3, 0
            , 4));
  }

  private static void helper
          (MarbleSolitaireModel model) {
    model.move(1, 3, 3, 3);
  }

}
