/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 08/12/2016
 *   [Last Date] : 12/12/2016
 *   [Description] :
 *      This class is the grid controler;
 *   [Increments] :
 *      - 09/12/2016 [v0.1] : Creation of the Class + main method;
 *      - 11/12/2016 [v0.2] : Adding Taskthread
 *      - 12/12/2016 [v0.3] : Adding focus movement, value change and error gesture
 *
 **/

package sudokufuzion.Controler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import sudokufuzion.Controler.GridEvents.Case;
import sudokufuzion.Controler.GridEvents.ChangeValueEvent;
import sudokufuzion.Controler.GridEvents.ErrorEvent;
import sudokufuzion.Controler.GridEvents.MoveFocusEvent;
import sudokufuzion.Modele.Grid;

public class GridControler extends Observable{ // This Class implements an Observer pattern with the View
    
    
    private final Runnable th = new Runnable() { // Thread Runnable Declaration
        @Override
        public void run() {
            notifyObservers(new ChangeValueEvent().putGridIntoArrayList(grid)); // Initial Loading of the matrix 
            while (true) { // Infinite Loop
                try {
                    if(queue.isEmpty()) synchronized (Th) { Th.wait(); } // When the Tasks Queue is empty the Thread is stopped and wait for a resume signal
                    handleEvent(queue.poll()); // It Take and Remove the task from the Queue and give it to an analyse method "handleEvent()" 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    //====================//
    // Instance Attributes //
    //====================//    
    
    private Point focusedCase; // It's the Coordinates of the case who is focused on the GUI 
    private ConcurrentLinkedQueue<KeyEvent> queue; // The task Queue
    private final Thread Th = new Thread(th); // The Thread Declaration
    private Grid grid = new Grid(); // Model Grid Declaration + Instanciation
    private int countTry = 0;

    public int getCountTry() {
        return countTry;
    }

    public void setCountTry(int countTry) {
        this.countTry = countTry;
    }
    
    public GridControler() {
        this.queue = new ConcurrentLinkedQueue<>(); 
    }
    
    public void runControler() { Th.run(); } // Method who start the Thread
    
    public void addToTasksQueue(KeyEvent evt) { // Method who push tasks into the Queue
        try {
            if(queue.isEmpty()) { // If the queue is Empty,  push your task and notify the Thread for a waiting Traitement
                queue.offer(evt);
                synchronized (Th) { Th.notify(); } // Equivalent to Thread.resume()
            } else {
                queue.offer(evt); // The task is push into the Queue
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void handleEvent(KeyEvent evt) {
        
        try {
            
            int val = evt.getKeyCode(); // Get the value of the Pressed Key
            
            if (val == KeyEvent.VK_DELETE) { // If the Key is a Suppr, it send to view an clear order for the focusedCase coordinates
                if (!grid.verifyInitialValue(focusedCase)) { // Verify if the case is an non modifiable grid case
                    
                    sendChangeValueEvent(focusedCase, 0); // send change zero to assign a null string into the grid case
                    
                }
            }
            else if(val >= KeyEvent.VK_NUMPAD1 && val <= KeyEvent.VK_NUMPAD9) { // Detect if is a pav num Key
                if (!grid.verifyInitialValue(focusedCase)) { // Verify if the case is an non modifiable grid case 
                    
                    countTry++;
                    
                    int keyValue = val - KeyEvent.VK_NUMPAD0; // Value of the key compare to 0 ID value
                    
                    sendChangeValueEvent(focusedCase, keyValue); // send change val to assign the value into the grid case
                    
                    ArrayList<Point> buff = grid.setCase(this.focusedCase.x, this.focusedCase.y, keyValue); // check errors
                    
                    if(buff!=null) { // if non null means the Case generate errors
                        
                        ErrorEvent ec = new ErrorEvent(buff);
                        ec.setInitialCase((Point) focusedCase.clone()); // Assign case who generate error 
                        this.notifyObservers(ec); // notify view
                        
                    } else {
                        
                        if (grid.victory()) this.notifyObservers(null);
                        
                    }
                    
                }
            } 
            else if(val >= KeyEvent.VK_LEFT && val <= KeyEvent.VK_DOWN){ // If the key is a movement Key UP, DOWN, LEFT, RIGHT
                              
                switch (val) {
                    
                    case KeyEvent.VK_UP : 
                        this.notifyObservers(new MoveFocusEvent().setMoveUp()); // notify view with the correspondant move value
                    break;
                    case KeyEvent.VK_DOWN :
                        this.notifyObservers(new MoveFocusEvent().setMoveDown()); // notify view with the correspondant move value
                    break;
                    case KeyEvent.VK_RIGHT :
                        this.notifyObservers(new MoveFocusEvent().setMoveRight()); // notify view with the correspondant move value
                    break;
                    case KeyEvent.VK_LEFT :
                        this.notifyObservers(new MoveFocusEvent().setMoveLeft()); // notify view with the correspondant move value
                    break;
                    default :
                        
                }     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setFocusedCase(Point pt) { this.focusedCase = pt; }
    
    private void sendChangeValueEvent(Point pt, int value) {
        
        ChangeValueEvent ev = new ChangeValueEvent();
        ev.add(new Case(pt.x, pt.y, value)); // Create an modication Event and add Case to modify
        this.notifyObservers(ev); // notify view
        
    }
    
    @Override
    public void notifyObservers(Object o1) { // Method who simplify notification process
        this.setChanged();
        super.notifyObservers(o1);
    }
    
    public void reloadNewGrid() {
        grid = new Grid(); // New Grid
        notifyObservers(new ChangeValueEvent().putGridIntoArrayList(grid)); // Initial Loading of the matrix
        countTry = 0;
    }
    
}
