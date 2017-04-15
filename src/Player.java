import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 123 on 13/04/2017.
 */
public class Player {
    private int x;
    private int y;
    private Image image;
    private boolean shootEnable;
    ArrayList<PlayerBullet> playerBullets;

    private int dx;
    private int dy;

    public Player(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.dx = 0;
        this.dy = 0;
        this.shootEnable = true;

        playerBullets = new ArrayList<>();
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

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y , null);
        for (PlayerBullet playerBullet : playerBullets) {
            playerBullet.draw(graphics);
        }
    }

    public void processInput(boolean isUpPressed,
                             boolean isDownPressed,
                             boolean isLeftPressed,
                             boolean isRightPressed,
                             boolean isSpacePressed) {
        dx = 0;
        dy = 0;
        if (isUpPressed) {
            dy -= 10;
        }
        if (isDownPressed) {
            dy += 10;
        }
        if (isLeftPressed) {
            dx -= 10;
        }
        if (isRightPressed) {
            dx += 10;
        }
        if (isSpacePressed) {
            if (shootEnable) {
                PlayerBullet playerBullet = null;
                playerBullet = new PlayerBullet(x , y, loadImage("res/bullet.png"));
                playerBullets.add(playerBullet);
                shootEnable = false;
                coolDownTime = 5;
            }
        }
    }

    int coolDownTime;

    Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(){
        for(PlayerBullet playerBullet : playerBullets){
            playerBullet.update();
        }
        this.x += dx;
        this.y += dy;
        if(!shootEnable){
            coolDownTime --;
            if (coolDownTime <= 0){
                shootEnable = true;
            }
        }
    }
}
