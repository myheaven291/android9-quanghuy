package game.player;

import java.awt.*;

/**
 * Created by 123 on 27/04/2017.
 */
public class PlayerRocket extends PlayerBullet{
    private int damage = 5;
    public PlayerRocket(int x, int y, Image image) {
        super(x, y, image);
    }

    public int getDamage() {
        return damage;
    }
}
