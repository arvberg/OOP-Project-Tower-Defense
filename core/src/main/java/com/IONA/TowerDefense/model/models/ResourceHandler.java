package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ResourceHandler {
    private final List<Resource> resources;
    private int money; // Players money
    private int lives; // Players health
    private int score; // Players current score

    public ResourceHandler(){
        this.resources = new ArrayList<>();
        this.lives = 100;
        this.money = 1000;
        this.score = 0;


        resources.add(new ResourceHP(
            lives,
            new Vector2(1.5f, 1.5f),
            3f,
            1f
        ));

        resources.add(new ResourceMoney(
            money,
            new Vector2(5.5f, 1.5f),
            3f,
            1f));
    }

    public void updateHpResource(){
        for (Resource r : resources){
            if (r instanceof ResourceHP && lives >= 0){
                r.setCurrentResource(lives);
                r.textBar = String.valueOf(lives);
            }
        }
    }

    public void updateMoneyResource(){
        for (Resource r : resources){
            if (r instanceof ResourceMoney){
                r.setCurrentResource(money);
                r.textBar = String.valueOf(money);
            }
        }
    }

    public int getMoney(){
        return money;
    }

    public void gainMoney(int amount){
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

    public int getScore(){ return score; }

    public List<Resource> getResources(){
        return resources;
    }

    public void resetResources() {
        this.lives = 100;
        this.money = 100;
        updateHpResource();
        updateMoneyResource();
    }

}
