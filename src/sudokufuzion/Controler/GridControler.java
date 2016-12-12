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
 *
 **/

package sudokufuzion.Controler;

import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GridControler {
    
    // Thread Runnable Declaration
    private final Runnable th = new Runnable() {
        @Override
        public void run() {
            long i=0;
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
    
    private ConcurrentLinkedQueue<KeyEvent> queue;
    private final Thread Th = new Thread(th);
    
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
                System.out.println("Vous avez Appuiez sur "+ (val-KeyEvent.VK_NUMPAD0));
            } 
            else if(val >= KeyEvent.VK_LEFT && val <= KeyEvent.VK_DOWN){
                              
                switch (val) {
                    case KeyEvent.VK_UP : 
                        System.out.println("Vous avez Appuiez sur UP");
                    break;
                    case KeyEvent.VK_DOWN :
                        System.out.println("Vous avez Appuiez sur DOWN");
                    break;
                    case KeyEvent.VK_RIGHT :
                        System.out.println("Vous avez Appuiez sur RIGHT");
                    break;
                    case KeyEvent.VK_LEFT :
                        System.out.println("Vous avez Appuiez sur LEFT");
                    break;
                    default :
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
