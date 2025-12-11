package com.IONA.TowerDefense.model.audio;

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
            case ENEMY_BASIC_DEATH -> soundManager.playSound("enemy_basic_death");
            case TOWER_FIRE -> soundManager.playSound("fire");
        }
    }
}
