package game.player;

import game.controllers.Collider;
import game.enemies.Boss;

import java.awt.*;

/**
 * Created by 123 on 27/04/2017.
 */
public class PlayerRocket extends PlayerBullet {
    private int damage = 5;

    public PlayerRocket(int x, int y, int w, int h, Image image) {
        super(x, y, w, h, image);
    }


    public int getDamage() {
        return damage;
    }

    @Override
    public void update() {
        gameRect.move(0, -10);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof Boss){
            ((Boss)other).getHit(50);
        }
    }
}
