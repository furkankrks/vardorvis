package net.runelite.client.plugins.microbot.vardorvis;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("phToa Config")
public interface vardorvisConfig extends Config {
    @ConfigSection(name = "magic", description = "magic configuration", position = 1, closedByDefault = true)
    String magicGear = "magicGear";

    @ConfigItem(keyName = "gearIDsMagicPrimary", name = "Gear IDs", description = "Comma-separated list of Gear IDs for primary Magic", position = 2, section = magicGear)
    default String gearIDsMagicPrimary() {
        return "";
    }
}