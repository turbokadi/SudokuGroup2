/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 11/12/2016
 *   [Last Date] : 11/12/2016
 *   [Description] :
 *      This class is an struct who contains value of a grid case
 *   [Increments] :
 *      - 11/12/2016 [v0.1] : Creation of the Class
 *
 **/

package sudokufuzion.Controler.GridEvents;

public class Case {
    
    public int x, y, value;
    private boolean initial = false; // It's true if the case is an initial non modifiable case the sudoku
    
    public Case(int x, int y, int value) { 
        this.x = x;
        this.y = y;
        this.value = value;
    }
    
    public Case(int x, int y, int value, boolean initial) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.initial = initial;
    }
    
    public boolean getInitial() { return this.initial; }
    
}
