package enemies;

import models.GameRect;

/**
 * Created by 123 on 16/04/2017.
 */
public class ToRightBehavior extends MoveBehavior{
    @Override
    public void move(GameRect gameRect) {
        if(gameRect.getY() < 300){
            gameRect.move(0,1);
        } else gameRect.move(1,0);
    }
}