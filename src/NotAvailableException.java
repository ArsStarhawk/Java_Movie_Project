/**
 * Program Name: NotAvailableException.java
 * Purpose: Exception for use in the Check for available film copy method
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: Jul 14, 2020
 */
public class NotAvailableException extends Exception
{
    public NotAvailableException(String message)
    {
        super(message);
    }
}// NotAvailableException