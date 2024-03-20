package me.lunatic.lunaui.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameOptions.class)
public class MixinGameOptions {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "net/minecraft/client/option/SimpleOption.ofBoolean(Ljava/lang/String;Lnet/minecraft/client/option/SimpleOption$TooltipFactory;Z)Lnet/minecraft/client/option/SimpleOption;", ordinal = 0))
    private SimpleOption<Boolean> init(String key, SimpleOption.TooltipFactory<Boolean> tooltipFactory, boolean defaultValue) {
        return SimpleOption.ofBoolean("Theme", SimpleOption.emptyTooltip(), (k, v) -> Text.literal(v ? "Dark" : "Light"), defaultValue, (val) -> {
        });
    }
}
