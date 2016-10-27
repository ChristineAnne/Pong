package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Racquet { 
	private static final int Y1 = 25;
	private static final int Y2 = 340;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int x = 130;
	int xa = 0;
        int xb = 0;
	private Game game;

        // Racquet constructor
        // we had to make a Game variable in order to easily access the variables found in the Game class
	public Racquet(Game game){
            this.game = game;
	}

	// moves the racquet left and right
	public void move(int turn){
            if(x + xa > 0 && x + xa < game.getWidth() - WIDTH){
                if(turn == 1)
                    x += xa;
                else if(turn == 2)
                    x += xb;
            }
	}

	// paints the racquet on the window frame
	public void paint(Graphics2D g, Player p){
            g.setColor(Color.GRAY);
            if(p.getTurn() == 1){
		g.fillRect(x, Y1, WIDTH, HEIGHT);
            }else{
		g.fillRect(x, Y2, WIDTH, HEIGHT);
            }
	}

        // what happens when the key is released
	public void keyReleased(KeyEvent e){
            xa = 0;
            xb = 0;
	}
        
        // if the player presses A or LEFT, the racquet moves to the left
        // else if the player presses D or RIGHT, the racquet moves to the right
        public void keyPressed(KeyEvent e){
           
            if(e.getKeyCode() == KeyEvent.VK_A){    // sir I don't know what is wrong with it, it worked before I did not change anything and now it's not working and I'm out of time
                xa = -game.speed;                   // A and D does not work, and if I press LEFT or RIGHT, the two racquets move together
            }
            if(e.getKeyCode() == KeyEvent.VK_D){
                xa = game.speed;
            }
            
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                xb = -game.speed;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                xb = game.speed;
            }
            
        }
        
        // returns a rectangle showing the bounds of the racquet
        // (in simpler words) it returns a rectangle with the racquet's size
	public Rectangle getBounds(Player player){ 
            if(player.getTurn() == 1){
                return new Rectangle(x, Y1, WIDTH, HEIGHT);
            }else{
                return new Rectangle(x, Y2, WIDTH, HEIGHT);	
            }
	}
        // returns the racquet's highest or lowest point where the ball can collide with the racquet
	public int getTopY(Player player){ 
            if(player.getTurn() == 1){
                return Y1 + HEIGHT;
            }else{
                return Y2 - HEIGHT;	
            }
	}
}
