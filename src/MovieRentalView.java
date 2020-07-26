import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
/**
 * Program Name: MovieRentalView.java
 * Purpose:
 * Coder: 
 * Date: Jul 14, 2020
 */

public class MovieRentalView extends JFrame {

	private JPanel addCustomer;
	private JComboBox countryCombo;
	
	MovieRentalView(List<String> countries){
    super("Movie Rental and Database");
    SetupJFrame();
    CreateTabbedForms();
    CreateAddCustomerPane(countries);
    CreateAddNewFilmPane();
    CreateAddNewRentalTransactionPane();
    CreateGenerateReportPane();
    this.setVisible(true);
  }

  public void SetupJFrame(){
    //boiler plate code
    //ensures window is closed when user exits
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(580,580);
    this.setLocationRelativeTo(null); //centers the frame in the screen
    this.setLayout(null);//This is to center the JTabbedPane
  }

  public void CreateTabbedForms(){
    //This JTabbedPane will have different forms and reports for the user to switch through
    JTabbedPane tabbedPane = new JTabbedPane();
    //setting the size of the tabbedPane 5px smaller than the size of the JFrame
    tabbedPane.setBounds(0,0,575,575);

    //JPanels for the JTabbedPane
    addCustomer = new JPanel();
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
  }

  private void CreateAddCustomerPane(List<String> countries) {
  	
  	// Build UI
  	addCustomer.setLayout(new GridLayout(16,2));
  	
  	// Firstname
  	JLabel firstNameLabel = new JLabel("First Name: "); 
  	JTextField firstNameField = new JTextField("");
  	addCustomer.add(firstNameLabel);
  	addCustomer.add(firstNameField);
    
    // Lastname
  	JLabel lastNameLabel = new JLabel("Last Name: "); 
  	JTextField lastNameField = new JTextField("");
  	addCustomer.add(lastNameLabel);
  	addCustomer.add(lastNameField);
    
    // Email
  	JLabel emailLabel = new JLabel("Email: "); 
  	JTextField emailField = new JTextField("");
  	addCustomer.add(emailLabel);
  	addCustomer.add(emailField);

    // Address
  	JLabel addressLabel_1 = new JLabel("Adress 1: "); 
  	JTextField addressField_1 = new JTextField("");
  	addCustomer.add(addressLabel_1);
  	addCustomer.add(addressField_1);
    
  	// Address 2
  	JLabel addressLabel_2 = new JLabel("Adress 2: "); 
  	JTextField addressField_2 = new JTextField("");
  	addCustomer.add(addressLabel_2);
  	addCustomer.add(addressField_2);
    
  	// Postal
  	JLabel postalLabel = new JLabel("Postal: "); 
  	JTextField postalField = new JTextField("");
  	addCustomer.add(postalLabel);
  	addCustomer.add(postalField);
  	
  	// Phone
  	JLabel phoneLabel = new JLabel("Phone: "); 
  	JTextField phoneField = new JTextField("");
  	addCustomer.add(phoneLabel);
  	addCustomer.add(phoneField);
    
  	// Country
    //MovieRentalModel data = new MovieRentalModel();
  	JLabel countryLabel = new JLabel("Country: ");
  	countryCombo = new JComboBox(countries.toArray(new String[0]));
  	addCustomer.add(countryLabel);
  	addCustomer.add(countryCombo);
  	
  	// City
  	// populate the cities based on what is currently selected
  	//String selectedCountry = countryCombo.getSelectedItem().toString();
  	//List<String> cities = data.getCitiesByCountry(selectedCountry);
  	String[] emptyCities = {"-"};
  	JComboBox cityCombo = new JComboBox(emptyCities);
  	JLabel cityLabel = new JLabel("City: ");
  	addCustomer.add(cityLabel);
  	addCustomer.add(cityCombo);
  	
  	// Empty cells
  	JLabel  emptyCell_1 = new JLabel ("");
  	JLabel  emptyCell_2 = new JLabel ("");
  	JLabel  emptyCell_3 = new JLabel ("");
  	addCustomer.add(emptyCell_1);
  	addCustomer.add(emptyCell_2);
  	addCustomer.add(emptyCell_3);
  	
  	// Add Customer Button
    JButton addCustomerButton = new JButton("Add Customer");
    addCustomer.add(addCustomerButton);	
  }
  
  public void addCountryComboListener(ActionListener listener) {
  	countryCombo.addActionListener(listener);
  }
  
  
  private void CreateGenerateReportPane() {
    // Sion's Codes
  }

  private void CreateAddNewRentalTransactionPane() {
    //Evan's codes
  }

  private void CreateAddNewFilmPane() {
    // Evan's codes
  }
}
