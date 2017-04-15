import java.awt.*;

/**
 * Created by 123 on 13/04/2017.
 */
public class PlayerBullet {
    private int x;
    private int y;
    private Image image;

    public PlayerBullet(int x, int y, Image image){
        this.x = x + 29;
        this.y = y - 15;
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
        this.y -= 15;
    }
}
