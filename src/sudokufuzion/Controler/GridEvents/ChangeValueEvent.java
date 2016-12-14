/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 11/12/2016
 *   [Last Date] : 11/12/2016
 *   [Description] :
 *      This class is an Modified ArrayList Typed to be detect as a Change Value Event into the View;
 *   [Increments] :
 *      - 11/12/2016 [v0.1] : Creation of the Class
 *
 **/

package sudokufuzion.Controler.GridEvents;

import java.util.ArrayList;
import sudokufuzion.Modele.Grid;

public class ChangeValueEvent extends ArrayList<Case>{ // This Class represent the List of all grid cases who have to be modified

    public ChangeValueEvent putGridIntoArrayList(Grid gd) { // This Method Copy the matrix of a Grid Object and paste it into itself ArrayList<Case> 
        int y=0, x=0;
        for(int[] buff: gd.getGrid()) {
            for(int bf: buff) {
                if (bf != 0) this.add(new Case(x,y,bf,true)); // If != 0 the value is initial
                else this.add(new Case(x,y,bf)); // If Not
                x++;
            }
            x=0;
            y++;
        } return this;
    }

}
