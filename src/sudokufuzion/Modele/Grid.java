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
    private int[][] matrix2 =   {{0,2,3,0,0,0,0,6,0},
                                {0,0,0,0,0,0,0,8,0},
                                {0,4,0,3,2,9,0,0,7},
                                {0,0,0,7,0,0,6,0,9},
                                {0,8,2,0,0,6,0,4,0},
                                {6,0,0,0,3,0,5,1,0},
                                {9,0,5,0,0,1,0,0,2},
                                {0,0,0,2,0,0,0,3,0},
                                {0,1,0,0,8,0,9,0,0}};
    
    private int[][] matrix =   {{2,9,6,3,1,8,5,7,4},
                                {5,8,4,9,7,2,6,1,3},
                                {7,1,3,6,4,5,2,8,9},
                                {6,2,5,8,9,7,3,4,1},
                                {9,3,1,4,2,6,8,5,7},
                                {4,7,8,5,3,1,9,2,6},
                                {1,6,7,2,5,3,4,9,8},
                                {8,5,9,7,6,4,1,3,0},
                                {3,4,2,1,8,9,7,0,0}};
    
    private final int[][] initialMatrix; //Matrix of the initial matrix we cannot change
    private final int GRID_SIZE = 9;    //Size of the sudoku
    
    // Difficulty configuration
    public static final int EASY = 0, MEDIUM = 1, HARD = 2; //New feature
    private static List list = new ArrayList(); // List of digit (1,2,3,4,5,6,7,8,9) use for fillGrid, and can be modified
    
    
    
    public Grid(){          //Builder of the class Grid and init final initialMatrix 
        fillGrid(EASY);     //this method is here to fill the grid
        //checkGrid();        //this method verify if the grid is correct and if there is no error in
        initialMatrix = new int[GRID_SIZE][GRID_SIZE];  //Instanciation of the initialMatrix in a array of int
        
        //Browse all the value of the filled matrix (matrix) and put it on the final initial Matrix
        //to prohibit the user to change initial value of the matrix
        
        for (int y=0; y<GRID_SIZE; y++) {               
            for (int x=0; x<GRID_SIZE; x++) initialMatrix[y][x] = matrix[y][x];
        }
    }
    
    //Method for recover the value of a case
    //being in the grid of coordinates corresponding to the parameters
    public int getCase(int x, int y){
        if(x<GRID_SIZE+1 && y<GRID_SIZE+1 && x>-1 && y >-1){return matrix[y][x];}
        else return -1; //return -1 if the value in the matrxix[y][x] is not correct
    }
    
    //This method returns a matrix of integer
    public int[][] getGrid(){
        return matrix; 
    }
    
    //This method returns a boolean, this method is used to verify if the value
    //belongs tp the initial matrix
    public boolean verifyInitialValue(Point pt){ 
        return initialMatrix[pt.y][pt.x]!=0;
    }
    
    
    //Method to put a number in a case
    public ArrayList<Point> setCase(int x, int y, int value){
        
        ArrayList<Point> list = new ArrayList<>(); //Instanciaton of a list for the return of List
        try {
            //complete the case with the value if there is no error
            if(x<GRID_SIZE && y<GRID_SIZE && x>-1 && y >-1 && value<GRID_SIZE+1 && value>0) {
                list = this.DetectError(x, y, value);   //Fill of the list for each error
                if(list == null) {                      //if there is no error (value possible)
                    matrix[y][x] = value;               //init the case [y][x]
                    return null;                        //return null, this means that a value is in the case
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; //return the list, this represents the case in conflict
    }

    
    //This method fill the entire Grid randomly, using arrayList and shuffling of Collection
    //The parameter represents the difficulty of the grid (New Feature)
    public void fillGrid(int diff){ 
        do{ 
            matrix = new int[9][9];         //init matrix 9 by 9
            list.clear();                   //clear the list to be sure, there is no error
            for(int i=1;i<GRID_SIZE+1;i++){ // add 1,2,3,4,5,6,7,8,9 in the list (digit)
                list.add(i);
            }
            Collections.shuffle(list);      //shuffling of the list
            List list0 = new ArrayList();   //Creation of 3 list usefull in the future 
            List list1 = new ArrayList();   //to fill beginning of the grid
            List list2 = new ArrayList();
            for(int i=0; i<3;i++){
                list0.add((int)list.get(i));    //add in the 3 list the number of the first 
                list1.add((int)list.get(i+3));  //list to complete the 2 next lines
                list2.add((int)list.get(i+6));  //The list takes differents value to avoid errors
            }
            for(int i =0; i<9;i++){setCase(0,i,(int)(list.get(i)));} //set the suffling list in the first line
            for(int i = 1; i<3; i++){
                Collections.shuffle(list0);     //List shuffle
                Collections.shuffle(list1);
                Collections.shuffle(list2);
                for(int x=0;x<GRID_SIZE;x++){   //fill the second and third line the the 3 list in a row
                    int T=x;                    //T : value to facilitate the filling of the grid
                    if(i==2){                   //if we are in this third line [y][2]
                        T= (x+3)%GRID_SIZE;     //to avoid the exeeding in the grid
                    }
                    if(x<3) setCase(i,T,(int)list2.get(T%3));       //fill the grid using list 2
                    if(x>2 && x<6) setCase(i,T,(int)list0.get(T%3));//fill the grid using list0
                    if(x>5) setCase(i,T,(int)list1.get(T%3));       //fill the grid using list1
                }
            }
            list0.clear();list1.clear();list2.clear();  //clear the list to use it again with differents values
            for(int i=0; i<3;i++){                      //I add the value - with a sub grid already filled,
                list0.add(matrix[6][i]);                //Every column of the sub grid represents a list
                list1.add(matrix[7][i]);
                list2.add(matrix[8][i]);
            }
            for(int j=0; j<2; j++){
                Collections.shuffle(list0);             //Shuffling of the list 
                Collections.shuffle(list1);
                Collections.shuffle(list2);
                for(int i=0; i<3;i++){
                    setCase(i+3*(1+j),j+7,(int)list0.get(i));   //fill the grid using list 0
                    setCase(i+3*(1+j),8-j*2,(int)list1.get(i)); //fill the grid using list 1
                    setCase(i+3*(1+j),j+6,(int)list2.get(i));   //fill the grid using list 2
                }
            }
            list0.clear();list1.clear();list2.clear();list.clear();     // clear of the list
            GenCase(4,1); GenCase(7,4); GenCase(5,2);                   //Init some case the limit the number
                                                                        //of possibilities to end the filling of the grid
            fillEndGrid();          //Ending of the filling grid
        }while(!gridFull());        //If the method fillEndGrid doesn't fill the entire grid, it repeat until it fill it entirely  
    }
    
    //This method return all points in conflict with matrix[Y][X]
    //val is the number you want to put in the case
    public ArrayList<Point> DetectError(int X, int Y, int val){

        ArrayList<Point> error = new ArrayList();   //Instanciation of a list to return
        for(int y=0; y<GRID_SIZE; y++){             //Browse all the column and add point in the list error if there is conflict
            if(matrix[y][X]==val && y!=Y) error.add(new Point(X,y)); 
        }
        for(int x=0; x<GRID_SIZE; x++){             //Browse all the line and add point in the list error if there is conflict
            if(matrix[Y][x]==val && x!=X) error.add(new Point(x,Y));
        }
        int offy = Y-Y%3;   //value use to identificate the sub-grid (line)
        int offx = X-X%3;   //value use to identificate the sub-grid (column)
        for(int y=offy; y<offy+3;y++){
            for(int x=offx ; x<offx + 3; x++){          //Browse all the case in the sub-grid and add point in the list error if there is conflict
                    
                if(matrix[y][x]==val && y!=Y && x!=X) error.add(new Point(x,y));
            }
        }
        if(error.isEmpty()) return null;    //return null if there is no error
        else return error;                  //return the list error if there are error(s);
    }
    
    
    //This method returns the number of possibilities in a case [y][x]
    public int nbOccurenceCase(int x, int y){
        
        int nb=0;   //init the value we will return
        for(int digit=1; digit<GRID_SIZE+1;digit++){
            
            if(DetectError(x, y, digit)==null) nb++; //if no error, add one possibility
        }
        return nb;      //return the number of possibilities
    }
    
    //This method returns the digit we can put in the case [y][x]
    public List listOccurenceCase(int x, int y){
        
        List nbValide = new ArrayList(); //the list we will return
        
        for(int digit=1; digit<GRID_SIZE+1;digit++){   //if no error, add the possible digit
            
            if(this.DetectError(x, y, digit)==null){
                nbValide.add(digit);
            }
        }
        if(nbValide.isEmpty()){             //if no possible digit, return null
            return null;
        }
        else{
            Collections.shuffle(nbValide);  //shuffling (to add a "random value") 
            return nbValide;                //and returns of the list of digit
        }
    }
    
    //This method put a possible digit in the case [y][x]
    //if not possible, it doesnt fill the case
    public void GenCase(int x, int y){      
        List nbValide = listOccurenceCase(x,y);
        if(nbValide != null){                       //if no possible digit
            matrix[y][x]= (int) nbValide.get(0);    //filling of the case
        }
    }
    
    //This method fill the ~36 case left with nothing
    //This method is called in fillGrid()
    public void fillEndGrid(){
        int round=0, numberOfgenCase;   //round : number of round in the while 
        boolean fin = true;             //numberOfgenCase : number of genCase during the first process
        while(fin){                     //fin : boolean to set while end
            numberOfgenCase=0;
            for(int x=3; x<GRID_SIZE; x++){         //FIRST PROCESS
                for(int y=0;y<GRID_SIZE-3; y++){
                    if(matrix[y][x]==0){            //verify if the case is not fill
                        if(nbOccurenceCase(x,y)==1){//look if the number of possibilities equals to one
                            GenCase(x,y);           //genCase if there is only possible digit
                            numberOfgenCase++;      //increments the counter
                        }
                    }
                }
            }
            if(numberOfgenCase==0){             //if no more evidence
                int[] tab = new int[36];        //tab represents the sub-grid we tried to fill in this method
                for(int i=0;i<36;i++) tab[i]=GRID_SIZE+2;   //browse array and fill it with maximal value
                int numCase=0;  //int to keep the index of the tab where the value is minimal
                int x;          //represents x in the grid
                int y;          //represents y in the grid
                for(int i=0; i<36;i++){     //browse array
                    x = i/6;                //init f(i)=x
                    y = i%6+3;              //init f(i)=y
                    if(matrix[y][x]==0){    //if matrix[y][x] not fill
                        tab[i]=nbOccurenceCase(x,y);    //put the number of possibilities in the array, index i
                    }
                    if(i>0){        //wait i=1
                        if(tab[i-1]>tab[i]) numCase=i;      //compare the differents value of the array
                    }
                }
                GenCase((numCase%6+3),numCase/6);       //genCase with the minimal possibilities
            }
            round++;            //increments round of while
            if(round>30){       //at 30 we have complete all the case we can
                                //the next case we will complete will unblocked the remains of the cases
                for(int x=3; x<GRID_SIZE; x++){     //Browse grid
                    for(int y=0; y<GRID_SIZE-3;y++){    
                        if(matrix[y][x]==0){    //search for empty case
                            GenCase(x,y);       //complete the case
                        }
                    }
                }
                fin=false;      //set fin false to end the while
            }
        }
    }
    
    //This method return a boolean, if true : grid is full. if false : grid is not full
    public boolean gridFull(){
        for(int x=0; x<GRID_SIZE;x++){
            for(int y=0; y<GRID_SIZE;y++){  //Browse grid
                if(matrix[y][x]==0){        //Looking for a empty case
                    return false;
                }
            }
        }
        return true;
    }
    
    //This method verifies every case of the grid and checks for errors
    public boolean checkGrid(){
        for(int x=0; x<GRID_SIZE;x++){      //browse grid
            for(int y=0; y<GRID_SIZE;y++){
                if(DetectError(x,y,matrix[y][x])!=null) {       //if error
                    return false;                           //grid is false
                }
            }
        }
        return true; //Grid is well-filled
    }
    
    //This method return true if the grid is full and without error
    //This method use the two previous methods
    //True means victory :)
    public boolean victory() {
        return gridFull() && checkGrid();
    }
    
}