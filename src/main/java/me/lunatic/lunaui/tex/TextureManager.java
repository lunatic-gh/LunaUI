package me.lunatic.lunaui.tex;

import me.lunatic.anitex.tex.AnimatedTexture;
import me.lunatic.anitex.util.TextureUtils;
import me.lunatic.lunaui.io.MemoryConfiguration;
import me.lunatic.lunaui.util.LogUtil;
import me.lunatic.lunaui.util.ResourceUtil;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private final Map<Class<? extends Screen>, AnimatedTexture> screenBackgroundTextures;
    private MemoryConfiguration config;

    public TextureManager() {
        this.screenBackgroundTextures = new HashMap<>();
        this.load();
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            public Identifier getFabricId() {
                return new Identifier("lunaui", "texture-loader");
            }

            public void reload(ResourceManager manager) {
                TextureManager.this.load();
            }
        });
    }

    private void load() {
        this.config = ResourceUtil.extractJsonConfiguration(new Identifier("lunaui", "config.jsonc"));
        String namespace = FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace();
        this.screenBackgroundTextures.clear();
        Map<String, String> map = (Map<String, String>) config.getValues().getOrDefault("screen-textures-" + namespace, Collections.emptyMap());
        map.forEach((k, v) -> {
            String[] s = v.split(",");
            String path = s[0];
            int delay = 50;
            if (s.length > 1) {
                try {
                    delay = Integer.parseInt(s[1]);
                } catch (NumberFormatException e) {
                    LogUtil.warn("Warning: Invalid entry for \"" + k + "\" in config. Expected: [path,delay].");
                }
            }
            try {
                this.registerScreenTexture((Class<? extends Screen>) Class.forName(k), path, delay);
            } catch (ClassNotFoundException e) {
                LogUtil.warn("Error: Could not find class '" + k + "'. Did you have a typo in your configs?");
            } catch (ClassCastException e) {
                LogUtil.warn("Error: Class \"" + k + "\" Does not extend class \"Screen\".");
            }
        });
        LogUtil.info("Loaded Assets.");
    }

    private void registerScreenTexture(Class<? extends Screen> screen, String path, int delay) {
        AnimatedTexture tex = new AnimatedTexture(delay);
        tex.load(TextureUtils.getFrameResources(new Identifier("lunaui", path)));
        this.screenBackgroundTextures.put(screen, tex);
    }

    @Nullable
    public AnimatedTexture getTextureForScreen(Class<? extends Screen> screen) {
        return this.screenBackgroundTextures.get(screen);
    }
}