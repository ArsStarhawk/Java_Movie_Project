/**
 * Program Name: MovieRentalController.java
 * Purpose: The Controller part of the MVC model.
 * Coder: Evan Sommers, James Scully, James Kidd, Sion Young
 * Date: Jul 14, 2020
 */

import java.sql.*;

public class MovieRentalController
{
  private MovieRentalView theView;
  private MovieRentalModel theModel;
  private HelperMethods helperMethods;

  MovieRentalController()
  {
    theView = new MovieRentalView();
    theModel = new MovieRentalModel();
    theView.addAddActorButtonListener(new AddActorListener(theView, theModel));
    theView.addGenerateReportLisenter(new GenerateReportListener(theView, theModel));
    helperMethods = new HelperMethods();
    populateCategoryDropdownForGenerateReportPane();
    populateStoreDropdownForGenerateReportPane();
  }

  /**
   * Method: populateCategoryDropdownForGenerateReportPane
   * Summary: Gets all categories from database and populate the drop-down list
   *          in the generate report panel.
   */
  private void populateCategoryDropdownForGenerateReportPane()
  {
    ResultSet categories = theModel.getAllCategories();
    theView.cbCategory.addItem("All Categories");
    try
    {
      while(categories.next())
      {
        theView.cbCategory.addItem(categories.getString("name"));
      }
    }
    catch(SQLException ex)
    {
      helperMethods.createPopupDialog("Error", "Not able to load categories. " +
          "Please contact IT support.");
      System.out.println(ex.getMessage());
    }
  }

  /**
   * Method: populateStoreDropdownForGenerateReportPane
   * Summary: Gets all stores from database and populate the drop-down list
   *          in the generate report panel.
   */
  private void populateStoreDropdownForGenerateReportPane()
  {
    ResultSet stores = theModel.getAllStores();
    theView.cbStore.addItem("All Stores       ");
    try
    {
      while(stores.next())
      {
        theView.cbStore.addItem(stores.getString("store_id"));
      }
    } catch(SQLException ex){
      helperMethods.createPopupDialog("Error", "Not able to load categories. " +
          "Please Contact IT Support.");
      System.out.println(ex.getMessage());
    }
  }
}

