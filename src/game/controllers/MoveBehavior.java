package game.controllers;

import game.models.GameRect;

/**
 * Created by 123 on 16/04/2017.
 */
public class MoveBehavior {
    public void move(GameRect gameRect){
        gameRect.move(0,2);
    }
}