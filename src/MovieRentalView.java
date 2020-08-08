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

import com.mysql.cj.jdbc.CallableStatement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    protected JTextField tflFirstname;
    protected JTextField tflLastname;
    protected JLabel lblTotalIncomeTitle;
    protected JLabel lblTotalIncome;

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

    //RentalPane Components
    JComboBox<String> comboFilmList, comboCustList;
    JLabel lblFilmCombo, lblCustCombo, lblStoreRadios;
    JRadioButton radioStore1, radioStore2;
    ButtonGroup radioGroup;
    JPanel pnlInput, pnlOutput, pnlFilmCombo, pnlCustCombo, pnlStoreRadio, pnlBtnSubmit;
    JTextPane txtOutput;
    JButton btnSubmit;
    Vector<String> custList;
    Vector<String> filmList;

    //addFilm attributes
    //film title
    JTextField title;
    JLabel titleLabel;
    //film description
    JTextField description;
    JLabel descriptionLabel;
    //film release year
    JLabel releaseYearLabel;
    JComboBox<String> releaseYear;

    //film language
    JLabel languageLabel;
    JComboBox<String> language;

    //film original language
    JLabel originalLanguageLabel;
    JComboBox<String> originalLanguage;

    //film category
    JLabel categoryLabel;
    JComboBox<String> category;

    //film rental duration
    JTextField rentalDuration;
    JLabel rentalDurationLabel;

    //import actors button
    JButton importActorsBtn;

    JLabel actorsLabel;

    //movie length
    JTextField movieLength;
    JLabel movieLengthLabel;

    //film replacement cost
    JTextField replacementCost;
    JLabel replacementCostLabel;

    //film rating
    JLabel ratingLabel;
    JComboBox<String> rating;

    //special features for films
    JCheckBox trailer;
    JCheckBox commentary;
    JLabel checkboxInstructions;
    JLabel checkboxInstructions2;
    //checkboxes for special features
    JCheckBox deletedScenes;
    JCheckBox behindScenes;
    JPanel checkboxesPanel1;
    JPanel checkboxesPanel2;

    //action buttons for adding to database or clearing user input
    JButton addActorsBtn;

    JButton addFilmButton;
    JButton clearFilmInput;

    JPanel addFilmFormPanel;
    ArrayList<String> selectedActors;
    FilmActorSelection actorFilmSelection;


    MovieRentalView()
    {
        super("Movie Rental and Database");
        setupJFrame();
        createTabbedForms();
        createAddCustomerPane();
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
        this.setSize(580, 580);
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
        tabbedPane.setBounds(0, 0, 575, 575);

        //JPanels for the JTabbedPane
        pnlAddCustomer = new JPanel();
        pnlAddActor = new JPanel();
        pnlAddFilm = new JPanel();
        pnlNewRental = new JPanel();
        pnlGenerateReport = new JPanel();

        //Adding the JPanels to the tabbedPane with appropriate titles
        tabbedPane.add("Add a new customer", pnlAddCustomer);
        tabbedPane.add("Add a new actor", pnlAddActor);
        tabbedPane.add("Add a new film", pnlAddFilm);
        tabbedPane.add("Rent a movie", pnlNewRental);
        tabbedPane.add("Generate report", pnlGenerateReport);
        //adding the tabbedPane to the JFrame
        this.add(tabbedPane);
    }

    private void createAddCustomerPane()
    {
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
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.CENTER;
        setGBCPosition(1, 0);
        pnlAddActor.add(lblAddNewActorTitle, gbc);

        //setupAllLabelsForAddActor();
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.weightx = 0.01;

        setGBCPosition(0, 2);
        pnlAddActor.add(lblFirstname, gbc);

        setGBCPosition(0, 3);
        pnlAddActor.add(lblLastname, gbc);

        //setupAllJTextFieldForAddActor();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 0.8;
        setGBCPosition(1, 2);
        pnlAddActor.add(tflFirstname, gbc);

        setGBCPosition(1, 3);
        pnlAddActor.add(tflLastname, gbc);

        gbc.weightx = 0.01;

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        setGBCPosition(0, 5);
        pnlAddActor.add(btnCleanActor, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        setGBCPosition(1, 5);
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
        cbCategory.setSize(30, 15);
        cbStore = new JComboBox();
        cbStore.setSize(30, 15);
        tflFrom = new JTextField(10);
        tflTo = new JTextField(10);
        btnClearGenerateReportView = new JButton("Clear");
        btnGenerateReport = new JButton("Generate Report");
        lblTotalIncome = new JLabel();
        gbc = new GridBagConstraints();

        //setUpAllJCompoboxesForGenerateReport();
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        setGBCPosition(0, 2);
        pnlGenerateReportNorth.add(new JLabel("Select by Category"), gbc);

        setGBCPosition(0, 3);
        pnlGenerateReportNorth.add(new JLabel("Select Store"), gbc);

        setGBCPosition(2, 2);
        pnlGenerateReportNorth.add(new JLabel("From (DD-MM-YYYY)"), gbc);

        setGBCPosition(2, 3);
        pnlGenerateReportNorth.add(new JLabel("To   (DD-MM-YYYY)"), gbc);

        setGBCPosition(2, 5);
        pnlGenerateReportNorth.add(new JLabel("Total Income: "), gbc);

        setGBCPosition(1, 2);
        pnlGenerateReportNorth.add(cbCategory, gbc);

        setGBCPosition(1, 3);
        pnlGenerateReportNorth.add(cbStore, gbc);

        setGBCPosition(4, 2);
        pnlGenerateReportNorth.add(tflFrom, gbc);

        setGBCPosition(4, 3);
        pnlGenerateReportNorth.add(tflTo, gbc);

        setGBCPosition(4, 5);
        pnlGenerateReportNorth.add(lblTotalIncome, gbc);

        //setUpAllButtonsForGenerateReport();
        setGBCPosition(0, 5);
        pnlGenerateReportNorth.add(btnGenerateReport, gbc);

        setGBCPosition(1, 5);
        pnlGenerateReportNorth.add(btnClearGenerateReportView, gbc);

        pnlGenerateReport.add(pnlGenerateReportNorth, BorderLayout.CENTER);
        pnlGenerateReport.add(scpGenerateReport, BorderLayout.SOUTH);
    }

    /**
     * <h1>Purpose:</h1> Creates new rental pane
     * <h1>Accepts:</h1> -
     * <h1>Returns:</h1> void
     * <h1>Date:</h1> Aug 5, 2020
     * <h1>Coder:</h1> James Kidd
     */
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
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        comboFilmList = new JComboBox<String>(filmList);
        AutoCompletion
                .enable(comboFilmList); // third party decorator class - By Thomas Bierhance (  // http://www.orbital-computer.de/JComboBox )
        comboCustList = new JComboBox<String>(custList);
        AutoCompletion
                .enable(comboCustList); // third party decorator class - By Thomas Bierhance ( // http://www.orbital-computer.de/JComboBox )

        comboFilmList.setPreferredSize(new Dimension(200, 30));
        comboCustList.setPreferredSize(new Dimension(200, 30));

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


    /**
     * Sets up the new film pane
     * Coder: Evan Somers
     * Date: Aug. 5, 2020
     */
    private void createAddNewFilmPane()
    {
        final int MIN_YEAR = 1920;

        //String arrays to load combo boxes
        String[] years = getYears(MIN_YEAR);
        String[] langs = {"English", "Italian", "Japanese", "Mandarin", "French", "German"};
        String[] categories = {"Action", "Animation", "Children", "Classics", "Comedy", "Documentary", "Drama",
                               "Family", "Foreign", "Games", "Horror", "Music", "New", "Sci-Fi", "Sports", "Travel"
        };
        String[] ratings = {"G", "PG", "PG-13", "R", "NC-17"};

        //All the input objects for the form
        title = new JTextField();
        titleLabel = new JLabel("Title");

        description = new JTextField();
        descriptionLabel = new JLabel("Description");

        releaseYearLabel = new JLabel("Year Released");
        releaseYear = new JComboBox<String>(years);
        releaseYear.setSelectedItem(null);


        languageLabel = new JLabel("Language");
        language = new JComboBox<String>(langs);
        language.setSelectedItem(null);

        originalLanguageLabel = new JLabel("Original Language");
        originalLanguage = new JComboBox<String>(langs);
        originalLanguage.setSelectedItem(null);

        categoryLabel = new JLabel("Category");
        category = new JComboBox<String>(categories);
        category.setSelectedItem(null);

        rentalDuration = new JTextField();
        rentalDurationLabel = new JLabel("Rental Duration(number of month)");


        importActorsBtn = new JButton("Import actors");

        actorsLabel = new JLabel("Press button to choose actors");

        movieLength = new JTextField();
        movieLengthLabel = new JLabel("Movie Length(hours)");

        replacementCost = new JTextField();
        replacementCostLabel = new JLabel("Replacement Cost");

        ratingLabel = new JLabel("Rating");
        rating = new JComboBox<String>(ratings);
        rating.setSelectedItem(null);

        trailer = new JCheckBox("Trailers");
        commentary = new JCheckBox("Commentary");
        checkboxInstructions = new JLabel("Select Special Features");
        checkboxInstructions2 = new JLabel("For the entered film");

        deletedScenes = new JCheckBox("Deleted Scenes");
        behindScenes = new JCheckBox("Behind the Scenes");
        checkboxesPanel1 = new JPanel();
        checkboxesPanel2 = new JPanel();


        //Button to import selected actors from external JFrame
        //tells user if they have incorrectly selected or have not selected actors
        importActorsBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (actorFilmSelection == null)
                {
                    JOptionPane.showMessageDialog(getContentPane(), "Please open actor import window first");
                }
                else
                {
                    importActors();
                    if (selectedActors.size() == 0)
                    {
                        JOptionPane.showMessageDialog(getContentPane(), "No actors selected in external window");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(getContentPane(), "Actor list imported from external window");
                    }
                }
            }
        });

        //action listener to open up a new jframe for the selection of actors for a film
        addActorsBtn = new JButton("Choose actors");
        addActorsBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                actorFilmSelection = new FilmActorSelection();
            }

        });

        //inserting objects into panels
        checkboxesPanel1.add(trailer);
        checkboxesPanel1.add(commentary);
        checkboxesPanel2.add(deletedScenes);
        checkboxesPanel2.add(behindScenes);

        addFilmButton = new JButton("Add Film");
        clearFilmInput = new JButton("Clear");

        //adding everything to main jpanel, then to the frame
        addFilmFormPanel = new JPanel();
        addFilmFormPanel.setLayout(new GridLayout(14, 2));
        addFilmFormPanel.setPreferredSize(new Dimension(550, 510));
        addFilmFormPanel.add(titleLabel);
        addFilmFormPanel.add(title);
        addFilmFormPanel.add(descriptionLabel);
        addFilmFormPanel.add(description);
        addFilmFormPanel.add(releaseYearLabel);
        addFilmFormPanel.add(releaseYear);
        addFilmFormPanel.add(languageLabel);
        addFilmFormPanel.add(language);
        addFilmFormPanel.add(originalLanguageLabel);
        addFilmFormPanel.add(originalLanguage);
        addFilmFormPanel.add(categoryLabel);
        addFilmFormPanel.add(category);
        addFilmFormPanel.add(rentalDurationLabel);
        addFilmFormPanel.add(rentalDuration);
        addFilmFormPanel.add(movieLengthLabel);
        addFilmFormPanel.add(movieLength);
        addFilmFormPanel.add(replacementCostLabel);
        addFilmFormPanel.add(replacementCost);
        addFilmFormPanel.add(ratingLabel);
        addFilmFormPanel.add(rating);

        addFilmFormPanel.add(checkboxInstructions);
        addFilmFormPanel.add(checkboxesPanel1);
        addFilmFormPanel.add(checkboxInstructions2);
        addFilmFormPanel.add(checkboxesPanel2);
        addFilmFormPanel.add(addActorsBtn);
        addFilmFormPanel.add(importActorsBtn);
        addFilmFormPanel.add(clearFilmInput);
        addFilmFormPanel.add(addFilmButton);
        pnlAddFilm.add(addFilmFormPanel);


        //button listener to clear user input
        clearFilmInput.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                title.setText("");
                description.setText("");
                releaseYear.setSelectedItem(null);
                language.setSelectedItem(null);
                originalLanguage.setSelectedItem(null);
                category.setSelectedItem(null);
                rentalDuration.setText("");
                movieLength.setText("");
                replacementCost.setText("");
                rating.setSelectedItem(null);
            }
        });

    }

    /**
     * Method: addAddActorButtonListener
     * Summary: Add an action listener to the buttons for add actor panel
     *
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
     *
     * @param listener An GenerateReportListener object
     */
    public void addGenerateReportLisenter(ActionListener listener)
    {
        btnClearGenerateReportView.addActionListener(listener);
        btnGenerateReport.addActionListener(listener);
    }

    /**
     * Method: setGBCPosition
     * Summary: A helper method to set GridBagConstrains coordinates.
     * GridBagLayout is used in add actor and generate report panels.
     *
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
    public void clearGeneratereportInput()
    {
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
        cust_lblAddress_1 = new JLabel("Address 1: ");
        cust_tflAddress_1 = new JTextField("");

        // Address 2
        cust_lblAddress_2 = new JLabel("Address 2: ");
        cust_tflAddress_2 = new JTextField("");

        // Postal
        cust_lblPostal = new JLabel("Postal: ");
        MaskFormatter postalMask = null;
        try
        {
            postalMask = new MaskFormatter("U#U-#U#");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cust_tflPostal = new JFormattedTextField(postalMask);
        cust_tflPostal.setFocusLostBehavior(JFormattedTextField.PERSIST);

        // Phone
        cust_lblPhone = new JLabel("Phone: ");
        MaskFormatter phoneMask = null;
        try
        {
            phoneMask = new MaskFormatter("(###) ###-####");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cust_tflPhone = new JFormattedTextField(phoneMask);
        cust_tflPhone.setFocusLostBehavior(JFormattedTextField.PERSIST);

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
    public void addJComponentsToCustomerPanel()
    {
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
     * Method: ValidateCustomer
     * Summary: Validates customer input form and displays a message if something is invalid
     *
     * @return boolean true if valid, false if invalid
     */
    public boolean validateCustomer()
    {

        // Check if empty
        if (this.cust_tflFirstName.getText().equals(""))
        {
            this.cust_lblError.setText("Invalid firstname");
            displayMessage("Customer NOT added: Invalid firstname");
            this.cust_tflFirstName.setText("");
            this.cust_tflFirstName.requestFocus();
            return false;
        }
        if (this.cust_tflLastName.getText().equals(""))
        {
            this.cust_lblError.setText("Invalid lastname");
            this.cust_tflLastName.setText("");
            displayMessage("Customer NOT added: Invalid lastname");
            this.cust_tflLastName.requestFocus();
            return false;
        }

        // validate email address
        if (this.cust_tflEmailField.getText().equals(""))
        {
            this.cust_lblError.setText("Invalid email");
            this.cust_tflEmailField.setText("");
            displayMessage("Customer NOT added: Invalid email");
            this.cust_tflEmailField.requestFocus();
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (!pat.matcher(this.cust_tflEmailField.getText()).matches())
        {
            displayMessage("Customer NOT added: Invalid email");
            this.cust_lblError.setText("Invalid email");
            this.cust_tflEmailField.setText("");
            this.cust_tflEmailField.requestFocus();
            return false;
        }

        // Get first address, no need to validate second address
        if (this.cust_tflAddress_1.getText().equals(" ") || this.cust_tflAddress_1.getText().isEmpty())
        {
            this.cust_lblError.setText("Invalid address: Requires at least one address");
            this.cust_tflAddress_1.setText("");
            this.cust_tflAddress_1.requestFocus();
            displayMessage("Customer NOT added: Invalid address");
            return false;
        }

        // validate postal
        String code = this.cust_tflPostal.getText().substring(0, 3) + this.cust_tflPostal.getText().substring(4, 7);
        if (!HelperMethods.postalCodeValidator(code))
        {
            this.cust_lblError.setText("Invalid Postal Code");
            this.cust_tflPostal.setText("");
            displayMessage("Customer NOT added: Invalid Postal Code");
            this.cust_tflPostal.requestFocus();
            return false;
        }

        // validate phone
        String phoneRegex = "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";
        Pattern phonePat = Pattern.compile(phoneRegex);
        if (!phonePat.matcher(this.cust_tflPhone.getText()).matches())
        {
            this.cust_lblError.setText("Invalid phone");
            displayMessage("Customer NOT added: Invalid phone number");
            this.cust_tflPhone.setText("");
            this.cust_tflPhone.requestFocus();
            return false;
        }

        if (this.cust_tflPhone.getText().equals(""))
        {
            this.cust_lblError.setText("Invalid phone");
            displayMessage("Customer NOT added: Invalid phone number");
            this.cust_tflPhone.setText("");
            this.cust_tflPhone.requestFocus();
            return false;
        }

        // only need to validate country
        if (this.cust_cmbCountry.getSelectedItem().equals("-"))
        {
            this.cust_lblError.setText("Invalid Country");
            displayMessage("Customer NOT added: Country not selected");
            this.cust_cmbCountry.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Method: 	displayMessage
     * Summary: 	displays a message in an JOptionpane
     *
     * @param: message to display
     */
    public void displayMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg);
    }

    /**
     * Method: 	addCountryComboListener
     * Summary: 	A setter method for the country comobo box listener
     *
     * @param: The action listener to be added
     */
    public void addCountryComboListener(ActionListener listener)
    {
        cust_cmbCountry.addActionListener(listener);
    }

    /**
     * Method: addCustomerButtonLIstener
     * Summary: A setter method for the add customer button listener
     *
     * @param: The action listener to be added
     */
    public void addCustomerButtonLIstener(ActionListener listener)
    {
        cust_btnAddCustomer.addActionListener(listener);
    }

    /**
     * Method: clearCustomerButtonListener
     * Summary: A setter method for the clear customer button listener
     *
     * @param: The action listener to be added
     */
    public void addClearCustomerButtonListener(ActionListener listener)
    {
        this.cust_btnClear.addActionListener(listener);
    }

    /**
     * Method: updateCountryList
     * Summary: Updates the customer country combobox with a list of countries
     *
     * @param: A list of the countries to be added
     */
    public void updateCountryList(List<String> countries)
    {
        cust_cmbCountry.removeAllItems();
        cust_cmbCountry.addItem("-");
        for (int i = 0; i < countries.size(); ++i)
        {
            cust_cmbCountry.addItem(countries.get(i).toString());
        }
    }

    /**
     * Method: setCityComboBox
     * Summary: Updates the customers city combobox with a list of cities
     *
     * @param: A list of the cities to be added
     */
    public void setCityComboBox(List<String> cities)
    {
        cust_cmbCity.removeAllItems();
        for (int i = 0; i < cities.size(); ++i)
        {
            cust_cmbCity.addItem(cities.get(i).toString());
        }
    }

    public void addWindowCloseListener(WindowCloseListener listener)
    {
        this.addWindowListener(listener);
    }


    /**
     * adds a action listener to the submit button for a new rental     *
     *
     * @param listener
     */
    public void addSubmitRentalListener(SubmitRentalListener listener)
    {
        btnSubmit.addActionListener(listener);
    }

    /**
     * adds an item listener to the store radio buttons
     *
     * @param listener
     */
    public void addStoreRadioListener(StoreRadioListener listener)
    {
        radioStore1.addItemListener(listener);
        radioStore2.addItemListener(listener);
    }

    /**
     * Method: importActors
     * Summary: updates the local array list with the selected actors from the actor selection window
     *
     * @return void
     */
    public void importActors()
    {
        selectedActors = actorFilmSelection.exportActors();

    }

    /**
     * Method: getActors
     * Summary: returns a list of all actors from the database
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getActors() throws SQLException
    {
        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRslt = null;
        ArrayList<String> actors = new ArrayList<String>();
        //Step 1: Use a try-catch to attempt the database connection
        try
        {
            //create a Connection object by calling a static method of DriverManager class
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "password"
            );

            //Step 2: create a Statement object by calling a method of the Connection object
            myStmt = (CallableStatement) myConn
                    .prepareCall("SELECT CONCAT(first_name, \" \", last_name) AS Name FROM sakila.actor");

            //Step 3: pass in a query string to the Statement object using a method
            // called executeQuery().
            //Assign the returned ResultSet object to myRslt.
            myRslt = myStmt.executeQuery();

            //Step 4: PROCESS the myRslt result set object using a while loop
            while (myRslt.next())
            {
                actors.add(myRslt.getString("name"));
            }


        }
        catch (Exception ex)
        {
            System.out.println("Exception caught, message is " + ex.getMessage());
        }

        //DO THE finally block!
        finally
        {
            //put your clean up code here to close the objects. Standard practice is to
            //close them in REVERSE ORDER of creation
            if (myRslt != null)
            {
                myRslt.close();
            }
            if (myStmt != null)
            {
                myStmt.close();
            }
            if (myConn != null)
            {
                myConn.close();
            }
        }
        return actors;
    }

    /**
     * purpose: returns a string array of all the years from the given "minYear" to the currentYear inclusive
     *
     * @param minYear
     * @return: string[] of the increments ints
     */
    public String[] getYears(int minYear)
    {
        String[] years;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        IntStream yearStream = IntStream.range(minYear, currentYear + 1);
        years = Arrays.stream(yearStream.toArray()).mapToObj(String::valueOf).toArray(String[]::new);
        return years;
    }
}