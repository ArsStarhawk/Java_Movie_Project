import java.sql.*;

/**
 * Program Name: MovieRentalModel.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalModel
{
  private static Connection conn = null;
  private static Statement  stmt = null;
  private static ResultSet  rslt = null;

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
      ex.printStackTrace();
    }
  }
}
