package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball { // OKAY (y)
    private static final int DIAMETER = 30;
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    int speedUp = 1;
    private Game game;

    // Ball constructor
    public Ball(Game game){
        this.game = game;
    }
    
    // determines if the ball moves up, down, left or right
    public void move(Racquet racquet, Player player, int turn){     /******_Sir there is a bug in the setting of the score_******/
                                                                    /********_It doesn't score unless the racquet is hit_********/
        // moves the ball to the right
        if(x + xa < 0) { 
            xa = game.speed;
        }// moves the ball to the left
        else if(x + xa > game.getWidth() - DIAMETER){
            xa = -game.speed;
        }// moves the ball down
        else if(y + ya < 0 ){
            ya = game.speed;
            if(turn == 2){
                player.setScore();
            }
//            if(player.getTurn() == 2)
//                player.setScore();
//            ya = game.speed;
        }// moves the ball up
        else if(y + ya > game.getHeight() - DIAMETER){
            if(player.getTurn() == 1)
                player.setScore();
            ya = -game.speed;
        }// if the ball collides with the racquet, it moves up or down depending where the racquet is located
        else if(collision(racquet, player)){
            if(player.getTurn() == 1){
                ya = game.speed;
                y = racquet.getTopY(player)+ 3;
            }else if(player.getTurn() == 2){
                ya = -game.speed;
                y = racquet.getTopY(player) - DIAMETER;
            }
            
            // para hinay hinay ra ang pgspeed-up sa ball
            // I made a variable called speedUp which will make the ball go faster everytime it hits a racquet twice
            if(speedUp % 2 == 0)
                game.speed++;
            
            speedUp++;
        }
            
        x += xa;
        y += ya;
    }
    
    // paints the ball on the frame
    public void paint(Graphics2D g2d){
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(x, y, DIAMETER, DIAMETER);
    }

    // checks if the ball will collide with the racquet
    private boolean collision(Racquet racquet, Player player){
        return racquet.getBounds(player).intersects(getBounds());
    }

    // returns a Rectangle with the given DIAMETERs of the ball
    public Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}