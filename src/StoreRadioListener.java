/**
 * Program Name: StoreRadioListener.java
 * Purpose: Listener for Store selection radio buttons on New Rental pane
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: Aug 3, 2020
 */

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRadioListener implements ItemListener
{

    MovieRentalView view;
    MovieRentalModel model;

    public StoreRadioListener(MovieRentalView view, MovieRentalModel model, MovieRentalController controller)
    {
        this.view = view;
        this.model = model;
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        view.custList.clear();
        view.custList.add("Start Typing to search...");
        ResultSet custResults = null;

        if (view.radioGroup.getSelection().getActionCommand().equals("1"))
        {
            custResults = model.getAllCustomers("1");
        }
        else if (view.radioGroup.getSelection().getActionCommand().equals("2"))
        {
            custResults = model.getAllCustomers("2");
        }

        try
        {
            while (custResults.next())
            {
                view.custList.add(custResults.getString("first_name") + " " + custResults.getString("last_name"));
            }
        }
        catch (SQLException e1)
        {
            System.out.println("SQL Exeption in LoadCustomerList(), message is: " + e1.getMessage());
        }
        view.comboCustList.setModel(new DefaultComboBoxModel<String>(view.custList));
    }
}
