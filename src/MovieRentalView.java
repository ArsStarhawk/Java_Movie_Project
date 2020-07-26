import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Program Name: MovieRentalView.java
 * Purpose:
 * Coder:
 * Date: Jul 14, 2020
 */

public class MovieRentalView extends JFrame {
  JTabbedPane tabbedPane;
  JPanel addCustomer;
  JPanel addActor;
  JPanel addFilm;
  JPanel newRental;
  JPanel report;

  JTextField tflFirstname;
  JTextField tflLastname;
  JTextField tflLastUpdate;
  JLabel lblFirstname;
  JLabel lblLastname;
  JLabel lblLastUpdate;
  JLabel lblAddNewActorTitle;

  JButton btnAddActor;

  GridBagConstraints gbc;

  MovieRentalView(){
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

  public void SetupJFrame(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(580,580);
    this.setLocationRelativeTo(null); //centers the frame in the screen
    this.setLayout(null);//This is to center the JTabbedPane
  }

  public void CreateTabbedForms(){
    //This JTabbedPane will have different forms and reports for the user to switch through
    tabbedPane = new JTabbedPane();
    //setting the size of the tabbedPane 5px smaller than the size of the JFrame
    tabbedPane.setBounds(0,0,575,575);

    //JPanels for the JTabbedPane
    addCustomer = new JPanel();
    addActor = new JPanel();
    addFilm = new JPanel();
    newRental = new JPanel();
    report = new JPanel();

    //Adding the JPanels to the tabbedPane with appropriate titles
    tabbedPane.add("Add a new customer",addCustomer);
    tabbedPane.add("Add a new actor",addActor);
    tabbedPane.add("Add a new film",addFilm);
    tabbedPane.add("Rent a movie",newRental);
    tabbedPane.add("Generate report",report);
    //adding the tabbedPane to the JFrame
    this.add(tabbedPane);
  }

  private void CreateAddCustomerPane() {
    // Scully's codes
  }

  private void CreateAddActorPane() {

    instantiateJComponentsForActorPane();
    gbc = new GridBagConstraints();
    gbc.insets = new Insets(3,3,3,3);
    gbc.anchor = GridBagConstraints.CENTER;

    setGBCPosition(1,0);
    addActor.add(lblAddNewActorTitle, gbc);

    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0.01;

    setGBCPosition(0,2);
    addActor.add(lblFirstname, gbc);

    setGBCPosition(0,3);
    addActor.add(lblLastname, gbc);

    setGBCPosition(0,4);
    addActor.add(lblLastUpdate, gbc);

    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 0.8;
    setGBCPosition(1,2);
    addActor.add(tflFirstname, gbc);

    setGBCPosition(1,3);
    addActor.add(tflLastname, gbc);

    setGBCPosition(1,4);
    addActor.add(tflLastUpdate, gbc);

    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    setGBCPosition(1,5);
    addActor.add(btnAddActor, gbc);
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

  public void instantiateJComponentsForActorPane(){
    lblAddNewActorTitle = new JLabel("Add a New Actor to Database");
    lblFirstname = new JLabel("First Name:");
    lblLastname = new JLabel("Last Name:");
    lblLastUpdate = new JLabel("First Name:");
    tflFirstname = new JTextField(20);
    tflLastname = new JTextField(20);
    tflLastUpdate = new JTextField(20);
    btnAddActor = new JButton("Add Actor");
    addActor.setLayout(new GridBagLayout());
    lblAddNewActorTitle.setPreferredSize(new Dimension(250, 50));
  }

  public void setGBCPosition(int x, int y){
    gbc.gridx = x;
    gbc.gridy = y;
  }

  public void addAddActorButtonListener(ActionListener listener){
    btnAddActor.addActionListener(listener);
  }
}