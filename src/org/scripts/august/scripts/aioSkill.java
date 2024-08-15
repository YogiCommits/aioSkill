package org.scripts.august.scripts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import org.data.CraftingData;
import org.data.CraftingData.Task;
import org.data.FishingData;
import org.data.LocationsData;
import org.data.MiningData;
import org.data.SlayerData;
import org.data.ThievingData;
import org.data.WoodcuttingData;
import org.scripter.ScriptController;
import org.scripts.august.scripts.tasks.Bank;
import org.scripts.august.scripts.tasks.Combat;
import org.scripts.august.scripts.tasks.Craft;
import org.scripts.august.scripts.tasks.Fish;
import org.scripts.august.scripts.tasks.GetSlayerTask;
import org.scripts.august.scripts.tasks.Mine;
import org.scripts.august.scripts.tasks.MineWalk;
import org.scripts.august.scripts.tasks.Runecraft;
import org.scripts.august.scripts.tasks.RunecraftBank;
import org.scripts.august.scripts.tasks.SlayerBank;
import org.scripts.august.scripts.tasks.SlayerTree;
import org.scripts.august.scripts.tasks.SlayerWalk;
import org.scripts.august.scripts.tasks.Thieve;
import org.scripts.august.scripts.tasks.Transport;
import org.scripts.august.scripts.tasks.EssenceMine;
import org.scripts.august.scripts.tasks.Woodcut;
import org.scripts.august.scripts.tasks.Magic;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.runelite.client.eventbus.Subscribe;
import net.unethicalite.api.packets.UpdateSlayerInfoServerPacket;
import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Viewport;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.script.Script;

@ScriptManifest(servers = {
        "August" }, discord = "", version = "4.0", name = "aioSkill", author = "fff7777", category = Category.UTILITY, description = "Skills")
public class aioSkill extends Script implements MouseListener {
    static ScriptController scriptController;
    private aioSkillPaint paintHelper;
    public static Object sellNpc;
    public static String skill;
    public String target;
    public boolean started;
    public static String status;
    private long startTime;
    SkillUI gui;
    public static MiningData.Task miningData;
    public static WoodcuttingData.Task woodcuttingData;
    public static FishingData.Task fishingData;
    public static ThievingData.Task thieveData;
    public int slayerTasksCompleted = 0;
    String slayerTaskNpc;
    public static Task craftingData;
    public static String boost;
    public static String primaryWeaponString;
    public static String secondaryWeaponString;
    public static String foodString;
    public static SimpleItem primaryWeapon;
    public static boolean best;
    public static String health;
    public static SlayerData slayerTask = new SlayerData("", 0);
    public static String slayerMaster;
    public static boolean usePrayer;
    long elapsedTime = System.currentTimeMillis() - getStartTime();
    public static boolean superiorUp;
    public static String alchItem;

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        final String message = chatMessage.getMessage();

        // First pattern for slayer tasks
        String slayerPattern = "(?i)you have completed \\d+ slayer";
        Matcher slayerMatcher = Pattern.compile(slayerPattern).matcher(message);

        if (slayerMatcher.find()) {
            slayerTask = new SlayerData("", 0);
            slayerTasksCompleted++;
        }

        // Second pattern for superior foe
        String superiorPattern = "A superior foe has ";
        Matcher superiorMatcher = Pattern.compile(Pattern.quote(superiorPattern)).matcher(message);

