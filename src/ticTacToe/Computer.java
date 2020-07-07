package ticTacToe;
import java.util.Random;

public class Computer extends Player{
	Random random = new Random();
    private Field field;
  
    private String difficulty;

    public Computer(Field field, PlayerMarker marker, String diff) {
    	super("computer", marker);
        this.field = field;
        this.difficulty = diff;
    }
    
    public void makeMove() {
    	
    	switch (this.difficulty) {
    	case "easy": 
    		makeEasyMove();
		default:
			makeEasyMove();
    	}
    	
    }

    //picks a cell on the board at random
    //if cell is occupied, continues to pick until an empty cell is found
    //once an empty cell is found, sets that cell according to whose turn it is
    public void makeEasyMove() {
        System.out.println("Making move level \"easy\"");
        field.tallyCells();
        if(field.getEmpty() != 0){
            int x = random.nextInt(3);
            int y = random.nextInt(3);

            while(!field.getCell(x, y).equals("_")) {
                x = random.nextInt(3);
                y = random.nextInt(3);
            }
            field.setCell(x, y, this.getMarker());
            field.printField();
            Main.setGameState();
        } else {
        	field.printField();
            Main.setGameState();
        }

        
    }
    

}

