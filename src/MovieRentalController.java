import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Program Name: MovieRentalController.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalController
{
  private MovieRentalView theView;
  private MovieRentalModel theModel;

  MovieRentalController()
  {
    theView = new MovieRentalView();
    theModel = new MovieRentalModel();
    theView.addAddActorButtonListener(new addActorListener());
  }

  private class addActorListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        System.out.println("abc");
        String fName = theView.tflFirstname.getText();
        String lName = theView.tflLastname.getText();
        String stmt = "INSERT INTO Actor (first_name, last_name) " +
                      "VALUES ( '" + fName + "', '" + lName + "');";
        theModel.addActor(stmt);
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }
  }
}
