package game;

import game.controllers.Background;
import game.controllers.CollisionManager;
import game.controllers.ControllManager;
import game.enemies.*;
import game.player.Bonus;
import game.player.PlayerController;
import game.player.Rocket;
import game.scenes.GameScene;
import game.scenes.Level1Scene;
import game.scenes.MenuScene;
import game.utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by 123 on 09/04/2017.
 */
public class GameWindow extends Frame {
    GameScene currentScene;

    public static GameWindow instance;

    private BufferedImage backBufferImage;
    private Graphics backBufferGraphic;

    public void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }

    public GameWindow() {
        setVisible(true);
        setSize(600, 800);
        setTitle("Game 1945");
        instance = this;
        currentScene = new MenuScene();


        backBufferImage = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic = backBufferImage.getGraphics();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                currentScene.keyPresses(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentScene.keyReleased(e);
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentScene.update();
                    ControllManager.instance.update();

                    CollisionManager.instance.update();

                    repaint();
                }
            }
        });

        thread.start();
    }


    @Override
    public void update(Graphics g) {
        currentScene.draw(backBufferGraphic);
        ControllManager.instance.draw(backBufferGraphic);

        g.drawImage(backBufferImage, 0, 0, null);
    }
}
