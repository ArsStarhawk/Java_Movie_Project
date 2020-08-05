/**
 * File Name: CountryChangeListener.java
 * Coder: Sion Young
 * Date: 8/5/2020
 * Description: Handles input for the Change country combo box on the add customer tab
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class: 		AddCustomerListener
 * Summary:
 */
class CountryChangeListener implements ActionListener
{
  MovieRentalModel theModel;
  MovieRentalView theView;

  CountryChangeListener(  MovieRentalView theView,MovieRentalModel theModel)
  {
    this.theView = theView;
    this.theModel = theModel;
  }
  @Override
  public void actionPerformed(ActionEvent event)
  {
    JComboBox countryBox = (JComboBox) event.getSource();
    List<String> selectedCities = theModel.getCitiesInCountry(countryBox.getSelectedItem().toString());
    theView.setCityComboBox(selectedCities);
  }
}