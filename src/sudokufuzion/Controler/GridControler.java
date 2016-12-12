/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 08/12/2016
 *   [Last Date] : 09/12/2016
 *   [Description] :
 *      This class is the grid controler;
 *   [Increments] :
 *      - 09/12/2016 [v0.1] : Creation of the Class + main method;
 *      - 11/12/2016 [v0.2] : Adding Taskthread
 *      - 12/12/2016 [v0.3] : Adding focus movement gesture
 *
 **/

package sudokufuzion.Controler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import sudokufuzion.Controler.GridEvents.Case;
import sudokufuzion.Controler.GridEvents.ChangeValueEvent;
import sudokufuzion.Controler.GridEvents.MoveFocusEvent;
import sudokufuzion.Modele.Grid;

public class GridControler extends Observable{
    
    // Thread Runnable Declaration
    private final Runnable th = new Runnable() {
        @Override
        public void run() {
            setChanged();
            notifyObservers(new ChangeValueEvent().putGridIntoArrayList(grid));
            while (true) {
                try {
                    if(queue.isEmpty()) synchronized (Th) { Th.wait(); }
                    handleEvent(queue.poll());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    //====================//
    // Instance Attributes //
    //====================//    
    
    private Point focusedCase;
    private ConcurrentLinkedQueue<KeyEvent> queue;
    private final Thread Th = new Thread(th);
    private final Grid grid = new Grid();
    
    public GridControler() {
        this.queue = new ConcurrentLinkedQueue<>();
    }
    
    public void runControler() { Th.run(); }
    
    public void addToTasksQueue(KeyEvent evt) {
        try {
            Th.sleep(1);
            if(queue.isEmpty()) {
                queue.offer(evt);
                synchronized (Th) { Th.notify(); }
            } else {
                queue.offer(evt);
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void handleEvent(KeyEvent evt) {
        
        try {
            
            int val = evt.getKeyCode();
            
            if(val >= KeyEvent.VK_NUMPAD1 && val <= KeyEvent.VK_NUMPAD9) {
                this.setChanged();
                ChangeValueEvent ev = new ChangeValueEvent();
                ev.add(new Case(this.focusedCase.x, this.focusedCase.y, val - KeyEvent.VK_NUMPAD0));
                this.notifyObservers(ev);
            } 
            else if(val >= KeyEvent.VK_LEFT && val <= KeyEvent.VK_DOWN){
                              
                switch (val) {
                    case KeyEvent.VK_UP : 
                        this.setChanged();
                        this.notifyObservers(new MoveFocusEvent().setMoveUp());
                    break;
                    case KeyEvent.VK_DOWN :
                        this.setChanged();
                        this.notifyObservers(new MoveFocusEvent().setMoveDown());
                    break;
                    case KeyEvent.VK_RIGHT :
                        this.setChanged();
                        this.notifyObservers(new MoveFocusEvent().setMoveRight());
                    break;
                    case KeyEvent.VK_LEFT :
                        this.setChanged();
                        this.notifyObservers(new MoveFocusEvent().setMoveLeft());
                    break;
                    default :
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setFocusedCase(Point pt) { this.focusedCase = pt; }
    
}
