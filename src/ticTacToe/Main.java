package ticTacToe;

import java.util.*;

import ticTacToe.Player.PlayerMarker;

//this class constitutes the actual gameplay
//it handles user inputs and determines if there's a win
public class Main {
	
    static Field field = new Field();
    // static Computer computerPlayer = new Computer(field);
    static Player player1;
    static Player player2;
    static Scanner scanner = new Scanner(System.in);
    
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


    public static void main(String[] args) {
       
        // field.printField();
        
        /* while(gameState == GameState.UNFINISHED) {
        	userTurn();
        	if(gameState == GameState.UNFINISHED) {
        	computerTurn();
        	}
        }
        */
        menu();
      

    }
    
    //this method 
    private static void menu() {
    	System.out.println("Input command:");
    	
    	if(scanner.hasNext()) {
    		try {
    			String input = scanner.nextLine();
    			String[] inputarr = input.split(" ");
    			if(inputarr[0].equalsIgnoreCase("start")) {
    				start(inputarr);
    			} else if (inputarr[0].equalsIgnoreCase("exit")) {
    				exit();
    			} else {
    				System.out.println("Bad parameters!");
    				menu();
    			}
    		} catch (Exception e){
    			System.out.println("Bad parameters!");
    			menu();
    		}
    		
    	} else {
    		System.out.println("Bad parameters!");
    	}
    	
    	
    }
    
    //start game
    //parameters: who will play 'x' and who will play 'o'
    //acceptable arg values: 'user' or 'easy' (later add 'medium' and 'hard')
    private static void start(String[] inputarr) {
    	try {
    		String player1 = inputarr[1];
    		String player2 = inputarr[2];
    		System.out.println(player1 + "<-p1  p2--> " + player2);
    		determinePlayers(player1, player2);
    	} catch (Exception e) {
    		System.out.println("Bad parameters!" + " in start");
    	}
		
    	while(gameState == GameState.UNFINISHED) {
    		player1.makeMove();
    		player2.makeMove();
    	}
    	menu();
    	
    	
    	
    }
    
    //param: two string inputs from start()
    //verifies input is either "user" or "easy" then implements players as either User or Computer
    //player 1 is always X, 2 is always O
    private static void determinePlayers(String p1, String p2) {
    	p1 = p1.toLowerCase();
    	p2 = p2.toLowerCase();
    	
    	if(!p1.equalsIgnoreCase("user") && !p1.equalsIgnoreCase("easy")
    			&& !p1.equalsIgnoreCase("medium") && p1.equalsIgnoreCase("hard")) {
    		System.out.println("Bad parameters!" +  " in detPl");
    		System.out.println("p1 =" + p1);
    		menu();
    	} else if (!p2.equalsIgnoreCase("user") && !p2.equalsIgnoreCase("easy")
    			&& !p2.equalsIgnoreCase("medium") && !p2.equalsIgnoreCase("hard")) {
    		System.out.println("Bad parameters!" +  " in detPl");
    		System.out.println("p2 =" + p2);
    		menu();
    	}
    	
    	player1 = p1.equalsIgnoreCase("user") ? 
    			new User(field, PlayerMarker.X) : new Computer(field, PlayerMarker.X, p1);
    	player2 = p2.equalsIgnoreCase("user") ? 
    			new User(field, PlayerMarker.O) : new Computer(field, PlayerMarker.O, p2);
    	
    }
    
    //terminates the program
    private static void exit() {
    	
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

