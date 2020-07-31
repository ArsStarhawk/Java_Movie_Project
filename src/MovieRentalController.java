import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

/**
 * Program Name: MovieRentalController.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalController
{
	// Properties
	private MovieRentalModel model;
	private MovieRentalView view;
	
	// Constructor
	public MovieRentalController(){
		this.model = new MovieRentalModel();
		this.view = new MovieRentalView();
		
		// Add customer updates
		view.updateCountryList(model.getAllCountries());
		view.addCountryComboListener(new CountryChangeListener());
		view.addCustomerButtonLIstener(new AddCustomerListener());
		
	}

  class CountryChangeListener implements ActionListener
  {
			@Override
			public void actionPerformed(ActionEvent event)
			{		
				JComboBox countryBox = (JComboBox) event.getSource();
				List<String> selectedCities = model.getCitiesInCountry(countryBox.getSelectedItem().toString());
				view.setCityComboBox(selectedCities);

			}
  }
  
  class AddCustomerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("ADD CUSTOMER CALLED");
			Customer cust = new Customer();
			cust.firstName = view.cust_tflFirstName.getText().toString();
			cust.lastName = view.cust_tflLastName.getText().toString();
			cust.email = view.cust_tflEmailField.getText().toString();
			cust.address1 = view.cust_tflAddress_1.getText().toString();
			cust.address2 = view.cust_tflAddress_2.getText().toString();
			cust.postal = view.cust_tflPostal.getText().toString();
			cust.phone = view.cust_tflPhone.getText().toString();
			cust.country = view.cust_cmbCountry.getSelectedItem().toString();
			cust.city = view.cust_cmbCity.getSelectedItem().toString();
			//model.addCustomerAddress(cust);
			model.addCustomer(cust);
		}
  	
  }
  
}
