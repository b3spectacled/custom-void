package com.bespectacled.customvoid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.bespectacled.customvoid.CustomVoid;
import com.bespectacled.customvoid.config.CustomVoidConfig;
import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.ClientWorld.Properties;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.HeightLimitView;

@Mixin(value = WorldRenderer.class, priority = 1002)
public class MixinWorldRenderer {

    @Shadow private ClientWorld world; 
    @Shadow private MinecraftClient client;

    @Unique private CustomVoidConfig VOID_CONFIG = CustomVoid.VOID_CONFIG;

    @Redirect(
        method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLjava/lang/Runnable;)V", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", 
            ordinal = 5
        )
    )
    private void modifyVoidColor(float r, float g, float b, float a) {
        Vec3d skyColor = this.world.method_23777(this.client.gameRenderer.getCamera().getPos(), 0.1f);
        float x = (float) skyColor.x;
        float y = (float) skyColor.y;
        float z = (float) skyColor.z;
                
        switch (VOID_CONFIG.voidType) {
            case CLASSIC -> RenderSystem.setShaderColor(x * 0.2F + 0.04F, y * 0.2F + 0.04F, z * 0.6F + 0.1F, a);
            case SKY -> RenderSystem.setShaderColor(x, y, z, a);
            default -> RenderSystem.setShaderColor(r, g, b, a);
        }
    }

    @Redirect(
        method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLjava/lang/Runnable;)V", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V"
        )
    )
    private void modifyVoidTranslation(MatrixStack self, double x, double y, double z) {
        // y = 12 by default
        y += VOID_CONFIG.voidTranslateHeight;
        
        if (VOID_CONFIG.voidDynamicHeight) {
            double voidOffset = Math.max(this.client.gameRenderer.getCamera().getPos().y - 65d, 0d);
            
            y += -voidOffset;
        }
        
        self.translate(x, y, z);
    }

    @Redirect(
        method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLjava/lang/Runnable;)V",
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/client/world/ClientWorld$Properties;getSkyDarknessHeight(Lnet/minecraft/world/HeightLimitView;)D"
        )
    )
    private double modifyVoidThreshold(Properties self, HeightLimitView world) {
        if (!VOID_CONFIG.renderVoid) return 0.0;
        
        return VOID_CONFIG.voidThreshold;
    }

}
