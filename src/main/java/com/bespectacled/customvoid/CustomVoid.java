package com.bespectacled.customvoid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bespectacled.customvoid.config.CustomVoidConfig;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;

import com.bespectacled.customvoid.client.GoVote;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class CustomVoid implements ModInitializer {
    public static final String ID = "custom_void";
    public static final Logger LOGGER = LogManager.getLogger("CustomVoid");
    public static final CustomVoidConfig VOID_CONFIG = AutoConfig.register(CustomVoidConfig.class, GsonConfigSerializer::new).getConfig();
    
    
    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Initializing Custom Void...");
        
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            GoVote.init();
        }
        
        LOGGER.log(Level.INFO, "Initialized Custom Void!");
    }
}
