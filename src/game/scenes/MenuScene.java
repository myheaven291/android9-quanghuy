package game.scenes;

import game.GameWindow;
import game.controllers.Background;
import game.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by 123 on 04/05/2017.
 */
public class MenuScene implements GameScene {
    private Image logo, tut, spacebutton;
    private Background background, background_2;

    public MenuScene() {
        logo = Utils.loadImage("res/logo1945.png");
        tut = Utils.loadImage("res/tut.png");
        spacebutton = Utils.loadImage("res/spacebutton.png");
        background = new Background(0, 0, 600, 800, Utils.loadImage("res/background1.jpg"));
        background_2 = new Background(0, -800, 600, 800, Utils.loadImage("res/background2.jpg"));
    }

    @Override
    public void keyPresses(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameWindow.instance.setCurrentScene(new Level1Scene());
        }
    }

    @Override
    public void update() {
        background.update();
        background_2.update();
    }
    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        background_2.draw(graphics);
        graphics.drawImage(logo, 100, 255,375,200, null);
        graphics.drawImage(tut, 50,500,null);
        graphics.drawImage(spacebutton, 205,510, 100,80,null);
    }
}
