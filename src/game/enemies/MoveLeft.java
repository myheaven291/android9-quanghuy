package game.enemies;

import game.models.GameRect;

/**
 * Created by 123 on 27/04/2017.
 */
public class MoveLeft extends MoveBehavior{
    @Override
    public void move(GameRect gameRect) {
        gameRect.move(-1,0);
    }
}