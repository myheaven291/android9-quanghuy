package game.player;

import game.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.enemies.EnemyBullet;
import game.enemies.EnemyController;
import game.models.GameRect;
import game.utils.Utils;
import game.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class PlayerController extends Controller implements Collider {
    int dx = 0;
    int dy = 0;
    private int playerHP = 10;
    private int damage = 1;

    private boolean rocketEnable = true;
    private boolean shootEnable = true;
    ArrayList<PlayerBullet> playerBullets;

    public PlayerController(int x, int y, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        CollisionManager.instance.add(this);
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public void setPlayerBullets(ArrayList<PlayerBullet> playerBullets) {
        this.playerBullets = playerBullets;
    }

    public GameRect getGameRect() {
        return gameRect;
    }


    public void getHit(int damage) {
        gameRect.setDead(true);
    }

    public void draw(Graphics graphics) {
        if (gameRect.isInVisible()) return;
        imageRenderer.render(graphics, gameRect);

        for (PlayerBullet playerBullet : playerBullets) {
            playerBullet.draw(graphics);
        }
    }

    public void processInput(boolean isUpPressed,
                             boolean isDownPressed,
                             boolean isLeftPressed,
                             boolean isRightPressed,
                             boolean isSpacePressed,
                             boolean isXpressed) {
        dx = 0;
        dy = 0;
        if (isUpPressed) {
            dy -= 7;
        }
        if (isDownPressed) {
            dy += 7;
        }
        if (isLeftPressed) {
            dx -= 7;
        }
        if (isRightPressed) {
            dx += 7;
        }
        if (isSpacePressed) {
            if (shootEnable) {
                PlayerBullet playerBullet = null;
                playerBullet = new PlayerBullet(gameRect.getX() + 28, gameRect.getY() - 15, Utils.loadImage("res/bullet.png"));
                playerBullets.add(playerBullet);
                shootEnable = false;
                cdTimeShootEnable = 10;
            }
        }
        if (isXpressed) {
            if (rocketEnable) {
                PlayerRocket playerRocket = null;
                playerRocket = new PlayerRocket(gameRect.getX(), gameRect.getY() + 70, Utils.loadImage("res/rocket.png"));
                playerBullets.add(playerRocket);
                rocketEnable  = false;
            }
        }
    }

    int cdTimeShootEnable,
        cdTimeRocketEnable;

    public void update() {
        System.out.println(playerHP);
        for (PlayerBullet playerBullet : playerBullets) {
            playerBullet.update();
        }
        if (playerHP <= 0) {
            System.out.println("GAME OVER");
            gameRect.setInVisible(true);
        }
        gameRect.move(dx, dy);

        if (!shootEnable) {
            cdTimeShootEnable--;
            if (cdTimeShootEnable <= 0) {
                shootEnable = true;
            }
        }
        if(!rocketEnable){
            cdTimeRocketEnable --;
            if(cdTimeRocketEnable<=0){
                rocketEnable = true;
                cdTimeRocketEnable = 200;
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
            playerHP = playerHP -1;
        }
        if(other instanceof Bonus){
            ((Bonus)other).getHit();
            setPlayerHP(10);
        }
    }
}
