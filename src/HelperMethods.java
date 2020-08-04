import javax.swing.*;

/**
 * File Name: GeneralHelper.java Coder: Evan Somers, Sion Young, James Kidd,
 * James Scully Date: 8/1/2020 Description: A helper class contains methods used
 * across multiple class
 */

public class HelperMethods
{

	/**
	 * Method: createPopupDialog Summary: Creates a pop-up Dialog to interacte with
	 * user
	 * 
	 * @param dialogTitle The title of the window
	 * @param msg         The message to display
	 */
	public void createPopupDialog(String dialogTitle, String msg)
	{
		JOptionPane pane = new JOptionPane(msg);
		JDialog dialog = pane.createDialog(dialogTitle);
		dialog.show();
	}

	/**
	 * Methods: generateSqlConditionedString Summary: This method is used
	 * specifically in generate report panel to create the condition(everything
	 * after WHERE keyword) for an SQL statement.
	 * 
	 * @param s                 The original SQL statement
	 * @param dateStoreCategory The wrapper contains date(from and to), store, and
	 *                          category
	 * @return The complete SQL string ready to run in database.
	 */
	public String generateSqlConditionedString(String s, DateStoreCategory dateStoreCategory)
	{
		if (dateStoreCategory.store.equals("1"))
		{
			s += "store_id = 1 AND ";
		}
		if (dateStoreCategory.store.equals("2"))
		{
			s += "store_id = 2 AND ";
		}
		if (!dateStoreCategory.category.equals("*"))
		{
			s += "c.name = '" + dateStoreCategory.category + "' AND ";
		}
		s += "(payment_date >= '" + dateStoreCategory.from + " 00:00:00'" + " AND ";
		s += "payment_date <= '" + dateStoreCategory.to + " 23:59:59')";
		return s;
	}

	// Postal Code Validator
	// Referenced from stack overflow code:
	// https://stackoverflow.com/questions/40353979/java-validating-postal-code
	public static boolean postalCodeValidator(String postal)
	{
		if (postal.length() > 7)
		{
			return false;
		}
		if (postal.length() < 6)
			return false;
		if (postal.length() == 7)
		{
			boolean valid = true;

			char a = postal.charAt(0);
			char b = postal.charAt(2);
			char c = postal.charAt(4);
			char d = postal.charAt(1);
			char e = postal.charAt(5);
			char f = postal.charAt(6);
			char g = postal.charAt(3);
			if (!Character.isLetter(a))
				valid = false;
			else if (!Character.isLetter(b))
				valid = false;
			else if (!Character.isDigit(c))
				valid = false;
			else if (!Character.isDigit(d))
				valid = false;
			else if (!Character.isLetter(e))
				valid = false;
			else if (!Character.isDigit(f))
				valid = false;
			else if (!Character.isWhitespace(g))
				valid = false;

			if (valid)
			{
				return true;
			} else
			{
				return false;
			}
		}
		if (postal.length() == 6)
		{
			boolean valid = true;

			char a = postal.charAt(0);
			char b = postal.charAt(2);
			char c = postal.charAt(4);
			char d = postal.charAt(1);
			char e = postal.charAt(3);
			char f = postal.charAt(5);
			if (!Character.isLetter(a))
				valid = false;
			else if (!Character.isLetter(b))
				valid = false;
			else if (!Character.isLetter(c))
				valid = false;
			else if (!Character.isDigit(d))
				valid = false;
			else if (!Character.isDigit(e))
				valid = false;
			else if (!Character.isDigit(f))
				valid = false;

			if (valid)
			{
				return true;
			} else
			{
				return false;
			}
		}
		// if we reached here, something is wrong
		return false;
	}
}
