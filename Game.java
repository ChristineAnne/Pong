package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Game extends JPanel {
    
    static JFrame frame;
    Ball ball = new Ball(this);
    Racquet racquet1 = new Racquet(this);
    Racquet racquet2 = new Racquet(this);
    static Player player1 = new Player(1);
    static Player player2 = new Player(2);
    int speed;
    
    // this is for the keyboard inputs so that the compiler will know what to do if a certain key is pressed and released
    public Game() {
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
            }
            
            @Override
            public void keyReleased(KeyEvent e){
                racquet1.keyReleased(e);
                racquet2.keyReleased(e);
            }
            
            @Override
            public void keyPressed(KeyEvent e){
                racquet1.keyPressed(e);
                racquet2.keyPressed(e);
            }
        });
        setFocusable(true);        
    }
    
    // calls on the move method in the Player class
    private void move(){
        player1.move(ball, racquet2, player1, 1);
        player2.move(ball, racquet1, player2, 2);
    }
    
    // paints on the frame 
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // calls ont the paint methods found in the Ball and Racquet classes
        ball.paint(g2d);  
        racquet1.paint(g2d, player1);
        racquet1.paint(g2d, player2);
        
        // sets the color to light gray
        g2d.setColor(Color.LIGHT_GRAY);
        // sets the font to Verdana, plain text, with font size 10
        g2d.setFont(new Font("Verdana", Font.PLAIN, 10));
        g2d.drawString("Player 1", 5, 15);
        //sets the text to Bold and font size to 30
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        // display score
        g2d.drawString(String.valueOf(player1.getScore()),150, 100); 
        
        // same with above 
        g2d.setFont(new Font("Verdana", Font.PLAIN, 10));
        g2d.drawString("Player 2", 5, 360);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(player2.getScore()), 150, 300);
    }
    
    // determines if a game is over
    // the game is over if a player reaches three points
    public void gameOver() throws InterruptedException {
        int p1Score = player1.getScore();
        int p2Score = player2.getScore();
        
        if(p1Score == 3 || p2Score == 3){
            repaint();
            if(p1Score > p2Score){ // shows message dialogs indicating which player wins
                JOptionPane.showMessageDialog(this, "Player1 wins!\nScore: " + player1.getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
            }else {
                JOptionPane.showMessageDialog(this, "Player2 wins!\nScore: " + player2.getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
            }
           
            // the restart method restarts the game
            restart();
            
        }else{
            repaint();
        }
    }
    
    // restarts the game and deletes the old frame, replacing it with a new one when gamePlay() is called
    public void restart() throws InterruptedException {
        JOptionPane.showMessageDialog(this, "Restart?", "", JOptionPane.YES_NO_CANCEL_OPTION);
        
        // to restart the game, we first set the players' scores to 0 and the game speed back to 1
        // gamePlay() is then called
        player1.setScore(0);
        player2.setScore(0);
        frame.dispose();
        gamePlay();
    }
    
    // this is the flow of the game
    public static void gamePlay() throws InterruptedException {
        frame = new JFrame("Pong"); // creates a new frame with the title "Pong"
        Game game = new Game();
        
        // sets the background of the frame to black
        game.setBackground(Color.BLACK);
        
        // adds the component game to frame
        frame.add(game);
        frame.setSize(300, 400); // sets the size of the frame to the sizes indicataed
        frame.setVisible(true);  // sets the frame to visible meaning it is now shown on our computer screens
        frame.setResizable(false); // doesn't allow the user to resize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // sets the speed to 1
        game.speed = 1;
        
        // while this is true, the game will continue until one of the players reach a score of 3
        while(true){
            game.move();
            game.repaint();
            game.gameOver();
            Thread.sleep(10);
        }
    }
    
    // the main basically just calls the gamePlay() method
    // the game does not exit
    public static void main(String[] args) throws InterruptedException {
        gamePlay();
    }
}
