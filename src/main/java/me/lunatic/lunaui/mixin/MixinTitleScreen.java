package me.lunatic.lunaui.mixin;

import me.lunatic.lunaui.util.RenderUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

    @Unique
    private DrawContext context;

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("HEAD"))
    public void assignContext(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.context = context;
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(FF)V"))
    public void renderBackground(RotatingCubeMapRenderer cubeMapRenderer, float delta, float alpha) {
        if (!RenderUtil.renderWallpaper(this.context, (TitleScreen) (Object) this)) {
            cubeMapRenderer.render(delta, alpha);
        }
    }
}