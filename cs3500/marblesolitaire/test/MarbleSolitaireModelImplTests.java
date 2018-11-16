package cs3500.marblesolitaire.test;

import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;


import static org.junit.Assert.assertEquals;

/**
 * This clas tests the original marble solitaire.
 */
public class MarbleSolitaireModelImplTests {

  private MarbleSolitaireModelImpl basic = new MarbleSolitaireModelImpl();
  private MarbleSolitaireModelImpl basic03 = new MarbleSolitaireModelImpl(0, 2);
  private MarbleSolitaireModelImpl five = new MarbleSolitaireModelImpl(5);
  private MarbleSolitaireModelImpl five05 = new MarbleSolitaireModelImpl(5, 0, 4);
  private MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl("fake");
  private MarbleSolitaireModelImpl one = new MarbleSolitaireModelImpl(1);



  //Constructor failures

  //tests to show you cannot have an even arm length
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl(4);
  }

  //shows you cannot place the empty slot on an invalid position
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl(5, 1, 1);
  }

  //shows you cannot have an even arm length in complex constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl(4, 5, 5);
  }

  //shows you cannot place empty slot in an invalid position for armLength 3 in bottom right.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl(7, 7);
  }

  //shows you cannot place empty slot in an invalid position for armLength 3 in top right.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl(1, 7);
  }

  //shows you cannot place empty slot in an invalid position for armLength 3 in bottom left.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor6() {
    MarbleSolitaireModelImpl fake = new MarbleSolitaireModelImpl(7, 1);
  }


  //Test game state and initial constructors

  //draw the first image of no variable constructor.
  @Test
  public void testGetGameState1() {
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", basic.getGameState());
  }


  //draws the first image of arm length 3 and new position of empty slot.
  @Test
  public void testGameState2() {
    assertEquals("    _ O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", basic03.getGameState());
  }

  //draws the first image of a 5 by 5 board with center empty slot.
  @Test
  public void testGameState3() {
    assertEquals("        O O O O O\n" +
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
            "        O O O O O", five.getGameState());
  }

  //draws first image of 5 by 5 board with empty slot not in center.
  @Test
  public void testGameState4() {
    assertEquals("        _ O O O O\n" +
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
            "        O O O O O", five05.getGameState());
  }


  //draws the first image of a 1 by 1 board.
  @Test
  public void testGameState5() {
    assertEquals("_", one.getGameState());
  }

  //draws the game state of a winning board.
  @Test
  public void testGameState8() {
    assertEquals("    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _", fake.getGameState());
  }




  //////Test GetScore

  //get score of initial 3 by 3 board with center empty slot.
  @Test
  public void testGetScore1() {
    assertEquals(32, basic.getScore());
  }

  //get score of initial 3 by 3 board with not center empty slot.
  @Test
  public void testGetScore2() {
    assertEquals(32, basic03.getScore());
  }

  //get score of initial 5 by 5 board with center empty slot.
  @Test
  public void testGetScore3() {
    assertEquals(104, five.getScore());
  }

  //get score of initial 5 by 5 board with not center empty slot.
  @Test
  public void testGetScore4() {
    assertEquals(104, five05.getScore());
  }

  //get score of initial 1 x 1 board with center empty slot.
  @Test
  public void testGetScore5() {
    assertEquals(0, one.getScore());
  }



  //get score of winning 3 by 3 board
  @Test
  public void testGetScore6() {
    assertEquals(1, fake.getScore());
  }


  //Test isGameOver

  // is the game over for the initial 3 by 3 game
  @Test
  public void testIsGameOver1() {
    assertEquals(false, basic.isGameOver());
  }



  //is the game over for the winning game.
  @Test
  public void testIsGameOver2() {
    assertEquals(true, fake.isGameOver());
  }



  //Test move

  //move to the left
  @Test
  public void testMove1() {
    basic.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", basic.getGameState());
  }

  // move to the right
  @Test
  public void testMove2() {
    basic.move(3, 5, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", basic.getGameState());
  }

  //move up
  @Test
  public void testMove3() {
    basic.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", basic.getGameState());
  }

  //move down
  @Test
  public void testMove4() {
    basic.move(5, 3, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O", basic.getGameState());
  }


  //meant to show you cannot jump two or more spaces to the right
  @Test (expected = IllegalArgumentException.class)
  public void testMove10() {
    basic.move(3,3,3,6);
  }

  //meant to show you cannot jump two or more spaces to the left
  @Test (expected = IllegalArgumentException.class)
  public void testMove11() {
    basic.move(3,5,3,2);
  }

  //meant to show you cannot jump two or more spaces to the bottom
  @Test (expected = IllegalArgumentException.class)
  public void testMove12() {
    basic.move(3,3,6,3);
  }

  //meant to show you cannot jump two or more spaces to the top
  @Test (expected = IllegalArgumentException.class)
  public void testMove13() {
    basic.move(3,3,1,3);
  }

  //cannot move from an empty slot.
  @Test (expected = IllegalArgumentException.class)
  public void testMove14() {
    basic.move(3,3,3,5);
  }

  //cannot move to a non-empty slot
  @Test (expected = IllegalArgumentException.class)
  public void testMove15() {
    basic.move(3,1,5,3);
  }

  //cannot jump over an empty slot
  @Test (expected = IllegalArgumentException.class)
  public void testMove16() {
    basic.move(3,2,3,4);
  }

  //cannot jump diagonally
  @Test (expected = IllegalArgumentException.class)
  public void testMove17() {
    basic.move(3,3,5,5);
  }


  //cannot move to space next to you
  @Test(expected = IllegalArgumentException.class)
  public void testMove5() {
    basic.move(1,3,3,4);
  }

  //cannot move to a non-existant space
  @Test(expected = IllegalArgumentException.class)
  public void testMove6() {
    basic.move(1,3,3,76);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove7() {
    basic.move(1,3,76,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove8() {
    basic.move(1,76,3,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove9() {
    basic.move(76,3,3,4);
  }

}
