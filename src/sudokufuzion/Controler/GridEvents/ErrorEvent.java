
package sudokufuzion.Controler.GridEvents;

import java.awt.Point;
import java.util.ArrayList;

public class ErrorEvent extends ArrayList<Point>{

    private Point initialCase;

    public Point getInitialCase() { return initialCase; }

    public void setInitialCase(Point initialCase) { this.initialCase = initialCase; }
    
    public ErrorEvent(ArrayList<Point> setCase) {
        super.addAll(setCase);
    }
 
}
