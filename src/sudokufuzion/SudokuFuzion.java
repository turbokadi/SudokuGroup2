/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokufuzion;
import sudokufuzion.Modele.Grid;
/**
 *
 * @author jabber
 */
public class SudokuFuzion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid mat = new Grid();
        //mat.fillGridTemp();
        for(int i=0; i<9 ; i++){
            for(int j = 0; j<9; j++){
                System.out.print("[" + mat.getCase(i, j) + "]");
            }
            System.out.println();
        }
    }
    
}
