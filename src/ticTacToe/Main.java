package ticTacToe;

import java.util.*;

public class Main {
	
    static String[][] field = new String[3][3];
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

        String input = scanner.nextLine();

        parseInput(input);
        printField();
        getCoordinates();
        getGameState();
        //printField();

    }

    /* converts line of symbols into 3x3 board
    also counts number of each piece to determine who's turn it is
    and game status
    */
    public static void parseInput(String input){
        int i = 0;
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                field[x][y] = Character.toString(input.charAt(i));
                if(field[x][y].equals("X")){
                    numberOfXs++;
                } else if(field[x][y].equals("O")){
                    numberOfOs++;
                } else if (field[x][y].equals("_")){
                    numberEmpty++;
                }
                i++;
            }
        }
    }

    //displays state of current field
    //called at beginning and following every move
    public static void printField(){
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
            System.out.println("x = " + x + "y = " + y);
            return false;
        }
        y = translateInputCoord(y);
        x = x - 1;
        if (!field[x][y].equals("_")){
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
    private static void placeMove(int x, int y){
        if(getWhosTurn() == WhosTurn.XTURN){
            field[x][y] = "X";
        } else {
            field[x][y] = "0";
        }
        printField();
        setGameState();
    }

    //determines whether to place an X or an 0 based on
    //number of each currently on board
    //returns findings to placeMove
    public static WhosTurn getWhosTurn(){
        tallyCells();
        if(numberOfOs == numberOfXs){
            whosTurn = WhosTurn.XTURN;
            return whosTurn;
        } else if (numberOfXs > numberOfOs) {
            whosTurn = WhosTurn.YTURN;
            return whosTurn;
        }
        return whosTurn;
    }

    //counts number of X's, Os, and empty cells
    //to determine who's next and game state
    public static void tallyCells(){
        numberOfXs = 0;
        numberOfOs = 0;
        numberEmpty = 0;
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){

                if(field[x][y].equals("X")){
                    numberOfXs++;
                } else if(field[x][y].equals("O")){
                    numberOfOs++;
                } else if (field[x][y].equals("_")){
                    numberEmpty++;
                }

            }
        }
    }

    public static void setGameState(){
        tallyCells();
        if(checkWins("X")) {
            gameState = GameState.XWIN;
        } else if(checkWins("O")){
            gameState = GameState.OWIN;
        } else if (numberEmpty == 0){
            gameState = GameState.DRAW;
        } else {

            gameState = GameState.UNFINISHED;
        }
    }
   
    public static void getGameState(){
        System.out.println(gameState.getMessage());
    }

    public static boolean checkWins(String str){
       
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

