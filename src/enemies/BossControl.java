package enemies;

import models.GameRect;
import views.ImageRenderer;

import java.awt.*;

/**
 * Created by 123 on 16/04/2017.
 */
public class BossControl {
    private ImageRenderer imageRenderer;
    private GameRect gameRect;

   public BossControl(int x, int y,Image image){
       imageRenderer = new ImageRenderer(image);
       gameRect = new GameRect(x,y,150,100);
   }

   public void draw(Graphics graphics){
       imageRenderer.render(graphics, gameRect);
   }
   int cd = 250;
   public void update(){
       cd --;
       if(cd <= 0){
           if(gameRect.getX() <= 100){
               gameRect.move(1,-1);
           } else if(gameRect.getX() <= 400){
               gameRect.move(1,1);
           } else gameRect.move(1,-1);
       }
   }
}
