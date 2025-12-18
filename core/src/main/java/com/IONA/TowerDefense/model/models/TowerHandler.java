package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.interfaces.TowerListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
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
    private final Path path;
    private final List<Decoration> decorations;
    private final List<TowerListener> listeners = new ArrayList<>();
    private final ResourceHandler resourceHandler;

    private boolean towerSelected = false;
    private boolean buyingState = false;

    private Tower pendingTower = null;
    private Tower selectedTower = null;

    private final List<TargetingStrategy> targetingStrategies = new ArrayList<>();
    private int currentStrategyIndex = 0;

    private UpgradeMenu upgradeMenu;

    private static final float TOWER_SELECTION_RADIUS = 0.65f; // Tower selection radius

    public TowerHandler (List<Tower> towers, TowerFactory factory, Path path, List<Decoration> decor, ResourceHandler resourceHandler, UpgradeMenu upgradeMenu) {
        this.towers = towers;
        this.path = path;
        this.decorations = decor;
        this.resourceHandler = resourceHandler;
        targetingStrategies.add(new TargetAllStrategy());
        targetingStrategies.add(new TargetLeadingStrategy());
        targetingStrategies.add(new TargetNearestStrategy());
        this.upgradeMenu = upgradeMenu;
    }

    public void selectTower(Vector2 selectedPoint) {
        Tower clickedTower = null;
        boolean itemsCreated = false;

        for (Tower tower : towers) {
            // Tornets mittpunkt
            Vector2 center = tower.getPosition(); // Om positionen redan är mittpunkten
            float distance = center.dst(selectedPoint);

            if (distance <= TOWER_SELECTION_RADIUS) {
                clickedTower = tower;
                upgradeMenu.setMenuPosition(clickedTower.getX() - upgradeMenu.getWidth()/2, clickedTower.getY() + clickedTower.getDimension().y);
                upgradeMenu.setTowerIsClicked(true);
                upgradeMenu.clearGridItems();
                upgradeMenu.createGridItems(clickedTower.getUpgradePath1(), clickedTower.getUpgradePath2());
                tower.setHasCurrentUpgradeMenu(true);


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
            selectTower(selectedPoint);
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
        // ev använd observer pattern med upgradehandler
        upgradeMenu.clearGridItems();
        upgradeMenu.setTowerIsClicked(false);
        upgradeMenu.setMenuPosition(16,9);
    }

    public void buyTower (String tower) {
        Tower newTower = TowerFactory.createTower(tower);

        if (resourceHandler.getMoney() >= newTower.getCost()) {
                deselectTower();
                setBuyingState(true);
                setPendingTower(newTower);
        }
        else {
            notifyNotEnoughMoney();
            System.out.println("Inte tillräckligt med resurser för att köpa " + tower);
        }
    }

    public void sellTower (Tower tower) {
        if (selectedTower != null) {
            deselectTower();
            towers.remove(tower);
            notifyTowerSoldEvent();
        }
    }

    public void cancelBuy () {
        if (pendingTower != null) {
            setBuyingState(false);
            setPendingTower(null);
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


    public void toggleTargetingStrategy() {
        currentStrategyIndex = (currentStrategyIndex + 1) % targetingStrategies.size();
        TargetingStrategy currentStrategy = targetingStrategies.get(currentStrategyIndex);
        selectedTower.setTargetingStrategy(currentStrategy);
        System.out.println("New strategy: " + currentStrategy);
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    public void removeAllTowers() {
        if (!towers.isEmpty()) {
            towers.subList(0, towers.size()).clear();
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

    public void notifyNotEnoughMoney() {
        for (TowerListener l : listeners) {
            l.onCouldNotBuy();
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
