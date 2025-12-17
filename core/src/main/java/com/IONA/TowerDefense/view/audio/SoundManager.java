package com.IONA.TowerDefense.view.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

public class SoundManager implements Disposable {
    private static final HashMap<String, Sound> sounds = new HashMap<>();
    private static final HashMap<String, Music> musics = new HashMap<>();
    private static final HashMap<String, Float> soundVolumes = new HashMap<>();
    private static float soundVolume = 1f;
    private static float musicVolume = 0.4f;

    public void load() {
        // FX
        // Tower
        sounds.put("place_tower", Gdx.audio.newSound(Gdx.files.internal("audio/place_tower.wav")));
        soundVolumes.put("place_tower", 0.7f);

        sounds.put("sell_tower", Gdx.audio.newSound(Gdx.files.internal("audio/sell_tower.wav")));
        soundVolumes.put("sell_tower", 0.7f);

        sounds.put("click_tower", Gdx.audio.newSound(Gdx.files.internal("audio/click_tower.wav")));
        soundVolumes.put("click_tower", 0.7f);

        sounds.put("tower_upgraded", Gdx.audio.newSound(Gdx.files.internal("audio/tower_upgraded.wav")));
        soundVolumes.put("tower_upgraded", 0.6f);

        // UI
        sounds.put("invalid_click", Gdx.audio.newSound(Gdx.files.internal("audio/invalid_click.wav")));
        soundVolumes.put("invalid_click", 0.7f);

        sounds.put("ui_button_click", Gdx.audio.newSound(Gdx.files.internal("audio/ui_button_click.wav")));
        soundVolumes.put("ui_button_click", 0.8f);

        sounds.put("fire", Gdx.audio.newSound(Gdx.files.internal("audio/fire_temp.wav")));
        soundVolumes.put("fire", 0.8f);

        sounds.put("pulse", Gdx.audio.newSound(Gdx.files.internal("audio/pulse.wav")));
        soundVolumes.put("pulse", 1f);


        sounds.put("enemy_basic_death", Gdx.audio.newSound(Gdx.files.internal("audio/enemy_basic_death.wav")));
        soundVolumes.put("enemy_basic_death", 0.2f);


        // Music
    }

    public void playSound(String name) {
        if (sounds.containsKey(name)) {
            float volume = soundVolumes.getOrDefault(name, soundVolume); // fallback till global volym
            sounds.get(name).play(volume);
        }
    }

    public void playMusic(String name) {
        if (musics.containsKey(name)) {
            Music music = musics.get(name);
            music.setVolume(musicVolume);
            if (!music.isPlaying()) {
                music.play();
            }
        }
    }

    public void stopMusic(String name) {
        if (musics.containsKey(name)) {
            musics.get(name).stop();
        }
    }

    public void stopAllSounds() {
        for (Sound sound : sounds.values()) {
            sound.stop();
        }
    }

    public void setSoundVolume(float volume) {
        soundVolume = Math.max(0, Math.min(volume, 1));
    }

    public void setMusicVolume(float volume) {
        musicVolume = Math.max(0, Math.min(volume, 1));
        for (Music music : musics.values()) {
            music.setVolume(musicVolume);
        }
    }

    public void unload() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        for (Music music : musics.values()) {
            music.dispose();
        }
        sounds.clear();
        musics.clear();
    }

    @Override
    public void dispose() {
        unload();
    }
}