        if (superiorMatcher.find()) {
            superiorUp = true;
        }
    }

    @Subscribe
    public void onCustomUpdateSlayerTaskServerPacketReceived(UpdateSlayerInfoServerPacket packet) {
        slayerTask.killAmount = packet.getTaskAmountRemaining();
        slayerTaskNpc = packet.getTaskName();

    }

    @Override
    public void onExecute() {
        slayerTask = new SlayerData("", 0);
        this.startTime = System.currentTimeMillis();
        scriptController = new ScriptController();
        scriptController.addTask("Bank", new Bank());
        scriptController.addTask("Thieve", new Thieve());
        scriptController.addTask("Woodcut", new Woodcut());
        scriptController.addTask("Mine", new Mine());
        scriptController.addTask("Transport", new Transport());
        scriptController.addTask("Fish", new Fish());
        scriptController.addTask("GetSlayerTask", new GetSlayerTask());
        scriptController.addTask("SlayerTree", new SlayerTree());
        scriptController.addTask("Combat", new Combat());
        scriptController.addTask("Runecraft", new Runecraft());
        scriptController.addTask("SlayerBank", new SlayerBank());
        scriptController.addTask("MineWalk", new MineWalk());
        scriptController.addTask("SlayerWalk", new SlayerWalk());
        scriptController.addTask("RunecraftBank", new RunecraftBank());
        scriptController.addTask("Craft", new Craft());
        scriptController.addTask("EssenceMine", new EssenceMine());
        scriptController.addTask("Magic", new Magic());
        scriptController.setTask("Bank");
        setupGUI();
        setupPaint();
    }

    void setupGUI() {
        try {
            if (gui == null || !gui.isVisible()) {
                aioSkill script = this;
                SwingUtilities.invokeLater(() -> {
                    gui = new SkillUI(script);
                    gui.setVisible(true);
                });
            } else {
                SwingUtilities.invokeLater(() -> gui.toFront());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProcess() {
        if (ctx.viewport.zoomValue() != 0 || ctx.viewport.pitch(382)) {
            ctx.viewport.zoom(Viewport.CameraZoom.ZOOM_0);
            ctx.viewport.pitch(382);
            ctx.menuActions.sendAction(57, 1, 35913751, 1, "Look North", "");
        }
        if (!started) {
            System.out.println(skill);
            return;
        }
        if (elapsedTime >= 3600000 && !ctx.user.forumsName().equals("ffff777")) {
            ctx.stopScript();
        }
        if (skill == "Thieving") {
            if (target == "Best") {
                thieveData = new ThievingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.THIEVING));
                best = true;
            } else {
                thieveData = new ThievingData().fromTaskName(target);
            }
        }
        if (skill == "Woodcutting") {
            if (target == "Best") {
                woodcuttingData = new WoodcuttingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.WOODCUTTING));
                best = true;
            } else {
                woodcuttingData = new WoodcuttingData().fromTaskName(target);
            }
        }
        if (skill == "Mining") {
            if (target == "Best") {
                miningData = new MiningData().getBestTaskForLevel(ctx.skills.realLevel(Skills.MINING));
                best = true;
            } else {
                miningData = new MiningData().fromTaskName(target);
            }
        }
        if (skill == "Fishing") {
            if (target == "Best") {
                fishingData = new FishingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.FISHING));
                best = true;
            } else {
                fishingData = new FishingData().fromNpcName(target);
            }
        }
        if (skill == "Slayer") {
            if (target == "Best") {
                slayerMaster = SlayerData.getBestTaskForLevel(ctx.skills.realLevel(Skills.SLAYER));
                best = true;
            } else {
                slayerMaster = target;
            }
        }
        if (skill == "Cooking") {
            if (target == "Best") {
                best = true;
                miningData = new MiningData().getBestTaskForLevel(ctx.skills.realLevel(Skills.MINING));
            } else {
                miningData = new MiningData().fromTaskName(target);
            }
        }
        if (skill == "Crafting") {
            if (target == "Best") {
                best = true;
                craftingData = new CraftingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.MINING));
            } else {
                craftingData = new CraftingData().fromTaskName(target);
            }
        }
        scriptController.run();
    }

    @Override
    public void onTerminate() {
        if (gui != null) {
            gui.onCloseGUI();
            gui = null;
        }
        skill = null;
        target = null;
        health = null;
        boost = null;
        scriptController = null;
        sellNpc = null;
        miningData = null;
        woodcuttingData = null;
        fishingData = null;
        thieveData = null;
        slayerTask = null;
        slayerMaster = null;
        if (scriptController != null) {
            scriptController.clean();
        }
        paintHelper = null;
        System.gc();
    }

    private void setupPaint() {
        if (this.paintHelper != null) {
            return;
        }
        this.paintHelper = new aioSkillPaint(this);
    }

    public void drawAllAreas(Graphics2D g) {
        ctx.paint.drawWorldArea(g, LocationsData.HOME.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.HOME_MINING.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.HOME_MINING2.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.WOODCUTTING.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.SLAYER_BARROWS.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.BARROWS_MINING.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.BARROWS_MINING2.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.SLAYER_EMERALD.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.SLAYER_GREEN_DRAGON.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.THIEVE.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.SLAYER_KRAKEN.getWorldArea());
        ctx.paint.drawTileMatrix(g, FishingData.ignoreFishingSpot);
        ctx.paint.drawTileMatrix(g, FishingData.ignoreFishingSpot2);
        ctx.paint.drawTileMatrix(g, FishingData.walkFishingSpot);
    }

    @Override
    public void paint(final Graphics g1) {
        if (this.paintHelper == null) {
            return;
        }
        final Graphics2D g = (Graphics2D) g1;
        this.paintHelper.drawPaint(g);
    }

    public static ScriptController getScriptController() {
        return scriptController;
    }

    public long getStartTime() {
        return this.startTime;
    }

    @SuppressWarnings("static-access")
    public String getScriptStatus() {
        return this.status;
    }

    public int getTaskCompleted() {
        return this.slayerTasksCompleted;
    }

    @SuppressWarnings("static-access")
    public void setScriptStatus(String status) {
        this.status = status;
    }

    public String getTarget() {
        return this.target;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (this.paintHelper != null) {
            this.paintHelper.handleMouseClick(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
