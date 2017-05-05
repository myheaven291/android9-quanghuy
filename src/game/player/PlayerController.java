package game.player;

import game.GameWindow;
import game.controllers.*;
import game.enemies.EnemyBullet;
import game.enemies.EnemyController;
import game.models.GameRect;
import game.scenes.LoseScene;
import game.utils.Utils;
import game.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class PlayerController extends Controller implements Collider {
    private int playerHP = 10;
    private int damage = 1;
    private boolean rocketEnable;
    private boolean shootEnable = true;
    private int cdTimeShootEnable, cdRocket;

    public PlayerController(int x, int y, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        CollisionManager.instance.add(this);
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public GameRect getGameRect() {
        return gameRect;
    }


    public void getHit(int damage) {
        playerHP -= damage;
        System.out.println(playerHP);
        if (playerHP <= 0) {
            gameRect.setDead(true);
        }
    }

    public void draw(Graphics graphics) {
        imageRenderer.render(graphics, gameRect);
    }

    public void processInput(boolean isUpPressed,
                             boolean isDownPressed,
                             boolean isLeftPressed,
                             boolean isRightPressed,
                             boolean isSpacePressed,
                             boolean isXpressed) {

        if (isUpPressed) {
            gameRect.move(0, -5);
        }
        if (isDownPressed) {
            gameRect.move(0, 5);
        }
        if (isLeftPressed) {
            gameRect.move(-5, 0);
        }
        if (isRightPressed) {
            gameRect.move(5, 0);
        }
        if (isSpacePressed) {
            if (shootEnable) {
                PlayerBullet playerBullet = new PlayerBullet(gameRect.getX() + 22, gameRect.getY() - 15, 13, 33, Utils.loadImage("res/bullet.png"));
                PlayerBullet playerBullet2 = new PlayerBullet(gameRect.getX() + 34, gameRect.getY() - 15, 13, 33, Utils.loadImage("res/bullet.png"));
                PlayerBulletWA playerBulletWA = new PlayerBulletWA(gameRect.getX() - 35, gameRect.getY() - 15, 24, 24, Utils.loadImage("res/bullet-right.png"));
                PlayerBulletWD playerBulletWD = new PlayerBulletWD(gameRect.getX() + 70, gameRect.getY() - 15, 24, 24, Utils.loadImage("res/bullet-left.png"));

                ControllManager.instance.add(playerBullet);
                ControllManager.instance.add(playerBullet2);
                ControllManager.instance.add(playerBulletWA);
                ControllManager.instance.add(playerBulletWD);
                shootEnable = false;
                cdTimeShootEnable = 7;
            }
        }
        if (isXpressed) {
            if (rocketEnable) {
                PlayerRocket playerRocket = null;
                playerRocket = new PlayerRocket(gameRect.getX(), gameRect.getY() + 70, 50, 75, Utils.loadImage("res/rocket.png"));
                ControllManager.instance.add(playerRocket);
                rocketEnable = false;
                cdRocket -= 1;
                if (cdRocket == 0) {
                    rocketEnable = false;
                }
            }
        }
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void update() {
        if (!shootEnable) {
            cdTimeShootEnable--;
            if (cdTimeShootEnable <= 0) {
                shootEnable = true;
            }
        }
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof EnemyBullet) {
            ((EnemyBullet) other).getHit(damage);
        }
        if (other instanceof EnemyController) {
            ((EnemyController) other).getHit(damage);
            playerHP = playerHP - 1;
        }
        if (other instanceof Bonus) {
            ((Bonus) other).getHit();
            setPlayerHP(10);
        }
        if (other instanceof Rocket) {
            rocketEnable = true;
            cdRocket = 3;
        }
    }
}
