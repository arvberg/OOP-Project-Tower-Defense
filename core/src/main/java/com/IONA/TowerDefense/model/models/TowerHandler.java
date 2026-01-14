package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.map.Path;
import com.IONA.TowerDefense.model.map.Segment;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.interfaces.TowerListener;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.IONA.TowerDefense.model.units.towers.TowerFactory;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles tower-related actions, including placement, selection, buying/selling,
 * and targeting strategy management. Also manages notifications to {@link TowerListener}s.
 */
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

    private int currentStrategyIndex = 0;

    private UpgradeMenu upgradeMenu;

    private static final float TOWER_SELECTION_RADIUS = 0.65f; // Tower selection radius

    public TowerHandler(List<Tower> towers, TowerFactory factory, Path path, List<Decoration> decor, ResourceHandler resourceHandler, UpgradeMenu upgradeMenu) {
        this.towers = towers;
        this.path = path;
        this.decorations = decor;
        this.resourceHandler = resourceHandler;
        this.upgradeMenu = upgradeMenu;
    }
    /**
     * Selects a tower at the given position.
     * If no tower is clicked, deselects the currently selected tower.
     *
     * @param selectedPoint The point where the player clicked.
     */
    public void selectTower(Vector2 selectedPoint) {
        Tower clickedTower = null;
        boolean itemsCreated = false;

        for (Tower tower : towers) {
            // Tornets mittpunkt
            Vector2 center = tower.getPosition(); // Om positionen redan är mittpunkten
            float distance = center.dst(selectedPoint);

            if (distance <= TOWER_SELECTION_RADIUS) {

                clickedTower = tower;
                notifyTowerSwitchedEvent();
                upgradeMenu.setMenuPosition(clickedTower.getX() - upgradeMenu.getWidth() / 2, clickedTower.getY() + clickedTower.getDimension().y);
                upgradeMenu.setTowerIsClicked(true, clickedTower.getTowerType());
                upgradeMenu.clearGridItems();
                upgradeMenu.createGridItems(clickedTower.getUpgradePath1(), clickedTower.getUpgradePath2(), clickedTower.getUpgradePath3(), clickedTower.getTargetingStrategy().getStrategy());

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

    /**
     * Places the pending tower at the given position if there are no overlaps.
     *
     * @param selectedPoint The position to place the tower.
     */
    public void placeTower(Vector2 selectedPoint) {

        if (pendingTower != null && !overlaps(pendingTower)) {
            //System.out.println(pendingTower.getTargetingStrategy().getStrategy());
            pendingTower.setPosition(selectedPoint);
            towers.add(pendingTower);

            setSelectedTower(pendingTower);
            selectTower(selectedPoint);
            setTowerSelected(true);
            System.out.println("Selected tower: " + selectedTower);

            setPendingTower(null);
            setBuyingState(false);
            notifyTowerPlacedEvent();
            System.out.println("tower placed: " + selectedTower);


        }
    }
    /**
     * Deselects the currently selected tower and updates the upgrade menu.
     */
    public void deselectTower() {
        setSelectedTower(null);
        setTowerSelected(false);
        notifyTowerDeselectedEvent();
        System.out.println("Tower deselected");
        // ev använd observer pattern med upgradehandler
        upgradeMenu.clearGridItems();
        upgradeMenu.setTowerIsClicked(false, "");
        upgradeMenu.setMenuPosition(16, 9);
    }
    /**
     * Starts the buying process for a new tower type if the player has enough money.
     *
     * @param tower The type of tower to buy.
     */
    public void buyTower(String tower) {
        Tower newTower = TowerFactory.createTower(tower);
        if (resourceHandler.getMoney() >= newTower.getCost()) {
            deselectTower();
            setBuyingState(true);
            setPendingTower(newTower);
        } else {
            notifyNotEnoughMoney();
            System.out.println("Inte tillräckligt med resurser för att köpa " + tower);
        }
    }

    /**
     * Sells the specified tower and notifies listeners.
     *
     * @param tower The tower to sell.
     */
    public void sellTower(Tower tower) {
        if (selectedTower != null) {
            deselectTower();
            towers.remove(tower);
            notifyTowerSoldEvent();
        }
    }
    /**
     * Cancels the current tower buying process.
     */
    public void cancelBuy() {
        if (pendingTower != null) {
            setBuyingState(false);
            setPendingTower(null);
        }
    }
    /**
     * Checks if the given tower overlaps with the path, decorations, or other towers.
     *
     * @param tower The tower to check for overlap.
     * @return True if overlapping, false otherwise.
     */
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
            tower.getPosition().x - tower.getDimension().x / 2f,
            tower.getPosition().y - tower.getDimension().y / 2f,
            tower.getDimension().x,
            tower.getDimension().y
        );

        // Check for all decorations
        for (Decoration decoration : decorations) {
            Rectangle decorationRect = new Rectangle(
                decoration.getPosition().x - decoration.width / 2f,
                decoration.getPosition().y - decoration.height / 2f,
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
                t.getPosition().x - t.getDimension().x / 2f,
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

    /**
     * Toggles the targeting strategy of the selected tower.
     */
    public void toggleTargetingStrategy() {
        currentStrategyIndex = (selectedTower.getTargetingStrategies().indexOf(selectedTower.getTargetingStrategy())+1) % selectedTower.getTargetingStrategies().size();
        TargetingStrategy currentStrategy = selectedTower.getTargetingStrategyAtIndex(currentStrategyIndex);
        selectedTower.setTargetingStrategy(currentStrategy);
        System.out.println("New strategy: " + currentStrategy);
        notifyTowerStrategyEvent(currentStrategy.getStrategy());
    }

    /**
     * Notifies listeners that a tower has been selected.
     */
    public void notifyTowerClickedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerSelected();
        }
    }
    /**
     * Notifies listeners that the selected tower has been switched.
     */
    public void notifyTowerSwitchedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerSwitched();
        }
    }

    /**
     * Notifies listeners that a tower has been placed.
     */
    public void notifyTowerPlacedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerPlaced();
        }
    }

    /**
     * Notifies listeners that the player could not buy a tower due to insufficient money.
     */
    public void notifyNotEnoughMoney() {
        for (TowerListener l : listeners) {
            l.onCouldNotBuy();
        }
    }
    /**
     * Notifies listeners that a tower has been sold.
     */
    public void notifyTowerSoldEvent() {
        for (TowerListener l : listeners) {
            l.onTowerSold();
        }
    }
    /**
     * Notifies listeners that a tower has been deselected.
     */
    public void notifyTowerDeselectedEvent() {
        for (TowerListener l : listeners) {
            l.onTowerDeselected();
        }
    }
    /**
     * Notifies listeners that the targeting strategy of a tower has been changed.
     *
     * @param strategy The new targeting strategy name.
     */
    public void notifyTowerStrategyEvent(String strategy) {
        for (TowerListener l : listeners) {
            l.onTowerStrategyToggle(strategy);
        }
    }

    public void removeAllTowers() {
        if (!towers.isEmpty()) {
            towers.subList(0, towers.size()).clear();
        }
    }

    ///  Getters and Setters
    ///
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



}
