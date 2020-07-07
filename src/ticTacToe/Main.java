package ticTacToe;

import java.util.*;

//this class constitutes the actual gameplay
//it handles user inputs and determines if there's a win
public class Main {
	
    static Field field = new Field();
    static Computer computerPlayer = new Computer(field);
    static Scanner scanner = new Scanner(System.in);
    static int numberOfOs = 0;
    static int numberOfXs = 0;
    static int numberEmpty = 0;
    
    public enum GameState {
        UNFINISHED("Game not finished"),
        DRAW("Draw"),
        XWIN("X wins"),
        OWIN("O wins");

        String message;
        GameState(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
    
    static GameState gameState = GameState.UNFINISHED;

    //X always starts, but after that it's determined by number of each on board
    public enum WhosTurn {
        XTURN, YTURN
    }
    static WhosTurn whosTurn = WhosTurn.XTURN;

    public static void main(String[] args) {
       
        field.printField();
        
        while(gameState == GameState.UNFINISHED) {
        	userTurn();
        	if(gameState == GameState.UNFINISHED) {
        	computerTurn();
        	}
        }
      

    }
    
    public static void userTurn() {
    	getCoordinates();
    	// getGameState();
    	field.printField();
    	whosTurn = WhosTurn.YTURN;
    }
    
    public static void computerTurn() {
    	computerPlayer.makeEasyMove();
    	field.printField();
    	setGameState();
    	whosTurn = WhosTurn.XTURN;
    }

    //asks for user input and responds to common errors
    //user must input coordinates as two ints to continue
    //after coordinates are determined, this method calls checkValidCoordinates
    //if coordinates aren't valid, the cycle continues
    public static void getCoordinates(){
        System.out.println("Enter the coordinates:");
        int x = -1;
        int y = -1;
        int inputX = -10;
        int inputY = -10;

        if(scanner.hasNext()) {
            try {
                inputX = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
                while(inputX == -10){
                    try {
                        inputX = Integer.parseInt(scanner.next());
                    } catch (Exception ex) {
                        scanner.nextLine();
                        System.out.println("You should enter numbers!");
                    }
                }
            }
            try {
                inputY = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
            }
        }
        x = inputX;
        y = inputY;
        if(!checkValidCoordinates(x, y)){
            getCoordinates();
        }

    }

    // adjusts user input coordinates to array values
    // x coordinates remain the same
    private static int translateInputCoord(int y){
        int newy = y == 3 ? 0 : y == 2 ? 1 : 2;
        return newy;
    }

    /*
    called by getCoordinates
    param: user input for next move
    prints error message if coordinates are not valid
    otherwise, sends coordinates to placeMove
     */
    private static boolean checkValidCoordinates(int x, int y){
        if(x > 3 || x < 1 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            // System.out.println("x = " + x + "y = " + y);
            return false;
        }
        y = translateInputCoord(y);
        x = x - 1;
        if (!field.getCell(x, y).equals("_")){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            placeMove(x, y);
            return true;
        }
    }

    // only called when checkValidCoordinates determines legal move
    // places user's move on board according to who's turn it is
    //prints field as it stands following added move
    // determines if move changed the state of the game
    private static void placeMove(int x, int y){
       
            field.setCell(x, y, "X");
       
        // field.printField();
        //setGameState();
    }

    //determines whether to place an X or an 0 based on
    //who moved last
    public static WhosTurn getWhosTurn(){
       
        if(whosTurn == WhosTurn.XTURN) {
        	whosTurn = WhosTurn.YTURN;
        } else {
        	whosTurn = WhosTurn.XTURN;
        }
        return whosTurn;
    }


    public static void setGameState(){
        field.tallyCells();
        if(field.checkWins("X")) {
            gameState = GameState.XWIN;
            System.out.println("X wins");
        } else if(field.checkWins("O")){
            gameState = GameState.OWIN;
            System.out.println("O wins");
        } else if (field.getXs() + field.getOs() == 9){
            gameState = GameState.DRAW;
            System.out.println("Draw");
        } else {
            gameState = GameState.UNFINISHED;
        }
    }
   
    public static void getGameState(){
        System.out.println(gameState.getMessage());
    }
    
    

    
    

}

