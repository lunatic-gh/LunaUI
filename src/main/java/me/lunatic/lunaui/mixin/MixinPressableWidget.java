package me.lunatic.lunaui.mixin;

import me.lunatic.lunaui.util.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PressableWidget.class)
public abstract class MixinPressableWidget extends ClickableWidget {
    @Shadow
    public abstract void drawMessage(DrawContext context, TextRenderer textRenderer, int color);

    public MixinPressableWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"))
    public void renderWidget$drawBackground(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        int themeColor = MinecraftClient.getInstance().options.getMonochromeLogo().getValue() ? 0 : 220;
        int alpha = this.active ? this.isSelected() ? 170 : 140 : 210;
        context.drawBorder(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Color.of(themeColor, themeColor, themeColor));
        context.fill(x + 2, y + 2, x + width - 2, y + height - 2, Color.of(themeColor,themeColor,themeColor, alpha));
    }
}
