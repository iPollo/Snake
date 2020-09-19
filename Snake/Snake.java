import java.awt.Color;

import javax.swing.JPanel;

public class Snake extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = -2888063292495053088L;

    
    int SNAKE_POS_X;
    int SNAKE_POS_Y;
    int SNAKE_DIR_X;
    int SNAKE_DIR_Y;

    Snake(int x, int y, int dirX, int dirY)
    {
        SNAKE_POS_X = x;
        SNAKE_POS_Y = y;
        SNAKE_DIR_X = dirX;
        SNAKE_DIR_Y = dirY;
        
        setLayout(null);
        setSize(Janela.BOARD_UNIT, Janela.BOARD_UNIT);
        setBackground(Color.green);
        setLocation(Janela.BOARD_UNIT * SNAKE_POS_X, Janela.BOARD_UNIT * SNAKE_POS_Y);
        setVisible(true);
    }

    public void moveSnake()
    {
        SNAKE_POS_X = SNAKE_POS_X + SNAKE_DIR_X;
        SNAKE_POS_Y = SNAKE_POS_Y + SNAKE_DIR_Y;
        setLocation(SNAKE_POS_X * Janela.BOARD_UNIT, SNAKE_POS_Y * Janela.BOARD_UNIT);
        setVisible(true);
    }

    public void setSnakeDirectionVelocity(int x, int y)
    {
        SNAKE_DIR_X = x;
        SNAKE_DIR_Y = y;
    }

    public boolean checkCollision()
    {
        if(SNAKE_POS_X > Janela.BOARD_UNITS_X -1 || SNAKE_POS_X < 0 || SNAKE_POS_Y > Janela.BOARD_UNITS_Y -1 || SNAKE_POS_Y < 0 )
            return true;
        return false;
    }

    public void updatePos()
    {
        setVisible(false);
        setLocation(SNAKE_POS_X * Janela.BOARD_UNIT, SNAKE_POS_Y * Janela.BOARD_UNIT);
        setVisible(true);     
    }

}