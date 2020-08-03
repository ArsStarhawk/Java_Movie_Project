/**
 * Program Name: MovieRentalView.java
 * Purpose: The view part of the MVC model
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: Jul 14, 2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MovieRentalView extends JFrame
{
  JTabbedPane tabbedPane;
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
  }

  /**
   * Method: createAddActorPane
   * Summary: Create add actor panel and its JComponents for the program
   */
  private void createAddActorPane()
  {
    instantiateJComponentForAddActor();
    setupTitleForAddActor();
    setupAllLabelsForAddActor();
    setupAllJTextFieldForAddActor();
    setupButtonForAddActor();
  }

  /**
   * Method: instantiateJComponentForAddActor
   * Summary: instantiate all the components for add actor panel
   */
  private void instantiateJComponentForAddActor()
  {
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
  }

  /**
   * Method: setupTitleForAddActor
   * Summary: Set up JLabel for the add actor panel
   */
  public void setupTitleForAddActor()
  {
    gbc.insets = new Insets(3,3,3,3);
    gbc.anchor = GridBagConstraints.CENTER;
    setGBCPosition(1,0);
    pnlAddActor.add(lblAddNewActorTitle, gbc);
  }

  /**
   * Method: setupAllLabelsForAddActor
   * Summary: set up all the labels on add actor panel and lay them out properly
   */
  public void setupAllLabelsForAddActor()
  {
    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0.01;

    setGBCPosition(0,2);
    pnlAddActor.add(lblFirstname, gbc);

    setGBCPosition(0,3);
    pnlAddActor.add(lblLastname, gbc);
  }

  /**
   * Method: setupAllJTextFieldForAddActor
   * Purpose: Sets up all the JTextField for the add actor panel and lay them out properly
   */
  public void setupAllJTextFieldForAddActor()
  {
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 0.8;
    setGBCPosition(1,2);
    pnlAddActor.add(tflFirstname, gbc);

    setGBCPosition(1,3);
    pnlAddActor.add(tflLastname, gbc);
  }

  /**
   * Method: setupButtonForAddActor
   * Purpose: Sets up all the JButton for add actor panel and lay them out properly
   */
  public void setupButtonForAddActor()
  {
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
   * Summary: Create the generate report panel and add it the the JFrame;
   */
  private void createGenerateReportPane()
  {
    instantiateJComponentForGenerateReport();
    setUpAllLablesForGenerateReport();
    setUpAllJCompoboxesForGenerateReport();
    setUpAllButtonsForGenerateReport();
    pnlGenerateReport.add(pnlGenerateReportNorth, BorderLayout.CENTER);
    pnlGenerateReport.add(scpGenerateReport, BorderLayout.SOUTH);
  }

  /**
   * Method: instantiateJComponentForGenerateReport
   * Summary: Instantiates all the components for generate report panel
   */
  private void instantiateJComponentForGenerateReport()
  {
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
  }

  /**
   * Method: setUpAllLablesForGenerateReport
   * Summary: Sets up all the labels for generate report panel and lay them out properly
   */
  public void setUpAllLablesForGenerateReport()
  {
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
  }

  /**
   * Method: setUpAllJCompoboxesForGenerateReport
   * Summary: Sets up all the JComboBox for generate report panel and lay them out properly
   */
  public void setUpAllJCompoboxesForGenerateReport()
  {
    setGBCPosition(1,2);
    pnlGenerateReportNorth.add(cbCategory, gbc);

    setGBCPosition(1,3);
    pnlGenerateReportNorth.add(cbStore, gbc);

    setGBCPosition(4,2);
    pnlGenerateReportNorth.add(tflFrom, gbc);

    setGBCPosition(4,3);
    pnlGenerateReportNorth.add(tflTo, gbc);
  }

  /**
   * Method: setUpAllButtonsForGenerateReport
   * Summary: Sets up all the JButtons for generate report panel and lay them out properly
   */
  public void setUpAllButtonsForGenerateReport()
  {
    setGBCPosition(0,5);
    pnlGenerateReportNorth.add(btnGenerateReport, gbc);

    setGBCPosition(1,5);
    pnlGenerateReportNorth.add(btnClearGenerateReportView, gbc);
  }

  private void createAddNewRentalTransactionPane()
  {
    //Evan's codes
  }

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
}