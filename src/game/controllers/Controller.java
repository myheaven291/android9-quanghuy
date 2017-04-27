package game.controllers;

import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by 123 on 27/04/2017.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRenderer imageRenderer;

    public Controller() {
    }

    public Controller(GameRect gameRect, ImageRenderer imageRenderer) {
        this.gameRect = gameRect;
        this.imageRenderer = imageRenderer;
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void draw(Graphics graphics) {
        imageRenderer.render(graphics, gameRect);
    }

    public void update() {

    }
}
