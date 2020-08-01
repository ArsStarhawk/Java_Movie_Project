import com.mysql.cj.protocol.Resultset;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

/**
 * Program Name: MovieRentalModel.java
 * Purpose:
 * Coder:
 * Date: Jul 14, 2020
 */

public class MovieRentalModel
{
  private Connection conn = null;
  private Statement  stmt = null;
  private ResultSet  rslt = null;

  public MovieRentalModel()
  {
    try
    {
      conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true",
          "root","password");
      stmt = conn.createStatement();

    }
    catch(SQLException ex)
    {
      System.out.println("SQLException while closing connection objects: "+ ex.getMessage());
      ex.printStackTrace();
    }
  }

  public void addActor(String statement) throws SQLException
  {
    try
    {
      stmt.executeUpdate(statement);
    } catch (SQLException ex){
      System.out.println("Error: " + ex.getMessage());
    }
  }

  public ResultSet getAllCategories() {
    try{
      rslt = stmt.executeQuery("SELECT Name FROM Category");
    }catch (SQLException ex ){
      System.out.println("Error: " + ex.getMessage());
    }
    return rslt;
  }

  public ResultSet getAllStores() {
    try{
      rslt = stmt.executeQuery("SELECT store_id FROM Store;");
    }catch (SQLException ex ){
      System.out.println("Error: " + ex.getMessage());
    }
    return rslt;
  }

  public TableModel generateReport(String s, DateStoreCategory dateStoreCategory)
  {
    TableModel model = new DefaultTableModel();
    try
    {
      String sql = generateSqlConditionString(s, dateStoreCategory);
      PreparedStatement prepStmt = conn.prepareStatement(sql);
      rslt = stmt.executeQuery(sql);
      model = DbUtils.resultSetToTableModel(rslt);
    } catch (SQLException ex){
      System.out.println( ex.getMessage());
    }
    return model;
  }

  private String generateSqlConditionString(String s, DateStoreCategory dateStoreCategory) {
    if(dateStoreCategory.store.equals("1"))
    {
      s += "store_id = 1 AND ";
    }
    if (dateStoreCategory.store.equals("2"))
    {
      s += "store_id = 2 AND ";
    }
    if(!dateStoreCategory.category.equals("*"))
    {
      s += "c.name = '" + dateStoreCategory.category + "' AND ";
    }
    s += "(payment_date >= '" + dateStoreCategory.from + " 00:00:00'" + " AND ";
    s += "payment_date <= '" + dateStoreCategory.to + " 23:59:59')";
    return s;
  }
}
