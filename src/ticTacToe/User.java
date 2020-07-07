package ticTacToe;
import java.util.Scanner;

public class User extends Player{
	Field field;
	static Scanner scanner = new Scanner(System.in);

	public User(Field field, PlayerMarker marker) {
		super("user", marker);
		this.field = field;
	}
	
	public void makeMove() {
		getCoordinates();
	}
	
	
   //asks for user input and responds to common errors
    //user must input coordinates as two ints to continue
    //after coordinates are determined, this method calls checkValidCoordinates
    //if coordinates aren't valid, the cycle continues
	private void getCoordinates(){
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
    private int translateInputCoord(int y){
        int newy = y == 3 ? 0 : y == 2 ? 1 : 2;
        return newy;
    }

    /*
    called by getCoordinates
    param: user input for next move
    prints error message if coordinates are not valid
    otherwise, sends coordinates to placeMove
     */
    private boolean checkValidCoordinates(int x, int y){
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
    private void placeMove(int x, int y){
            field.setCell(x, y, this.getMarker());
            Main.setGameState();
    }


}
