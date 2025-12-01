package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ResourceHandler {
    private final GameModel model;
    private final List<Resource> resources;
    private int money; // Players money
    private int lives; // Players health
    private int score; // Players current score

    public ResourceHandler(GameModel model){
        this.model = model;
        this.resources = new ArrayList<>();
        this.lives = 100;
        this.money = 100;
        this.score = 0;


        resources.add(new ResourceHP(
            lives,
            new Vector2(1.5f, 1.5f),
            3f,
            1f
        ));

        resources.add(new ResourceMoney(
            lives,
            new Vector2(5.5f, 1.5f),
            3f,
            1f));
    }

    public void updateHpResource(){
        for (Resource r : resources){
            if (r instanceof ResourceHP){
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
    }

    public void spendMoney(int amount) {
        this.money -= amount;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int hpChange) {
        this.lives = lives + hpChange;
    }

    public List<Resource> getResources(){
        return resources;
    }

}
