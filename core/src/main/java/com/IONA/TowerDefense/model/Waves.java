package com.IONA.TowerDefense.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Array;

public class Waves {

    protected static class Enemy {
        private static String type;
        //public String difficulty;
        private static float delay;

        public String getEnemyType() {
            return Enemy.type;
        }

        public float getDelay() {
            return Enemy.delay;
        }
    }

    public static class Wave {
        public int id;
        public Array<Enemy> enemies;
    }

    public Array<Wave> waveslist;

    public static Waves load() {
        Json json = new Json();
        return json.fromJson(Waves.class, Gdx.files.internal("WaveDatabase.json"));
    }


}


