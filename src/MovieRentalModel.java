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

	// Gets a list of all countries in the database
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

	// Get a list of all cities in a country
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

	// Gets the id of a country
	private String getCountryID(String country)
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

	// Gets the id of the city ( country is required to avoid things like "London,
	// CA" and "London, UK" )
	private String getCityID(String city, String country)
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
				return rslt.getString("city_id");
			}

		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return "";
	}

	// Adds a customers address to the database
	private String addCustomerAddress(Customer cust) throws SQLException
	{
		try
		{
			String cityID = getCityID(cust.city, cust.country);
			String countryID = getCountryID(cust.country);

			String q = "insert into address ( address, address2, district, city_id, postal_code, phone, location ) "
					+ "values('" + cust.address1 + "', '" + cust.address2 + "', '" + cust.city + "', " + cityID + ", '"
					+ cust.postal + "', '" + cust.phone + "', ST_GeomFromText('POINT(" + cityID + " " + countryID + ")'));";
			int i = stmt.executeUpdate(q);
			if (i == 1)
			{
				q = "select LAST_INSERT_ID()";
				rslt = stmt.executeQuery(q);
				while (rslt.next())
				{
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

	// Adds a customer to the database
	public int addCustomer(Customer cust)
	{
		try // add address
		{
			addCustomerAddress(cust);
		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			return -1;
		}
		try // add customer
		{
			String q = "insert into customer(store_id, first_name, last_name, email, address_id, active) " + "values (1, '"
					+ cust.firstName + "', '" + cust.lastName + "', '" + cust.email + "', LAST_INSERT_ID(), 1 );";
			int i = stmt.executeUpdate(q);
		} catch (SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			;
			return -1;
		}

		
		
		return 1;
	}
}