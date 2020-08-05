/**
 * Program Name: MovieRentalController.java
 * Purpose: The Controller part of the MVC model.
 * Coder: Evan Sommers, James Scully, James Kidd, Sion Young
 * Date: Jul 14, 2020
 */

//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
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
    theView.addSubmitRentalListener(new SubmitRentalListener(theView, theModel));
    theView.addStoreRadioListener(new StoreRadioListener(theView, theModel, this));
    theView.addFilmButton.addActionListener(new AddFilmListener(theView, theModel));
    helperMethods = new HelperMethods();
    populateCategoryDropdownForGenerateReportPane();
    populateStoreDropdownForGenerateReportPane();
    
		// Setup for addCustomer
    theView.updateCountryList(theModel.getAllCountries());
    theView.addCountryComboListener(new CountryChangeListener());
    theView.addCustomerButtonLIstener(new AddCustomerListener());
    theView.addClearCustomerButtonListener(new ClearCustomerListener());
    populateFilmDropdownForRentalPane();
    populateCustomerDropDownForRentalPant();

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

		// Setup for addCustomer
		theView.updateCountryList(theModel.getAllCountries());
		theView.addCountryComboListener(new CountryChangeListener());
		theView.addCustomerButtonLIstener(new AddCustomerListener());
		theView.addClearCustomerButtonListener(new ClearCustomerListener());
		populateFilmDropdownForRentalPane();
		populateCustomerDropDownForRentalPant();

	}

	/**
	 * <h1>Purpose:</h1> Loads the Film JComboBox in the Rental Tab
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
		theView.comboFilmList.setSelectedIndex(0);

	}

	/**
	 * <h1>Purpose:</h1> Loads the Customer JComboBox in the Rental Tab
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
		theView.comboCustList.setSelectedIndex(0);
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
	 * Method: AddCustomerListener Summary: Gets all the fiels from the customer
	 * table, Validates them and builds a query for the model
	 */
	class AddCustomerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			boolean custAdded = false;
			Customer cust = new Customer();
			if (theView.validateCustomer())
			{
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
					custAdded = true;
				}
			}
			
			// find validation error
			
			
			// of add was successful
			if (custAdded)
			{
				theView.cust_lblError.setText("");
				theView.displayMessage(cust.firstName + " " + cust.lastName + " has been added");

			} else
			{
				theView.cust_lblError.setForeground(Color.RED);
				theView.displayMessage("Customer NOT added");
			}
		}
	}

	/**
	 * Method: populateCategoryDropdownForGenerateReportPane Summary: Gets all
	 * categories from database and populate the drop-down list in the generate
	 * report panel.
	 */
	private void populateCategoryDropdownForGenerateReportPane()
	{
		ResultSet categories = theModel.getAllCategories();
		theView.cbCategory.addItem("All Categories");
		try
		{
			while (categories.next())
			{
				theView.cbCategory.addItem(categories.getString("name"));
			}
		} catch (SQLException ex)
		{
			helperMethods.createPopupDialog("Error", "Not able to load categories. " + "Please contact IT support.");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Method: populateStoreDropdownForGenerateReportPane Summary: Gets all stores
	 * from database and populate the drop-down list in the generate report panel.
	 */
	private void populateStoreDropdownForGenerateReportPane()
	{
		ResultSet stores = theModel.getAllStores();
		theView.cbStore.addItem("All Stores       ");
		try
		{
			while (stores.next())
			{
				theView.cbStore.addItem(stores.getString("store_id"));
			}
		} catch (SQLException ex)
		{
			helperMethods.createPopupDialog("Error", "Not able to load categories. " + "Please Contact IT Support.");
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Method: ClearCustomerListener Summary: Action listener to clear the add
	 * customer form.
	 */
	public class ClearCustomerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.cust_tflFirstName.setText("");
			theView.cust_tflLastName.setText("");
			theView.cust_tflEmailField.setText("");
			theView.cust_tflAddress_1.setText("");
			theView.cust_tflAddress_2.setText("");
			theView.cust_tflPostal.setValue(null);
			theView.cust_tflPhone.setValue(null);
			theView.cust_cmbCountry.setSelectedIndex(0);
			theView.cust_cmbCountry.setSelectedIndex(0);
			theView.cust_lblError.setText("");
		}
	}
}
