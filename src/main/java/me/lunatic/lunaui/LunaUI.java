package me.lunatic.lunaui;

import me.lunatic.lunaui.tex.TextureManager;

public class LunaUI {

    private static LunaUI INSTANCE;
    private TextureManager textureManager;

    public void preInit() {
    }

    public void init() {
        this.textureManager = new TextureManager();
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public static LunaUI getInstance() {
        return INSTANCE != null ? INSTANCE : (INSTANCE = new LunaUI());
    }
}