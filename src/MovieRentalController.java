import java.sql.*;


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
  private GeneralHelper gHelper;

  MovieRentalController()
  {
    theView = new MovieRentalView();
    theModel = new MovieRentalModel();
    theView.addAddActorButtonListener(new AddActorListener(theView, theModel));
    theView.addGenerateReportLisenter(new GenerateReportListener(theView, theModel));
    gHelper = new GeneralHelper();
    populateCategoryDropdownForGenerateReportPane();
    populateStoreDropdownForGenerateReportPane();
  }

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
      gHelper.createPopupDialog("Error", "Not able to load categories");
      System.out.println(ex.getMessage());
    }
  }

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
      gHelper.createPopupDialog("Error", "Not able to load categories");
      System.out.println(ex.getMessage());
    }
  }
}

