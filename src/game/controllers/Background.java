package game.controllers;

import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by 123 on 04/05/2017.
 */
public class Background extends Controller {
    public Background(int x, int y, int w, int h, Image image) {
        gameRect = new GameRect(x, y, w, h);
        imageRenderer = new ImageRenderer(image);
    }

    @Override
    public void update() {
        gameRect.move(0,1);
        if(gameRect.getY() >= 800){
            gameRect.setY(-800);
        }
    }
}