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

public class GridCase extends JTextField {
    
    // Size Configuration
    private static final int CASE_WIDTH = 30, CASE_HEIGHT = 30;
    
    // Font Configuration
    private static final Font BASE_FONT = new Font("baseFont", Font.BOLD , 15);
    
    //Color Normalized
    private static final Color BASE_COLOR = new Color(120,120,120,255);
    private static final Color FOCUS_COLOR = new Color(58,134,236,255);
    private static final Color ERROR_COLOR = new Color(232,35,35,255);
    
    // Border Configuration
    private static final Border BASE_BORDER = (Border) new LineBorder(GridCase.BASE_COLOR, 1);
    private static final Border FOCUS_BORDER = (Border) new LineBorder(GridCase.FOCUS_COLOR, 2);
    private static final Border ERROR_BORDER = (Border) new LineBorder(GridCase.ERROR_COLOR, 2);
    
    // State Configuration
    public static final int BASE = 0, FOCUS = 1, ERROR = 2;
    
    //====================//
    // Instance Attribute //
    //====================//
      
    private int State;
    
    public GridCase() {
        this.setFocusable(false);
        this.setCursor(null);
        this.setSize(GridCase.CASE_WIDTH , GridCase.CASE_HEIGHT);
        this.setHorizontalAlignment(GridCase.CENTER);
        this.setFont(GridCase.BASE_FONT);
        this.setState(GridCase.BASE);  
    }
    
    public GridCase(int Width, int Height) {
        this.setFocusable(false);
        this.setCursor(null);
        this.setSize( Width, Height);
        this.setHorizontalAlignment(GridCase.CENTER);
        this.setFont(GridCase.BASE_FONT);
        this.setState(GridCase.BASE);  
    }
    
    final public void setState(int State) {
        try {
            switch (State) {
                case GridCase.BASE :
                    this.setBorder(GridCase.BASE_BORDER);
                    this.State = GridCase.BASE;
                break;
                case GridCase.FOCUS :
                    this.setBorder(GridCase.FOCUS_BORDER);
                    this.State = GridCase.FOCUS;
                break;
                case GridCase.ERROR :
                    this.setBorder(GridCase.ERROR_BORDER);
                    this.State = GridCase.ERROR;
                break;
                default :
                    throw(new Exception("Unknown Type Definition"));
            }
        } catch (Exception e) {
            System.err.println("[GridCase] Failure "+Arrays.toString(e.getStackTrace()));
        }
    }
    
    final public int getState() { return State; }
        
}
