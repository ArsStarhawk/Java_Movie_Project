import javax.swing.*;
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
    createSelectionsForGenerateReportPane();
  }

  private class addActorListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        String fName = theView.tflFirstname.getText();
        String lName = theView.tflLastname.getText();
        String stmt = "INSERT INTO Actor (first_name, last_name) " +
                      "VALUES ( '" + fName + "', '" + lName + "');";
        theModel.addActor(stmt);
        JOptionPane pane = new JOptionPane("Actor " + fName + " " + lName + " added to the database");
        JDialog dialog = pane.createDialog("Database Updated");
        dialog.show();
      } catch (SQLException exception) {
        System.out.println(exception.getMessage());
        JOptionPane pane = new JOptionPane("Not able to add actor to the database");
        JDialog dialog = pane.createDialog("Database not Updated");
        dialog.show();
      }
      theView.tflFirstname.requestFocus();
      theView.tflFirstname.setText("");
      theView.tflLastname.setText("");
    }
  }

  private void createSelectionsForGenerateReportPane(){
    theModel.getAllCategories();
    theModel.getAllStores();
    theModel.getUpdteDate();

    //theView.cbCategory
  }
}
