package net.runelite.client.plugins.microbot.vardorvis;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.questhelper.tools.QuestTile;
import net.runelite.client.plugins.microbot.util.coords.Rs2LocalPoint;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.magic.Rs2Magic;
import net.runelite.client.plugins.microbot.util.misc.Rs2Food;
import net.runelite.client.plugins.microbot.util.npc.Rs2Npc;
import net.runelite.client.plugins.microbot.util.npc.Rs2NpcModel;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.prayer.Rs2Prayer;
import net.runelite.client.plugins.microbot.util.prayer.Rs2PrayerEnum;
import net.runelite.client.plugins.microbot.util.tile.Rs2Tile;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;
import net.runelite.client.plugins.skillcalculator.skills.MagicAction;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;
import javax.inject.Inject;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.StreamSupport;
public class vardorvisOverlay extends OverlayPanel {
    private final ModelOutlineRenderer modelOutlineRenderer;
    vardorvisConfig config;
    @Inject
    private vardorvisScript script;
    @Inject
    private vardorvisOverlay(ModelOutlineRenderer modelOutlineRenderer, vardorvisConfig config) {
        this.modelOutlineRenderer = modelOutlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);
        setNaughty();
        this.config = config;
    }

    public long lastClickTimeDown = -1;
    public long lastClickTimeUp = -1;
    public long lastClickTimeC = -1;
    public long lastClickTimeAttack = -1;
    public long currentTime = Long.MAX_VALUE;
    public static Rs2NpcModel isAxeOnSouthEastObj = null;
    public static Rs2NpcModel isAxeOnNorthWestObj = null;
    public static Rs2NpcModel isMovingAxeOnSouthEastObj = null;
    public static Rs2NpcModel isMovingAxeOnNorthWestObj = null;
    public static boolean isAxeOnSouthEast = false;
    public static boolean isAxeOnNorthWest = false;
    public static boolean canGoBaseLocation = false;
    public static boolean canReturnBaseWithoutCollidingToAxe = false;
    public static Integer axesOneDistanceToPlayer = 10000000;
    public static Integer axesTwoDistanceToPlayer = 10000000;
    public static Integer axesThreeDistanceToPlayer = 10000000;
    public static boolean canHightlightUp = false;
    public static boolean canHightlightDown = false;
    public static Integer northWestAxeDistanceToPlayer = 10000000;
    public static long count = 10000; //count of red tiles
    public static WorldPoint targetWorldPointUp;
    public static LocalPoint localPointUp;
    public static Point canvasPointUp;
    public static WorldPoint targetWorldPointDown;
    public static LocalPoint localPointDown;
    public static Point canvasPointDown;
    public static WorldPoint targetWorldPointC;
    public static LocalPoint localPointC;
    public static Point canvasPointC;
    public boolean tileTrapUp = false; //A
    public boolean tileTrapDown = false; //B
    public boolean tileTrapEscapeC = false; //C
    public boolean doesTileTrapExists = false;
    public boolean endingFasterAxes = false;
    public boolean isPlayerOnUp = false;
    public boolean isPlayerOnDown = false;
    public boolean isPlayerOnC = false;
    public boolean onStartStand = false;
    public long lastAttackTime = 0;





    @Override
    public Dimension render(Graphics2D graphics) {
        if(isAxeOnNorthWestObj!=null){
            if(Rs2Npc.getNpc(isAxeOnNorthWestObj.getId())==null){
                isAxeOnNorthWestObj = null;
            }
        }
        if(isAxeOnSouthEastObj!=null){
            if(Rs2Npc.getNpc(isAxeOnSouthEastObj.getId())==null){
                isAxeOnSouthEastObj = null;
            }
        }
        if(isMovingAxeOnNorthWestObj!=null){
            if(Rs2Npc.getNpc(isMovingAxeOnNorthWestObj.getId())==null){
                isMovingAxeOnNorthWestObj  = null;
            }
        }
        if(Rs2Player.getWorldLocation().equals(new WorldPoint(1127, 3421, 0))){
            isPlayerOnUp = true;
        }else{
            isPlayerOnUp = false;
        }
        if(Rs2Player.getWorldLocation().equals(new WorldPoint(1125, 3423, 0))){
            isPlayerOnDown = true;
        }else{
            isPlayerOnDown = false;
        }
        if(Rs2Player.getWorldLocation().equals(new WorldPoint(1127, 3422, 0))){
            isPlayerOnC = true;
        }else{
            isPlayerOnC = false;
        }
        if(Rs2Widget.getWidget(54591490) != null){
            if(Rs2Widget.getWidget(54591490).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591490);
            }
        }
        if(Rs2Widget.getWidget(54591491) != null){
            if(Rs2Widget.getWidget(54591491).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591491);
            }
        }
        if(Rs2Widget.getWidget(54591492) != null){
            if(Rs2Widget.getWidget(54591492).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591492);
            }
        }
        if(Rs2Widget.getWidget(54591493) != null){
            if(Rs2Widget.getWidget(54591493).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591493);
            }
        }
        if(Rs2Widget.getWidget(54591494) != null){
            if(Rs2Widget.getWidget(54591494).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591494);
            }
        }
        if(Rs2Widget.getWidget(54591495) != null){
            if(Rs2Widget.getWidget(54591495).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591495);
            }
        }
        if(Rs2Widget.getWidget(54591496) != null){
            if(Rs2Widget.getWidget(54591496).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591496);
            }
        }
        if(Rs2Widget.getWidget(54591497) != null){
            if(Rs2Widget.getWidget(54591497).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591497);
            }
        }
        if(Rs2Widget.getWidget(54591498) != null){
            if(Rs2Widget.getWidget(54591498).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591498);
            }
        }
        if(Rs2Widget.getWidget(54591499) != null){
            if(Rs2Widget.getWidget(54591499).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591499);
            }
        }
        if(Rs2Widget.getWidget(54591500) != null){
            if(Rs2Widget.getWidget(54591500).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591500);
            }
        }
        if(Rs2Widget.getWidget(54591501) != null){
            if(Rs2Widget.getWidget(54591501).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591501);
            }
        }
        if(Rs2Widget.getWidget(54591502) != null){
            if(Rs2Widget.getWidget(54591502).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591502);
            }
        }
        if(Rs2Widget.getWidget(54591503) != null){
            if(Rs2Widget.getWidget(54591503).getAnimationId() == 10374){
                Rs2Widget.clickWidget(54591503);
            }
        }
        doesTileTrapExists = StreamSupport.stream(Microbot.getClient().getGraphicsObjects().spliterator(), false)
                .anyMatch(graphic -> graphic.getId() == 2510);
        for (GraphicsObject graphic : Microbot.getClient().getGraphicsObjects()) {
            if (graphic.getId() == 2510) {
                if(graphic.getLocation().getSceneX() == 63 && graphic.getLocation().getSceneY() == 45){
                    tileTrapUp = true;
                }
                if(graphic.getLocation().getSceneX() == 61 && graphic.getLocation().getSceneY() == 47){
                    tileTrapDown = true;
                }
                if(graphic.getLocation().getSceneX() == 63 && graphic.getLocation().getSceneY() == 46){
                    tileTrapEscapeC = true;
                }
            }
        }
        if(doesTileTrapExists){
            if(tileTrapUp && !tileTrapDown && isAxeOnSouthEastObj == null){
                moveToDown();
            }else if(tileTrapUp && !tileTrapDown && isAxeOnSouthEastObj == null && isMovingAxeOnSouthEastObj == null){
                moveToC();
            }else if(tileTrapDown && !tileTrapUp){
                moveToUp();
            }else if(tileTrapDown && tileTrapUp && !tileTrapEscapeC){
                moveToC();
            }
        }else{
            tileTrapUp = false;
            tileTrapDown = false;
            tileTrapEscapeC = false;
        }

        if (isMovingAxeOnNorthWestObj != null) {
            northWestAxeDistanceToPlayer = (int) Math.hypot(
                    isMovingAxeOnNorthWestObj.getLocalLocation().getSceneX() - Rs2Player.getLocalLocation().getSceneX(),
                    isMovingAxeOnNorthWestObj.getLocalLocation().getSceneY() - Rs2Player.getLocalLocation().getSceneY()
            );
        }


        if(!onStartStand && Rs2GameObject.getGameObject(47599) != null){
            if(!isPlayerOnUp){
                moveToUp();
            }else{
                onStartStand = true;
            }

        }
        if(Rs2Npc.getNpc("Vardorvis")==null){
            onStartStand = false;
        }



        if (System.currentTimeMillis() - lastAttackTime >= 2000) {
            Rs2Npc.getNpcs("Vardorvis").findFirst().ifPresent(vardorvis -> {
                if (!vardorvis.isDead() && !Rs2Player.isAnimating()
                        && !Rs2Player.isInteracting()
                        && Rs2GameObject.getGameObject(47599)!=null
                        && isMovingAxeOnNorthWestObj == null) {
                    Rs2Npc.attack(vardorvis);
                    lastAttackTime = System.currentTimeMillis(); // Update last attack time
                }
            });
        }

        if(Rs2GameObject.getGameObject(47599)!=null && Rs2Npc.getNpc(12225) == null && Rs2Npc.getNpc(12227) == null && Rs2Npc.getNpc(12229) == null){
            if(Rs2Npc.getNpc(10880) == null){
                Rs2Magic.cast(MagicAction.RESURRECT_GREATER_GHOST);
            }
        }






        if(Rs2GameObject.getGameObject(47599) == null){
            lastClickTimeDown = -1;
            lastClickTimeUp = -1;
            lastClickTimeC = -1;
            lastClickTimeAttack = -1;
            currentTime = Long.MAX_VALUE;
            onStartStand = false;

        }

        if(Rs2Npc.getNpc(12223) == null && Rs2Npc.getNpc(12426) == null){
            ClientUI.getClient().setEnabled(true);
            lastClickTimeDown = -1;
            lastClickTimeUp = -1;
            lastClickTimeC = -1;
            lastClickTimeAttack = -1;
            currentTime = Long.MAX_VALUE;
            isAxeOnSouthEast = false;
            isAxeOnNorthWest = false;
            canGoBaseLocation = false;
            canReturnBaseWithoutCollidingToAxe = false;
            axesOneDistanceToPlayer = 10000000;
            axesTwoDistanceToPlayer = 10000000;
            axesThreeDistanceToPlayer = 10000000;
            canHightlightUp = false;
            canHightlightDown = false;
            northWestAxeDistanceToPlayer = 10000000;
            count = 10000; //count of red tiles
            tileTrapUp = false; //A
            tileTrapDown = false; //B
            tileTrapEscapeC = false; //C
            doesTileTrapExists = false;
            endingFasterAxes = false;
            
        }




        currentTime = System.currentTimeMillis();
        targetWorldPointUp = new WorldPoint(1127, 3421, 0);
        localPointUp =   Rs2LocalPoint.fromWorldInstance(targetWorldPointUp);
        canvasPointUp = Perspective.localToCanvas(Microbot.getClient(), localPointUp, targetWorldPointUp.getPlane());
        targetWorldPointDown = new WorldPoint(1125, 3423, 0);
        localPointDown = Rs2LocalPoint.fromWorldInstance(targetWorldPointDown);
        canvasPointDown = Perspective.localToCanvas(Microbot.getClient(), localPointDown, targetWorldPointDown.getPlane());
        targetWorldPointC = new WorldPoint(1127, 3421+1, 0);
        localPointC =   Rs2LocalPoint.fromWorldInstance(new WorldPoint(targetWorldPointUp.getX(),targetWorldPointUp.getY()+1,targetWorldPointUp.getPlane()));
        canvasPointC = Perspective.localToCanvas(Microbot.getClient(), localPointC, targetWorldPointUp.getPlane());
        if ((Rs2Npc.getNpc(12223) != null || Rs2Npc.getNpc(12426) != null) && Rs2GameObject.getGameObject(47599) != null) {
            handleAxeDodging();
            protectionPrayers();
            movingAxeObjects();
            idleAxeObjects();
            resetVariables();
        }else {
            resetState();
        }
        return super.render(graphics);
    }
    public void handleAxeDodging() {
        if (isAxeOnNorthWestObj != null && isAxeOnSouthEastObj == null) {
            if (!isAtDownPosition() && !isPlayerOnDown) {
                moveToDown();
            }
            else if (northWestAxeDistanceToPlayer == 5 && !isPlayerOnUp) {
                moveToUp();
            }
        }
        else if (isAxeOnNorthWestObj == null && isAxeOnSouthEastObj != null) {
            if (shouldMoveDownForSEAxe() && !isPlayerOnDown) {


                moveToDown();
            } else if(!isPlayerOnUp){
                moveToUp();
            }
        }
        else if (isAxeOnNorthWestObj != null && isAxeOnSouthEastObj != null) {
            if (shouldMoveDownForSEAxe()) {
                moveToDown();
            }else if(!shouldMoveDownForSEAxe()){
                if (isPlayerOnDown && northWestAxeDistanceToPlayer == 5 && isMovingAxeOnNorthWestObj != null) {
                    moveToUp();
                }else if(isPlayerOnDown && northWestAxeDistanceToPlayer != 5 && isMovingAxeOnNorthWestObj == null){

                    moveToUp();
                }
            }
            //else {
            //    moveToUp();
            //}
        }else if (isAxeOnNorthWestObj == null && isAxeOnSouthEastObj == null) {
            if (isMovingAxeOnNorthWestObj != null && isPlayerOnUp) {
                moveToDown();
            }else if(isMovingAxeOnNorthWestObj != null && isPlayerOnDown){
                if (isAtDownPosition() && northWestAxeDistanceToPlayer == 5) {
                    moveToUp();
                }
            }
        }
    }
    // Helper methods for dodging
    private boolean shouldMoveDownForSEAxe() {
        return isAxeOnSouthEastObj != null &&
                isAxeOnSouthEastObj.getAnimation() == 10365 &&
                isAxeOnSouthEastObj.getAnimationFrame() >= 30;
    }
    private boolean isAtDownPosition() {
        return Rs2Player.getWorldLocation().equals(new WorldPoint(1125, 3423, 0));
    }
    private void moveToUp() {
        executeMovement(canvasPointUp);
    }
    private void moveToDown() {
        executeMovement(canvasPointDown);
    }
    private void moveToC() {
        executeMovement(canvasPointC);
    }
    private void executeMovement(Point target) {
        if (target == null) return;
        if (target.equals(canvasPointUp) && !target.equals(canvasPointDown) && currentTime > lastClickTimeUp + 100) {
            ClientUI.getClient().setEnabled(false);
            Microbot.getMouse().move(target);
            Microbot.getMouse().click();
            lastClickTimeUp = currentTime;


        }
        if (target.equals(canvasPointDown) && !target.equals(canvasPointUp) && currentTime > lastClickTimeDown + 100) {
            ClientUI.getClient().setEnabled(false);
            Microbot.getMouse().move(target);
            Microbot.getMouse().click();
            lastClickTimeDown = currentTime;


        }
        if (target.equals(canvasPointC) && !target.equals(canvasPointUp) && !target.equals(canvasPointDown) && currentTime > lastClickTimeC + 100) {
            ClientUI.getClient().setEnabled(false);
            Microbot.getMouse().move(target);
            Microbot.getMouse().click();
            lastClickTimeC = currentTime;

        }
    }
    public void resetVariables(){
        if(Rs2Npc.getNpc(12227) == null){
            canHightlightUp = false;
        }
        if(Rs2Npc.getNpc(12225) == null ||  (Rs2Npc.getNpc(12223) == null)){
            northWestAxeDistanceToPlayer = 100000;
            canHightlightDown = false;
            northWestAxeDistanceToPlayer = 100000;
            count = 100000;
            isAxeOnSouthEast = false;
            isAxeOnNorthWest = false;
            canGoBaseLocation = false;
            canReturnBaseWithoutCollidingToAxe = false;
            tileTrapUp = false;
            tileTrapDown = false;
            tileTrapEscapeC = false;
        }
    }
    public void protectionPrayers() {
        // Only handle prayers if not currently in a highlight state
        if (!canHightlightUp && !canHightlightDown) {
            if (Rs2Npc.getNpc(12226) != null) {
                if (Rs2Player.getLocalPlayer().getOverheadIcon() != HeadIcon.RANGED) {
                    if (!Rs2Prayer.isPrayerActive(Rs2PrayerEnum.PROTECT_RANGE)) {
                        Rs2Prayer.toggle(Rs2PrayerEnum.PROTECT_RANGE, true);
                    }
                }
            } else {
                if (Rs2Player.getLocalPlayer().getOverheadIcon() != HeadIcon.MELEE) {
                    if (!Rs2Prayer.isPrayerActive(Rs2PrayerEnum.PROTECT_MELEE)) {
                        Rs2Prayer.toggle(Rs2PrayerEnum.PROTECT_MELEE, true);
                    }
                }
            }
        }
    }
    public void movingAxeObjects() {
        Rs2Npc.getNpcs(12227).forEach(npc -> {
            if (npc == null || npc.getLocalLocation() == null) return;
            int x = npc.getLocalLocation().getSceneX();
            int y = npc.getLocalLocation().getSceneY();

            switch (x + "," + y) {
                case "68,39":
                    isMovingAxeOnNorthWestObj = npc;
                    break;
                case "62,45":
                    isMovingAxeOnSouthEastObj = npc;
                    break;
            }
        });
    }
    public void idleAxeObjects() {
        Rs2Npc.getNpcs(12225).forEach(npc -> {
            if (npc == null || npc.getLocalLocation() == null) return;
            int x = npc.getLocalLocation().getSceneX();
            int y = npc.getLocalLocation().getSceneY();
            // Use switch for coordinate mapping
            switch (x + "," + y) {

                case "70,37":
                    isAxeOnNorthWestObj = npc;
                    isAxeOnNorthWest = true;
                    break;
                case "60,47":
                    isAxeOnSouthEastObj = npc;
                    isAxeOnSouthEast = true;
                    break;
            }
        });
    }
    private void resetState() {
        northWestAxeDistanceToPlayer = 100000;
        count = 100000;
        canHightlightUp = false;
        canHightlightDown = false;
        // Reset all axe objects
        Rs2NpcModel[] axesToReset = {
                isAxeOnSouthEastObj, isAxeOnNorthWestObj, isMovingAxeOnSouthEastObj, isMovingAxeOnNorthWestObj
        };
        Arrays.stream(axesToReset).forEach(axe -> axe = null);
        // Reset all boolean flags
        boolean[] flagsToReset = {
                isAxeOnSouthEast, isAxeOnNorthWest,
                canGoBaseLocation, canReturnBaseWithoutCollidingToAxe
        };
        Arrays.fill(flagsToReset, false);
    }
}
