package com.bespectacled.customvoid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bespectacled.customvoid.config.CustomVoidConfig;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class CustomVoid implements ModInitializer {
    public static final String MOD_ID = "custom_void";
    public static final String MOD_NAME = "Custom Void";
    public static final CustomVoidConfig VOID_CONFIG = AutoConfig.register(CustomVoidConfig.class, GsonConfigSerializer::new).getConfig();
    
    private static final Logger LOGGER = LogManager.getLogger("CustomVoid");

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] {}", message);
    }
    
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Custom Void...");
    }
}
