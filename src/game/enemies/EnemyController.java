package game.enemies;

import game.controllers.Collider;
import game.controllers.CollisionManager;
import game.controllers.ControllManager;
import game.controllers.Controller;
import game.models.GameRect;
import game.player.PlayerBullet;
import game.player.PlayerController;
import game.utils.Utils;
import game.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class EnemyController extends Controller implements Collider {
    private int damage = 0;
    private MoveBehavior moveBehavior;
    private int cdTime;
    private boolean shootEnableEnemy = true;

    public EnemyController(int x, int y,int w, int h, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, w, h);

        CollisionManager.instance.add(this);
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }
    public void setShootEnableEnemy(){
        if (shootEnableEnemy) {
            EnemyBullet enemyBullet = null;
            enemyBullet = new EnemyBullet(gameRect.getX() - 2, gameRect.getY() + 13,30,30, Utils.loadImage("res/enemy_bullet.png"));
            ControllManager.instance.add(enemyBullet);
            shootEnableEnemy = false;
            cdTime = 20;
        }
    }

    public void update() {
        if (moveBehavior != null) {
            moveBehavior.move(gameRect);
        }
        if (shootEnableEnemy == false) {
            cdTime--;
            if (cdTime <= 0) {
                shootEnableEnemy = true;
            }
        }
    }

    public void getHit(int damage) {
        gameRect.setDead(true);
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof PlayerController) {
            ((PlayerController) other).getHit(damage);
        }
        if (other instanceof PlayerBullet) {
            ((PlayerBullet) other).getHit(damage);
        }
    }
}
