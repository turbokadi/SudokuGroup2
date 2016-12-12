
package sudokufuzion.Controler.GridEvents;

import java.util.ArrayList;
import sudokufuzion.Modele.Grid;

public class ChangeValueEvent extends ArrayList<Case>{

    public ChangeValueEvent putGridIntoArrayList(Grid gd) {
        int y=0, x=0;
        for(int[] buff: gd.getGrid()) {
            for(int cli: buff) {
                if (cli != 0) this.add(new Case(x,y,cli,true));
                x++;
            }
            x=0;
            y++;
        } return this;
    }

}
