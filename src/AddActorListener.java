/**
 * File Name: AddActorListener.java
 * Coder: Sion Young
 * Date: 8/1/2020
 * Description: Action Listener class for adding actor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AddActorListener implements ActionListener
{
  private MovieRentalView theView;
  private MovieRentalModel theModel;
  private HelperMethods helper;

  public AddActorListener(MovieRentalView theView, MovieRentalModel theModel)
  {
    this.theView = theView;
    this.theModel = theModel;
    helper = new HelperMethods();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Clear")) // clear button hit
    {
      clearTextField();
    }
    else // add actor button hit
    {

        String fName = theView.tflFirstname.getText();
        String lName = theView.tflLastname.getText();
        boolean isValidFirstLastName = isValidFirstLastName(fName,lName);
        if(isValidFirstLastName)
        {
          String stmt = "INSERT INTO Actor (first_name, last_name) " +
              "VALUES ( '" + fName + "', '" + lName + "');";
          theModel.addActor(stmt);
          helper.createPopupDialog("Database updated",
              "Actor " + fName + " " + lName + " is added to the database.");
          clearTextField();
        }
    }
  }

  /**
   * Method: isValidFirstLastName
   * Summary: Checks if first and last name are empty strings. Prompt user to re-enter if name is invalid
   * @param fName The first name to validate
   * @param lName The last name to validate
   * @return true if first and last name are valid.
   */
  private boolean isValidFirstLastName(String fName, String lName)
  {
    if (fName.isEmpty())
    {
      helper.createPopupDialog("Error", "Please enter actor's first name.");
      theView.tflFirstname.requestFocus();
      return false;
    }
    else if (lName.isEmpty())
    {
      helper.createPopupDialog("Error", "Please enter actor's last name.");
      theView.tflLastname.requestFocus();
      return false;
    }else{
      return true;
    }
  }

  /**
   * Method: clearTextField
   * Purpose: Clear text fields for add actor Panel
   */
  public void clearTextField (){
    theView.tflFirstname.setText("");
    theView.tflLastname.setText("");
  }
}