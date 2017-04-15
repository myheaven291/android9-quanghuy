package player;
import models.GameRect;
import views.ImageRenderer;

import java.awt.*;
/**
 * Created by Huy on 4/15/2017.
 */
public class PlayerBullet {
  private ImageRenderer imageRenderer;
  private GameRect gameRect;

  public PlayerBullet(int x, int y, Image image){
      imageRenderer = new ImageRenderer(image);
      gameRect = new GameRect(x,y,image.getWidth(null),image.getHeight(null));
  }

    public ImageRenderer getImageRenderer() {
        return imageRenderer;
    }

    public void setImageRenderer(ImageRenderer imageRenderer) {
        this.imageRenderer = imageRenderer;
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void setGameRect(GameRect gameRect) {
        this.gameRect = gameRect;
    }

    public void draw(Graphics graphics){
      imageRenderer.render(graphics, gameRect);
    }

    public void update(){
        gameRect.move(0,-15);
    }
}
