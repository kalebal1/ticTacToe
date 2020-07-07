package ticTacToe;

abstract class Player {
	
	enum PlayerMarker {
    	X("X"), 
    	O("O");
		
		private String marker;
        PlayerMarker(String marker){
            this.marker = marker;
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

}
