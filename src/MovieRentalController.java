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
    theView.addSubmitRentalListener(new SubmitRentalListener(theView, theModel));
    theView.addStoreRadioListener(new StoreRadioListener(theView, theModel, this));
    helperMethods = new HelperMethods();
    populateCategoryDropdownForGenerateReportPane();
    populateStoreDropdownForGenerateReportPane();
    populateFilmDropdownForRentalPane();
    populateCustomerDropDownForRentalPant();



  }

  /**
   * <h1>Purpose:</h1>  Loads the Film JComboBox in the Rental Tab
   * <h1>Accepts:</h1> -
   * <h1>Returns:</h1> VOID
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  private void populateFilmDropdownForRentalPane()
  {
    // load film list
    theView.filmList.add("Start Typing to search...");
    ResultSet filmResults = theModel.GetAllFilms();
    try
    {
      while (filmResults.next())
      {
        theView.filmList.add(filmResults.getString("title"));
      }
    } catch (SQLException e)
    {
      System.out.println("SQL Exception while loading films in class ctor, message is: " + e.getMessage());
    }
  }


  /**
   * <h1>Purpose:</h1>  Loads the Customer JComboBox in the Rental Tab
   * <h1>Accepts:</h1> String: Store ID
   * <h1>Returns:</h1> VOID
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  private void populateCustomerDropDownForRentalPant()
  {
    theView.custList.add("Start Typing to search...");
    ResultSet custResults = theModel.GetAllCustomers("1");
    try
    {
      while (custResults.next())
      {
        theView.custList.add(custResults.getString("first_name") + " " + custResults.getString("last_name"));
      }
    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in LoadCustomerList(), message is: " + e.getMessage());
    }
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

