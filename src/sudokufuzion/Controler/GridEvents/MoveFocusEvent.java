
package sudokufuzion.Controler.GridEvents;

import sudokufuzion.Views.mainView;

public class MoveFocusEvent {
    
    private int Move = mainView.ERROR ;
    
    public int getMove() { return Move; }
    
    public MoveFocusEvent setMoveUp() { 
        Move = mainView.UP;
        return this;
    }
    
    public MoveFocusEvent setMoveDown() { 
        Move = mainView.DOWN;
        return this;
    }
    
    public MoveFocusEvent setMoveLeft() { 
        Move = mainView.LEFT;
        return this;
    }
    
    public MoveFocusEvent setMoveRight() { 
        Move = mainView.RIGHT;
        return this;
    }
    
}
