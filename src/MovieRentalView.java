/**
 * Program Name: MovieRentalView.java
 * Purpose: The view part of the MVC model
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: Jul 14, 2020
 */

import java.util.*;

import java.text.ParseException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.regex.Pattern;

public class MovieRentalView extends JFrame
{
  JPanel pnlAddCustomer;
  JPanel pnlAddActor;
  JPanel pnlAddFilm;
  JPanel pnlNewRental;
  JPanel pnlGenerateReport;
  JPanel pnlGenerateReportNorth;

  JComboBox cbCategory;
  JComboBox cbStore;

  JTextField tflFrom;
  JTextField tflTo;

  protected  JTextField tflFirstname;
  protected  JTextField tflLastname;

  JLabel lblFirstname;
  JLabel lblLastname;
  JLabel lblAddNewActorTitle;
  JLabel lblGenerateReportTitle;

  JButton btnAddActor;
  JButton btnCleanActor;
  JButton btnClearGenerateReportView;
  JButton btnGenerateReport;

  JTable tblGenerateReport;
  JScrollPane scpGenerateReport;
  GridBagConstraints gbc;

  // Add Customer Variables
  JTabbedPane tabbedPane;	
	JPanel addCustomer;	

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
	JFormattedTextField cust_tflPostal;	

	// Customer Phone	
	JLabel cust_lblPhone;	
	JFormattedTextField cust_tflPhone;	

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

	// Clear Button	
	JButton cust_btnClear;	

	// Customer Button	
	JButton cust_btnAddCustomer;	

	// Error Cell	
	JLabel cust_lblError;

  //James Kidd's mess of stuff
  JComboBox<String> comboFilmList, comboCustList;
  JLabel lblFilmCombo, lblCustCombo, lblStoreRadios;
  JRadioButton radioStore1, radioStore2;
  ButtonGroup radioGroup;
  JPanel pnlInput, pnlOutput, pnlFilmCombo, pnlCustCombo, pnlStoreRadio, pnlBtnSubmit;
  JTextPane txtOutput;
  JButton btnSubmit;
  Vector<String> custList;
  Vector<String> filmList;

  MovieRentalView()
  {
    super("Movie Rental and Database");
    setupJFrame();
    createTabbedForms();
    CreateAddCustomerPane();
    createAddActorPane();
    createAddNewFilmPane();
    createAddNewRentalTransactionPane();
    createGenerateReportPane();
    this.setVisible(true);
  }

  /**
   * Method: SetupJFrame
   * Summary: Initiate the JFrame for the app
   */
  public void setupJFrame()
  {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(580,580);
    this.setLocationRelativeTo(null); //centers the frame in the screen
    this.setLayout(null);//This is to center the JTabbedPane
  }

  /**
   * Method: createTabbedForms
   * Summary: create 5 tabbed forms for the program
   */
  public void createTabbedForms()
  {
    //This JTabbedPane will have different forms and reports for the user to switch through
    tabbedPane = new JTabbedPane();
    //setting the size of the tabbedPane 5px smaller than the size of the JFrame
    tabbedPane.setBounds(0,0,575,575);

    //JPanels for the JTabbedPane
    pnlAddCustomer = new JPanel();
    pnlAddActor = new JPanel();
    pnlAddFilm = new JPanel();
    pnlNewRental = new JPanel();
    pnlGenerateReport = new JPanel();

    //Adding the JPanels to the tabbedPane with appropriate titles
    tabbedPane.add("Add a new customer",pnlAddCustomer);
    tabbedPane.add("Add a new actor",pnlAddActor);
    tabbedPane.add("Add a new film",pnlAddFilm);
    tabbedPane.add("Rent a movie",pnlNewRental);
    tabbedPane.add("Generate report",pnlGenerateReport);
    //adding the tabbedPane to the JFrame
    this.add(tabbedPane);
  }

  private void CreateAddCustomerPane()
  {
    // Scully's codes
		// Build UI	
		instantiateJComponentsForCustomerPane();	
		addJComponentsToCustomerPanel();
  }

