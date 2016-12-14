/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 11/12/2016
 *   [Last Date] : 11/12/2016
 *   [Description] :
 *      This class is an Modified ArrayList Typed to be detect as a Move Focus Event into the View;
 *   [Increments] :
 *      - 11/12/2016 [v0.1] : Creation of the Class
 *
 **/

package sudokufuzion.Controler.GridEvents;

import sudokufuzion.Views.mainView;

public class MoveFocusEvent { // This class represent a Move by a value, Key Pressed by Key Pressed
    
    private int Move = mainView.ERROR ; // This is the attribute who store the Move value, by default is an impossible move value to not impact the View in case of an error.
    
    public int getMove() { return Move; }
    
    public MoveFocusEvent setMoveUp() { // Method who set the UP move value into the Attr and return it instance
        Move = mainView.UP;
        return this;
    }
    
    public MoveFocusEvent setMoveDown() { // Method who set the DOWN move value into the Attr and return it instance
        Move = mainView.DOWN;
        return this;
    }
    
    public MoveFocusEvent setMoveLeft() { // Method who set the LEFT move value into the Attr and return it instance
        Move = mainView.LEFT;
        return this;
    }
    
    public MoveFocusEvent setMoveRight() { // Method who set the RIGHT move value into the Attr and return it instance
        Move = mainView.RIGHT;
        return this;
    }
    
}
