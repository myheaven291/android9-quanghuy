package game.player;

import game.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.enemies.EnemyController;
import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by Huy on 4/15/2017.
 */
public class PlayerBullet extends Controller implements Collider {
    private int damage = 1;

    public PlayerBullet(int x, int y, Image image) {
        int rectX = x - image.getWidth(null) / 2;
        int rectY = y - image.getHeight(null);
        int rectWidth = image.getWidth(null);
        int rectHeight = image.getHeight(null);
        this.gameRect = new GameRect(rectX, rectY, rectWidth, rectHeight);
        this.imageRenderer = new ImageRenderer(image);

        CollisionManager.instance.add(this);
    }

    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }
    public void update() {
        gameRect.move(0, -15);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof EnemyController){
            ((EnemyController) other).getHit(damage);
        }
    }
}
