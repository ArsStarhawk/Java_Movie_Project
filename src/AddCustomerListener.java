/**
 * File Name: AddCustomerListener.java
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: 8/5/2020
 * Description: Gets all the fields from the customer table,
 *  					  Validates them and builds a query for the model
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Method: AddCustomerListener
 * Summary: Gets all the fields from the customer table,
 * 					Validates them and builds a query for the model
 */
class AddCustomerListener implements ActionListener
{
  MovieRentalModel theModel;
  MovieRentalView theView;

  AddCustomerListener(MovieRentalView theView, MovieRentalModel theModel)
  {
    this.theView = theView;
    this.theModel = theModel;
  }
  @Override
  public void actionPerformed(ActionEvent e)
  {
    boolean custAdded = false;
    Customer cust = new Customer();
    if (theView.validateCustomer())
    {
      cust.firstName = theView.cust_tflFirstName.getText();
      cust.lastName = theView.cust_tflLastName.getText();
      cust.email = theView.cust_tflEmailField.getText();
      cust.address1 = theView.cust_tflAddress_1.getText();
      cust.address2 = theView.cust_tflAddress_2.getText();
      cust.postal = theView.cust_tflPostal.getText();
      cust.phone = theView.cust_tflPhone.getText();
      cust.country = theView.cust_cmbCountry.getSelectedItem().toString();
      cust.city = theView.cust_cmbCity.getSelectedItem().toString();
      if (theModel.addCustomer(cust) > 0)
      {
        custAdded = true;
      }
    }

    // if add was successful
    if (custAdded)
    {
      theView.displayMessage(cust.firstName + " " + cust.lastName + " has been added");
    }
  }
}