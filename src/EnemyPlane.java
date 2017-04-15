import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Huy on 4/14/2017.
 */
public class EnemyPlane {
    private int x;
    private int y;
    private int dx;
    private int dy;

    private Image image;

    private boolean shootEnableEnemy;
    ArrayList<EnemyBullet> enemyBullets;

    public EnemyPlane(int x, int y, Image image) {
        this.x = x + 10;
        this.y = y + 10;
        this.image = image;
        this.shootEnableEnemy = true;

        enemyBullets = new ArrayList<>();
    }
    int cdTime;
    public void setShootEnableEnemy(){
        if(shootEnableEnemy){
            EnemyBullet enemyBullet = null;
            enemyBullet = new EnemyBullet(x,y,loadImage("res/enemy_bullet.png"));
            enemyBullets.add(enemyBullet);
            shootEnableEnemy = false;
            cdTime = 10;
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics){
        graphics.drawImage(image,x,y,null);
        for(EnemyBullet enemyBullet : enemyBullets){
            enemyBullet.draw(graphics);
        }
    }

    public void update(){
        this.y += 5;
        for(EnemyBullet enemyBullet : enemyBullets){
            enemyBullets.add(enemyBullet);
        }
        if(!shootEnableEnemy){
            cdTime --;
            if (cdTime <= 0){
                shootEnableEnemy = true;
            }
        }
    }
    Image loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }
}