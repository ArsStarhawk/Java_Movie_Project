import java.util.*;

// Dynamic due
import java.sql.*;
import javax.sql.*;

/**
 * Program Name: MovieRentalModel.java Purpose: Coder: Date: Jul 14, 2020
 */

public class MovieRentalModel
{

	// Get all countries
	public List<String> getAllCountries()
	{
		List<String> countries = new ArrayList<String>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		try
		{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "password");
			myStmt = myConn.createStatement();

			myRslt = myStmt.executeQuery("select * from country");

			while (myRslt.next())
			{
				countries.add(myRslt.getString("country"));
			}

		} catch (Exception ex)
		{
			System.out.println("Exception caught, message is " + ex.getMessage());
		} finally
		{
			try
			{
				if (myRslt != null)
					myRslt.close();
				if (myStmt != null)
					myStmt.close();
				if (myConn != null)
					myConn.close();
			} catch (SQLException ex)
			{
				System.out.println("SQL Exception INSIDE finally block: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return countries;
	}

	// get CityByCountry
	public List<String> getCitiesByCountry(String country)
	{
		List<String> cities = new ArrayList<String>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		try
		{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "password");
			myStmt = myConn.createStatement();
			
			String query = 	"select ci.city " + 
											"from country c " + 
											"inner join city ci " + 
											"on c.country_id = ci.country_id " + 
											"where c.country = \"" + country + "\"";
			
			myRslt = myStmt.executeQuery(query);

			while (myRslt.next())
			{
				cities.add(myRslt.getString("city"));
			}
			
		} catch (Exception ex)
		{
			System.out.println("Exception caught, message is " + ex.getMessage());
		} finally
		{
			try
			{
				if (myRslt != null)
					myRslt.close();
				if (myStmt != null)
					myStmt.close();
				if (myConn != null)
					myConn.close();
			} catch (SQLException ex)
			{
				System.out.println("SQL Exception INSIDE finally block: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return cities;
	}
}
