package ticTacToe;

public class Field {
	String[][] field;
	int numX;
	int numO;
	int numEmpty;
	
	//sets up empty field for beginning of game
    public Field() {
    	field = new String[3][3];
    	for(int y = 0; y < 3; y++) {
    		for(int x = 0; x < 3; x++) {
    			field[x][y] = "_";
    		}
    	}
    	numX = 0;
    	numO = 0;
    }
    
    public void setCell(int x, int y, String str) {
    	field[x][y] = str;
    }
    
    public String getCell(int x, int y) {
    	return field[x][y];
    }
    
    public int getXs() {
    	return numX;
    }
    
    public int getOs() {
    	return numO;
    }
    
    //displays state of current field
    //called at beginning and following every move
    public void printField(){
        //upper border
        System.out.println("---------");

        for(int y = 0; y < 3; y++){
            //left border
            System.out.print("| ");

            for(int x = 0; x < 3; x++){
                //handles blank spaces
                if(field[x][y].equals("_")){
                    System.out.print("  ");
                } else {
                    System.out.print(field[x][y] + " ");
                }
            }
            //right border
            System.out.println("|");
        }
        //lower border
        System.out.println("---------");
    }
    
    
    //resets count of each type of cell
    public void tallyCells() {
    	numEmpty = 0;
    	numX = 0;
    	numO = 0;
    	
    	for(int y = 0; y < 3; y++){
    		for(int x = 0; x < 3; x++){

            if(field[x][y].equals("X")){
                numX++;
            } else if(field[x][y].equals("O")){
                numO++;
            } else if (field[x][y].equals("_")){
                numEmpty++;
            }

    		}
    	}
    }
    
    
    public boolean checkWins(String str){
        
        //checks for horizontal wins
        for(int row = 0; row < 3; row++){
            if(field[row][0].equals(str) &&
            		field[row][1].equals(str) &&
            		field[row][2].equals(str)){
                
                return true;
            }
        }
        //checks for vertical wins
        for(int col = 0; col < 3; col++){
            if(field[0][col].equals(str) &&
            		field[1][col].equals(str) &&
            		field[2][col].equals(str)){
                
                return true;
            }
        }
        //checks diagonal (top-R to B-L and top L to B-R)
        if(field[0][0].equals(str) &&
        		field[1][1].equals(str) &&
        		field[2][2].equals(str)){
           
            return true;
        }
        if(field[0][2].equals(str) &&
        		field[1][1].equals(str) &&
        		field[2][0].equals(str)){
       
            return true;
        }
        return false;
    }

    
}
