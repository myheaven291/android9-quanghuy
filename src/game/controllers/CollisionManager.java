package game.controllers;

import game.models.GameRect;
import java.util.ArrayList;

/**
 * Created by 123 on 27/04/2017.
 */
public class CollisionManager {
    public static final CollisionManager instance = new CollisionManager();
    private ArrayList<Collider> colliders;

    private CollisionManager() {
        colliders = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                Collider ci = colliders.get(i);
                Collider cj = colliders.get(j);

                GameRect recti = ci.getGameRect();
                GameRect rectj = cj.getGameRect();
                if (recti.intersects(rectj)) {
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                    if(ci.getGameRect().isDead()) {
                        colliders.remove(ci);
                    }
                    if(cj.getGameRect().isDead()){
                        colliders.remove(cj);
                    }
                }
            }
        }
    }
    public void add(Collider collider) {
        colliders.add(collider);
    }
    public void remove(Collider collider){
        colliders.remove(collider);
    }
}