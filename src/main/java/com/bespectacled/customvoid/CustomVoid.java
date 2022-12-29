package com.bespectacled.customvoid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import com.bespectacled.customvoid.config.CustomVoidConfig;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class CustomVoid implements ModInitializer {
    public static final String MOD_ID = "custom_void";
    public static final String MOD_NAME = "Custom Void";
    public static final CustomVoidConfig VOID_CONFIG = AutoConfig.register(CustomVoidConfig.class, GsonConfigSerializer::new).getConfig();
    
    private static final Logger LOGGER = LoggerFactory.getLogger("CustomVoid");

    public static void log(Level level, String message) {
        message = String.format("[%s] %s", MOD_NAME, message);
        
        switch(level) {
            case DEBUG: LOGGER.debug(message);
            case ERROR: LOGGER.error(message);
            case WARN: LOGGER.warn(message);
            default: LOGGER.info(message);
        }
    }
    
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Custom Void...");
    }
}
