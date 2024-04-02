package me.lunatic.lunaui.mixin;

import me.lunatic.lunaui.LunaUI;
import me.lunatic.lunaui.util.LogUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow
    @Nullable
    public Screen currentScreen;

    @Shadow
    @Final
    private Window window;

    @Shadow
    public abstract CompletableFuture<Void> reloadResources();

    @Unique
    private long prevTime = 0L;

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;resourceManager:Lnet/minecraft/resource/ReloadableResourceManagerImpl;", shift = At.Shift.AFTER))
    public void preInit(RunArgs args, CallbackInfo ci) {
        LunaUI.getInstance().preInit();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(RunArgs args, CallbackInfo ci) {
        LunaUI.getInstance().init();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void handleGlobalKeypresses(CallbackInfo ci) {
        long time = System.currentTimeMillis();
        if (time > this.prevTime + 500L && this.currentScreen != null) {
            if (Screen.hasControlDown() && Screen.hasShiftDown() && GLFW.glfwGetKey(this.window.getHandle(), GLFW.GLFW_KEY_U) == 1) {
                LogUtil.info("Current Screen: \"" + this.currentScreen.getTitle().getString() + "\". Class: \"" + this.currentScreen.getClass().getName() + "\"");
                this.prevTime = time;
            } else if (this.currentScreen != null && GLFW.glfwGetKey(this.window.getHandle(), GLFW.GLFW_KEY_F3) == 1 && GLFW.glfwGetKey(this.window.getHandle(), GLFW.GLFW_KEY_T) == 1) {
                this.reloadResources();
                this.prevTime = time;
            }
        }
    }
}
