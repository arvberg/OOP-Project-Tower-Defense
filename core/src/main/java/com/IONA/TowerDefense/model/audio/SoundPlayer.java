package com.IONA.TowerDefense.model.audio;

import com.IONA.TowerDefense.model.units.interfaces.SoundListener;

public class SoundPlayer implements SoundListener {
    private final SoundManager soundManager;

    public SoundPlayer(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    @Override
    public void onSoundEvent(SoundEvent event) {
        switch(event) {
            case TOWER_PLACED -> soundManager.playSound("place_tower");
            case TOWER_SOLD -> soundManager.playSound("sell_tower");
            case CLICK_TOWER -> soundManager.playSound("click_tower");
            case ENEMY_DEATH -> soundManager.playSound("enemy_death");
        }
    }
}
