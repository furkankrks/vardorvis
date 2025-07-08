//new
//ClientUI.getClient().setEnabled(false);

package net.runelite.client.plugins.microbot.vardorvis;

// ... imports remain unchanged ...

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.HeadIcon;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.plugins.microbot.BlockingEvent;
import net.runelite.client.plugins.microbot.InputSelector;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.antiban.Rs2Antiban;
import net.runelite.client.plugins.microbot.util.antiban.Rs2AntibanSettings;
import net.runelite.client.plugins.microbot.util.antiban.enums.Activity;
import net.runelite.client.plugins.microbot.util.combat.Rs2Combat;
import net.runelite.client.plugins.microbot.util.coords.Rs2LocalPoint;
import net.runelite.client.plugins.microbot.util.equipment.Rs2Equipment;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.keyboard.Rs2Keyboard;
import net.runelite.client.plugins.microbot.util.magic.Rs2Magic;
import net.runelite.client.plugins.microbot.util.mouse.naturalmouse.NaturalMouse;
import net.runelite.client.plugins.microbot.util.npc.Rs2Npc;
import net.runelite.client.plugins.microbot.util.npc.Rs2NpcModel;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.prayer.Rs2Prayer;
import net.runelite.client.plugins.microbot.util.prayer.Rs2PrayerEnum;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;
import net.runelite.client.plugins.skillcalculator.skills.MagicAction;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.runelite.client.plugins.microbot.shortestpath.WorldPointUtil;
import java.util.stream.StreamSupport;

import static java.awt.event.KeyEvent.*;
public class vardorvisScript extends Script {
    // ... variable declarations remain unchanged ...
    public static String version = "1.0.0";
    @Inject
    private static vardorvisConfig config;
    boolean init = false;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private vardorvisOverlay overlay;
    @Inject
    private ModelOutlineRenderer modelOutlineRenderer;
    @Inject
    private EventBus eventBus;
    @Inject
    protected Client client;
    public ExecutorService executor;
    private vardorvisPlugin plugin;

    @Inject









    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public boolean run(vardorvisConfig config) {
        // ... initialization code remains mostly unchanged ...
        vardorvisScript.config = config;

        init = true;
        eventBus.register(this);
        if (executor == null || executor.isShutdown()) {
            executor = Executors.newFixedThreadPool(30); // 4 is safer than 16
        }

        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                // ... unchanged validation checks ...
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                if (!init) {
                    init = true;
                }


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
        return true;
    }


    // ... other methods remain unchanged ...
}