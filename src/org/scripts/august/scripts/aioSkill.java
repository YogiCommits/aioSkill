package org.scripts.august.scripts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import net.runelite.client.util.Text;
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
import org.scripts.august.scripts.tasks.FishBank;
import org.scripts.august.scripts.tasks.GetSlayerTask;
import org.scripts.august.scripts.tasks.Mine;
import org.scripts.august.scripts.tasks.MineBank;
import org.scripts.august.scripts.tasks.MineWalk;
import org.scripts.august.scripts.tasks.Runecraft;
import org.scripts.august.scripts.tasks.RunecraftBank;
import org.scripts.august.scripts.tasks.RunecraftTransport;
import org.scripts.august.scripts.tasks.SlayerBank;
import org.scripts.august.scripts.tasks.SlayerTree;
import org.scripts.august.scripts.tasks.SlayerWalk;
import org.scripts.august.scripts.tasks.SmithnSmelt;
import org.scripts.august.scripts.tasks.Thieve;
import org.scripts.august.scripts.tasks.Transport;
import org.scripts.august.scripts.tasks.EssenceMine;
import org.scripts.august.scripts.tasks.Woodcut;
import org.scripts.august.scripts.tasks.Magic;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.runelite.api.ChatMessageType;
import net.runelite.client.eventbus.Subscribe;
import net.unethicalite.api.packets.UpdateSlayerInfoServerPacket;
import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.LoopingScript;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.script.Script;

@ScriptManifest(servers = {
        "August" }, discord = "", version = "6.0", name = "aioSkill", author = "fff7777", category = Category.MONEYMAKING, description = "Please read the forum post for detials")
public class aioSkill extends Script implements MouseListener, LoopingScript {
    static ScriptController scriptController;
    private aioSkillPaint paintHelper;
    public static Object sellNpc;
    public static String skill;
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
    public static SlayerData slayerTask = new SlayerData("", 0);
    public static String slayerMaster;
    public static boolean usePrayer;
    long elapsedTime = System.currentTimeMillis() - getStartTime();
    int superiorSpawned;
    public boolean slayWorldBoss;
    public boolean randomizeSkill;
    public boolean prestige;
    public static String firstOption;
    public static String secondOption;
    public static boolean superiorUp;
    public static String alchItem;
    public static int tGobblinCount;

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        final String message = chatMessage.getMessage();

        if (message.matches("(?i)(?s).*\\byou have completed \\d+ slayer\\b.*")) {
            slayerTask = new SlayerData("", 0);
            slayerTasksCompleted++;
            return;
        }

        String cleanMessage = Text.removeTags(message);
        if (cleanMessage.matches("(?i)(?s).*\\bA superior foe has appeared\\.\\.\\.\\b.*")
                || message.matches("(?i)(?s).*\\bA superior foe has appeared\\.\\.\\.\\b.*")
                || message.contains("superior foe has appeared")) {
            superiorUp = true;
            superiorSpawned++;
            return;
        }

