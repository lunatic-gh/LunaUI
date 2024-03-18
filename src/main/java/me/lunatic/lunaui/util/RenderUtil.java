package me.lunatic.lunaui.util;

import me.lunatic.anitex.tex.AnimatedTexture;
import me.lunatic.lunaui.LunaUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public class RenderUtil {

    public static boolean renderWallpaper(DrawContext context, Screen screen) {
        return renderWallpaper(context, screen.getClass());
    }

    public static boolean renderWallpaper(DrawContext context, Class<? extends Screen> screen) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            AnimatedTexture tex = LunaUI.getInstance().getTextureManager().getTextureForScreen(screen);
            if (tex != null) {
                tex.render(context, 0, 0, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight());
                return true;
            }
        }
        return false;
    }
}
