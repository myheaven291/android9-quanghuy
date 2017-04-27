package game.enemies;

import game.Collider;
import game.controllers.Controller;
import game.models.GameRect;
import game.utils.Utils;
import game.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by 123 on 16/04/2017.
 */
public class UfoControl extends Controller implements Collider {
    private MoveBehavior moveBehavior;
    private int ufoHP = 15;
    private boolean createTinyUfo = false;
    private int cdCreateTinyUfo = 50;

    public UfoControl(int x, int y, int w, int h, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, w, h);
        moveBehavior = new UfomoveBehavior();
    }

    public void update() {
        if (!createTinyUfo) {
            cdCreateTinyUfo--;
            if (cdCreateTinyUfo <= 0) {
                createTinyUfo = true;
            }
        }
        moveBehavior.move(gameRect);
    }

    @Override
    public void onCollide(Collider other) {

    }
}
