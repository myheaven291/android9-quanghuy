package game.controllers;

import game.enemies.EnemyController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 123 on 04/05/2017.
 */
public class ControllManager {
    private ArrayList<Controller> controllers;
    public static final ControllManager instance = new ControllManager();
    private boolean clear = false;

    private ControllManager() {
        controllers = new ArrayList<>();
    }

    public void add(Controller controller) {
        controllers.add(controller);
    }

    public void draw(Graphics graphics) {
        for (Controller controller : controllers) {
            controller.draw(graphics);
        }
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public void update() {
        if (clear) {
            Iterator<Controller> iterator = controllers.iterator();
            while (iterator.hasNext()) {
                Controller controller = iterator.next();
                iterator.remove();
            }
        }
        //Remove dead onjects
        Iterator<Controller> iterator = controllers.iterator();
        while (iterator.hasNext()) {
            Controller controller = iterator.next();
            if (controller.getGameRect().isDead()) {
                iterator.remove();

            }
        }

        for (Controller controller : controllers) {
            controller.update();
        }
    }
}
