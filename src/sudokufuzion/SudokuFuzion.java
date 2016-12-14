/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 08/12/2016
 *   [Last Date] : 09/12/2016
 *   [Description] :
 *      This class is the launcher of the Application;
 *   [Increments] :
 *      - 09/12/2016 [v0.1] : Creation of the Class;
 *      - 12/12/2016 [v0.2] : Add Patterns 
 *
 **/
 
package sudokufuzion;

import sudokufuzion.Controler.GridControler;
import sudokufuzion.Views.mainView;

public class SudokuFuzion {

    public static void main(String[] args) {
        try {
            
            // Non final !!!
            GridControler gc = new GridControler(); // Grid Controler Instanciation
            mainView view = new mainView(gc); // Main View Instanciation
            gc.addObserver(view); // Subscribe Main View to the Grid Controler feed
            view.setVisible(true); // Display the GUI
            gc.runControler(); // Run the controler Thread
            
        } catch (Exception e) {
            System.err.println("[Main] Instanciation Failure :");
            e.printStackTrace();
        }
    }

}
