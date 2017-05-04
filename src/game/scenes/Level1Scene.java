package game.scenes;

import game.GameWindow;
import game.controllers.Background;
import game.controllers.ControllManager;
import game.enemies.Boss;
import game.enemies.EnemyController;
import game.enemies.MoveBehavior;
import game.player.Bonus;
import game.player.PlayerController;
import game.player.Rocket;
import game.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by 123 on 04/05/2017.
 */
public class Level1Scene implements GameScene {
    private boolean isUpPressed,
            isDownPressed,
            isLeftPressed,
            isRightPressed,
            isSpacePressed,
            isXpressed;
    private boolean createEnemy = true;
    private int cdCreateEnemy;
    PlayerController playerController;
    Boss boss;

    private Bonus health;
    private Rocket rocket;
    private Background background, background_2;

    public Level1Scene() {
        background = new Background(0, 0, 600, 800, Utils.loadImage("res/background1.jpg"));
        background_2 = new Background(0, -800, 600, 800, Utils.loadImage("res/background2.jpg"));

        boss = new Boss(200,-1500,200,150,Utils.loadImage("res/ufo.png"));
        ControllManager.instance.add(boss);

        playerController = new PlayerController(300 - 35, 800 - 70, Utils.loadImage("res/plane3.png"));
        ControllManager.instance.add(playerController);
        health = new Bonus(2000, -1000, 35, 30, Utils.loadImage("res/heart.png"));
        rocket = new Rocket(1000, -0, 35, 30, Utils.loadImage("res/power-up.png"));
        ControllManager.instance.add(health);
        ControllManager.instance.add(rocket);
    }

    @Override
    public void keyPresses(KeyEvent e) {
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

    @Override
    public void update() {
        if(boss.getGameRect().getY()>=0){
            boss.setShootEnableEnemy();
        }
        if (playerController.getPlayerHP() <= 0) {
            System.out.println("GAME OVER");
            GameWindow.instance.setCurrentScene(new LoseScene());
            ControllManager.instance.setClear(true);
        }

        background.update();
        background_2.update();

        if (createEnemy) {
            for (int x = 80; x < 570; x += 100) {
                EnemyController enemyController = new EnemyController(x, 0,30,30, Utils.loadImage("res/enemy-green-3.png"));
                enemyController.setMoveBehavior(new MoveBehavior());
                ControllManager.instance.add(enemyController);
                enemyController.setShootEnableEnemy();
            }

            createEnemy = false;
            cdCreateEnemy = 200;
        }

        playerController.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed, isXpressed);

        if (!createEnemy) {
            cdCreateEnemy--;
            if (cdCreateEnemy <= 0) {
                createEnemy = true;
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        background_2.draw(graphics);
    }
}
