import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program Name: MovieRentalController.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalController {

  private MovieRentalView theView;
  private MovieRentalModel theModel;

  MovieRentalController(MovieRentalView view, MovieRentalModel model){
    theView = view;
    theModel = model;
    view.addAddActorButtonListener(new addActorListener());
  }

  private class addActorListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("Test");
    }
  }
}
