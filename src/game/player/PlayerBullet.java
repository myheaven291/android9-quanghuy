package game.player;

import game.controllers.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.enemies.Boss;
import game.enemies.EnemyController;
import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by Huy on 4/15/2017.
 */
public class PlayerBullet extends Controller implements Collider {
    private int damage = 1;

    public PlayerBullet(int x, int y, int w, int h, Image image) {
        gameRect = new GameRect(x,y,w,h);
        imageRenderer = new ImageRenderer(image);
        CollisionManager.instance.add(this);
    }

    public void getHit(int damage){
        gameRect.setDead(true);
    }
    public void update() {
        if(gameRect.getY()<=0){
            CollisionManager.instance.remove(this);
        }
        gameRect.move(0, -15);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof EnemyController){
            ((EnemyController) other).getHit(damage);
        }
        if(other instanceof Boss){
            ((Boss)other).getHit(damage);
        }
    }
}
