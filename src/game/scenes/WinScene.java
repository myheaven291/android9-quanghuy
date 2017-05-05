package game.scenes;

import game.controllers.Background;
import game.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by 123 on 04/05/2017.
 */
public class WinScene implements GameScene{
    private Background background, background_2;
    private Image gameover;

    public WinScene(){
        background = new Background(0, 0, 600, 800, Utils.loadImage("res/background1.jpg"));
        background_2 = new Background(0, -800, 600, 800, Utils.loadImage("res/background2.jpg"));
        gameover = Utils.loadImage("res/won.png");
    }

    @Override
    public void keyPresses(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void update() {background.update();
        background_2.update();
    }

    @Override
    public void draw(Graphics graphics) {background.draw(graphics);
        background_2.draw(graphics);
        graphics.drawImage(gameover,125,250, 400,300,null);
    }
}
