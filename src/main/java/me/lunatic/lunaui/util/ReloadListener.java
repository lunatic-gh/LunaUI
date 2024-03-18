package me.lunatic.lunaui.util;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class ReloadListener implements SimpleSynchronousResourceReloadListener {

    private final Runnable runnable;

    public ReloadListener(Runnable r) {
        this.runnable = r;
    }

    @Override
    public Identifier getFabricId() {
        return null;
    }

    @Override
    public void reload(ResourceManager manager) {
        this.runnable.run();
    }
}
