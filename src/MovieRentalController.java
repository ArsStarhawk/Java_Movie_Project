import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program Name: MovieRentalController.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalController implements ActionListener {

  MovieRentalView theView;
  MovieRentalModel theModel;

  MovieRentalController(){
    theView = new MovieRentalView();
    theModel = new MovieRentalModel();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
