/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 11/12/2016
 *   [Last Date] : 11/12/2016
 *   [Description] :
 *      This class is an Modified ArrayList Typed to be detect as a Error Event into the View;
 *   [Increments] :
 *      - 11/12/2016 [v0.1] : Creation of the Class
 *
 **/

package sudokufuzion.Controler.GridEvents;

import java.awt.Point;
import java.util.ArrayList;

public class ErrorEvent extends ArrayList<Point>{ // This Class is a List who contains all errors detected at a demand coordinates and value into the sudoku grid. 

    private Point initialCase; // It's the grid case who generate error into the grid

    public Point getInitialCase() { return initialCase; }

    public void setInitialCase(Point initialCase) { this.initialCase = initialCase; }
    
    public ErrorEvent(ArrayList<Point> setCase) { // Filling Method
        super.addAll(setCase);
    }
 
}
