package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.io.IOException;
import java.util.Scanner;


/**
 * This class represents the implementation of the MarbleSolitaireController Interface. Each
 * instantiation of a MarbleSolitaireControllerImpl represents one controller for the game Marble
 * Solitaire.
 */

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {


  private Appendable ap;
  private Scanner scanner;


  /**
   * Constructor for a controller.
   *
   * @param rd readable object
   * @param ap appendable object
   * @throws IllegalArgumentException if either rd or ap are null
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {

    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable cannot be null.");
    }

    this.ap = ap;
    this.scanner = new Scanner(rd);


  }


  /**
   * Runs marble solitaire using the given model.
   *
   * @param model the model that will be run on to make the game playable.
   * @throws IllegalArgumentException if model is null
   * @throws IllegalStateException    if Readable ends without the game ending or quitting
   */
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {


    //checks for nullity of model
    if (model == null) {
      throw new IllegalArgumentException("Model must be non-null.");
    }


    //print initial board
    this.print("Score: " + model.getScore() + "\n" + model.getGameState() + "\n");


    if (model.isGameOver()) {
      this.print("Game over!\n" + model.getGameState() + "\nScore: " + model.getScore());
      return;
    }

    this.makeMoves(model);
  }


  /**
   * Processes the readable and makes given moves until game is over.
   *
   * @param model The model that will be run
   */
  private void makeMoves(MarbleSolitaireModel model) {
    //make as many moves as you want until game is over or press q to quit
    while (!(model.isGameOver())) {

      //initial values for a move (will fail if not filled with valid inputs)
      int[] input = new int[]{-1, -1, -1, -1};

      for (int i = 0; i < 4; i++) {
        if (this.scanner.hasNext()) {
          String temp = this.scanner.next();
          if (temp.equals("q") || temp.equals("Q")) { //check for quit
            //react to quit
            this.print("Game quit!\nState of game when quit:\n" + model.getGameState()
                    + "\nScore: " + model.getScore());
            return; //end method
          } else if (isValidInput(temp)) { //check if its an integer
            //add integer to the list if its an integer
            input[i] = Integer.parseInt(temp);
          } else {
            i--; //subtract one if its not a valid input
          }
        } else {
          try {
            //try to make move
            model.move(input[0] - 1, input[1] - 1, input[2] - 1, input[3] - 1);
          } catch (IllegalArgumentException exception) {
            //if the move is invalid and game is over end game otherwise throw illegal state
            if (model.isGameOver()) {
              this.print("Game over!\n" + model.getGameState() + "\nScore: " + model.getScore());
              return;
            } else {
              throw new IllegalStateException("Readable is empty");
            }
          }
        }
      }

      try {
        model.move(input[0] - 1, input[1] - 1, input[2] - 1, input[3] - 1); //attempt move
      } catch (IllegalArgumentException exception) {
        this.print(exception.getMessage() + "\n"); //if move is invalid
      }

      if (model.isGameOver()) {
        this.print("Game over!\n" + model.getGameState() + "\nScore: " + model.getScore());
        return;
      }

      //print score and game state again
      this.print("Score: " + model.getScore() + "\n" + model.getGameState() + "\n");
    }
  }


  /**
   * Checks to see if the String is a valid input (integer) for the mvoe method.
   *
   * @param s String s is to be checked if it is an Integer
   */
  private boolean isValidInput(String s) {
    try {
      Integer.parseInt(s); //is the string an integer
    } catch (NumberFormatException exception) {
      return false; //return false if not
    }
    return true; //return true if it is an integer
  }

  /**
   * Appends s to the appendable.
   *
   * @param s Append s to the appendable
   */
  private void print(String s) {
    try {
      this.ap.append(s); //attempt to append
      System.out.println(s);
    } catch (IOException exception) {
      //throw illegal state if cannot be appended.
      throw new IllegalStateException("Appendable object is not functioning properly");
    }
  }
}
