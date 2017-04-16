package enemies;

import models.GameRect;
import utils.Utils;
import views.ImageRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class EnemyController {
    private ImageRenderer imageRenderer;
    private GameRect gameRect, gameRectUfo;
    private boolean shootEnableEnemy;
    int cdTime;
    private MoveBehavior moveBehavior;

    ArrayList<EnemyController> enemyControllers;
    ArrayList<EnemyBullet> enemyBullets;
    EnemyController ufo;


    public EnemyController(int x, int y, Image image){
        this.shootEnableEnemy = true;
        imageRenderer = new ImageRenderer(image);

        gameRect = new GameRect(x,y,image.getWidth(null),image.getHeight(null));

        enemyBullets = new ArrayList<>();
    }

    public void draw(Graphics graphics){
        imageRenderer.render(graphics, gameRect);

        for(EnemyBullet enemyBullet : enemyBullets){
            enemyBullet.draw(graphics);
        }
        setShootEnableEnemy();
    }

    public void setShootEnableEnemy() {
        if (shootEnableEnemy) {
            EnemyBullet enemyBullet = null;
            enemyBullet = new EnemyBullet(gameRect.getX() -2, gameRect.getY() + 13, Utils.loadImage("res/enemy_bullet.png"));
            enemyBullets.add(enemyBullet);
            shootEnableEnemy = false;
            cdTime = 100;
        }
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void update(){
        if(moveBehavior != null){
            moveBehavior.move(gameRect);
        }

        for(EnemyBullet enemyBullet : enemyBullets){
            enemyBullet.update();
        }
        if(!shootEnableEnemy){
            cdTime --;
            if (cdTime <= 0){
                shootEnableEnemy = true;
            }
        }
    }
}
