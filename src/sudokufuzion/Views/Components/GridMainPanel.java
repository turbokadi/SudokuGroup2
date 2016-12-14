/**
 *
 *   //-- Create by J4BB3R<johanmaurel@gmail.com> --//
 *
 *   [Initial Date] : 08/12/2016
 *   [Last Date] : 09/12/2016
 *   [Description] :
 *      This class is the Parent grid class;
 *   [Increments] :
 *      - 09/12/2016 [v0.1] : Creation of the class with base attrs and methods;
 *
 **/

package sudokufuzion.Views.Components;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GridMainPanel extends JPanel{ // It's a JPanel modified to have the right comportement and attributes

    // Panel Configuration
    private final static int PANEL_WIDTH = 94, PANEL_HEIGHT = 94; // Size of SubGrib
    
    // Border Configuration
    public final static int BORDER_SIZE = 2; // Border size of the SubGrib, public to be use from the outside to excel offset position
    private final static Color BORDER_COLOR = new Color(80,80,80,255); // Border color of the SubGrib, public to be use from the outside to excel offset position
    private final static Border BORDER = (Border) new LineBorder(GridMainPanel.BORDER_COLOR, GridMainPanel.BORDER_SIZE); // Border Object of the SubGrib
    
    public GridMainPanel(){
        this.setSize(GridMainPanel.PANEL_WIDTH, GridMainPanel.PANEL_HEIGHT);
        this.setBorder(GridMainPanel.BORDER);
    }
    
    public GridMainPanel(int Width, int Height){
        this.setSize(Width, Height);
        this.setBorder(GridMainPanel.BORDER);
    }
    
}
