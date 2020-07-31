import java.util.*;
import java.sql.*;
import javax.sql.*;

/**
 * Program Name: MovieRentalModel.java Purpose: Coder: Date: Jul 14, 2020
 */

public class MovieRentalModel
{
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rslt = null;
	private static PreparedStatement prepStmt = null;

	public MovieRentalModel()
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true",
					"root", "password");
			stmt = conn.createStatement();

		} catch (SQLException ex)
		{
			System.out.println("SQLException while closing connection objects: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void addActor(String statement) throws SQLException
	{
		try
		{
			stmt.executeUpdate(statement);
		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// Get all Countries
	// Returns a list of every country in the database
	public List<String> getAllCountries()
	{
		List<String> lstCountries = new ArrayList<String>();
		try
		{
			rslt = stmt.executeQuery("select * from country");
			while (rslt.next())
			{
				lstCountries.add(rslt.getString("country"));
			}
		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lstCountries;
	}

	/*
	 * getCitiesInCountry Returns a list of all cities specified by country Params:
	 * The specified country Return: A list containing all cities located in
	 * specified country that appear in the database
	 **/
	public List<String> getCitiesInCountry(String country)
	{
		List<String> lstCities = new ArrayList<String>();
		try
		{
			prepStmt = conn.prepareStatement(
					"select ci.city from country c inner join city ci on c.country_id = ci.country_id where c.country = ? ");
			prepStmt.setString(1, country);
			rslt = prepStmt.executeQuery();

			while (rslt.next())
			{
				lstCities.add(rslt.getString("city"));
			}

		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lstCities;
	}

	// A helper method to get a countries id
	public String getCountryID(String country)
	{
		try
		{
			prepStmt = conn.prepareStatement("select country_id from country where country = ?");
			prepStmt.setString(1, country);
			rslt = prepStmt.executeQuery();

			while (rslt.next())
			{
				return rslt.getString("country_id");
			}

		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	// A helper method to get the cities id
	public String getCityID(String city, String country)
	{
		try
		{
			prepStmt = conn.prepareStatement(
					"select city_id from city where city = ? and country_id = ( select country_id  from country where country = ?);");
			prepStmt.setString(1, city);
			prepStmt.setString(2, country);
			rslt = prepStmt.executeQuery();

			while (rslt.next())
			{
				// System.out.println(rslt.getString("city_id"));
				return rslt.getString("city_id");
			}

		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	// Returns the adressId
	public String addCustomerAddress(Customer cust) throws SQLException
	{
		try
		{
			System.out.println("about to start ");
			String cityID = getCityID(cust.city, cust.country);
			String countryID = getCountryID(cust.country);
			String location = "POINT(" + cityID + " " + countryID + ")";

			String q = "insert into address ( address, address2, district, city_id, postal_code, phone, location ) "
					+ "values('" + cust.address1 + "', '" + cust.address2 + "', '" + cust.city + "', " + cityID + ", '"
					+ cust.postal + "', '" + cust.phone + "', ST_GeomFromText('POINT(" + cityID + " " + countryID + ")'));";
			System.out.println(q);
			int i = stmt.executeUpdate(q);
			System.out.println("UPDATED: " + i);
			if (i == 1)
			{
				q = "select LAST_INSERT_ID()";
				rslt = stmt.executeQuery(q);
				while (rslt.next())
				{
					System.out.println("RETURN: " + rslt.getString("LAST_INSERT_ID()"));
					return rslt.getString("LAST_INSERT_ID()");
				}

			} else
			{
				System.out.println("Adress not added");
				return "";
			}

		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	/*
	 * addCustomer Adds a new customer to the database. Will not add duplicates.
	 * Params: The query to add the customer
	 **/
	public int addCustomer(Customer cust)
	{
		try // to add adress
		{
			String addressID = addCustomerAddress(cust);
		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			return -1;	
		}
		try
		{
			String q = "insert into customer(store_id, first_name, last_name, email, address_id, active) " + "values (1, '"
					+ cust.firstName + "', '" + cust.lastName + "', '" + cust.email + "', LAST_INSERT_ID(), 1 );";
			System.out.println(q);
			int i = stmt.executeUpdate(q);
			System.out.println("UPDATED: " + i);
		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			return -1;
		}
		
		return 1;
		// we need a rollback method here
//		insert into customer(store_id, first_name, last_name, email, address_id, active)
//		values (1, "James", "Scully", "js@gmail.com", LAST_INSERT_ID(), 1);
//
//		COMMIT 
//		// will require multiple calls to the database?
//		// will require muliple quieries
//		
	}
}