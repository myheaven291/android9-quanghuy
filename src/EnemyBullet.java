import java.awt.*;

/**
 * Created by Huy on 4/14/2017.
 */
public class EnemyBullet {
    private int x;
    private int y;
    private Image image;

    public EnemyBullet(int x, int y, Image image) {
        this.x = x - 3;
        this.y = y + 10;
        this.image = image;
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

    public void draw(Graphics graphics){
        graphics.drawImage(image,x,y,null);
    }

    public void update(){
        this.y += 3;
    }
}
