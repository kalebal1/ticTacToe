package ticTacToe;

abstract class Player {
	
	enum PlayerMarker {
    	X("X"), 
    	O("O");
		
		private String marker;
		private String opponentMarker;
		
        PlayerMarker(String marker){
            this.marker = marker;
            this.opponentMarker = marker.equals("X") ? "O" : "X";
        }
    }
	
	PlayerMarker playerMarker;
	String type;

	public Player(String type, PlayerMarker marker) {
		this.type = type;
		this.playerMarker = marker;
	}
	
	
	public String getMarker() {
        return playerMarker.marker;
    }
	
	public String getOpponentMarker() {
		return playerMarker.opponentMarker;
	}
	
	public void makeMove() {
		
	}

}
