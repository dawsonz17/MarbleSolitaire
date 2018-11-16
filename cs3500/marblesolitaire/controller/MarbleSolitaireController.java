package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This interface represents the operations offered by the marble solitaire controller.
 * One object of the model represents one controller for the game of marble solitaire
 */

public interface MarbleSolitaireController {


  /**
   * This method allows the game to be played by entering in moves until either
   * the game ends due to lack of moves or you quit by typing 'q' or 'Q'.
   * @param model the model that will be run on to make the game playable.
   */
  void playGame(MarbleSolitaireModel model);
}
