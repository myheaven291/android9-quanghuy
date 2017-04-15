import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 123 on 09/04/2017.
 */
public class GameWindow extends Frame {

    Image backgroundImage;

    BufferedImage backBufferImage;
    Graphics backBufferGraphic;

    boolean isUpPressed;
    boolean isDownPressed;
    boolean isLeftPressed;
    boolean isRightPressed;
    boolean isSpacePressed;
    boolean CreatEnemy;

    Player player;
    EnemyPlane enemyPlane;
    ArrayList<Integer> turn1;

    public GameWindow() {
        setVisible(true);
        setSize(800, 600);
        setTitle("Game 1945");

        turn1 = new ArrayList<>();
        turn1.add(0,100);
        turn1.add(1,300);
        turn1.add(2,500);
        this.CreatEnemy = true;

        player = new Player(400 - 25,600 - 70, Utils.loadImage("res/plane3.png"));
        if(CreatEnemy){

        }
        enemyPlane = new EnemyPlane(turn1.get(0),0, Utils.loadImage("res/enemy-green-3.png"));

        backBufferImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic = backBufferImage.getGraphics();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        isRightPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeftPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        isUpPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDownPressed = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpacePressed = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        isRightPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeftPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        isUpPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDownPressed = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpacePressed = false;
                        break;
                }
            }
        });

        try {
            backgroundImage = ImageIO.read(new File("res/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    player.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed);
                    player.update();

                    enemyPlane.update();
                    repaint();
                }
            }
        });

        thread.start();
    }

    @Override
    public void update(Graphics g) {
        backBufferGraphic.drawImage(backgroundImage, 0, 0, 800, 600, null);
        player.draw(backBufferGraphic);
        enemyPlane.draw(backBufferGraphic);

        g.drawImage(backBufferImage, 0, 0, null);
    }
}
