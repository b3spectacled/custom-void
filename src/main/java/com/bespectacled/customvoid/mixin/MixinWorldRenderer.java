package com.bespectacled.customvoid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.bespectacled.customvoid.CustomVoid;
import com.bespectacled.customvoid.config.CustomVoidConfig;
import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.ClientWorld.Properties;
import net.minecraft.util.math.Vec3d;

@Mixin(value = WorldRenderer.class, priority = 1002)
public class MixinWorldRenderer {

    @Shadow
    private ClientWorld world;

    @Shadow
    private MinecraftClient client;

    @Unique
    private CustomVoidConfig VOID_CONFIG = CustomVoid.VOID_CONFIG;

    @Redirect(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;color3f(FFF)V", ordinal = 1))
    private void modifyVoidColor(float r, float g, float b) {
        Vec3d skyColor = this.world.method_23777(this.client.gameRenderer.getCamera().getBlockPos(), 0.1f);
        float x = (float) skyColor.x;
        float y = (float) skyColor.y;
        float z = (float) skyColor.z;

        if (VOID_CONFIG.blueVoid) {
            RenderSystem.color3f(x * 0.2F + 0.04F, y * 0.2F + 0.04F, z * 0.6F + 0.1F);
        } else if (VOID_CONFIG.skyVoid) {
            RenderSystem.color3f(x, y, z);
        } else {
            RenderSystem.color3f(0f, 0f, 0f);
        }

    }

    @Redirect(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V"))
    private void modifyVoidTranslation(MatrixStack self, double x, double y, double z) {
        // y = 12 by default
        y += VOID_CONFIG.voidTranslateHeight;
        self.translate(x, y, z);
    }

    @Redirect(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld$Properties;getSkyDarknessHeight()D"))
    private double modifyVoidThreshold(Properties self) {
        if (!VOID_CONFIG.renderVoid) return 0.0;
        
        return VOID_CONFIG.voidThreshold;
    }

}
