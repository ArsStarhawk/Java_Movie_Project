import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AddFilmListener implements ActionListener
{

	MovieRentalView view;
	MovieRentalModel model;
	public AddFilmListener(MovieRentalView view, MovieRentalModel model)
	{
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		
		//Collecting information from user and setting attributes
		double rentalRateFinal = 0.0;
	
		boolean hasTrailers = view.trailer.isSelected()? true: false;
		boolean hasCommentary = view.commentary.isSelected()? true: false;
		boolean hasDeletedScenes = view.deletedScenes.isSelected()? true: false;
		boolean hasBehindScenes = view.behindScenes.isSelected()? true: false;
		
		String titleStr = view.title.getText();
		String descriptionStr = view.description.getText();
		int yearEntered = Integer.parseInt(view.releaseYear.getSelectedItem().toString());
		int languageStr = view.language.getSelectedIndex() + 1;
		int origLanguageStr = view.originalLanguage.getSelectedIndex() + 1;
		int categoryStr = view.category.getSelectedIndex() + 1;
		int rentalDurationStr = Integer.parseInt(view.rentalDuration.getText());
		int movieLengthStr = Integer.parseInt(view.movieLength.getText());
		double replacementCostStr = Double.parseDouble(view.replacementCost.getText());
		String ratingStr = view.rating.getSelectedItem().toString();
		String specialFeatureStr = "";
		ArrayList<String> specialFeatures = new ArrayList<String>();
		
		if(yearEntered < 2015)
		{
			rentalRateFinal = 0.99;
		}
		else
		{
			rentalRateFinal = 4.99;
		}
		

		
		//Getting SET of special features
		if(hasTrailers)
			specialFeatures.add("Trailers");
		if(hasCommentary)
			specialFeatures.add("Commentaries");
		if(hasDeletedScenes)
			specialFeatures.add("Deleted Scenes");
		if(hasBehindScenes)
			specialFeatures.add("Behind the Scenes");
		
		specialFeatureStr = String.join(",", specialFeatures);
		
		//insert into film statement
		String insertStatement = String.format("INSERT INTO film "
				+ "(title, description, release_year,language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
				+ "VALUES(\"%s\",\"%s\",%d,%d,%d,%d,%f,%d,%f,\"%s\",'%s');"
				, titleStr, descriptionStr, yearEntered, languageStr, origLanguageStr,rentalDurationStr,rentalRateFinal,movieLengthStr,replacementCostStr,ratingStr, specialFeatureStr); 

		//start of database updates
		try
		{
			//create a Connection object by calling a static method of DriverManager class
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sakila?useSSL=false","root", "password!"
					);
			
			//Step 2: create a Statement object by calling a method of the Connection object
			myStmt = myConn.createStatement();
			
			//Step 3: create a String that holds the INSERT statmement that will be passed
			//        to the DB.
		
			System.out.println(insertStatement);
			//catch the returned int value after the insert is executed
			int returnedValue = myStmt.executeUpdate(insertStatement);
			String selectStatement = String.format("SELECT film_id FROM film WHERE title = \"%s\"", titleStr);
			myRslt = myStmt.executeQuery(selectStatement);
			myRslt.next();
			int updatedId = myRslt.getInt(1);

			//INSERTING ACTORS
			for(String actorName : view.selectedActors)
			{
				String firstName = actorName.split(" ")[0];
				String lastName = actorName.split(" ")[1];
				String selectActorId = String.format("SELECT actor_id FROM actor WHERE first_name = \"%s\" and last_name = \"%s\";", firstName, lastName);
				myRslt = myStmt.executeQuery(selectActorId);
				myRslt.next();
				int actorId = myRslt.getInt(1);
				String insertActor = String.format("INSERT INTO film_actor (actor_id, film_id) VALUES (%d, %d);", actorId, updatedId);
				int ret = myStmt.executeUpdate(insertActor);
			}
			  
			 //Insert film_categories
			String insertStatement2 = String.format("INSERT INTO film_category (film_id, category_id) VALUES (%d, %d);",updatedId, categoryStr);
			System.out.println(insertStatement2);
			
			int ret = myStmt.executeUpdate(insertStatement2);
			
			
			//Step 4: output results of operation
			JOptionPane.showMessageDialog(view.getContentPane(), titleStr + " film inserted into sakila database");

		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(view.getContentPane(), "There was an error inserting");
		}
		
		//DO THE finally block!
		finally
		{
			//put your clean up code here to close the objects. Standard practice is to
			//close them in REVERSE ORDER of creation
			try
			{
			if(myRslt != null)
				myRslt.close();
			if(myStmt != null)
				myStmt.close();
			if(myConn != null)
				myConn.close();			
			}
			catch(Exception ex)
			{
				System.out.println("Exception caught, message is " + ex.getMessage());
			}
		}
	}
}
