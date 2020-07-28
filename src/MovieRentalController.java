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

	}

  class CountryChangeListener implements ActionListener
  {
			@Override
			public void actionPerformed(ActionEvent event)
			{		
				JComboBox countryBox = (JComboBox) event.getSource();
				List<String> selectedCities = model.getCitiesByCountry(countryBox.getSelectedItem().toString());
				view.setCityComboBox(selectedCities);
			}
  }
}
