import java.awt.FlowLayout;
import java.io.Console;
import javax.swing.*;  

/**
 * Program Name: MovieRentalMain.java
 * Purpose: Provide a GUI interface for users to access a movie rental database. 
 * 
 * 					The interface will allow the user to:
 * 							1. add new records
 * 							2. generate reports
 * 							3. perform a movie rental transaction on the database
 * 
 * Coder: James Kidd, Evan Somers
 * Date: Jul 14, 2020
 */

public class MovieRentalMain extends JFrame
{
	public MovieRentalMain() 
	{
		//pass title text in super class constructor call
		super("Movie Rental and Database");
		
		//boiler plate code
		//ensures window is closed when user exits
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(580,580);
		this.setLocationRelativeTo(null); //centers the frame in the screen
		this.setLayout(null);//This is to center the JTabbedPane
		
		//This JTabbedPane will have different forms and reports for the user to switch through
		JTabbedPane tabbedPane = new JTabbedPane();
		
		//setting the size of the tabbedPane 5px smaller than the size of the JFrame
		tabbedPane.setBounds(0,0,575,575);  
		
		//JPanels for the JTabbedPane
		JPanel addCustomer = new JPanel();   
		JPanel addActor = new JPanel();  
		JPanel addFilm = new JPanel();
		JPanel newRental = new JPanel();
		JPanel report = new JPanel();
		
		//Adding the JPanels to the tabbedPane with appropriate titles
		tabbedPane.add("Add a new customer",addCustomer);  
		tabbedPane.add("Add a new actor",addActor);  
		tabbedPane.add("Add a new film",addFilm);    
		tabbedPane.add("Rent a movie",newRental);
		tabbedPane.add("Generate report",report);
		
		//adding the tabbedPane to the JFrame
		this.add(tabbedPane);
		
		//making the JFrame Visible
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		//running an instance of the main program JFrame
		new MovieRentalMain();
	}

}
