package ticTacToe;
import java.util.Random;

public class Computer extends Player{
	Random random = new Random();
    private Field field;
    private String difficulty;
    private String marker;

    public Computer(Field field, PlayerMarker marker, String diff) {
    	super("computer", marker);
        this.field = field;
        this.difficulty = diff;
        this.marker = marker.toString();
    }
    
    public void makeMove() {
    	
    	switch (this.difficulty) {
    	case "easy": 
    		makeEasyMove();
    	case "medium":
    		makeMediumMove();
    	case "hard":
    		makeHardMove();
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
    
    public void makeMediumMove() {
    	makeEasyMove();
    }
    
    public void makeHardMove() {
    	makeEasyMove();
    }
    
    private void bestMove(Field board, Player player) {
    	int bestScore = Integer.MIN_VALUE;
    	int move = 0;
    	boolean isMaximizing = player.getMarker().equals(this.marker);
    	
    	for(int r = 0; r < 3; r++) {
    		for(int c = 0; c < 3; c++) {
    			if(board.getCell(r, c).equalsIgnoreCase("_")) {
    				board.setCell(r, c, player.getMarker());
    				int score = minimax(board, player, isMaximizing);
    				board.setCell(r, c, "_");
    				
    				if(score > bestScore) {
    					bestScore = score;
    				}
    				
    				move = (r * 3) + c;
    				
    			}
    		}
    	}
    	
    	board.setCell(Math.floorDiv(move, 3), move % 3, player.getMarker());
    }
    
    
    private int getScore(String str) {
    	int score = 0;
    	if(str.equals(marker)) {
    		score = 10;
    	} else if (str.equals(getOpponentMarker())) {
    		score = -10;
    	} else if (str.equals("tie")) {
    		return score;
    	}
    	return Integer.MIN_VALUE;
    }
    
    private int minimax(Field newBoard, Player player, boolean isMaximizing) {
    	
    	// checks if game is already over, and if so returns terminal score
    	String result = newBoard.findWinner();
    	int score = getScore(result);
    	if(score != Integer.MIN_VALUE) {
    		return score;
    	}
    	
    	
    	return 0;
    	
    }
    

}