  /**
   * Method: createAddActorPane
   * Summary: Create add actor panel, its JComponents for the program, and lay them out properly
   */
  private void createAddActorPane()
  {
    // instantiate JComponent For Add Actor;
    lblAddNewActorTitle = new JLabel("Add a New Actor to Database");
    lblFirstname = new JLabel("First Name:");
    lblLastname = new JLabel("Last Name:");
    pnlAddActor.setLayout(new GridBagLayout());
    lblAddNewActorTitle.setPreferredSize(new Dimension(250, 50));
    tflFirstname = new JTextField(20);
    tflLastname = new JTextField(20);
    btnAddActor = new JButton("Add Actor");
    btnCleanActor = new JButton("Clear");
    gbc = new GridBagConstraints();

    // setupTitleForAddActor();
    gbc.insets = new Insets(3,3,3,3);
    gbc.anchor = GridBagConstraints.CENTER;
    setGBCPosition(1,0);
    pnlAddActor.add(lblAddNewActorTitle, gbc);

    //setupAllLabelsForAddActor();
    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0.01;

    setGBCPosition(0,2);
    pnlAddActor.add(lblFirstname, gbc);

    setGBCPosition(0,3);
    pnlAddActor.add(lblLastname, gbc);


    //setupAllJTextFieldForAddActor();
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 0.8;
    setGBCPosition(1,2);
    pnlAddActor.add(tflFirstname, gbc);

    setGBCPosition(1,3);
    pnlAddActor.add(tflLastname, gbc);

    //setupButtonForAddActor();
    gbc.weighty = 1;
    gbc.weightx = 0.01;

    gbc.anchor = GridBagConstraints.FIRST_LINE_END;
    setGBCPosition(0,5);
    pnlAddActor.add(btnCleanActor, gbc);

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    setGBCPosition(1,5);
    pnlAddActor.add(btnAddActor, gbc);
  }

  /**
   * Method: createGenerateReportPane
   * Summary: Create the generate report panel, add it  the the JFrame, and lay them out properly
   */
  private void createGenerateReportPane()
  {
    //setUpAllLablesForGenerateReport();
    tblGenerateReport = new JTable();
    pnlGenerateReport.setLayout(new BorderLayout());
    lblGenerateReportTitle = new JLabel("Generate Report", SwingConstants.CENTER);
    pnlGenerateReport.add(lblGenerateReportTitle, BorderLayout.NORTH);
    pnlGenerateReportNorth = new JPanel();
    pnlGenerateReportNorth.setLayout(new GridBagLayout());
    scpGenerateReport = new JScrollPane(tblGenerateReport);
    cbCategory = new JComboBox();
    cbCategory.setSize(30,15);
    cbStore = new JComboBox();
    cbStore.setSize(30,15);
    tflFrom = new JTextField(20);
    tflTo = new JTextField(20);
    btnClearGenerateReportView = new JButton("Clear");
    btnGenerateReport = new JButton("Generate Report");
    gbc = new GridBagConstraints();

    //setUpAllJCompoboxesForGenerateReport();
    gbc.weightx = 0.1;
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    setGBCPosition(0,2);
    pnlGenerateReportNorth.add(new JLabel("Select by Category"), gbc);

    setGBCPosition(0,3);
    pnlGenerateReportNorth.add(new JLabel("Select Store"), gbc);

    setGBCPosition(2,2);
    pnlGenerateReportNorth.add(new JLabel("From (DD-MM-YYYY)"), gbc);

    setGBCPosition(2,3);
    pnlGenerateReportNorth.add(new JLabel("To   (DD-MM-YYYY)"), gbc);

    setGBCPosition(1,2);
    pnlGenerateReportNorth.add(cbCategory, gbc);

    setGBCPosition(1,3);
    pnlGenerateReportNorth.add(cbStore, gbc);

    setGBCPosition(4,2);
    pnlGenerateReportNorth.add(tflFrom, gbc);

    setGBCPosition(4,3);
    pnlGenerateReportNorth.add(tflTo, gbc);

    //setUpAllButtonsForGenerateReport();
    setGBCPosition(0,5);
    pnlGenerateReportNorth.add(btnGenerateReport, gbc);

    setGBCPosition(1,5);
    pnlGenerateReportNorth.add(btnClearGenerateReportView, gbc);

    pnlGenerateReport.add(pnlGenerateReportNorth, BorderLayout.CENTER);
    pnlGenerateReport.add(scpGenerateReport, BorderLayout.SOUTH);
  }

