package com.IONA.TowerDefense.model.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

public class SoundManager implements Disposable {
    private static final HashMap<String, Sound> sounds = new HashMap<>();
    private static final HashMap<String, Music> musics = new HashMap<>();
    private static float soundVolume = 0.75f;
    private static float musicVolume = 0.4f;

    public void load() {
        // FX
        sounds.put("place_tower", Gdx.audio.newSound(Gdx.files.internal("audio/place_tower.wav")));
        sounds.put("sell_tower", Gdx.audio.newSound(Gdx.files.internal("audio/sell_tower.wav")));
        sounds.put("click_tower", Gdx.audio.newSound(Gdx.files.internal("audio/click_tower.wav")));

        sounds.put("invalid_click", Gdx.audio.newSound(Gdx.files.internal("audio/invalid_click.wav")));
        sounds.put("ui_button_click", Gdx.audio.newSound(Gdx.files.internal("audio/ui_button_click.wav")));

        sounds.put("fire", Gdx.audio.newSound(Gdx.files.internal("audio/fire_temp.wav")));


        // Music
    }

    public void playSound(String name) {
        if (sounds.containsKey(name)) {
            sounds.get(name).play(soundVolume);
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
