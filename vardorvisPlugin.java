package net.runelite.client.plugins.microbot.vardorvis;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

@PluginDescriptor(
        name = PluginDescriptor.PH + "vardorvis",
        description = "vardorvis plugin",
        tags = {"vardorvis"},
        enabledByDefault = false
)

@Slf4j
public class vardorvisPlugin extends Plugin {
    @Inject
    Client client;
    @Inject
    private vardorvisConfig config;
    @Provides
    vardorvisConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(vardorvisConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private vardorvisOverlay dmOverlay;

    @Inject
    private KeyManager keyManager;

    @Inject
    vardorvisScript script;


    @Override
    protected void startUp() throws AWTException {


        if (overlayManager != null) {
            overlayManager.add(dmOverlay);
        }
        script.run(config);

    }

    protected void shutDown() {
        if (script != null) {
            overlayManager.remove(dmOverlay);

            script.shutdown();
        }
    }



}
