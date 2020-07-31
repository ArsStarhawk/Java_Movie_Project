import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

/**
 * Program Name: MovieRentalView.java Purpose: Coder: Date: Jul 14, 2020
 */

public class MovieRentalView extends JFrame
{
	JTabbedPane tabbedPane;
	JPanel addCustomer;

	// Customer Properties
	
	// Customer Firstname
	JLabel cust_lblFirstName;
	JTextField cust_tflFirstName;

	// Customer Lastname
	JLabel cust_lblLastName;
	JTextField cust_tflLastName;

	// Customer Email
	JLabel cust_lblEmail;
	JTextField cust_tflEmailField;

	// Customer Address
	JLabel cust_lblAddress_1;
	JTextField cust_tflAddress_1;

	// Customer Address 2
	JLabel cust_lblAddress_2;
	JTextField cust_tflAddress_2;

	// Customer Postal
	JLabel cust_lblPostal;
	JTextField cust_tflPostal;

	// Customer Phone
	JLabel cust_lblPhone;
	JTextField cust_tflPhone;

	// Customer Country
	JLabel cust_lblCountry;
	protected JComboBox<String> cust_cmbCountry;

	// Customer City
	JLabel cust_lblCity;
	JComboBox<String> cust_cmbCity;

	// Empty Cells
	JLabel cust_lblEmptyCell_1;
	JLabel cust_lblEmptyCell_2;
	JLabel cust_lblEmptyCell_3;

	// Customer Button
	JButton cust_btnAddCustomer;

	MovieRentalView()
	{
		super("Movie Rental and Database");
		SetupJFrame();
		CreateTabbedForms();
		CreateAddCustomerPane();
		CreateAddNewFilmPane();
		CreateAddNewRentalTransactionPane();
		CreateGenerateReportPane();
		this.setVisible(true);
	}

	public void SetupJFrame()
	{
		// boiler plate code
		// ensures window is closed when user exits
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(580, 580);
		this.setLocationRelativeTo(null); // centers the frame in the screen
		this.setLayout(null);// This is to center the JTabbedPane
	}

	public void CreateTabbedForms()
	{
		// This JTabbedPane will have different forms and reports for the user to switch
		// through
		JTabbedPane tabbedPane = new JTabbedPane();
		// setting the size of the tabbedPane 5px smaller than the size of the JFrame
		tabbedPane.setBounds(0, 0, 575, 575);

		// JPanels for the JTabbedPane
		addCustomer = new JPanel();
		JPanel addActor = new JPanel();
		JPanel addFilm = new JPanel();
		JPanel newRental = new JPanel();
		JPanel report = new JPanel();

		// Adding the JPanels to the tabbedPane with appropriate titles
		tabbedPane.add("Add a new customer", addCustomer);
		tabbedPane.add("Add a new actor", addActor);
		tabbedPane.add("Add a new film", addFilm);
		tabbedPane.add("Rent a movie", newRental);
		tabbedPane.add("Generate report", report);
		// adding the tabbedPane to the JFrame
		this.add(tabbedPane);
	}

	private void CreateAddCustomerPane()
	{
		// Build UI
		instantiateJComponentsForCustomerPane();
		addJComponentsToCustomerPanel();
	}
	
	// Add listener to the comboBox and data
  public void addCountryComboListener(ActionListener listener) {
  	cust_cmbCountry.addActionListener(listener);
  }
  
  public void addCustomerButtonLIstener(ActionListener listener) {
  	cust_btnAddCustomer.addActionListener(listener);
  }
  
  // Update CountryList
  public void updateCountryList(List<String> countries) {
  	cust_cmbCountry.removeAllItems();
  	cust_cmbCountry.addItem("-");
  	for(int i = 0; i < countries.size(); ++i) {
  		cust_cmbCountry.addItem(countries.get(i).toString());
  	}
  }
  
  public void setCityComboBox(List<String> cities ) {
  	cust_cmbCity.removeAllItems();
  	for(int i = 0; i < cities.size(); ++i) 
  		cust_cmbCity.addItem(cities.get(i).toString());
  }

	private void CreateGenerateReportPane()
	{
		// Sion's Codes
	}

	private void CreateAddNewRentalTransactionPane()
	{
		// Evan's codes
	}

	private void CreateAddNewFilmPane()
	{
		// Evan's codes
	}

	public void instantiateJComponentsForCustomerPane()
	{

		// Firstname
		cust_lblFirstName = new JLabel("First Name: ");
		cust_tflFirstName = new JTextField("");

		// Lastname
		cust_lblLastName = new JLabel("Last Name: ");
		cust_tflLastName = new JTextField("");

		// Email
		cust_lblEmail = new JLabel("Email: ");
		cust_tflEmailField = new JTextField("");

		// Address
		cust_lblAddress_1 = new JLabel("Adress 1: ");
		cust_tflAddress_1 = new JTextField("");

		// Address 2
		cust_lblAddress_2 = new JLabel("Adress 2: ");
		cust_tflAddress_2 = new JTextField("");

		// Postal
		cust_lblPostal = new JLabel("Postal: ");
		cust_tflPostal = new JTextField("");

		// Phone
		cust_lblPhone = new JLabel("Phone: ");
		cust_tflPhone = new JTextField("");

		// Country
		String[] country = {"-"};
		cust_lblCountry = new JLabel("Country: ");
		cust_cmbCountry = new JComboBox<String>(country);

		// City
		cust_lblCity = new JLabel("City: ");
		String[] city = {"-"};
		cust_cmbCity = new JComboBox<String>(city);
		
		// Empty
		cust_lblEmptyCell_1 = new JLabel("");
		cust_lblEmptyCell_2 = new JLabel("");
		cust_lblEmptyCell_3 = new JLabel("");
		
		// Button
		cust_btnAddCustomer = new JButton("Add Customer");
	}
	
	public void addJComponentsToCustomerPanel() {
		// Add JComponents to the addCustomer panel
		addCustomer.setLayout(new GridLayout(16, 2));

		// Firstname
		addCustomer.add(cust_lblFirstName);
		addCustomer.add(cust_tflFirstName);

		// Lastname
		addCustomer.add(cust_lblLastName);
		addCustomer.add(cust_tflLastName);

		// Email
		addCustomer.add(cust_lblEmail);
		addCustomer.add(cust_tflEmailField);

		// Address
		addCustomer.add(cust_lblAddress_1);
		addCustomer.add(cust_tflAddress_1);

		// Address 2
		addCustomer.add(cust_lblAddress_2);
		addCustomer.add(cust_tflAddress_2);

		// Postal
		addCustomer.add(cust_lblPostal);
		addCustomer.add(cust_tflPostal);

		// Phone
		addCustomer.add(cust_lblPhone);
		addCustomer.add(cust_tflPhone);

		// Country
		addCustomer.add(cust_lblCountry);
		addCustomer.add(cust_cmbCountry);

		// City
		addCustomer.add(cust_lblCity);
		addCustomer.add(cust_cmbCity);

		// Empty cells
		addCustomer.add(cust_lblEmptyCell_1);
		addCustomer.add(cust_lblEmptyCell_2);
		addCustomer.add(cust_lblEmptyCell_3);

		// Add Customer Button
		addCustomer.add(cust_btnAddCustomer);
	}

}
