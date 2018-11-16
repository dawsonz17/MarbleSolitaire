package cs3500.marblesolitaire.test;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;


import static org.junit.Assert.assertEquals;

/**
 * This class tests the triangle solitaire model.
 */
public class TriangleSolitaireModelImplTests {

  private TriangleSolitaireModelImpl basic = new TriangleSolitaireModelImpl();
  private TriangleSolitaireModelImpl basic21 = new TriangleSolitaireModelImpl(2, 1);
  private TriangleSolitaireModelImpl nine = new TriangleSolitaireModelImpl(9);
  private TriangleSolitaireModelImpl nine88 = new TriangleSolitaireModelImpl(9, 8, 8);
  private TriangleSolitaireModelImpl nine63 = new TriangleSolitaireModelImpl(10, 6, 3);
  private TriangleSolitaireModelImpl fake = new TriangleSolitaireModelImpl("fake");
  private TriangleSolitaireModelImpl one = new TriangleSolitaireModelImpl(1);



  //Constructor failures

  //tests to show you cannot have an length less than 1
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    TriangleSolitaireModelImpl fake = new TriangleSolitaireModelImpl(0);
  }


  //shows you cannot place the empty slot on an invalid position-right
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    TriangleSolitaireModelImpl fake = new TriangleSolitaireModelImpl(5, 1, 3);
  }

  //shows you cannot have an even arm length in complex constructor-below
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    TriangleSolitaireModelImpl fake = new TriangleSolitaireModelImpl(4, 5, 5);
  }

  //shows you cannot place empty slot in an invalid position-above
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    TriangleSolitaireModelImpl fake = new TriangleSolitaireModelImpl(-1, 4);
  }

  //shows you cannot place empty slot in an invalid position-left
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor6() {
    TriangleSolitaireModelImpl fake = new TriangleSolitaireModelImpl(4, -1);
  }


  //Test game state and initial constructors

  //draw the first image of no variable constructor.
  @Test
  public void testGetGameState1() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", basic.getGameState());
  }


  //draws the first image of size 5 with new position of empty slot.
  @Test
  public void testGameState2() {
    assertEquals("    O\n" +
            "   O O\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O", basic21.getGameState());
  }

  //draws the first image of a 9 length board with default empty slot.
  @Test
  public void testGameState3() {
    assertEquals("        _\n" +
            "       O O\n" +
            "      O O O\n" +
            "     O O O O\n" +
            "    O O O O O\n" +
            "   O O O O O O\n" +
            "  O O O O O O O\n" +
            " O O O O O O O O\n" +
            "O O O O O O O O O", nine.getGameState());
  }

  //draws first image of a board with length 9 and empty slot not in default spot.
  @Test
  public void testGameState4() {
    assertEquals("        O\n" +
            "       O O\n" +
            "      O O O\n" +
            "     O O O O\n" +
            "    O O O O O\n" +
            "   O O O O O O\n" +
            "  O O O O O O O\n" +
            " O O O O O O O O\n" +
            "O O O O O O O O _", nine88.getGameState());
  }


  //draws the first image of a 1 by 1 board.
  @Test
  public void testGameState5() {
    assertEquals("_", one.getGameState());
  }



  //draws the game state of a winning board.
  @Test
  public void testGameState6() {
    assertEquals("    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _", fake.getGameState());
  }




  //////Test GetScore

  //get score of initial 3 by 3 board with center empty slot.
  @Test
  public void testGetScore1() {
    assertEquals(14, basic.getScore());
  }

  //get score of initial 3 by 3 board with not center empty slot.
  @Test
  public void testGetScore2() {
    assertEquals(14, basic21.getScore());
  }

  //get score of initial 5 by 5 board with center empty slot.
  @Test
  public void testGetScore3() {
    assertEquals(44, nine.getScore());
  }

  //get score of initial 5 by 5 board with not center empty slot.
  @Test
  public void testGetScore4() {
    assertEquals(44, nine88.getScore());
  }

  //get score of initial 1 x 1 board with center empty slot.
  @Test
  public void testGetScore5() {
    assertEquals(0, one.getScore());
  }



  //get score of winning 5 length board
  @Test
  public void testGetScore6() {
    assertEquals(1, fake.getScore());
  }


  //Test isGameOver

  // is the game over for the initial 5 sized board game
  @Test
  public void testIsGameOver1() {
    assertEquals(false, basic.isGameOver());
  }



  //is the game over for the winning game.
  @Test
  public void testIsGameOver2() {
    assertEquals(true, fake.isGameOver());
  }

  //is a board with length 0 over?
  @Test
  public void testIsGameOver3() {
    assertEquals(true, one.isGameOver());
  }



  //Test move

  //move up
  @Test
  public void testMove1() {
    basic.move(2, 0, 0, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", basic.getGameState());
  }

  // move up
  @Test
  public void testMove2() {
    basic21.move(4, 1, 2, 1);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O _ O O\n" +
            "O _ O O O", basic21.getGameState());
  }

  //move right
  @Test
  public void testMove3() {
    nine63.move(6, 1, 6, 3);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O _ _ O O O O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", nine63.getGameState());
  }

  //move left
  @Test
  public void testMove4() {
    nine63.move(6, 5, 6, 3);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O O _ _ O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", nine63.getGameState());
  }


  //move diagonal down right
  @Test
  public void testMove5() {
    nine63.move(4, 1, 6, 3);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O _ O O O\n" +
            "    O O _ O O O\n" +
            "   O O O O O O O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", nine63.getGameState());
  }

  //move diagonal up left
  @Test
  public void testMove6() {
    nine63.move(8, 5, 6, 3);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O O O O O\n" +
            "  O O O O _ O O O\n" +
            " O O O O O _ O O O\n" +
            "O O O O O O O O O O", nine63.getGameState());
  }

  //move diagonal up left
  @Test
  public void testMove32() {
    nine63.move(4, 3, 6, 3);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O _ O\n" +
            "    O O O _ O O\n" +
            "   O O O O O O O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", nine63.getGameState());
  }

  //move diagonal up left
  @Test
  public void testMove33() {
    nine63.move(8, 5, 6, 3);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O O O O O\n" +
            "  O O O O _ O O O\n" +
            " O O O O O _ O O O\n" +
            "O O O O O O O O O O", nine63.getGameState());
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

  //meant to show you cannot jump two or more spaces to the diagonal left up
  @Test (expected = IllegalArgumentException.class)
  public void testMove20() {
    nine63.move(8, 5, 5, 2);
  }


  //meant to show you cannot jump two or more spaces to the diagonal right down
  @Test (expected = IllegalArgumentException.class)
  public void testMove21() {
    nine63.move(4, 1, 7, 4);
  }

  //meant to show you cannot jump directly above
  @Test (expected = IllegalArgumentException.class)
  public void testMove22() {
    nine63.move(2, 1, 6, 3);
  }

  //meant to show you cannot jump directly below
  @Test (expected = IllegalArgumentException.class)
  public void testMove23() {
    nine63.move(4, 2, 6, 3);
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
  public void testMove18() {
    basic.move(1,3,3,4);
  }

  //cannot move to a non-existant space
  @Test(expected = IllegalArgumentException.class)
  public void testMove19() {
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

