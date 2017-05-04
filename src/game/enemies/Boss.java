package game.enemies;

import game.GameWindow;
import game.controllers.Collider;
import game.controllers.CollisionManager;
import game.controllers.ControllManager;
import game.controllers.Controller;
import game.models.GameRect;
import game.player.PlayerBullet;
import game.player.PlayerController;
import game.scenes.WinScene;
import game.utils.Utils;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by 123 on 04/05/2017.
 */
public class Boss extends Controller implements Collider {
    private int damage = 10;
    private int hp = 100;
    private boolean shootEnableEnemy;
    private int cdTime;

    public Boss(int x, int y, int w, int h, Image image) {
        gameRect = new GameRect(x, y, w, h);
        imageRenderer = new ImageRenderer(image);
        CollisionManager.instance.add(this);
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof PlayerController) {
            ((PlayerController) other).getHit(damage);
        }
        if (other instanceof PlayerBullet) {
            ((PlayerBullet) other).getHit(damage);
        }
    }
    public void setShootEnableEnemy(){
        if (shootEnableEnemy) {
            EnemyBullet enemyBullet = null;
            enemyBullet = new EnemyBullet(gameRect.getX() +80, gameRect.getY() + 100, 50 ,50, Utils.loadImage("res/bomb.png"));
            ControllManager.instance.add(enemyBullet);
            shootEnableEnemy = false;
            cdTime = 50;
        }
    }
    public void getHit(int damage) {
        hp = hp - damage;
        System.out.println(hp);
    }

    @Override
    public void update() {
        if (shootEnableEnemy == false) {
            cdTime--;
            if (cdTime <= 0) {
                shootEnableEnemy = true;
            }
        }
        if(hp <= 0){
            GameWindow.instance.setCurrentScene(new WinScene());
            ControllManager.instance.setClear(true);
        }
        gameRect.move(0, 1);
        {
            if (gameRect.getY() == 200) {
                gameRect.move(1, -1);
                if (gameRect.getX() >= 600) {
                    gameRect.setX(-200);
                }
            }
        }
    }
}
