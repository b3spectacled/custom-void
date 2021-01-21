package com.bespectacled.customvoid.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "customvoid")
public final class CustomVoidConfig implements ConfigData {
    public boolean renderVoid = true;
    
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean voidDynamicHeight = false;
    
    @ConfigEntry.Gui.Tooltip(count = 3)
    public VoidType voidType = VoidType.DEFAULT;
    
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int voidThreshold = 63;

    @ConfigEntry.BoundedDiscrete(min = -64, max = 0)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int voidTranslateHeight = 0;
    
    public enum VoidType {
        DEFAULT, CLASSIC, SKY
    }
}