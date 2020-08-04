/**
 * Program Name: MovieRentalController.java
 * Purpose: The Controller part of the MVC model.
 * Coder: Evan Sommers, James Scully, James Kidd, Sion Young
 * Date: Jul 14, 2020
 */

//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

import javax.swing.JComboBox;

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
    
		// Setup for addCustomer
    theView.updateCountryList(theModel.getAllCountries());
    theView.addCountryComboListener(new CountryChangeListener());
    theView.addCustomerButtonLIstener(new AddCustomerListener());
  }

	// Listener for country combobox on addCustomer
	class CountryChangeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			JComboBox countryBox = (JComboBox) event.getSource();
			List<String> selectedCities = theModel.getCitiesInCountry(countryBox.getSelectedItem().toString());
			theView.setCityComboBox(selectedCities);
		}
	}

	 /**
   * Method: AddCustomerListener
   * Summary: Gets all the fiels from the customer table, 
   * 					Validates them and builds a query for the model
   */
	class AddCustomerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Customer cust = new Customer();
			cust.firstName = theView.cust_tflFirstName.getText().toString();
			cust.lastName = theView.cust_tflLastName.getText().toString();
			cust.email = theView.cust_tflEmailField.getText().toString();
			cust.address1 = theView.cust_tflAddress_1.getText().toString();
			cust.address2 = theView.cust_tflAddress_2.getText().toString();
			cust.postal = theView.cust_tflPostal.getText().toString();
			cust.phone = theView.cust_tflPhone.getText().toString();
			cust.country = theView.cust_cmbCountry.getSelectedItem().toString();
			cust.city = theView.cust_cmbCity.getSelectedItem().toString();
			if (theModel.addCustomer(cust) > 0)
			{
				theView.displayMessage(cust.firstName + " " + cust.lastName + " has been added");
			} else
			{
				theView.displayMessage("ERROR: Customer not added");
			}
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

