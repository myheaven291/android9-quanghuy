package game.player;

import game.controllers.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by 123 on 27/04/2017.
 */
public class Bonus extends Controller implements Collider{
    private int damage = 0;
    public Bonus(int x, int y, int w, int h, Image image){
        gameRect = new GameRect(x,y,w,h);
        imageRenderer = new ImageRenderer(image);
        CollisionManager.instance.add(this);
    }
    @Override
    public void update() {
        gameRect.move(-1,1);
    }
    public void getHit(){

    }
    @Override
    public void onCollide(Collider other) {
        if(other instanceof PlayerController){
            ((PlayerController) other).getHit(damage);
            gameRect.setX(1000);
            gameRect.move(0,0);
        }
    }
}
