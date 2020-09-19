import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * Snake
 */
public class Janela extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public final static int BOARD_SIZE_X = 500;
    public final static int BOARD_SIZE_Y = 500;
    public final static int BOARD_UNIT = 10;
    public final static int BOARD_UNITS_X = BOARD_SIZE_X / BOARD_UNIT;
    public final static int BOARD_UNITS_Y = BOARD_SIZE_X / BOARD_UNIT;
    final int gameTickRate = 50;

    char playerLastKey = 'd';

    int snakeBodyAmount = 0;

    Snake[] snake = new Snake[1000];
    JPanel windowPanel = new JPanel();
    boolean gameRunning = false;
    Timer timer = new Timer();
    JLabel loseText = new JLabel();
    JLabel playerScore = new JLabel();
    Apple apple = new Apple(10, 10);
    {snake[0] = new Snake(0, 0, 1, 0);}

    public Janela() {

        // Configura o Window
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Snake Game");
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new KeyListener(){
            @Override
               public void keyPressed(KeyEvent e) {
                   if((e.getKeyChar() == 'w') && playerLastKey != 's'){ snake[0].setSnakeDirectionVelocity(0, -1); playerLastKey = 'w';}
                   if((e.getKeyChar() == 'a') && playerLastKey != 'd'){ snake[0].setSnakeDirectionVelocity(-1, 0); playerLastKey = 'a';}
                   if((e.getKeyChar() == 's') && playerLastKey != 'w'){ snake[0].setSnakeDirectionVelocity(0, 1); playerLastKey = 's';}
                   if((e.getKeyChar() == 'd') && playerLastKey != 'a'){ snake[0].setSnakeDirectionVelocity(1, 0); playerLastKey = 'd';}
               }

               @Override
               public void keyTyped(KeyEvent e) {}

               @Override
               public void keyReleased(KeyEvent e) {}
       });

        //Lose Text Label;
        loseText.setText("VOCÊ PERDEU / YOU LOSE");
        loseText.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        loseText.setOpaque(true);
        loseText.setBackground(Color.black);
        loseText.setForeground(Color.RED);
        loseText.setSize(loseText.getPreferredSize());
        loseText.setLocation(140, 200);
        loseText.setHorizontalAlignment(SwingConstants.CENTER);
        loseText.setVisible(false);

        //PlayerScore Text Label;
        playerScore.setText(String.valueOf(snakeBodyAmount));
        playerScore.setFont(new java.awt.Font("Arial", Font.BOLD, 28));
        playerScore.setOpaque(true);
        playerScore.setBackground(Color.black);
        playerScore.setForeground(Color.pink);
        playerScore.setSize(playerScore.getPreferredSize());
        playerScore.setLocation(5, 0);
        playerScore.setHorizontalAlignment(SwingConstants.CENTER);
        playerScore.setVisible(true);

        // Configura o panel do Window
        windowPanel.setLayout(null);
        windowPanel.setPreferredSize(new Dimension(BOARD_SIZE_X, BOARD_SIZE_Y));
        windowPanel.setBackground(Color.BLACK);
        windowPanel.add(snake[0]);
        windowPanel.add(loseText);
        windowPanel.add(apple);
        windowPanel.add(playerScore);

        // Final sets
        add(windowPanel);
        setVisible(true);
        startGame();
    }

    public static void main(String[] args) {
        new Janela();
    };

    TimerTask gametick = new TimerTask() {
        @Override
        public void run() {
            if (gameRunning) {
                snake[0].moveSnake();
                checkEat();
                updateSnakeBody();
                checkAutoCollision();
               if(snake[0].checkCollision())
                   endGame();
            }
        }
    };

    public void endGame()
    {
        timer.cancel();
        gameRunning = false;
        loseText.setVisible(true);
    }

    public void startGame()
    {
        gameRunning = true;
        timer.schedule(gametick, 0, gameTickRate);
        loseText.setVisible(false);
        apple.newApple();
    }

    public void checkEat()
    {
        if(snake[0].SNAKE_POS_X == apple.APPLE_POS_X && snake[0].SNAKE_POS_Y == apple.APPLE_POS_Y)
        {
            apple.newApple();
            newSnakeBody();
            playerScore.setText(String.valueOf(snakeBodyAmount));
            playerScore.setVisible(true);
        }
    }

    public void newSnakeBody()
    {
        snakeBodyAmount++;
        System.out.println();
        for(int i = 0; i < snakeBodyAmount; i++);
        snake[snakeBodyAmount] = new Snake(snake[snakeBodyAmount - 1].SNAKE_POS_X, snake[snakeBodyAmount - 1].SNAKE_POS_Y, snake[0].SNAKE_DIR_X, snake[0].SNAKE_DIR_Y);
        windowPanel.add(snake[snakeBodyAmount]);
        System.out.println(snake[snakeBodyAmount].SNAKE_POS_X);
        System.out.println(snake[snakeBodyAmount].SNAKE_POS_Y);
    }

    public void updateSnakeBody()
    {
        for(int i = snakeBodyAmount; i > 0; i--)
        {
            snake[i].SNAKE_POS_X = snake[i-1].SNAKE_POS_X;
            snake[i].SNAKE_POS_Y = snake[i-1].SNAKE_POS_Y;
            snake[i].updatePos();
        }
    }

    public void checkAutoCollision()
    {
        for(int i = snakeBodyAmount; i > 1; i--)
        {
            if(snake[0].SNAKE_POS_X == snake[i].SNAKE_POS_X && snake[0].SNAKE_POS_Y == snake[i].SNAKE_POS_Y)
                endGame();

        }    
    }

}