package me.lunatic.lunaui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PackScreen.class)
public abstract class MixinPackScreen extends Screen {

    protected MixinPackScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/pack/PackScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 3, shift = At.Shift.AFTER))
    public void addReloadButton(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Reload Resources"), (button) -> {
            MinecraftClient.getInstance().reloadResources();
        }).dimensions(4, this.height - 24, 150, 20).build());
    }
}
