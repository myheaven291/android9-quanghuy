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
    private GameRect gameRect;
    private boolean shootEnableEnemy;
    int cdTime;

    ArrayList<EnemyController> enemyControllers;
    ArrayList<EnemyBullet> enemyBullets;


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
            enemyBullet = new EnemyBullet(gameRect.getX() - 2, gameRect.getY() + 10, Utils.loadImage("res/enemy_bullet.png"));
            enemyBullets.add(enemyBullet);
            shootEnableEnemy = false;
            cdTime = 20;
        }
    }
    public void update(){
        gameRect.move(0,1);


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
