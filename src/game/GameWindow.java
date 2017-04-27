package game;

import game.controllers.CollisionManager;
import game.enemies.*;
import game.player.Bonus;
import game.player.PlayerBullet;
import game.player.PlayerController;
import game.utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 123 on 09/04/2017.
 */
public class GameWindow extends Frame {

    private Image backgroundImage;

    private BufferedImage backBufferImage;
    private Graphics backBufferGraphic;

    private boolean isUpPressed,
            isDownPressed,
            isLeftPressed,
            isRightPressed,
            isSpacePressed,
            isXpressed,
            shootEnableEnemy = true,
            createEnemy = true,
            createEnemy2 = false;
    private int cdTime;
    private int cdCreateEnemy;
//            cdCreateEnemy2 = 500;

    private PlayerController playerController;
    private ArrayList<EnemyController> enemyControllers;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<PlayerBullet> playerBullets;
    private UfoControl ufoControl;
    private Bonus bonus;

    public GameWindow() {
        setVisible(true);
        setSize(800, 600);
        setTitle("Game 1945");
        enemyControllers = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();

        playerController = new PlayerController(400 - 25, 600 - 70, Utils.loadImage("res/plane3.png"));
        playerController.setPlayerBullets(playerBullets);
        bonus = new Bonus(1000,0,35,30,Utils.loadImage("res/heart.png"));
        ufoControl = new UfoControl(-150, 150, 150, 100, Utils.loadImage("res/ufo.png"));

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
                    case KeyEvent.VK_X:
                        isXpressed = true;
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
                    case KeyEvent.VK_X:
                        isXpressed = false;
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
                    if (createEnemy) {
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
                        createEnemy = false;
                        cdCreateEnemy = 500;
                    }

//                    if (createEnemy2) {
//                        EnemyController enemyController = new EnemyController(880, 200, Utils.loadImage("res/plane1.png"));
//                        enemyController.setMoveBehavior(new MoveLeft());
//                        enemyControllers.add(enemyController);
//                        createEnemy2 = false;
////                        cdCreateEnemy2 = 500;
//                    }
                    playerController.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed, isXpressed);
                    playerController.update();
                    ufoControl.update();
                    bonus.update();

                    for (EnemyController enemyController : enemyControllers) {
                        enemyController.update();
                    }

                    for (EnemyBullet enemybullet : enemyBullets) {
                        enemybullet.update();
                    }
                    CollisionManager.instance.update();
                    setShootEnableEnemy();

                    repaint();
                }
            }
        });

        thread.start();
    }

    public void setShootEnableEnemy() {
        if (shootEnableEnemy) {
            EnemyBullet enemyBullet = null;
            for (EnemyController enemyController : enemyControllers) {
                enemyBullet = new EnemyBullet(enemyController.getGameRect().getX() - 2, enemyController.getGameRect().getY() + 13, Utils.loadImage("res/enemy_bullet.png"));
                enemyBullets.add(enemyBullet);
                shootEnableEnemy = false;
                cdTime = 100;
            }
        }
    }

    @Override
    public void update(Graphics g) {
        backBufferGraphic.drawImage(backgroundImage, 0, 0, 800, 600, null);
        playerController.draw(backBufferGraphic);
        ufoControl.draw(backBufferGraphic);
        bonus.draw(backBufferGraphic);
//
//        if (!createEnemy2) {
//            cdCreateEnemy2--;
//            if (cdCreateEnemy2 <= 0) {
//                createEnemy2 = true;
//            }
//        }
        if (!createEnemy) {
            cdCreateEnemy--;
            if (cdCreateEnemy <= 0) {
                createEnemy = true;
            }
        }
        if (!shootEnableEnemy) {
            cdTime--;
            if (cdTime <= 0) {
                shootEnableEnemy = true;
            }

            for (EnemyController enemyController : enemyControllers) {
                enemyController.draw(backBufferGraphic);
            }
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.draw(backBufferGraphic);
            }

            Iterator<EnemyController> enemyIterator = enemyControllers.iterator();
            Iterator<PlayerBullet> playerBulletIterator = playerBullets.iterator();
            Iterator<EnemyBullet> enemyBulletIterator = enemyBullets.iterator();
            while (enemyIterator.hasNext()) {
                EnemyController enemyController = enemyIterator.next();
                if (enemyController.getGameRect().isDead()) {
                    enemyIterator.remove();
                }
            }
            while (playerBulletIterator.hasNext()) {
                PlayerBullet playerBullet = playerBulletIterator.next();
                if (playerBullet.getGameRect().isDead()) {
                    playerBulletIterator.remove();
                }
            }

            while (enemyBulletIterator.hasNext()) {
                EnemyBullet enemyBullet = enemyBulletIterator.next();
                if (enemyBullet.getGameRect().isDead()) {
                    enemyBulletIterator.remove();
                    playerController.setPlayerHP(playerController.getPlayerHP() - enemyBullet.getDamage());
                }
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}
