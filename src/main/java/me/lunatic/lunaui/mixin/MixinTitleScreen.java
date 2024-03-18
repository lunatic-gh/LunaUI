package me.lunatic.lunaui.mixin;

import me.lunatic.lunaui.util.RenderUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {
    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Redirect(method = "renderPanoramaBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(Lnet/minecraft/client/gui/DrawContext;IIFF)V"))
    public void renderBackground(RotatingCubeMapRenderer cubeMapRenderer, DrawContext context, int i, int j, float f, float g) {
        if (!RenderUtil.renderWallpaper(context, (TitleScreen) (Object) this)) {
            cubeMapRenderer.render(context, i, j, f, g);
        }
    }
}