package game.enemies;

import game.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.models.GameRect;
import game.player.PlayerBullet;
import game.player.PlayerController;
import game.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class EnemyController extends Controller implements Collider{
    private int damage = 1;
    private MoveBehavior moveBehavior;

    ArrayList<EnemyBullet> enemyBullets;

    public EnemyController (int x, int y, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));

        enemyBullets = new ArrayList<>();
        CollisionManager.instance.add(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void update() {
        if (moveBehavior != null) {
            moveBehavior.move(gameRect);
        }
    }
    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof PlayerController){
            ((PlayerController)other).getHit(damage);
        }
        if(other instanceof PlayerBullet){
            ((PlayerBullet) other).getHit(damage);
        }
    }
}