        if (chatMessage.getType() == ChatMessageType.GAMEMESSAGE || chatMessage.getType() == ChatMessageType.SPAM) {
            String chatMsg = Text.removeTags(chatMessage.getMessage());
            if (chatMsg.equals("A superior foe has appeared...")) {
                superiorUp = true;
                superiorSpawned++;
                return;
            }

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
        scriptController.addTask("MineBank", new MineBank());
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
        scriptController.addTask("SmithnSmelt", new SmithnSmelt());
        scriptController.addTask("FishBank", new FishBank());
        scriptController.addTask("RunecraftTransport", new RunecraftTransport());
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
        if (!started) {
            return;
        }
        if (System.currentTimeMillis() - getStartTime() >= 7200000 && !ctx.user.forumsName().equals("ffff777")) {
            ctx.stopScript();
        }
        if (skill == "Thieving") {
            if (firstOption == "Best") {
                thieveData = new ThievingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.THIEVING));
                best = true;
            } else {
                thieveData = new ThievingData().fromTaskName(firstOption);
            }
        }
        if (skill == "Woodcutting") {
            if (firstOption == "Best") {
                woodcuttingData = new WoodcuttingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.WOODCUTTING));
                best = true;
            } else {
                woodcuttingData = new WoodcuttingData().fromTaskName(firstOption);
            }
        }
        if (skill == "Mining") {
            if (firstOption == "Best") {
                miningData = new MiningData().getBestTaskForLevel(ctx.skills.realLevel(Skills.MINING));
                best = true;
            } else {
                miningData = new MiningData().fromTaskName(firstOption);
            }
        }
        if (skill == "Fishing") {
            if (firstOption == "Best") {
                fishingData = new FishingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.FISHING));
                best = true;
            } else {
                fishingData = new FishingData().fromAction(firstOption);
            }
        }
        if (skill == "Slayer") {
            if (firstOption == "Best") {
                slayerMaster = SlayerData.getBestTaskForLevel(ctx.skills.realLevel(Skills.SLAYER));
                best = true;
            } else {
                slayerMaster = firstOption;
            }
        }
        if (skill == "Cooking") {
            if (firstOption == "Best") {
                best = true;
                miningData = new MiningData().getBestTaskForLevel(ctx.skills.realLevel(Skills.MINING));
            } else {
                miningData = new MiningData().fromTaskName(firstOption);
            }
        }
        if (skill == "Crafting") {
            if (firstOption == "Best") {
                best = true;
                craftingData = new CraftingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.MINING));
            } else {
                craftingData = new CraftingData().fromTaskName(firstOption);
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
        firstOption = null;
        firstOption = null;
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
        ctx.paint.drawWorldArea(g, LocationsData.MINING_DONATOR_ZONE.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.WOODCUTTING_DONATOR_ZONE.getWorldArea());
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

    public String getfirstOption() {
        return this.firstOption;
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

    public aioSkillPaint getPaintHelper() {
        return paintHelper;
    }

    public static Object getSellNpc() {
        return sellNpc;
    }

    public String getSkill() {
        return skill == null ? "" : skill;
    }

    public boolean isStarted() {
        return started;
    }

    public String getStatus() {
        return status;
    }

    public SkillUI getGui() {
        return gui;
    }

    public static MiningData.Task getMiningData() {
        return miningData;
    }

    public static WoodcuttingData.Task getWoodcuttingData() {
        return woodcuttingData;
    }

    public static FishingData.Task getFishingData() {
        return fishingData;
    }

    public static ThievingData.Task getThieveData() {
        return thieveData;
    }

    public int getSlayerTasksCompleted() {
        return slayerTasksCompleted;
    }

    public String getSlayerTaskNpc() {
        return slayerTaskNpc;
    }

    public static Task getCraftingData() {
        return craftingData;
    }

    public static String getBoost() {
        return boost;
    }

    public static String getPrimaryWeaponString() {
        return primaryWeaponString;
    }

    public static String getSecondaryWeaponString() {
        return secondaryWeaponString;
    }

    public static String getFoodString() {
        return foodString;
    }

    public static SimpleItem getPrimaryWeapon() {
        return primaryWeapon;
    }

    public static boolean isBest() {
        return best;
    }

    public static SlayerData getSlayerTask() {
        return slayerTask;
    }

    public static String getSlayerMaster() {
        return slayerMaster;
    }

    public static boolean isUsePrayer() {
        return usePrayer;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public int getSuperiorSpawned() {
        return superiorSpawned;
    }

    public static String getFirstOption() {
        return firstOption == null ? "" : firstOption;
    }

    public static String getSecondOption() {
        return secondOption == null ? "" : secondOption;
    }

    public static boolean isSuperiorUp() {
        return superiorUp;
    }

    public static String getAlchItem() {
        return alchItem;
    }

    public int getTGobblinCount() {
        return tGobblinCount;
    }

    @Override
    public int loopDuration() {
        return 300;
    }

}
