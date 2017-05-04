package game.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by 123 on 04/05/2017.
 */
public interface GameScene {
    void keyPresses(KeyEvent e);
    void keyReleased(KeyEvent e);
    void update();
    void draw(Graphics graphics);
}