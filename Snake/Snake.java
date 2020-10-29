import java.awt.Color;

import javax.swing.JPanel;

public class Snake extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -2888063292495053088L;

    int snakePosX;
    int snakePosY;
    int snakeDirX;
    int snakeDirY;

    Snake(int x, int y, int dirX, int dirY) {
        snakePosX = x;
        snakePosY = y;
        snakeDirX = dirX;
        snakeDirY = dirY;

        setLayout(null);
        setSize(Janela.BOARD_UNIT, Janela.BOARD_UNIT);
        setBackground(Color.green);
        setLocation(Janela.BOARD_UNIT * snakePosX, Janela.BOARD_UNIT * snakePosY);
        setVisible(true);
    }

    public void moveSnake() {
        snakePosX = snakePosX + snakeDirX;
        snakePosY = snakePosY + snakeDirY;
        setLocation(snakePosX * Janela.BOARD_UNIT, snakePosY * Janela.BOARD_UNIT);
        setVisible(true);
    }

    public void setSnakeDirectionVelocity(int x, int y) {
        snakeDirX = x;
        snakeDirY = y;
    }

    public boolean checkCollision() {
        if (snakePosX > Janela.BOARD_UNITS_X - 1 || snakePosX < 0 || snakePosY > Janela.BOARD_UNITS_Y - 1 || snakePosY < 0)
            return true;
            
        return false;
    }

    public void updatePos() {
        setVisible(false);
        setLocation(snakePosX * Janela.BOARD_UNIT, snakePosY * Janela.BOARD_UNIT);
        setVisible(true);
    }

}