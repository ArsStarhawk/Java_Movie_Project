/**
 * Program Name: MovieRentalModel.java
 * Purpose:
 * Coder: James Kidd, James Scully, Evan Somers, Sion Young
 * Date: Jul 14, 2020
 */

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.Vector;

public class MovieRentalModel
{
  private Connection conn = null;
  private Statement  stmt = null;
  private ResultSet  rslt = null;
  private PreparedStatement prepStmt = null;
  private CallableStatement callStmt = null;
  HelperMethods helperMethods;

  public MovieRentalModel()
  {
    try
    {
      conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true",
          "root","password");
      stmt = conn.createStatement();
      prepStmt = conn.prepareStatement("");
      helperMethods = new HelperMethods();
    }
    catch(SQLException ex)
    {
      System.out.println("SQLException while closing connection objects: "+ ex.getMessage());
      ex.printStackTrace();
    }
  }

  /**
   * Method: addActor
   * Summary: Runs a SQL statement to add an actor to database
   * @param statement An insert SQL statement to run
   */
  public void addActor(String statement)
  {
    try
    {
      stmt.executeUpdate(statement);
    } catch (SQLException ex){
      System.out.println("Error: " + ex.getMessage());
    }
  }

  /**
   * Method: getAllCategories
   * Summary: Gets all categorries from database
   * @return  All categories as a ResultSet
   */
  public ResultSet getAllCategories()
  {
    try{
      rslt = stmt.executeQuery("SELECT Name FROM Category");
    }catch (SQLException ex ){
      System.out.println("Error: " + ex.getMessage());
    }
    return rslt;
  }

  /**
   * Method: getAllStores
   * Summary: Gets all stores from database
   * @return  All stores as a ResultSet
   */
  public ResultSet getAllStores()
  {
    try{
      rslt = stmt.executeQuery("SELECT store_id FROM Store;");
    }catch (SQLException ex ){
      System.out.println("Error: " + ex.getMessage());
    }
    return rslt;
  }

  /**
   * Method: generateReport
   * Summary: Runs a select statement and return a TableModel to the controller
   * @param s The SQL statement
   * @param dateStoreCategory The date, store, category wrapped in a data structure
   * @return the Data as TableModel
   */
  public TableModel generateReport(String s, DateStoreCategory dateStoreCategory)
  {
    TableModel model = new DefaultTableModel();
    try
    {
      String sql = helperMethods.generateSqlConditionedString(s, dateStoreCategory);
      rslt = stmt.executeQuery(sql);
      model = DbUtils.resultSetToTableModel(rslt);
    } catch (SQLException ex){
      helperMethods.createPopupDialog("Database Error", "Problem loading table. " +
          "Please contact IT support.");
      System.out.println( ex.getMessage());
    }
    return model;
  }

  /**
   * <h1>Purpose:</h1>  Retrieves list of all films in Database
   * <h1>Accepts:</h1> -
   * <h1>Returns:</h1> ResulSet: All films in DB
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public ResultSet GetAllFilms()
  {
    try
    {
      String sql = "SELECT title FROM film";
      rslt = stmt.executeQuery(sql);
    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in GetAllFilms(), message is: " + e.getMessage());
    }
    return rslt;
  }

  /**
   * <h1>Purpose:</h1> Retrieves all ACTIVE customers from a given store
   * <h1>Accepts:</h1> String: StoreId
   * <h1>Returns:</h1> ResultSet: All active Customers
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public ResultSet GetAllCustomers(String storeId)
  {
    try
    {
      prepStmt = conn.prepareStatement("SELECT first_name, last_name FROM customer WHERE store_id = ? AND active = 1;");
      prepStmt.setString(1, storeId);

      rslt = prepStmt.executeQuery();
    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in GetAllCustomers(), message is: " + e.getMessage());
    }
    return rslt;
  }

  /**
   * <h1>Purpose:</h1>  Calculates the return date for the latest rental
   * <h1>Accepts:</h1> int: Rental Duration
   * <h1>Returns:</h1> String: Return date
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public String GetReturnDate(int duration)
  {
    String retVal = null;
    try
    {
      stmt.executeQuery("SELECT rental_date FROM rental ORDER BY rental_id DESC LIMIT 1");
      rslt.next();
      String rentalDate = rslt.getString(1);

      callStmt = conn.prepareCall("{ ? = call DATE_ADD(?, INTERVAL ? DAY) }");

      callStmt.registerOutParameter(1, java.sql.Types.TIMESTAMP);
      callStmt.setString(2, rentalDate);
      callStmt.setInt(3, duration);
      callStmt.execute();
      retVal = callStmt.getString(1);

    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in GetReturnDate, message is: " + e.getMessage());
    }
    return retVal;
  }// GetReturnDate

  /**
   * <h1>Purpose:</h1>  Retrieves the rental price of a given filmId
   * <h1>Accepts:</h1> int: filmId
   * <h1>Returns:</h1> String: rental price
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public String GetRentalPrice(int filmId)
  {
    String retVal = "";
    try
    {
      conn = DriverManager.getConnection(
              "jdbc:mySql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true", "root", "password");
      prepStmt = conn.prepareStatement("SELECT rental_rate FROM film WHERE film_id = ?");
      prepStmt.setString(1, String.valueOf(filmId));
      rslt = prepStmt.executeQuery();
      rslt.next();
      return rslt.getString(1);
    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in GetRentalPrice(), message is: " + e.getMessage());
    }
    return retVal;
  }//GetRentalPrice

  /**
   * <h1>Purpose:</h1>  Retrieves a customerId
   * <h1>Accepts:</h1> String: customer first AND last name - space delineated
   * <h1>Returns:</h1> int: customerId
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public int GetCustomerId(String custName) {
    String[] names = custName.split(" ");
    String firstName = names[0];
    String lastName = names[1];
    int retVal = -1;

    try {
      prepStmt = conn.prepareStatement("SELECT customer_id FROM customer WHERE first_name = ? AND last_name = ?");
      prepStmt.setString(1, firstName);
      prepStmt.setString(2, lastName);
      rslt = prepStmt.executeQuery();
      rslt.next();
      retVal = rslt.getInt(1);
    } catch (SQLException e) {
      System.out.println("SQL Exeption in GetCustomerId(), message is: " + e.getMessage());
    }
    return retVal;
  }

  /**
   * <h1>Purpose:</h1>  Inserts the new rental into the rental table
   * <h1>Accepts:</h1> int: inventoryId, int: customerId, int: staffId (or storeID)
   * <h1>Returns:</h1> String: the rental date of the inserted rental
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public String AddRental(int inventoryId, int custId, int staffId)
  {
    String retVal = "";

    try
    {
      prepStmt = conn.prepareStatement("INSERT INTO rental (rental_date, inventory_id, customer_id, staff_id)" +
              "VALUES ( current_timestamp(), ?, ?, ?)");

      prepStmt.setInt(1, inventoryId);
      prepStmt.setInt(2, custId);
      prepStmt.setInt(3, staffId);
      prepStmt.executeUpdate();

      prepStmt = conn.prepareStatement("SELECT rental_date FROM rental WHERE rental_id = last_insert_id()");
      rslt = prepStmt.executeQuery();
      rslt.next();
      retVal = rslt.getString(1);
    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in AddRental(), message is: " + e.getMessage());
    }

    return retVal;
  }//AddRental

  /**
   * <h1>Purpose:</h1>  retrieves the rental duration
   * <h1>Accepts:</h1> int: filmId
   * <h1>Returns:</h1> int: duration of rental
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public int GetFilmDuration(int filmId)
  {
    int retVal = -1;

    try
    {
      prepStmt = conn.prepareStatement("SELECT rental_duration FROM film WHERE film_id = ?");
      prepStmt.setString(1, String.valueOf(filmId));
      rslt = prepStmt.executeQuery();
      rslt.next();
      retVal = rslt.getInt(1);
    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in GetFilmDuration(), message is: " + e.getMessage());
    }
    return retVal;
  }

  /**
   * <h1>Purpose:</h1> Inserts a new row into the payment table
   * <h1>Accepts:</h1> int: customerId, int: staffId (or storeId), doubl: price of rental
   * <h1>Returns:</h1> VOID
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public void AddPayment(int customerId, int staffId, double price)
  {
    int rentalId = -1;

    try
    {
      rslt = stmt.executeQuery("SELECT rental_id FROM rental ORDER BY rental_id DESC LIMIT 1");
      rslt.next();
      rentalId = rslt.getInt(1);

      prepStmt = conn.prepareStatement(
              "INSERT INTO payment (customer_id, staff_id, rental_id, amount, payment_date)" + "VALUES (?,?,?,?,NOW())");

      prepStmt.setInt(1, customerId);
      prepStmt.setInt(2,staffId);
      prepStmt.setInt(3, rentalId);
      prepStmt.setDouble(4, price);
      prepStmt.executeUpdate();
    }catch (SQLException e)
    {
      System.out.println("SQL Exeption in AddPayment(), message is: " + e.getMessage());
    }
  }

  /**
   * <h1>Purpose:</h1>  Finds the Inventory ID of an available copy of the given film
   * <h1>Accepts:</h1> int: Film ID, String: storeId
   * <h1>Returns:</h1> int: inventoryId
   * <h1>Date:</h1> Aug 3, 2020
   * <h1>Coder:</h1> James Kidd
   */
  public int findAvailableCopyOfFilm(int filmId, String storeId)
  {
    Vector<Integer> inventoryIds = new Vector<Integer>();
    Vector<Integer> unavailableIds = new Vector<Integer>();

    try
    {
      //get inventory ids for given film
      prepStmt = conn
              .prepareStatement("SELECT inventory_id FROM inventory WHERE film_id = ? AND store_id = ?");

      prepStmt.setString(1, String.valueOf(filmId));
      prepStmt.setString(2, storeId);
      rslt = prepStmt.executeQuery();

      while (rslt.next())
      {
        inventoryIds.add(rslt.getInt(1));
      }

      // get list of unavailable films
      prepStmt = conn.prepareStatement(
              "SELECT inventory.inventory_id "
                      + "FROM rental "
                      + "INNER JOIN inventory "
                      + "ON rental.inventory_id = inventory.inventory_id "
                      + "WHERE isnull(rental.return_date)	"
                      + "AND inventory.store_id = ?");
      prepStmt.setString(1, storeId);
      rslt = prepStmt.executeQuery();

      while (rslt.next())
      {
        unavailableIds.add(rslt.getInt(1));
      }

    } catch (SQLException e)
    {
      System.out.println("SQL Exeption in findAvailableCopyOfFilm(), message is: " + e.getMessage());
    }

    // check if any of the selected film's inventory IDs don't exist in the
    // unavailable list
    for (int i = 0; i < inventoryIds.size(); i++)
    {
      if (!unavailableIds.contains(inventoryIds.elementAt(i)))
      {
        return inventoryIds.elementAt(i);
      }
    }
    return -1;
  }// findAvailableCopyOfFilm

















}//class


