/**
 * Program Name: SubmitRentalListener.java
 * Purpose:
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: Jul 14, 2020
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitRentalListener implements ActionListener {
    MovieRentalView view;
    MovieRentalModel model;


    public SubmitRentalListener(MovieRentalView view, MovieRentalModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        HelperMethods helperMethods = new HelperMethods();

        String selectedFilm = view.comboFilmList.getSelectedItem().toString();
        String selectedCust = view.comboCustList.getSelectedItem().toString();
        String rentalDate, returnDate, price, report;
        int inventoryId, duration, customerId, storeId, staffId;

        if (selectedFilm.equals("Start Typing to search..."))
        {
            helperMethods.createPopupDialog("Error", "Please choose a film.");
            view.comboFilmList.requestFocus();
        } else if (selectedCust.equals("Start Typing to search..."))
        {
            helperMethods.createPopupDialog("Error", "Please choose a customer.");
           view.comboCustList.requestFocus();
        } else
        {
            // establish that there is an available copy of the selected film
            // call method to return inventory_id of rentable copy, return -1 if no
            // available copy
            inventoryId = model.findAvailableCopyOfFilm(view.comboFilmList.getSelectedIndex(),
                    view.radioGroup.getSelection().getActionCommand());
            try
            {
                if (inventoryId == -1)
                {
                    throw new NotAvailableException(selectedFilm);
                }
            } catch (NotAvailableException e1)
            {
                helperMethods.createPopupDialog("Sorry", "No available copies of " + selectedFilm);
                view.comboFilmList.requestFocus();
                return;
            }

            customerId = model.GetCustomerId(selectedCust);
            storeId = Integer.parseInt(view.radioGroup.getSelection().getActionCommand());
            staffId = storeId;

            rentalDate = model.AddRental(inventoryId, customerId, staffId);
            duration = model.GetFilmDuration(view.comboFilmList.getSelectedIndex());
            price = model.GetRentalPrice(view.comboFilmList.getSelectedIndex());
            returnDate = model.GetReturnDate(duration);
            report = "Rental Accepted\n\nFilm: " + selectedFilm
                    + "\nCustomer: " + selectedCust
                    + "\nPrice: $" + price
                    + "\nStore: " +  storeId
                    + "\nRental Date: " + rentalDate
                    + "\nRental Duration: " + duration
                    + "\nReturn Date: " + returnDate
                    + "\n\nEnjoy!";

            view.txtOutput.setText(report);

            model.AddPayment(customerId, staffId, Double.parseDouble(price));
        } // main else
    }
}// actionlistener
