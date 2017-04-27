package game.enemies;

import game.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.models.GameRect;
import game.player.PlayerController;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by Huy on 4/14/2017.
 */
public class EnemyBullet extends Controller implements Collider{
    private int damage = 1;

    public EnemyBullet(int x, int y, Image image) {
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        imageRenderer = new ImageRenderer(image);
        CollisionManager.instance.add(this);

    }

    public void update() {
        gameRect.move(0,3);
    }

    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof PlayerController){
            ((PlayerController) other).getHit(damage);
        }
    }
}