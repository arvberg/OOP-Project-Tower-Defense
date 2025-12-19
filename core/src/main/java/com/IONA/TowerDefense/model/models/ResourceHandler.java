package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
/**
 * Manages the player's resources, including money, lives, and score.
 * Updates visual representations of resources through {@link Resource} objects.
 */
public class ResourceHandler {
    private final List<Resource> resources;
    private int money; // Players money
    private int lives; // Players health
    private int score; // Players current score

    public ResourceHandler() {
        this.resources = new ArrayList<>();
        this.lives = 100;
        this.money = 1000;
        this.score = 0;


        resources.add(new ResourceHP(
            lives,
            new Vector2(13f, 0.7f),
            3f,
            1f
        ));

        resources.add(new ResourceMoney(
            money,
            new Vector2(13f, 1f),
            3f,
            1f));
    }


    /**
     * Updates the health (HP) display for all ResourceHP objects.
     */
    public void updateHpResource() {
        for (Resource r : resources) {
            if (r instanceof ResourceHP && lives >= 0) {
                r.setCurrentResource(lives);
                r.textBar = String.valueOf(lives);
            }
        }
    }
    /**
     * Updates the money display for all ResourceMoney objects.
     */
    public void updateMoneyResource() {
        for (Resource r : resources) {
            if (r instanceof ResourceMoney) {
                r.setCurrentResource(money);
                r.textBar = String.valueOf(money);
            }
        }
    }

    public void resetResources() {
        this.lives = 100;
        this.money = 100;
        updateHpResource();
        updateMoneyResource();
    }

    ///  Getters and Setters
    ///
    public int getMoney() {
        return money;
    }

    public void gainMoney(int amount) {
        this.money += amount;
        this.score += amount;
    }

    public void spendMoney(int amount) {
        this.money -= amount;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int newLives) {
        this.lives = Math.max(newLives, 0);
    }

    public List<Resource> getResources() {
        return resources;
    }



}
