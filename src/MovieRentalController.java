/**
 * Program Name: MovieRentalController.java
 * Purpose: Manages the MovieRentalModel and the MovieRentalView classes
 * Coder: James Scully
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
	}
	
}
