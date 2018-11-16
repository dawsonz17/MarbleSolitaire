package cs3500.marblesolitaire;

import java.io.InputStreamReader;


import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

/**
 * This class represents a game of marble solitaire.
 */
public final class MarbleSolitaire {

  /**
   * Main method runs game given the arguments to manufacture the model.
   *
   * @param args String of arguments used to construct game
   */
  public static void main(String[] args) {


    String type = "";
    String size = "";
    String r = "";
    String c = "";
    int count = 0;

    for (String s : args) {

      if (s.equals("european") || s.equals("english") || s.equals("triangular")) {
        type = s;
      }

      if (s.equals("-size")) {
        size = args[count + 1];
      }

      if (s.equals("-hole")) {
        r = args[count + 1];
        c = args[count + 2];
      }

      count++;
    }

    MarbleSolitaireModel model;
    if (type.equals("english")) {
      if (size.equals("") && r.equals("")) {
        //MarbleSolitaireModelImpl
        model = new MarbleSolitaireModelImpl();
      } else if (!size.equals("") && r.equals("")) {
        //MarbleSolitaireModelImpl
        model = new MarbleSolitaireModelImpl(Integer.parseInt(size));
      } else if (size.equals("") && !r.equals("")) {
        //MarbleSolitaireModelImpl
        model = new MarbleSolitaireModelImpl(Integer.parseInt(r) - 1, Integer.parseInt(c) - 1);
      } else {
        //MarbleSolitaireModelImpl
        model = new MarbleSolitaireModelImpl(Integer.parseInt(size),
                Integer.parseInt(r) - 1, Integer.parseInt(c) - 1);
      }
    } else if (type.equals("european")) {
      if (size.equals("") && r.equals("")) {
        //EuropeanSolitaireModelImpl
        model = new EuropeanSolitaireModelImpl();
      } else if (!size.equals("") && r.equals("")) {
        //EuropeanSolitaireModelImpl
        model = new EuropeanSolitaireModelImpl(Integer.parseInt(size));
      } else if (size.equals("") && !r.equals("")) {
        //EuropeanSolitaireModelImpl
        model = new EuropeanSolitaireModelImpl(Integer.parseInt(r) - 1, Integer.parseInt(c) - 1);
      } else {
        //EuropeanSolitaireModelImpl
        model = new EuropeanSolitaireModelImpl(Integer.parseInt(size),
                Integer.parseInt(r) - 1, Integer.parseInt(c) - 1);
      }
    } else {
      if (size.equals("") && r.equals("")) {
        //TriangleSolitaireModelImpl
        model = new TriangleSolitaireModelImpl();
      } else if (!size.equals("") && r.equals("")) {
        //TriangleSolitaireModelImpl
        model = new TriangleSolitaireModelImpl(Integer.parseInt(size));
      } else if (size.equals("") && !r.equals("")) {
        //TriangleSolitaireModelImpl
        model = new TriangleSolitaireModelImpl(Integer.parseInt(r) - 1, Integer.parseInt(c) - 1);
      } else {
        //TriangleSolitaireModelImpl
        model = new TriangleSolitaireModelImpl(Integer.parseInt(size),
                Integer.parseInt(r) - 1, Integer.parseInt(c) - 1);
      }
    }

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(new InputStreamReader(System.in),
                    new StringBuilder());

    controller.playGame(model);
  }
}

