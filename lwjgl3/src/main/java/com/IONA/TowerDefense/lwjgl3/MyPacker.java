package com.IONA.TowerDefense;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class MyPacker {
    public static void main(String[] args) throws Exception {

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;   // 🔥 öka från 1024 → 4096
        settings.maxHeight = 2048;  // 🔥 öka från 1024 → 4096
        settings.edgePadding = true;
        settings.paddingX = 2;
        settings.paddingY = 2;

        TexturePacker.process(
            settings,
            "assets/animations/collections/towerbasicfire",
            "assets/animations/atlases/towerbasicfire",
            "Animation_Towerbasicfire"
        );

        System.out.println("✔ Atlas packed!");
    }
}



