package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.interfaces.TowerListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class TowerHandler {

    private final List<Tower> towers;
    private final TowerFactory factory;
    private final Path path;
    private final List<Decoration> decorations;
    private final List<TowerListener> listeners = new ArrayList<>();
    private final ResourceHandler resourceHandler;

    private boolean towerSelected = false;
    private boolean buyingState = false;

    private Tower pendingTower = null;
    private Tower selectedTower = null;

    private static final float TOWER_SELECTION_RADIUS = 0.65f; // Tower selection radius

    public TowerHandler (List<Tower> towers, TowerFactory factory, Path path, List<Decoration> decor, ResourceHandler resourceHandler) {
        this.towers = towers;
        this.factory = factory;
        this.path = path;
        this.decorations = decor;
        this.resourceHandler = resourceHandler;
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
        } else if (selectedTower != clickedTower) {
            setSelectedTower(clickedTower);
            setTowerSelected(true);
            notifyTowerClickedEvent();
            System.out.println("Tower selected at: " + selectedTower.getPosition());
        }
    }

    public void placeTower (Vector2 selectedPoint) {

        if (pendingTower != null && !overlaps(pendingTower)) {
            pendingTower.setPosition(selectedPoint);
            towers.add(pendingTower);

            setSelectedTower(pendingTower);
            setTowerSelected(true);
            System.out.println("Selected tower: " + selectedTower);

            setPendingTower(null);
            setBuyingState(false);
            notifyTowerPlacedEvent();
            System.out.println("tower placed");
        }
    }

    public void deselectTower () {
        setSelectedTower(null);
        setTowerSelected(false);
        notifyTowerDeselectedEvent();
        System.out.println("Tower deselected");
    }

    public void buyTower (String tower) {
        Tower newTower = factory.createTower(tower);

        if (resourceHandler.getMoney() >= newTower.getCost()) {
                deselectTower();
                setBuyingState(true);
                setPendingTower(newTower);
        }
        else {
            System.out.println("Inte tillräckligt med resurser för att köpa " + tower);
        }
    }

    public void sellTower (Tower tower) {
        if (selectedTower != null) {
            towers.remove(tower);
            notifyTowerSoldEvent();
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

    public Tower getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(Tower tower) {
        this.selectedTower = tower;
    }

    public boolean isTowerSelected() {
        return towerSelected;
    }

    public void setTowerSelected(Boolean bool) {
        this.towerSelected = bool;
    }

    public boolean isBuyingState() {
        return buyingState;
    }

    public void setBuyingState(Boolean bool) {
        this.buyingState = bool;
    }

    public Tower getPendingTower() {
        return pendingTower;
    }

    public void setPendingTower(Tower tower) {
        this.pendingTower = tower;
    }

    public void addTowerListener(TowerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(TowerListener listener) {
        listeners.remove(listener);
    }

    public void notifyTowerClickedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerSelected();
        }
    }

    public void notifyTowerPlacedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerPlaced();
        }
    }

    public void notifyTowerSoldEvent() {
        for (TowerListener l : listeners) {
            l.onTowerSold();
        }
    }

    public void notifyTowerDeselectedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerDeselected();
        }
    }

}
