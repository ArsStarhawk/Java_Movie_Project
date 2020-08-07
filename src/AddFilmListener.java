import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AddFilmListener implements ActionListener
{

  MovieRentalView view;
  MovieRentalModel model;

  Connection myConn;
  Statement myStmt;
  ResultSet myRslt;

  String titleStr;
  String descriptionStr;
  int yearEntered;
  int languageStr;
  int origLanguageStr;
  int categoryStr;
  int rentalDurationStr;
  int movieLengthStr;
  double replacementCostStr;
  String ratingStr;
  String specialFeatureStr;
  ArrayList<String> specialFeatures;

  HelperMethods helperMethods;
  public AddFilmListener(MovieRentalView view, MovieRentalModel model) {
    this.view = view;
    this.model = model;
    helperMethods = new HelperMethods();

    titleStr = new String();
    descriptionStr = new String();
    ratingStr = new String();
    specialFeatureStr = new String();
    specialFeatureStr = "";

    yearEntered = 0;
    languageStr  = 0;
    origLanguageStr  = 0;
    categoryStr  = 0;
    rentalDurationStr  = 0;
    movieLengthStr  = 0;
    replacementCostStr = 0;
    specialFeatures = new ArrayList<String>();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //Collecting information from user and setting attributes
    double rentalRateFinal = 0.0;

    boolean hasTrailers = view.trailer.isSelected()? true: false;
    boolean hasCommentary = view.commentary.isSelected()? true: false;
    boolean hasDeletedScenes = view.deletedScenes.isSelected()? true: false;
    boolean hasBehindScenes = view.behindScenes.isSelected()? true: false;

    boolean isValidInput = getInputAndValidate();
    if (isValidInput) {
      if (yearEntered < 2015) {
        rentalRateFinal = 0.99;
      } else {
        rentalRateFinal = 4.99;
      }

      //Getting SET of special features
      if (hasTrailers)
        specialFeatures.add("Trailers");
      if (hasCommentary)
        specialFeatures.add("Commentaries");
      if (hasDeletedScenes)
        specialFeatures.add("Deleted Scenes");
      if (hasBehindScenes)
        specialFeatures.add("Behind the Scenes");

      specialFeatureStr = String.join(",", specialFeatures);

      //insert into film statement
      String insertStatement = String.format("INSERT INTO film "
              + "(title, description, release_year,language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
              + "VALUES(\"%s\",\"%s\",%d,%d,%d,%d,%f,%d,%f,\"%s\",'%s');"
          , titleStr, descriptionStr, yearEntered, languageStr, origLanguageStr, rentalDurationStr, rentalRateFinal, movieLengthStr, replacementCostStr, ratingStr, specialFeatureStr);
      //start of database updates
      try
      {
        myConn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sakila?useSSL=false","root", "password");

        myStmt = myConn.createStatement();

        //catch the returned int value after the insert is executed
        int returnedValue = myStmt.executeUpdate(insertStatement);
        String selectStatement = String.format("SELECT film_id FROM film WHERE title = \"%s\"", titleStr);
        myRslt = myStmt.executeQuery(selectStatement);
        myRslt.next();
        int updatedId = myRslt.getInt(1);

        if(view.selectedActors == null)
        {
          helperMethods.createPopupDialog("Error",
              "Please Select at least one actor, and hit 'Import Actor' button");
        }
        else
        {
          //INSERTING ACTORS
          for(String actorName : view.selectedActors)
          {
            String firstName = actorName.split(" ")[0];
            String lastName = actorName.split(" ")[1];
            String selectActorId =
                String.format("SELECT actor_id FROM actor WHERE first_name = \"%s\" and last_name = \"%s\";",
                    firstName, lastName);
            myRslt = myStmt.executeQuery(selectActorId);
            myRslt.next();
            int actorId = myRslt.getInt(1);
            String insertActor = String.format(
                "INSERT INTO film_actor (actor_id, film_id) VALUES (%d, %d);", actorId, updatedId);
            int ret = myStmt.executeUpdate(insertActor);
          }
          //Insert film_categories
          String insertStatement2 = String.format(
              "INSERT INTO film_category (film_id, category_id) VALUES (%d, %d);",updatedId, categoryStr);
          System.out.println(insertStatement2);

          int ret = myStmt.executeUpdate(insertStatement2);

          //output results of operation
          JOptionPane.showMessageDialog(view.getContentPane(),
              titleStr + " film inserted into sakila database");
        }
      }
      catch(Exception ex)
      {
        JOptionPane.showMessageDialog(view.getContentPane(), "Movie Already Exist");
      }
      //DO THE finally block!
      finally
      {
        //put your clean up code here to close the objects. Standard practice is to
        //close them in REVERSE ORDER of creation
        try {
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

  private boolean getInputAndValidate()
  {
    boolean	isValid = false;
    while(!isValid)
    {
      titleStr = view.title.getText();
      if(titleStr.isEmpty()){
        helperMethods.createPopupDialog("Error", "Invalid Movie Title");
        view.title.requestFocus();
        break;
      }

      descriptionStr = view.description.getText();
      if(descriptionStr.isEmpty())
      {
        helperMethods.createPopupDialog("Error", "Invalid Movie Description");
        view.description.requestFocus();
        break;
      }

      try{
        yearEntered = Integer.parseInt(view.releaseYear.getSelectedItem().toString());
      } catch (NullPointerException npex){
        helperMethods.createPopupDialog("Error", "Select year");
        view.releaseYear.requestFocus();
        break;
      }

      languageStr = view.language.getSelectedIndex();
      if(languageStr == -1)
      {
        helperMethods.createPopupDialog("Error", "Select language");
        view.language.requestFocus();
        break;
      }
      languageStr++;

      origLanguageStr = view.originalLanguage.getSelectedIndex() +1;
      if(origLanguageStr == 0){
        helperMethods.createPopupDialog("Error", "Select original language.");
        view.originalLanguage.requestFocus();
        break;
      }

      categoryStr = view.category.getSelectedIndex() + 1;
      if(categoryStr == 0){
        helperMethods.createPopupDialog("Error", "Select Category.");
        view.category.requestFocus();
        break;
      }

      try{
        rentalDurationStr = Integer.parseInt(view.rentalDuration.getText());
      } catch (NullPointerException np){
        helperMethods.createPopupDialog("Error", "Enter rental duration for the movie.");
        view.rentalDuration.requestFocus();
        break;
      } catch (NumberFormatException nfe){
        helperMethods.createPopupDialog("Error", "Incorrect format for the rental duration." +
            "Please re-enter.");
        view.rentalDuration.requestFocus();
        break;
      }

      try
      {
        movieLengthStr = Integer.parseInt(view.movieLength.getText());
      }
      catch (NullPointerException np)
      {
        helperMethods.createPopupDialog("Error", "Enter length of the movie.");
        view.movieLength.requestFocus();
        break;
      } catch (NumberFormatException nfe){
        helperMethods.createPopupDialog("Error", "Incorrect format for the length");
        view.movieLength.requestFocus();
        break;
      }

      try
      {
        movieLengthStr = Integer.parseInt(view.movieLength.getText());
      } catch (NullPointerException nex)
      {
        helperMethods.createPopupDialog("Error", "Enter length of the movie.");
        view.movieLength.requestFocus();
        break;
      }
      catch (NumberFormatException numEx)
      {
        helperMethods.createPopupDialog("Error", "Incorrect day format.");
        view.movieLength.requestFocus();
        break;
      }

      try{
        replacementCostStr = Double.parseDouble(view.replacementCost.getText());
      } catch (NullPointerException nex){
        helperMethods.createPopupDialog("Error", "Enter replacement cost.");
        view.replacementCost.requestFocus();
        break;
      } catch (NumberFormatException numEx){
        helperMethods.createPopupDialog("Error", "Incorrect currency format.");
        view.replacementCost.requestFocus();
        break;
      }

      try{
        replacementCostStr = Double.parseDouble(view.replacementCost.getText());
      } catch (NullPointerException nex){
        helperMethods.createPopupDialog("Error", "Enter replacement cost.");
        view.replacementCost.requestFocus();
        break;
      } catch (NumberFormatException numEx){
        helperMethods.createPopupDialog("Error", "Incorrect currency format.");
        view.replacementCost.requestFocus();
        break;
      }
      try
      {
        ratingStr = view.rating.getSelectedItem().toString();
      }
      catch (NullPointerException npx){
        helperMethods.createPopupDialog("Error", "Select rating.");
        view.rating.requestFocus();
        break;
      }
      isValid = true;
    }
    return isValid;
  }
}
