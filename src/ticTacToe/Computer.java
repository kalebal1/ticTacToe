package ticTacToe;
import java.util.Random;

public class Computer {
	Random random = new Random();
	Field field;

	public Computer(Field field) {
		this.field = field;
	}
	
	//picks a cell on the board at random
	//if cell is occupied, continues to pick until an empty cell is found
	//once an empty cell is found, sets that cell to "O"
	public void makeEasyMove() {
		System.out.println("Making move level \"easy\"");
		
		int x = random.nextInt(3);
		int y = random.nextInt(3);
		
		while(!field.getCell(x, y).equals("_")) {
			x = random.nextInt(3);
			y = random.nextInt(3);
		}
		field.setCell(x, y, "O");
	}

}
