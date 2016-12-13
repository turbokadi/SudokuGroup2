/**

 * //--Create by nico <nicolascorronel@gmail.com>;--//
 * [Contributors] none;
 * [Initial Date]: 09/12/2016
 * [Last Date]: 09/12/2016
 * [Description]:
 *      - This class is the Grid of the Sudoku;
 * [Increments]:
 *      - 09/12/2016[v0.1]: Creation of basic method
 
 */
 
package sudokufuzion.Modele;
import java.awt.Point;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    // Grid 
    private int[][] matrix =   {{0,2,3,0,0,0,0,6,0},
                                {0,0,0,0,0,0,0,8,0},
                                {0,4,0,3,2,9,0,0,7},
                                {0,0,0,7,0,0,6,0,9},
                                {0,8,2,0,0,6,0,4,0},
                                {6,0,0,0,3,0,5,1,0},
                                {9,0,5,0,0,1,0,0,2},
                                {0,0,0,2,0,0,0,3,0},
                                {0,1,0,0,8,0,9,0,0}};
    private final int[][] initialMatrix;
    private final int GRID_SIZE = 9;    
    
    // Difficulty configuration
    public static final int EASY = 0, MEDIUM = 1, HARD = 2;
    private static List list = new ArrayList();
    
    
    
    public Grid(){
        initialMatrix = new int[GRID_SIZE][GRID_SIZE];
        for (int y=0; y<GRID_SIZE; y++) {
            for (int x=0; x<GRID_SIZE; x++) initialMatrix[y][x] = matrix[y][x];
        }
    }
    
    //Méthode pour récuperer la valeur d'une case
    //étant dans la grille de coordonnées correspondant aux paramètres
    public int getCase(int x, int y){
        if(x<GRID_SIZE+1 && y<GRID_SIZE+1 && x>-1 && y >-1){return matrix[y][x];}
        else return -1;
    }
    
    public int[][] getGrid(){
        return matrix;
    }
    
    public boolean verifyInitialValue(Point pt){ 
        return initialMatrix[pt.y][pt.x]!=0;
    }
    
    
    //Méthode pour incrire un numero dans une case
    public ArrayList<Point> setCase(int x, int y, int value){
        
        ArrayList<Point> list = new ArrayList<>();
        try {
            if(x<GRID_SIZE && y<GRID_SIZE && x>-1 && y >-1 && value<GRID_SIZE+1 && value>0) {

                list = this.DetectError(x, y, value);
                if(list == null) {
                    matrix[y][x] = value;
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void fillGrid(int diff) throws Exception{
        
        for(int i=1;i<GRID_SIZE+1;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        List list0 = new ArrayList();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        for(int i=0; i<3;i++){
            list0.add((int)list.get(i));
            list1.add((int)list.get(i+3));
            list2.add((int)list.get(i+6));
        }
        for(int i =0; i<9;i++){setCase(0,i,(int)(list.get(i)));}
        for(int i = 1; i<3; i++){
            Collections.shuffle(list0);
            Collections.shuffle(list1);
            Collections.shuffle(list2);
            for(int x=0;x<GRID_SIZE;x++){
                int T=x;
                if(i==2){
                    T= (x+3)%GRID_SIZE;
                }
                if(x<3) setCase(i,T,(int)list2.get(T%3));
                if(x>2 && x<6) setCase(i,T,(int)list0.get(T%3));
                if(x>5) setCase(i,T,(int)list1.get(T%3));
            }
        }
        list0.clear();list1.clear();list2.clear();
        for(int i=0; i<3;i++){
            list0.add(matrix[3][i]);
            list1.add(matrix[4][i]);
            list2.add(matrix[5][i]);
        }
        for(int j=0; j<2; j++){
            Collections.shuffle(list0);
            Collections.shuffle(list1);
            Collections.shuffle(list2);
            for(int i=0; i<3;i++){
                setCase(i+3*(1+j),j+4,(int)list0.get(i));
                setCase(i+3*(1+j),5-j*2,(int)list1.get(i));
                setCase(i+3*(1+j),j+3,(int)list2.get(i));
            }
        }
        list0.clear();list1.clear();list2.clear();
        for(int x=3;x<GRID_SIZE;x++) {
            for(int y=0;y<3;y++){
                for(int j=1;j<GRID_SIZE+1;j++){
                    if(this.DetectError(x, y, j)==null){
                        setCase(x,y,j);
                    }
                }
            }
        }
        for(int x=3;x<GRID_SIZE;x++) {
            for(int y=6;y<GRID_SIZE;y++){
                for(int j=1;j<GRID_SIZE+1;j++){
                    if(this.DetectError(x, y, j)==null){
                        setCase(x,y,j);
                    }
                }
            }
        }
        
        Collections.shuffle(list0);
        System.out.println(list0);
        Collections.sort(list0);
        System.out.println(list0);

    }
    public ArrayList<Point> DetectError(int X, int Y, int val){

        ArrayList<Point> error = new ArrayList();
        for(int y=0; y<GRID_SIZE; y++){
            if(matrix[y][X]==val && y!=Y) error.add(new Point(X,y)); 
        }
        for(int x=0; x<GRID_SIZE; x++){
            if(matrix[Y][x]==val && x!=X) error.add(new Point(x,Y));
        }
        int offy = Y-Y%3;
        int offx = X-X%3;
        for(int y=offy; y<offy+3;y++){
            for(int x=offx ; x<offx + 3; x++){
                if(matrix[y][x]==val && y!=Y && x!=X) {
                    error.add(new Point(x,y));
                }
            }
        }
        if(error.isEmpty()) return null;
        else return error;
    }
}