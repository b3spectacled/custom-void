package com.bespectacled.customvoid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bespectacled.customvoid.config.CustomVoidConfig;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;

import net.fabricmc.api.ModInitializer;

public class CustomVoid implements ModInitializer {
    public static final String ID = "custom_void";
    public static final Logger LOGGER = LogManager.getLogger("CustomVoid");
    public static final CustomVoidConfig VOID_CONFIG = AutoConfig
            .register(CustomVoidConfig.class, GsonConfigSerializer::new).getConfig();

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Initializing Custom Void...");
        LOGGER.log(Level.INFO, "Initialized Custom Void!");
    }
}
