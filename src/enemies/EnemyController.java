package enemies;

import models.GameRect;
import views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class EnemyController {
    private ImageRenderer imageRenderer;
    private GameRect gameRect;
    private MoveBehavior moveBehavior;

    ArrayList<EnemyBullet> enemyBullets;

    public EnemyController(int x, int y, Image image) {
        imageRenderer = new ImageRenderer(image);

        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));

        enemyBullets = new ArrayList<>();
    }

    public void draw(Graphics graphics) {
        imageRenderer.render(graphics, gameRect);
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void update() {
        if (moveBehavior != null) {
            moveBehavior.move(gameRect);
        }
    }
}
