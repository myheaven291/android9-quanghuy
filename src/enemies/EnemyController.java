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
    private boolean enemyshootEnable;

    ArrayList<EnemyController> enemyControllers;

    public EnemyController(ImageRenderer imageRenderer, GameRect gameRect, Image image) {
        this.imageRenderer = imageRenderer;
        this.gameRect = gameRect;
    }

    public EnemyController(int x, int y, Image image){
        imageRenderer = new ImageRenderer(image);

        gameRect = new GameRect(x,y,image.getWidth(null),image.getHeight(null));

    }

    public void draw(Graphics graphics){
        imageRenderer.render(graphics, gameRect);
    }

    public void update(){
        gameRect.move(0, 1);
    }
}
