package me.lunatic.lunaui.mixin;

import me.lunatic.lunaui.util.RenderUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Screen.class)
public abstract class MixinScreen {

    @Shadow
    protected abstract void renderPanoramaBackground(DrawContext context, float delta);

    @Redirect(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;renderPanoramaBackground(Lnet/minecraft/client/gui/DrawContext;F)V"))
    public void renderWallpaper(Screen screen, DrawContext context, float delta) {
        if (!RenderUtil.renderWallpaper(context, (Screen) (Object) this)) {
            if (!RenderUtil.renderWallpaper(context, TitleScreen.class)) {
                this.renderPanoramaBackground(context, delta);
            }
        }
    }
}
