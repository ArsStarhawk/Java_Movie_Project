/**
 * Program Name: MovieRentalView.java
 * Purpose:
 * Coder:
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

  JComboBox cbCategory;
  JComboBox cbStore;
  JComboBox cbFrom;
  JComboBox cbTo;


  protected  JTextField tflFirstname;
  protected  JTextField tflLastname;

  JLabel lblFirstname;
  JLabel lblLastname;
  JLabel lblAddNewActorTitle;
  JLabel lblGenerateReportTitle;

  JButton btnAddActor;
  JButton btnClearGenerateReportView;
  JButton btnGenerateReport;

  JTable tblGenerateReport;
  JScrollPane scpGenerateReport;
  GridBagConstraints gbc;

  MovieRentalView()
  {
    super("Movie Rental and Database");
    SetupJFrame();
    CreateTabbedForms();
    CreateAddCustomerPane();
    CreateAddActorPane();
    CreateAddNewFilmPane();
    CreateAddNewRentalTransactionPane();
    CreateGenerateReportPane();
    this.setVisible(true);
  }

  public void SetupJFrame()
  {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(580,580);
    this.setLocationRelativeTo(null); //centers the frame in the screen
    this.setLayout(null);//This is to center the JTabbedPane
  }

  public void CreateTabbedForms()
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

  private void CreateAddActorPane()
  {
    InstantiateJComponentForAddActor();
    setupTitleForAddActor();
    setupAllLabelsForAddActor();
    setupAllJTextFieldForAddActor();
    setupButtonForAddActor();
  }

  private void InstantiateJComponentForAddActor()
  {
    lblAddNewActorTitle = new JLabel("Add a New Actor to Database");
    lblFirstname = new JLabel("First Name:");
    lblLastname = new JLabel("Last Name:");
    pnlAddActor.setLayout(new GridBagLayout());
    lblAddNewActorTitle.setPreferredSize(new Dimension(250, 50));
    tflFirstname = new JTextField(20);
    tflLastname = new JTextField(20);
    btnAddActor = new JButton("Add Actor");
    gbc = new GridBagConstraints();
  }

  public void setupTitleForAddActor()
  {
    gbc.insets = new Insets(3,3,3,3);
    gbc.anchor = GridBagConstraints.CENTER;
    setGBCPosition(1,0);
    pnlAddActor.add(lblAddNewActorTitle, gbc);
  }

  public void setupAllLabelsForAddActor()
  {

    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0.01;

    setGBCPosition(0,2);
    pnlAddActor.add(lblFirstname, gbc);

    setGBCPosition(0,3);
    pnlAddActor.add(lblLastname, gbc);
  }

  public void setupAllJTextFieldForAddActor()
  {
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 0.8;
    setGBCPosition(1,2);
    pnlAddActor.add(tflFirstname, gbc);

    setGBCPosition(1,3);
    pnlAddActor.add(tflLastname, gbc);
  }

  public void setupButtonForAddActor()
  {
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    setGBCPosition(1,5);
    pnlAddActor.add(btnAddActor, gbc);
  }


  private void CreateGenerateReportPane()
  {
    InstantiateJComponentForGenerateReport();
    setUpTitleForGenerateReport();
    setUpAllLablesForGenerateReport();
    setUpAllJCompoboxesForGenerateReport();
    setUpAllButtonsForGenerateReport();
    setUpJTableForGenerateReport();
  }

  private void InstantiateJComponentForGenerateReport()
  {
      tblGenerateReport = new JTable();
      scpGenerateReport = new JScrollPane(tblGenerateReport);
      cbCategory = new JComboBox();
      cbStore = new JComboBox();
      cbFrom= new JComboBox();
      cbTo = new JComboBox();
      pnlGenerateReport.setLayout(new GridBagLayout());
      lblGenerateReportTitle = new JLabel("Generate Report");
      btnClearGenerateReportView = new JButton("Clear");
      btnGenerateReport = new JButton("Generate Report");
      lblGenerateReportTitle.setPreferredSize(new Dimension(100, 30));
      gbc = new GridBagConstraints();
  }

  public void setUpTitleForGenerateReport()
  {
    gbc.insets = new Insets(3,3,3,3);
    gbc.weighty = 0.001;
    gbc.anchor = GridBagConstraints.CENTER;
    setGBCPosition(1,0);
    pnlGenerateReport.add(lblGenerateReportTitle, gbc);
  }

  public void setUpAllLablesForGenerateReport()
  {
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 0.01;

    setGBCPosition(0,2);
    pnlGenerateReport.add(new JLabel("Select by Category"), gbc);

    setGBCPosition(0,3);
    pnlGenerateReport.add(new JLabel("Select Store"), gbc);

    setGBCPosition(2,2);
    pnlGenerateReport.add(new JLabel("From: "), gbc);

    setGBCPosition(2,3);
    pnlGenerateReport.add(new JLabel("To"), gbc);
  }

  public void setUpAllJCompoboxesForGenerateReport()
  {
    gbc.weightx = 0.01;
    setGBCPosition(1,2);
    pnlGenerateReport.add(cbCategory, gbc);

    setGBCPosition(1,3);
    pnlGenerateReport.add(cbStore, gbc);

    setGBCPosition(4,2);
    pnlGenerateReport.add(cbFrom, gbc);

    setGBCPosition(4,3);
    pnlGenerateReport.add(cbTo, gbc);
  }

  public void setUpAllButtonsForGenerateReport()
  {
    gbc.weighty = 0.5;
    gbc.weightx = 0.01;
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    setGBCPosition(0,5);
    pnlGenerateReport.add(btnGenerateReport, gbc);
    setGBCPosition(1,5);
    pnlGenerateReport.add(btnClearGenerateReportView, gbc);
  }

  private void setUpJTableForGenerateReport()
  {
    setGBCPosition(0,6);
    pnlGenerateReport.add(scpGenerateReport, gbc);
  }

  private void CreateAddNewRentalTransactionPane()
  {
    //Evan's codes
  }

  private void CreateAddNewFilmPane()
  {
    // Evan's codes
  }

  public void addAddActorButtonListener(ActionListener listener)
  {
    btnAddActor.addActionListener(listener);
  }

  public void setGBCPosition(int x, int y)
  {
    gbc.gridx = x;
    gbc.gridy = y;
  }
}