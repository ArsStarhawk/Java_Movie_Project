import javax.swing.*;

/**
 * Program Name: MovieRentalView.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalView extends JFrame {
  MovieRentalView(){
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
}
