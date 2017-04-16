package enemies;

import models.GameRect;
import views.ImageRenderer;

import java.awt.*;

/**
 * Created by Huy on 4/14/2017.
 */
public class EnemyBullet {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;

    public EnemyBullet(int x, int y, Image image){
        gameRect = new GameRect(x,y,image.getWidth(null),image.getHeight(null));
        imageRenderer = new ImageRenderer(image);
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public ImageRenderer getImageRenderer() {
        return imageRenderer;
    }
    public void setGameRect(GameRect gameRect) {
        this.gameRect = gameRect;
    }

    public void setImageRenderer(ImageRenderer imageRenderer) {
        this.imageRenderer = imageRenderer;
    }

    public void draw(Graphics graphics){
        imageRenderer.render(graphics, gameRect);
    }

    public void update(){
        gameRect.move(0,2);
    }
}