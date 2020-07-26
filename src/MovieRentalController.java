import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
		this.view = new MovieRentalView(model.getAllCountries());
		
		// Add listeners
		view.addCountryComboListener(new CountryChangeListener());
	}

	 //Listner for the country combobox
  class CountryChangeListener implements ActionListener
  {
			@Override
			public void actionPerformed(ActionEvent e)
			{
			  //Call the method in model to load the ciphered file when the relevant button is clicked
				System.out.println("THIS WAS CHANGED");
			}
  }
}
