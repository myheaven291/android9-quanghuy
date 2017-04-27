package game;

import game.models.GameRect;

/**
 * Created by 123 on 27/04/2017.
 */
public interface Collider {
    GameRect getGameRect();
    void onCollide(Collider other);
}