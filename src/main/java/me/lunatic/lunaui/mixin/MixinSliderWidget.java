package me.lunatic.lunaui.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SliderWidget.class)
public abstract class MixinSliderWidget extends ClickableWidget {
    public MixinSliderWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 0))
    public void renderWidget$drawBackground(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        int alpha = this.active ? this.isSelected() ? 170 : 140 : 210;
        context.drawBorder(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Colors.BLACK);
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
    public void renderWidget$drawProgress(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        
    }
}
