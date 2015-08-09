package com.beariksonstudios.existence.scenes.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by BrianErikson on 8/8/2015.
 */
public class Assets {
    public static AssetManager manager = new AssetManager();

    public static void loadAsync() {
        manager.load("ui/bottomPane.png", Texture.class);
        manager.load("map_resources/FertileLand.png", Texture.class);
        manager.load("map_resources/Forest.png", Texture.class);
        manager.load("map_resources/Lake.png", Texture.class);
        manager.load("map_resources/Mountain.png", Texture.class);
        manager.load("settlements/Diamond.png", Texture.class);
        manager.load("settlements/Rectangle.png", Texture.class);
        manager.load("settlements/Star.png", Texture.class);
        manager.load("settlements/Triangle.png", Texture.class);
    }

    public static void load() {
        loadAsync();
        manager.finishLoading();
    }
}
