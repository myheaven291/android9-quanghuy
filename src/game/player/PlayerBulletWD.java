package game.player;

import game.controllers.Collider;
import game.controllers.CollisionManager;

import java.awt.*;

/**
 * Created by 123 on 05/05/2017.
 */
public class PlayerBulletWD extends PlayerBullet implements Collider{
    public PlayerBulletWD(int x, int y, int w, int h, Image image) {
        super(x, y, w, h, image);
    }

    @Override
    public void update() {
        if(gameRect.getY()<=0){
            CollisionManager.instance.remove(this);
        }
        gameRect.move(10, -10);
    }
}
