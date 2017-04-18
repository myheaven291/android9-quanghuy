package enemies;

import models.GameRect;
import views.ImageRenderer;

import java.awt.*;

/**
 * Created by 123 on 16/04/2017.
 */
public class UfoControl {
    private ImageRenderer imageRenderer;
    private GameRect gameRect;
    private MoveBehavior moveBehavior;

    public UfoControl(int x, int y, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, 150, 100);
        moveBehavior = new UfomoveBehavior();
    }

    public void draw(Graphics graphics) {
        imageRenderer.render(graphics, gameRect);
    }

    public void update() {
        moveBehavior.move(gameRect);
    }
}
