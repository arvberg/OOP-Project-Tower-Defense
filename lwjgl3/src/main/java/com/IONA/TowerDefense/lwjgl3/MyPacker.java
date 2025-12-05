package com.IONA.TowerDefense;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class MyPacker {
    public static void main(String[] args) throws Exception {

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 8192;   // 🔥 öka från 1024 → 4096
        settings.maxHeight = 8192;  // 🔥 öka från 1024 → 4096
        settings.edgePadding = true;
        settings.paddingX = 2;
        settings.paddingY = 2;

        TexturePacker.process(
            settings,
            "assets/Core_animation",
            "assets/atlas",
            "core_animation"
        );

        System.out.println("✔ Atlas packed!");
    }
}



