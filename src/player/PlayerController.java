package player;

import models.GameRect;
import utils.Utils;
import views.ImageRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Huy on 4/15/2017.
 */
public class PlayerController {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    int dx = 0;
    int dy = 0;

    private boolean shootEnable;
    ArrayList<PlayerBullet> playerBullets;

    public PlayerController(int x, int y, Image image) {
        this.imageRenderer = new ImageRenderer(image);

        gameRect = new GameRect(x,y,image.getWidth(null),image.getHeight(null));

        this.shootEnable = true;

        playerBullets = new ArrayList<>();
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void draw(Graphics graphics) {
       imageRenderer.render(graphics, gameRect);


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
                playerBullet = new PlayerBullet(gameRect.getX() + 28, gameRect.getY() - 15, Utils.loadImage("res/bullet.png"));
                playerBullets.add(playerBullet);
                shootEnable = false;
                coolDownTime = 10;
            }
        }
    }

    int coolDownTime;

    public void update(){
        for(PlayerBullet playerBullet : playerBullets){
            playerBullet.update();
        }

        gameRect.move(dx,dy);

        if(!shootEnable){
            coolDownTime --;
            if (coolDownTime <= 0){
                shootEnable = true;
            }
        }
    }
}
