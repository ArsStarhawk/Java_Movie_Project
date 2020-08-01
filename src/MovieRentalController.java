import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
    populateCategoryDropdownForGenerateReportPane();
    populateStoreDropdownForGenerateReportPane();
  }

  private class addActorListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        String fName = theView.tflFirstname.getText();
        String lName = theView.tflLastname.getText();
        if (fName.isEmpty())
        {
          createPopupDialog("Error", "Please enter actor's first name.");
          theView.tflFirstname.requestFocus();
        }
        else if (lName.isEmpty())
        {
          createPopupDialog("Error", "Please enter actor's last name.");
          theView.tflLastname.requestFocus();
        }
        else
        {
          String stmt = "INSERT INTO Actor (first_name, last_name) " +
              "VALUES ( '" + fName + "', '" + lName + "');";
          theModel.addActor(stmt);
          createPopupDialog("Database updated",
              "Actor " + fName + " " + lName + " is added to the database.");
        }
      }
      catch(SQLException exception)
      {
          exception.printStackTrace();
      }
    }
  }

  private void populateCategoryDropdownForGenerateReportPane()
  {
    ResultSet categories = theModel.getAllCategories();
    try
    {
      while(categories.next())
      {
        theView.cbCategory.addItem(categories.getString("name"));
      }
    }
    catch(SQLException ex)
    {
      createPopupDialog("Error", "Not able to load categories");
      System.out.println(ex.getMessage());
    }
  }

  private void populateStoreDropdownForGenerateReportPane()
  {

    ResultSet stores = theModel.getAllStores();
    theView.cbStore.addItem("All Stores      ");
    try
    {
      while(stores.next())
      {
        theView.cbStore.addItem(stores.getString("store_id"));
      }
    } catch(SQLException ex){
      createPopupDialog("Error", "Not able to load categories");
      System.out.println(ex.getMessage());
    }
  }

  private void createPopupDialog(String dialogTitle, String msg)
  {
    JOptionPane pane = new JOptionPane(msg);
    JDialog dialog = pane.createDialog(dialogTitle);
    dialog.show();
  }
}

