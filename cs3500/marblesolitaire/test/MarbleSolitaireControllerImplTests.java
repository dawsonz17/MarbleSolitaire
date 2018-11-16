package cs3500.marblesolitaire.test;

import org.junit.Test;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;


/**
 * This class is used for testing.
 */
public class MarbleSolitaireControllerImplTests {

  private MarbleSolitaireModelImpl basic = new MarbleSolitaireModelImpl();
  private MarbleSolitaireModelImpl basic03 = new MarbleSolitaireModelImpl(0, 2);
  private MarbleSolitaireModelImpl five = new MarbleSolitaireModelImpl(5);
  private MarbleSolitaireModelImpl five05 = new MarbleSolitaireModelImpl(5, 0, 4);
  //private MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl("fake");
  //private MarbleSolitaireModelImpl fake2 = new MarbleSolitaireModelImpl("fake");
  private MarbleSolitaireModelImpl one = new MarbleSolitaireModelImpl(1);
  private MarbleSolitaireModelImpl basicLong = new MarbleSolitaireModelImpl();


  private StringBuilder basicAp = new StringBuilder();
  private StringBuilder basic03Ap = new StringBuilder();
  private StringBuilder fiveAp = new StringBuilder();
  private StringBuilder five05Ap = new StringBuilder();
  private StringBuilder fakeAp = new StringBuilder();
  private StringBuilder oneAp = new StringBuilder();
  private StringBuilder longAp = new StringBuilder();
  private StringBuilder test1Ap = new StringBuilder();
  private StringBuilder test2Ap = new StringBuilder();


  private Readable basicRd = new StringReader("4 2 p 4 4 q");
  private Readable basic03Rd = new StringReader("1 5 1 3 4 q");
  private Readable fiveRd = new StringReader("5 7 7 7 4 5 Q");
  private Readable five05Rd = new StringReader("3 5 1 5 2 7 2 5 1 2 3 q");
  private Readable fakeRd = new StringReader("3 4 5 6 q");
  private Readable oneRd = new StringReader("");
  private Readable longRd = new StringReader("4 4 4 7 4 6 4 3 4 4 7 4 6 4 3 4 4 4 4 6 4 1 " +
          "4 3 4 2 4 4 4 4 4 2 2 4 4 76 1 3 4 k y t q q q Q");
  private Readable exceptionExpected = new StringReader("a b c d e f g h i j k 1 2 3");
  private Readable exceptionExpected2 = new StringReader("a e i o u d a 3 4 6 a 2 3 3 3 3 3 3 6");

  private MarbleSolitaireController basicController =
          new MarbleSolitaireControllerImpl(basicRd, basicAp);
  private MarbleSolitaireController basic03Controller =
          new MarbleSolitaireControllerImpl(basic03Rd, basic03Ap);
  private MarbleSolitaireController fiveController =
          new MarbleSolitaireControllerImpl(fiveRd, fiveAp);
  private MarbleSolitaireController five05Controller =
          new MarbleSolitaireControllerImpl(five05Rd, five05Ap);
  private MarbleSolitaireController fakeController =
          new MarbleSolitaireControllerImpl(fakeRd, fakeAp);
  private MarbleSolitaireController oneController =
          new MarbleSolitaireControllerImpl(oneRd, oneAp);
  private MarbleSolitaireController longController =
          new MarbleSolitaireControllerImpl(longRd, longAp);
  private MarbleSolitaireController exceptionExpectedController =
          new MarbleSolitaireControllerImpl(exceptionExpected, test1Ap);
  private MarbleSolitaireController exceptionExpectedController2 =
          new MarbleSolitaireControllerImpl(exceptionExpected2, test2Ap);


  /*
  //test if an inverse to the most basic board with bogus input will still win
  @Test
  public void testGameOverDefaultBoardWithErrorInputsThroughController() {
    exceptionExpectedController.playGame(fake2);
    assertEquals("Score: 1\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1", test1Ap.toString());
  }*/

  //tests if a readable running out of next inputs on a non-finished
  // board will result in an illegal state
  @Test (expected = IllegalStateException.class)
  public void testReadableIllegalStateException() {
    exceptionExpectedController2.playGame(basic);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    MarbleSolitaireModelImpl nullModel = null;
    StringBuilder nullAp = null;
    Readable nullRd = null;
    MarbleSolitaireController nullReadable =
            new MarbleSolitaireControllerImpl(oneRd, oneAp);
    MarbleSolitaireController nullAppndable =
            new MarbleSolitaireControllerImpl(oneRd, oneAp);
    basicController.playGame(nullModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    MarbleSolitaireModelImpl nullModel = null;
    StringBuilder nullAp = null;
    Readable nullRd = null;
    MarbleSolitaireController nullReadable =
            new MarbleSolitaireControllerImpl(nullRd, oneAp);
    MarbleSolitaireController nullAppndable =
            new MarbleSolitaireControllerImpl(oneRd, oneAp);
    nullReadable.playGame(basic);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    StringBuilder nullAp = null;
    Readable nullRd = null;
    MarbleSolitaireController nullReadable =
            new MarbleSolitaireControllerImpl(oneRd, oneAp);
    MarbleSolitaireController nullAppndable =
            new MarbleSolitaireControllerImpl(oneRd, nullAp);
    nullAppndable.playGame(basic);
  }


  //tests move left
  @Test
  public void testMove1() {
    basicController.playGame(basic);

    assertEquals("Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", basicAp.toString());
  }


  //tests move right
  @Test
  public void testMove2() {
    basic03Controller.playGame(basic03);

    assertEquals("Score: 32\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", basic03Ap.toString());
  }


  //tests move down
  @Test
  public void testMove3() {
    fiveController.playGame(five);

    assertEquals("Score: 104\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103", fiveAp.toString());
  }

  //tests move up then left
  @Test
  public void testMove4() {
    five05Controller.playGame(five05);

    assertEquals("Score: 104\n" +
            "        _ O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "        O O O O O\n" +
            "        _ O O O O\n" +
            "        _ O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 102\n" +
            "        O O O O O\n" +
            "        O _ _ O O\n" +
            "        _ O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O _ _ O O\n" +
            "        _ O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 102", five05Ap.toString());
  }


  /*
  //tests end of game
  @Test
  public void testMove5() {
    fakeController.playGame(fake);

    assertEquals("Score: 1\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1", fakeAp.toString());
  }

  */

  //tests one length board
  @Test
  public void testMove6() {
    oneController.playGame(one);

    assertEquals("Score: 0\n" +
            "_\n" +
            "Game over!\n" +
            "_\n" +
            "Score: 0", oneAp.toString());
  }

  //tests to show all types of invalid moves and their error messages being
  //caught by try catch in the play game method.
  @Test
  public void testMove7() {
    longController.playGame(basicLong);
    assertEquals("Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "From and to must be two spaces apart, either horizontally or vertically.\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "From and to must be two spaces apart, either horizontally or vertically.\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "From and to must be two spaces apart, either horizontally or vertically.\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "From and to must be two spaces apart, either horizontally or vertically.\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "The from location must have a marble.\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "The to location must be empty.\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "You must jump over a marble\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Both fromRow and toRow must be valid move locations\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", longAp.toString());
  }
}
