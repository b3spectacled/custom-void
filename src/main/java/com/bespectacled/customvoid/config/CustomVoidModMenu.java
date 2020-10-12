package com.bespectacled.customvoid.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

public class CustomVoidModMenu implements ModMenuApi {
    @Override
    public String getModId() {
        return "customvoid";
    }

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return screen -> AutoConfig.getConfigScreen(CustomVoidConfig.class, screen).get();
    }
}
