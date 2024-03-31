package me.lunatic.lunaui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntryListWidget.class)
public abstract class MixinEntryListWidget {

    @Shadow
    public abstract double getScrollAmount();

    @Shadow
    @Final
    protected MinecraftClient client;

    @Shadow public abstract void setRenderBackground(boolean renderBackground);

    @Shadow protected abstract void setRenderHeader(boolean renderHeader, int headerHeight);

    @Shadow private boolean renderHeader;
    @Shadow private boolean renderBackground;
    @Unique
    private double smoothScrollAmount = this.getScrollAmount();

    @Unique
    private boolean returnSmoothAmount = false;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(MinecraftClient client, int width, int height, int y, int itemHeight, CallbackInfo ci) {
        this.smoothScrollAmount = this.getScrollAmount();
        this.renderBackground = false;
    }

    @Inject(method = "renderWidget", at = @At("HEAD"))
    public void enableSmoothScrolling(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.smoothScrollAmount = MathHelper.lerp(this.client.getTickDelta() * 0.3, this.smoothScrollAmount, this.getScrollAmount());
        this.returnSmoothAmount = true;
    }

    @Inject(method = "renderWidget", at = @At("TAIL"))
    public void disableSmoothScrolling(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.returnSmoothAmount = false;
    }

    @Inject(method = "getScrollAmount", at = @At("HEAD"), cancellable = true)
    public void setScrollAmount(CallbackInfoReturnable<Double> cir) {
        if (this.returnSmoothAmount) {
            cir.setReturnValue(this.smoothScrollAmount);
        }
    }

    //@Inject(method = "drawMenuListBackground", at = @At("HEAD"), cancellable = true)
    //public void disableBackground(DrawContext context, CallbackInfo ci) {
    //    ci.cancel();
    //}

    //@Inject(method = "drawHeaderAndFooterSeparators", at = @At("HEAD"), cancellable = true)
    //public void disableSeparators(DrawContext context, CallbackInfo ci) {
    //    ci.cancel();
    //}
}
