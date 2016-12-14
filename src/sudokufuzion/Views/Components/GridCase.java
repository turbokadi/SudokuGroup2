/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 08/12/2016
 *   [Last Date] : 09/12/2016
 *   [Description] :
 *      This class is the grid Case class;
 *   [Increments] :
 *      - 09/12/2016 [v0.1] : Creation of the class with base attrs and methods;
 *
 **/

package sudokufuzion.Views.Components;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import sudokufuzion.Controler.GridEvents.ErrorEvent;

public class GridCase extends JTextField { /* It's an JTextField modified to have the right comportement and attributs,
    a non focusable case, and composed with a border and a text field */
    
    // Size Configuration
    private static final int CASE_WIDTH = 30, CASE_HEIGHT = 30; // Size of the Case
    
    // Font Configuration
    private static final Font BASE_FONT = new Font("baseFont", Font.BOLD , 15); // Font of the Case
    
    //Color Normalized
    private static final Color BASE_COLOR = new Color(120,120,120,255); // Base color of the Case
    public static final Color NON_MODIFABLE_COLOR = new Color(0,50,126,255); // Color of the Initials Case 
    private static final Color FOCUS_COLOR = new Color(0,150,255,255); // Color of Focused Case
    private static final Color ERROR_COLOR = new Color(232,35,35,255); // Color of Errored Case
    
    // Border Configuration
    private static final Border BASE_BORDER = (Border) new LineBorder(GridCase.BASE_COLOR, 1); // Border base Case
    private static final Border FOCUS_BORDER = (Border) new LineBorder(GridCase.FOCUS_COLOR, 2); // Border focused Case
    private static final Border ERROR_BORDER = (Border) new LineBorder(GridCase.ERROR_COLOR, 2); // Border Errored Case
    
    // State Configuration
    public static final int BASE = 0, FOCUS = 1, ERROR = 2; // Type of State in public to be use from the outside
    
    //====================//
    // Instance Attribute //
    //====================//
      
    private int previousState = GridCase.BASE;
    private String previousText = "";
    private int State;
    private ErrorEvent errorEvent; // Error Event rely to the GridCase

    public ErrorEvent getErrorEvent() { return errorEvent; }

    public void setErrorEvent(ErrorEvent errorEvent) { this.errorEvent = errorEvent; }
    
    public GridCase() { // Configuration 
        
        this.setFocusable(false); // Deny focus to disable keyboard entry
        this.setCursor(null); // Deny cursor hover modification
        this.setSize(GridCase.CASE_WIDTH , GridCase.CASE_HEIGHT);
        this.setHorizontalAlignment(GridCase.CENTER);
        this.setFont(GridCase.BASE_FONT);
        this.setState(GridCase.BASE);  
        this.setForeground(GridCase.BASE_COLOR);
    }
    
    public GridCase(int Width, int Height) { // Alternative Configuration
        
        this.setFocusable(false);
        this.setCursor(null);
        this.setSize( Width, Height);
        this.setHorizontalAlignment(GridCase.CENTER);
        this.setFont(GridCase.BASE_FONT);
        this.setState(GridCase.BASE);  
        this.setForeground(GridCase.BASE_COLOR);
    }
    
    final public void setState(int State) {
        
        try {
            switch (State) { // set the State 
                case GridCase.BASE : 
                    this.setStateProcess(GridCase.BASE , BASE_BORDER); // Set Base State
                break;
                case GridCase.FOCUS :
                    this.setStateProcess(GridCase.FOCUS , FOCUS_BORDER); // Set Focus State
                break;
                case GridCase.ERROR :
                    if (this.State != FOCUS) this.setStateProcess(GridCase.ERROR , ERROR_BORDER); // Set Error Focus
                    else {
                        this.setStateProcess(GridCase.ERROR , ERROR_BORDER); 
                        this.previousState = BASE; /* Special Comportement Wanted, Unfocus previousState to not keep focus color on unfocused case,
                                                    3 type of states, broke the state and previoustate system */ 
                    }
                break;
                default :
                    throw(new Exception("Unknown Type Definition"));
            }
            
        } catch (Exception e) {
            System.err.println("[GridCase] Failure "+Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }
    
    final public int getState() { return State; }
    
    final public int getPreviousState() { return previousState; }
    
    public void setColorErrorInitialCase() {
        this.setForeground(ERROR_COLOR); // set to textfield Error Color
    }
    
    public void unsetColorErrorInitialCase() {
        this.setForeground(BASE_COLOR); // set to textfield Base Color
    }
    
    @Override
    public void setText(String string) {
        previousText = this.getText(); // Bufferrise the previous text of the Case
        super.setText(string); // Set the Text via it parent
    }
    
    public void setPreviousText() { this.setText(previousText); } 
    
    private void setStateProcess(int state, Border border) {
        
        this.previousState = this.State; // Bufferrise the previous State of the Case
        this.setBorder(border); // set the border according the State
        this.State = state; // set the current value
        
    }
        
}
