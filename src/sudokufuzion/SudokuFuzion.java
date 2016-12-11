/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 08/12/2016
 *   [Last Date] : 09/12/2016
 *   [Description] :
 *      This class is the game view;
 *   [Increments] :
 *      - 09/12/2016 [v0.1] : Creation of the views;
 *
 **/
 
package sudokufuzion;

import sudokufuzion.Views.mainView;

public class SudokuFuzion {

    public static void main(String[] args) {
        try {
            
            // Non final !!! //
            new mainView().setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error");
        }
    }

}
