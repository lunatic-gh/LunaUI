package me.lunatic.lunaui.mixin;

import me.lunatic.lunaui.util.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SliderWidget.class)
public abstract class MixinSliderWidget extends ClickableWidget {
    @Shadow
    protected double value;

    public MixinSliderWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 0))
    public void renderWidget$drawBackground(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        int themeColor = MinecraftClient.getInstance().options.getMonochromeLogo().getValue() ? 0 : 220;
        context.drawBorder(x, y, width, height, Color.of(themeColor, themeColor, themeColor));
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
    public void renderWidget$drawProgress(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        int themeColor = MinecraftClient.getInstance().options.getMonochromeLogo().getValue() ? 0 : 220;
        int alpha = this.active ? this.isSelected() ? 170 : 140 : 210;
        context.fill(this.getX() + 2, this.getY() + 2, (this.getX() + 2) + (int) ((this.width - 4) * this.value), this.getY() + this.getHeight() - 2, Color.of(themeColor, themeColor, themeColor, alpha));
    }
}
