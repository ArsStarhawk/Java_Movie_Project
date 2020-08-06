import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Purpose: To call the model's close() method on window close.
 * Coder: James Kidd, James Scully, Evan Somers, Sion Young
 * Date: Aug 5, 2020
 */

public class WindowCloseListener implements WindowListener
{

    MovieRentalView view;
    MovieRentalModel model;

    public WindowCloseListener(MovieRentalView view, MovieRentalModel model)
    {
        this.view = view;
        this.model = model;
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        model.close();
        System.out.println("Closing JDBC Objects");
    }

    public void windowClosed(WindowEvent we)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
    }
}
