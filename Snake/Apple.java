import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

public class Apple extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -9143479096598508541L;

    int APPLE_POS_X, APPLE_POS_Y;

    Apple(int x, int y)
    {   
        APPLE_POS_X = x;
        APPLE_POS_Y = y;

        setLayout(null);
        setSize(Janela.BOARD_UNIT, Janela.BOARD_UNIT);
        setBackground(Color.RED);
        setLocation(Janela.BOARD_UNIT * APPLE_POS_X, Janela.BOARD_UNIT * APPLE_POS_Y);
        setVisible(true);
    }

    public void newApple()
    {
        setVisible(false);
        int randomX = ThreadLocalRandom.current().nextInt(0, 49 + 1);
        int randomY = ThreadLocalRandom.current().nextInt(0, 49 + 1);
        APPLE_POS_X = randomX;
        APPLE_POS_Y = randomY;
        setLocation(APPLE_POS_X * Janela.BOARD_UNIT, APPLE_POS_Y * Janela.BOARD_UNIT);
        setVisible(true);
    }
    
}