  private void createAddNewRentalTransactionPane()
  {
    filmList = new Vector<String>();
    custList = new Vector<String>();


    pnlNewRental.setLayout(new BorderLayout());
      // instantiate components for view
      lblFilmCombo = new JLabel("Name of Film: ");
      lblCustCombo = new JLabel("Name of Customer: ");
      lblStoreRadios = new JLabel("Store Location: ");

      radioStore1 = new JRadioButton("1", true);
      radioStore1.setActionCommand("1");

      radioStore2 = new JRadioButton("2", false);
      radioStore2.setActionCommand("2");

      radioGroup = new ButtonGroup();
      radioGroup.add(radioStore1);
      radioGroup.add(radioStore2);

      btnSubmit = new JButton("Submit");

      txtOutput = new JTextPane();
      txtOutput.setFont(new Font("Arial", Font.BOLD, 18));
      txtOutput.setEditable(false);
      txtOutput.setOpaque(false);
      txtOutput.setBorder(new EmptyBorder(0, 0, 115, 0));

      StyledDocument doc = txtOutput.getStyledDocument();
      SimpleAttributeSet center = new SimpleAttributeSet();
      StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
      doc.setParagraphAttributes(0, doc.getLength(), center, false);

      comboFilmList = new JComboBox<String>(filmList);
      AutoCompletion.enable(comboFilmList); // third party decorator class - By Thomas Bierhance (  // http://www.orbital-computer.de/JComboBox )
      comboCustList = new JComboBox<String>(custList);
      AutoCompletion.enable(comboCustList); // third party decorator class - By Thomas Bierhance ( // http://www.orbital-computer.de/JComboBox )

      comboFilmList.setPreferredSize(new Dimension(200,30));
      comboCustList.setPreferredSize(new Dimension(200,30));


      // make panels
      pnlInput = new JPanel(new BorderLayout());
      pnlFilmCombo = new JPanel(new GridLayout(2, 1));
      pnlCustCombo = new JPanel(new GridLayout(2, 1));
      pnlStoreRadio = new JPanel();
      pnlOutput = new JPanel(new BorderLayout());
      pnlBtnSubmit = new JPanel();

      pnlStoreRadio.setBorder(new EmptyBorder(20, 10, 5, 10));
      pnlFilmCombo.setBorder(new EmptyBorder(10, 50, 25, 10));
      pnlCustCombo.setBorder(new EmptyBorder(10, 10, 25, 50));

      pnlFilmCombo.add(lblFilmCombo);
      pnlFilmCombo.add(comboFilmList);

      pnlCustCombo.add(lblCustCombo);
      pnlCustCombo.add(comboCustList);

      pnlStoreRadio.add(lblStoreRadios);
      pnlStoreRadio.add(radioStore1);
      pnlStoreRadio.add(radioStore2);

      pnlInput.add(pnlFilmCombo, BorderLayout.WEST);
      pnlInput.add(pnlCustCombo, BorderLayout.EAST);
      pnlInput.add(pnlStoreRadio, BorderLayout.NORTH);

      pnlBtnSubmit.add(btnSubmit);

      pnlOutput.add(txtOutput);

      pnlNewRental.add(pnlInput, BorderLayout.NORTH);
      pnlNewRental.add(pnlBtnSubmit, BorderLayout.CENTER);
      pnlNewRental.add(pnlOutput, BorderLayout.SOUTH);

  }//create method

  private void createAddNewFilmPane()
  {
    // Evan's codes
  }

  /**
   * Method: addAddActorButtonListener
   * Summary: Add an action listener to the buttons for add actor panel
   * @param listener An AddActorListener object
   */
  public void addAddActorButtonListener(ActionListener listener)
  {
    btnAddActor.addActionListener(listener);
    btnCleanActor.addActionListener(listener);
  }

  /**
   * Method: addGenerateReportLisenter
   * Summary: Add an action listener to the buttons for generate report panel
   * @param listener An GenerateReportListener object
   */
  public void addGenerateReportLisenter(ActionListener listener) {
    btnClearGenerateReportView.addActionListener(listener);
    btnGenerateReport.addActionListener(listener);
  }

  /**
   * Method: setGBCPosition
   * Summary: A helper method to set GridBagConstrains coordinates.
   *          GridBagLayout is used in add actor and generate report panels.
   * @param x x coordinate
   * @param y y coordinate
   */
  public void setGBCPosition(int x, int y)
  {
    gbc.gridx = x;
    gbc.gridy = y;
  }

  /**
   * Method: clearGeneratereportInput
   * Summary: Clear/reset inputs for generate report panel
   */
  public void clearGeneratereportInput() {
    tflFrom.setText("");
    tflTo.setText("");
    cbCategory.setSelectedIndex(0);
    cbStore.setSelectedIndex(0);
  }
  
  /**
   * Method: instantiateJComponentsForCustomerPane
   * Summary: Inits components for customer panel
   */
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
    MaskFormatter postalMask = null;	
		try	
		{	
			postalMask = new MaskFormatter("U#U-#U#");	
		} catch (ParseException e)	
		{	
			e.printStackTrace();	
		}	
		cust_tflPostal = new JFormattedTextField(postalMask);	

		// Phone	
		cust_lblPhone = new JLabel("Phone: ");	
    MaskFormatter phoneMask = null;	
		try	
		{	
			phoneMask = new MaskFormatter("(###) ###-####");
		} catch (ParseException e) {	
			e.printStackTrace();	
		}	
		cust_tflPhone = new JFormattedTextField(phoneMask);	

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

		// Clear and Add Button	
		cust_btnClear = new JButton("Clear");	
		cust_btnAddCustomer = new JButton("Add Customer");	

		// Empty	
		cust_lblEmptyCell_3 = new JLabel("");	

