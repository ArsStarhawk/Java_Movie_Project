/**
 * Program Name: MovieRentalMain.java
 * Purpose: Provide a GUI interface for users to access a movie rental database. 
 * 
 * 					The interface will allow the user to:
 * 							1. add new records
 * 							2. generate reports
 * 							3. perform a movie rental transaction on the database
 * 
 * Coder: James Kidd, Evan Somers, James Scully, Sion Young
 * Date: Jul 14, 2020
 */

public class MovieRentalMain
{
	public static void main(String[] args)
	{
		//running an instance of the main program JFrame
		//new MovieRentalView();
		MovieRentalController program = new MovieRentalController();
	}

}