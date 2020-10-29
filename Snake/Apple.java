import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

public class Apple extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -9143479096598508541L;

    int applePosX, applePosY;

    Apple(int x, int y) {
        applePosX = x;
        applePosY = y;

        setLayout(null);
        setSize(Janela.BOARD_UNIT, Janela.BOARD_UNIT);
        setBackground(Color.RED);
        setLocation(Janela.BOARD_UNIT * applePosX, Janela.BOARD_UNIT * applePosY);
        setVisible(true);
    }

    public void newApple() {
        setVisible(false);
        int randomX = ThreadLocalRandom.current().nextInt(0, 49 + 1);
        int randomY = ThreadLocalRandom.current().nextInt(0, 49 + 1);
        applePosX = randomX;
        applePosY = randomY;
        setLocation(applePosX * Janela.BOARD_UNIT, applePosY * Janela.BOARD_UNIT);
        setVisible(true);
    }

}