		// Error	
		cust_lblError = new JLabel("");	
	}
  
  /**
   * Method: addJComponentsToCustomerPanel
   * Summary: Creates components for customer panel
   */
	public void addJComponentsToCustomerPanel() {	
		// Add JComponents to the addCustomer panel	
		pnlAddCustomer.setLayout(new GridLayout(16, 2));	

		// Firstname	
		pnlAddCustomer.add(cust_lblFirstName);	
		pnlAddCustomer.add(cust_tflFirstName);	

		// Lastname	
		pnlAddCustomer.add(cust_lblLastName);	
		pnlAddCustomer.add(cust_tflLastName);	

		// Email	
		pnlAddCustomer.add(cust_lblEmail);	
		pnlAddCustomer.add(cust_tflEmailField);	

		// Address	
		pnlAddCustomer.add(cust_lblAddress_1);	
		pnlAddCustomer.add(cust_tflAddress_1);	

		// Address 2	
		pnlAddCustomer.add(cust_lblAddress_2);	
		pnlAddCustomer.add(cust_tflAddress_2);	

		// Postal	
		pnlAddCustomer.add(cust_lblPostal);	
		pnlAddCustomer.add(cust_tflPostal);	

		// Phone	
		pnlAddCustomer.add(cust_lblPhone);	
		pnlAddCustomer.add(cust_tflPhone);	

		// Country	
		pnlAddCustomer.add(cust_lblCountry);	
		pnlAddCustomer.add(cust_cmbCountry);	

		// City	
		pnlAddCustomer.add(cust_lblCity);	
		pnlAddCustomer.add(cust_cmbCity);	

		// Empty cells	
		pnlAddCustomer.add(cust_lblEmptyCell_1);	
		pnlAddCustomer.add(cust_lblEmptyCell_2);	

		// Clear, Add Buttons	
		pnlAddCustomer.add(cust_btnClear);	
		pnlAddCustomer.add(cust_btnAddCustomer);	

		// Empty, Error	
		pnlAddCustomer.add(cust_lblEmptyCell_3);	
		pnlAddCustomer.add(cust_lblError);	

	}
	
	 /**
   * Method: validateCustomer
   * Summary: validates customer input form
   */
 public boolean validateCustomer() {	

 	// Check if empty	
 	if(this.cust_tflFirstName.getText().equals("")) {	
 		this.cust_lblError.setText("Invalid firstname");	
 		return false;	
 	}	
 	if(this.cust_tflLastName.getText().equals("")) {	
 		this.cust_lblError.setText("Invalid lastname");	
 		return false;	
 	}	
 	// Get first address, no need to validate second address	
 	if(this.cust_lblAddress_1.getText().equals("")) {	
 		this.cust_lblError.setText("Invalid address: Requirest at least one");	
 		return false;	
 	}	

/**
  * Method: validatesEmail address
  * Summary: validates customer input form
  */
 	if (this.cust_tflEmailField.getText().equals("")) {	
 		this.cust_lblError.setText("Invalid email");	
 		return false;	
   }	
   String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 	
       "[a-zA-Z0-9_+&*-]+)*@" + 	
       "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 	
       "A-Z]{2,7}$";          	
   Pattern pat = Pattern.compile(emailRegex);
   if(!pat.matcher(this.cust_tflEmailField.getText()).matches()) {	
   	this.cust_lblError.setText("Invalid email");	
   	return false;	
   } 	

  // validate phone	
   String phoneRegex = "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";          	
   Pattern phonePat = Pattern.compile(phoneRegex);  	
   if(!phonePat.matcher(this.cust_tflPhone.getText()).matches()) {	
   	this.cust_lblError.setText("Invalid phone");	
   	return false;	
   } 	

   if(this.cust_tflPhone.getText().equals("")) {	
   	this.cust_lblError.setText("Invalid phone");	
   	return false;	
   }	

 	// only need to validate country	
   if(this.cust_cmbCountry.getSelectedItem().equals("-")){	
   	this.cust_lblError.setText("Invalid Country");	
   	return false;	
   }	
 	return true;	
 }	

	// Use to display messages	
	public void displayMessage(String msg) {	
   JOptionPane.showMessageDialog(this,msg);  	
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

  // Updates the cityComboBox	
  public void setCityComboBox(List<String> cities ) {	
  	cust_cmbCity.removeAllItems();	
  	for(int i = 0; i < cities.size(); ++i) 	
  		cust_cmbCity.addItem(cities.get(i).toString());	
  }

  public void addSubmitRentalListener(SubmitRentalListener listener){
    btnSubmit.addActionListener(listener);
  }

  public void addStoreRadioListener(StoreRadioListener listener){
    radioStore1.addItemListener(listener);
    radioStore2.addItemListener(listener);
  }
}