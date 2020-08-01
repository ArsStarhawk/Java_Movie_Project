import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    theView.addGenerateReportLisenter(new generateReportListener());
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

  private class generateReportListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Clear")
        {
          theView.clearGeneratereportInput();
        }
        else
        {
          String category = theView.cbCategory.getSelectedItem().toString();
          String store = getStoreValueFromView();
          String from = getFormateDate(theView.tflFrom.getText());
          String to = getFormateDate(theView.tflTo.getText());
          boolean isValidFromDate = validateFormat(from);
          boolean isValidToDate = validateFormat(to);
          if(!isValidFromDate){
            createPopupDialog("Error", "Not a valid date");
            theView.tflFrom.requestFocus();
          }
          else if(!isValidToDate)
          {
            createPopupDialog("Error", "Not a valid date");
            theView.tflFrom.requestFocus();
          }
          else if(isValidFromDate && isValidToDate)
          {
            boolean isValidFromAndTo = isValidFromAndToDate(from, to);
            if(!isValidFromAndTo){
              createPopupDialog("Error", "From is greater than to date");
              theView.tflFrom.requestFocus();
            }
            else
            {
              String stmt =
              "SELECT title, amount, payment_date " +
              "FROM Payment" +
              "INNER JOIN Rental"+
              "INNER JOIN Inventory"+
              "INNER JOIN Film ?";
              PreparedStatement pStatement = createPreparedStatement(stmt);
            }
          }
        }
    }



    private String getFormateDate(String d) {
      String []DDMMYYYY = d.split("-");
      return DDMMYYYY[2]+ DDMMYYYY[2] + DDMMYYYY[0];
    }

    private boolean validateFormat(String date) {
      String ptrn =
          "^(((((0[1-9])|(1\\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|" +
              "((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))" +
              "-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$";
//      Description
//      This expression validates a date field in the European DD-MM-YYYY format.
//      Days are validate for the given month and year.
//      Matches
//      05-01-2002 | 29-02-2004 | 31-12-2002
//      Non-Matches
//      1-1-02 | 29-02-2002 | 31-11-2002

      Pattern pattern = Pattern.compile(ptrn);
      Matcher matcher = pattern.matcher(date);
      if(matcher.find())
      {
        return true;
      }
      else{
        return false;
      }
    }

    private boolean isValidFromAndToDate(String from, String to) {
      String fromYMD[] = from.split("-");
      String toYMD[] = to.split("-");
      if(Integer.parseInt(fromYMD[0]) > Integer.parseInt(toYMD[0]))
      {
        return false;
      }
      else if(Integer.parseInt(fromYMD[0]) == Integer.parseInt(toYMD[0]) &&
             Integer.parseInt(fromYMD[1]) > Integer.parseInt(toYMD[1]))
      {
        return false;
      }
      else if ( Integer.parseInt(fromYMD[0]) == Integer.parseInt(toYMD[0]) &&
                Integer.parseInt(fromYMD[1]) == Integer.parseInt(toYMD[1]) &&
                Integer.parseInt(fromYMD[2]) > Integer.parseInt(toYMD[2])
      )
      {
        return false;
      }
      return true;
    }

    private String getStoreValueFromView()
    {
      String store = theView.cbStore.getSelectedItem().toString().trim();
      if(store.equals("All Stores"))
      {
        return "*";
      }
      return store;
    }
  }

  private PreparedStatement createPreparedStatement(String stmt) {

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

