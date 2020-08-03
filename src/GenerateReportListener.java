/**
 * File Name: GenerateReportListener.java
 * Coders: Sion Young
 * Date: 8/1/2020
 * Description: Action Listener class for generate report panel
 */

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateReportListener implements ActionListener
{
  MovieRentalView theView;
  MovieRentalModel theModel;
  HelperMethods helper;

  public GenerateReportListener(MovieRentalView theView, MovieRentalModel theModel)
  {
    this.theView = theView;
    this.theModel = theModel;
    helper = new HelperMethods();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand() == "Clear") // clear button hit
    {
      theView.clearGeneratereportInput();
    }
    else // generate report button hit
    {
      DateStoreCategory dateStoreCategory = getAllInputForGenerateReport();
      boolean isValidFromDate = validateDateFormat(dateStoreCategory.from);
      boolean isValidToDate = validateDateFormat(dateStoreCategory.to);
      if(!isValidFromDate){
        helper.createPopupDialog ("Error", "Not a valid date for the 'From' column");
        theView.tflFrom.requestFocus();
      }
      else if(!isValidToDate)
      {
        helper.createPopupDialog("Error", "Not a valid date for the 'To' column");
        theView.tflFrom.requestFocus();
      }
      else if(isValidFromDate && isValidToDate)
      {
        dateStoreCategory = getFormattedDate(dateStoreCategory);
        boolean isValidFromAndTo = isDateInCorrectOrder(dateStoreCategory.from, dateStoreCategory.to);
        if(!isValidFromAndTo)
        {
          helper.createPopupDialog("Error", "'From' is greater than 'To'");
          theView.tflFrom.requestFocus();
        }
        else
        {
          String stmt =
              "SELECT title AS 'Movie Title', amount AS Amount, payment_date AS 'Payment Date'" +
                  "FROM Payment p " +
                  "INNER JOIN Rental r "+
                  "ON p.rental_id = r.rental_id "+
                  "INNER JOIN Inventory i "+
                  "ON r.inventory_id = i.inventory_id " +
                  "INNER JOIN Film f " +
                  "ON i.film_id = f.film_id "+
                  "INNER JOIN film_category fc "+
                  "ON f.film_id = fc.film_id " +
                  "INNER JOIN Category c " +
                  "ON c.category_id = fc.category_id WHERE ";
          TableModel table = theModel.generateReport(stmt, dateStoreCategory);
          theView.tblGenerateReport.setModel(table);
        }
      }
    }
  }

  /**
   * Method: getAllInputForGenerateReport
   * Summary: Get user input from generate report panel
   * @return  Date, store, and category wrapped in a DateStoreCategory object
   */
  public DateStoreCategory getAllInputForGenerateReport()
  {
    String category = getCategoryFromView();
    String store = getStoreFromView();
    String from = theView.tflFrom.getText();
    String to = theView.tflTo.getText();
    return new DateStoreCategory(from,to,store,category);
  }

  /**
   * Method: getCategoryFromView
   * Summary: get user input for category from generate report panel
   * @return  The selected date as a string
   */
  private String getCategoryFromView()
  {
    String category = theView.cbCategory.getSelectedItem().toString();
    if(category.equals("All Categories"))
    {
      return "*";
    }
    return category;
  }

  /**
   * Method: getStoreFromView
   * Summary: get user input for store from generate report panel
   * @return:  The selected store as a string
   */
  private String getStoreFromView()
  {
    String store = theView.cbStore.getSelectedItem().toString().trim();
    if(store.equals("All Stores"))
    {
      return "*";
    }
    return store;
  }

  /**
   * Method: validateDateFormat
   * Summary: Validate the format of the input date, format description down in the method body.
   * @param date the date to be validated
   * @return true if the date is valid
   */
  private boolean validateDateFormat(String date)
  {
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
    else
    {
      return false;
    }
  }

  /**
   * Method: getFormattedDate
   * Summary: Date is entered in form of popular DD-MM-YYYY.
   *          This method converts it to YYYY-MM-DD format.
   * @param dsc The data structure wraps the date in
   * @return The data structure with formatted date
   */
  private DateStoreCategory getFormattedDate(DateStoreCategory dsc)
  {
    String []DDMMYYYYFrom = dsc.from.split("-");
    dsc.from = DDMMYYYYFrom[2] + "-" + DDMMYYYYFrom[1] + "-" +DDMMYYYYFrom[0];
    String []DDMMYYYYTo = dsc.to.split("-");
    dsc.to = DDMMYYYYTo[2] + "-" + DDMMYYYYTo[1] + "-" +DDMMYYYYTo[0];
    return dsc;
  }

  /**
   * Method: isDateInCorrectOrder
   * Purpose: Tests if the 'to' date is greate than 'from' date
   * @param from The first date of the report
   * @param to   The last date of the report
   * @return true if 'to' is greater then 'from'
   */
  private boolean isDateInCorrectOrder(String from, String to)
  {
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
}