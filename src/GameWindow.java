import enemies.*;
import player.PlayerController;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by 123 on 09/04/2017.
 */
public class GameWindow extends Frame {

    Image backgroundImage;

    BufferedImage backBufferImage;
    Graphics backBufferGraphic;

    boolean isUpPressed;
    boolean isDownPressed;
    boolean isLeftPressed;
    boolean isRightPressed;
    boolean isSpacePressed;

    PlayerController playerController;
    ArrayList<EnemyController> enemyControllers;
    BossControl bossControl;

    public GameWindow() {
        setVisible(true);
        setSize(800, 600);
        setTitle("Game 1945");
        enemyControllers = new ArrayList<>();

        playerController = new PlayerController(400 - 25, 600 - 70, Utils.loadImage("res/plane3.png"));

        //Create Enemies
        for (int x = 200; x < getWidth(); x += 200) {
            EnemyController enemyController = new EnemyController(x, 0, Utils.loadImage("res/enemy-green-3.png"));
            enemyController.setMoveBehavior(new MoveBehavior());

            if (x == 400) {
                enemyController.setMoveBehavior(new MoveBehavior());
            } else if (x == 200) {
                enemyController.setMoveBehavior(new ToLeftBehavior());
            } else enemyController.setMoveBehavior(new ToRightBehavior());

            enemyControllers.add(enemyController);
        }
        bossControl = new BossControl(-150, 150, Utils.loadImage("res/ufo.png"));

        backBufferImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
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
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        isRightPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeftPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        isUpPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDownPressed = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpacePressed = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        isRightPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeftPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        isUpPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDownPressed = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpacePressed = false;
                        break;
                }
            }
        });

        backgroundImage = Utils.loadImage("res/background.png");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    playerController.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed);

                    playerController.update();
                    bossControl.update();

                    for (EnemyController enemyController : enemyControllers) {
                        enemyController.update();
                    }

                    repaint();
                }
            }
        });

        thread.start();
    }

    @Override
    public void update(Graphics g) {
        backBufferGraphic.drawImage(backgroundImage, 0, 0, 800, 600, null);
        playerController.draw(backBufferGraphic);
        bossControl.draw(backBufferGraphic);

        for (EnemyController enemyController : enemyControllers) {
            enemyController.draw(backBufferGraphic);
        }

        g.drawImage(backBufferImage, 0, 0, null);
    }
}
