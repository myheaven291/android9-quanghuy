package game.enemies;

import game.models.GameRect;

/**
 * Created by 123 on 16/04/2017.
 */
public class UfomoveBehavior extends MoveBehavior {
    int cd = 250;

    @Override
    public void move(GameRect gameRect) {
        cd--;
        if (cd <= 0) {
            if (gameRect.getX() <= 100) {
                gameRect.move(1, -1);
            } else if (gameRect.getX() <= 300) {
                gameRect.move(1, 1);
            } else if (gameRect.getX() <= 500) {
                gameRect.move(1, -1);
            } else gameRect.move(1, 1);
        }
        if (gameRect.getX() >= 800) {
            gameRect.setX(-150);
            gameRect.setY(150);
        }
    }
}
