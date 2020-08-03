/**
 * Program Name: MovieRentalModel.java
 * Purpose:
 * Coder: James Kidd, James Scully, Evan Somers, Sion Young
 * Date: Jul 14, 2020
 */

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class MovieRentalModel
{
  private Connection conn = null;
  private Statement  stmt = null;
  private ResultSet  rslt = null;
  HelperMethods helperMethods;

  public MovieRentalModel()
  {
    try
    {
      conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true",
          "root","password");
      stmt = conn.createStatement();
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
}
