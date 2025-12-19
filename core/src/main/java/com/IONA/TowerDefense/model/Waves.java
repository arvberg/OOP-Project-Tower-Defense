package com.IONA.TowerDefense.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Array;

public class Waves {
    public static boolean TEST_MODE = false;

    protected static class Enemy {
        private String type;
        //public String difficulty;
        private float delay;

        public String getEnemyType() { return type; }

        public float getDelay() {
            return delay;
        }
    }

    public static class Wave {
        public int id;
        public Array<Enemy> enemies;
    }

    public Array<Wave> waveslist;

    public static Waves load() {
        if (TEST_MODE) {
            Waves w = new Waves();
            w.waveslist = new Array<>();
            return w;
        }

        Json json = new Json();
        return json.fromJson(Waves.class, Gdx.files.internal("WaveDatabase.json"));
    }


}


