package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class TowerHandler {

    private final List<Tower> towers;
    private final GameModel model;
    private final TowerFactory factory;
    private final Path path;
    private final List<Decoration> decorations;

    private static final float TOWER_SELECTION_RADIUS = 0.65f; // Tower selection radius

    public TowerHandler (GameModel model) {
        this.model = model;
        this.towers = model.getTowers();
        this.factory = model.getTowerFactory();
        this.path = model.getPath();
        this.decorations = model.getDecor();
    }

    public void updateTowerAngle(Tower tower){

            if(tower.isAiming()) {
                float dx = tower.getCurrentTarget().getX() - tower.getX();
                float dy = tower.getCurrentTarget().getY() - tower.getY();
                float angleRad = (float)Math.atan2(dy,dx);
                tower.setAngleDeg((float)Math.toDegrees(angleRad));
            }

    }

    public void selectTower(Vector2 selectedPoint) {
        Tower clickedTower = null;

        for (Tower tower : towers) {
            // Tornets mittpunkt
            Vector2 center = tower.getPosition(); // Om positionen redan är mittpunkten
            float distance = center.dst(selectedPoint);

            if (distance <= TOWER_SELECTION_RADIUS) {
                clickedTower = tower;
                break; // break om torn hittat
            }
        }
        // Om vi klickar utanför ett torn
        if (clickedTower == null) {
            deselectTower();
            // selecta nytt torn om vi trycker på ett torn
        } else if (model.getSelectedTower() != clickedTower) {
            model.setSelectedTower(clickedTower);
            model.setTowerSelected(true);
            System.out.println("Tower selected at: " + model.getSelectedTower().getPosition());
        }
    }

    public void placeTower (Vector2 selectedPoint) {
        Tower pendingTower = model.getPendingTower();

        if (pendingTower != null && !overlaps(pendingTower)) {
            pendingTower.setPosition(selectedPoint);
            towers.add(pendingTower);

            model.setSelectedTower(pendingTower);
            model.setTowerSelected(true);
            System.out.println("Selected tower: " + model.getSelectedTower());

            model.setPendingTower(null);
            model.setBuyingState(false);
            System.out.println("tower placed");
        }
    }

    public void deselectTower () {
        model.setSelectedTower(null);
        model.setTowerSelected(false);
        System.out.println("Tower deselected");
    }

    public void buyTower (String tower) {
        Tower newTower = factory.createTower(tower);

        if (model.getMoney() >= newTower.getCost()) {
                model.setBuyingState(true);
                model.setPendingTower(newTower);
        }
        else {
            System.out.println("Inte tillräckligt med resurser för att köpa " + tower);
        }
    }

    public void sellTower (Tower tower) {
        if (model.getSelectedTower() != null) {
            towers.remove(tower);
        }
    }

    public boolean overlaps(Tower tower) {

        Vector2 towerPos = tower.getPosition();

        float halfX = tower.getDimension().x / 1.5f;
        float halfY = tower.getDimension().y / 1.5f;

        float radius = Math.max(halfX, halfY);

        // Overlaps Path
        for (Segment segment : path.getSegments()) {
            float distance = Intersector.distanceSegmentPoint(
                segment.getStartPosition(),
                segment.getEnd(),
                towerPos
            );
            if (distance < radius) {
                return true;
            }
        }

        // Set coordinates to compare with
        Rectangle towerRect = new Rectangle(
            tower.getPosition().x - tower.getDimension().x /2f,
            tower.getPosition().y - tower.getDimension().y / 2f,
            tower.getDimension().x,
            tower.getDimension().y
        );

        // Check for all decorations
        for (Decoration decoration : decorations) {
            Rectangle decorationRect = new Rectangle(
                decoration.getPosition().x - decoration.width /2f,
                decoration.getPosition().y - decoration.height/2f,
                decoration.width,
                decoration.height
            );
            if (towerRect.overlaps(decorationRect)) {
                return true;
            }
        }

        // Check for every tower on the map
        for (Tower t : towers) {
            Rectangle placedTowerRect = new Rectangle(
                t.getPosition().x - t.getDimension().x /2f,
                t.getPosition().y - t.getDimension().y / 2f,
                t.getDimension().x,
                t.getDimension().y
            );
            if (towerRect.overlaps(placedTowerRect)) {
                return true;
            }
        }
        return false;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    public void removeAllTowers() {
        for (int i = towers.size() - 1; i >= 0; i--) {
            towers.remove(i);
        }
    }

}
