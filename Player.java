package game;

public class Player { 
	private int turn;
	private int score = 0;

        // Player constructor
        // sets the player's turn (either 1 or 2)
	public Player(int turn){
            this.turn = turn;
	}
	
        // returns the player's turn 
	public int getTurn(){
            return turn;
	}

        // increments the player's score by 1
	public void setScore(){
            score += 1;
	}
        
        // sets the player's score to the value passed
        public void setScore(int score){
            this.score = score;
        }

        // returns the player's score
	public int getScore(){
            return score;
	}
        
        // calls on the move methods in Ball and Racquet
	public void move(Ball ball, Racquet racquet, Player player, int turn){
            ball.move(racquet, player, turn);
            racquet.move(player.getTurn());
	}
}