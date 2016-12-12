
package sudokufuzion.Controler.GridEvents;

public class Case {
    
    public int x, y, value;
    private boolean initial = false;
    
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
