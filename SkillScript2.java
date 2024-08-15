package skill;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.SwingUtilities;
import skill.task.Thieving.Bank;
import skill.task.Thieving.Sell;
import skill.task.Thieving.Thieve;

import net.runelite.api.Point;
import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.LoopingScript;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Viewport;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;
import skill.data.LocationsData;
import skill.data.ThievingData;
// import skill.data.WoodcuttingData;
// import skill.data.WoodcuttingData.Task;
import skill.task.ThievingTask;
import skill.utils.RandomEvents;

@ScriptManifest(author = "fff7777", name = "autoAIO", category = Category.UTILITY, version = "0.0.1", description = "Completes a ton of Theiving tasks", discord = "", servers = {
        "August" }, vip = false)

public class SkillScript extends TaskScript
        implements LoopingScript, SimplePaintable, SimpleMessageListener, MouseListener {

    private long coinsAtStart;
    private long totalRandomsDismissed;
    private String status;
    private SkillScriptPaint paintHelper;
    public boolean progressive;
    public String stall;
    public boolean started;
    public String method;
    public SkillUI gui;
    public ThievingData.Task thieveTask;
    private SimpleNpc sellNpc;
    private Graphics2D graphics;
    private long previousCoinCount = 0;
    private long totalCoinsGained = 0;
    private long startTime = System.currentTimeMillis();
    public String target;
    public String skill;
    private WoodcuttingData.Task woodcuttingTask;
    private skill.data.ThievingData.Task thieveData;

    @Override
    public void onExecute() {
        this.state = new TemplateState(this);
        setScriptStatus("Waiting to start...");
        this.startTime = System.currentTimeMillis();
        this.coinsAtStart = getCoinCount();
        setupPaint();
        setupGUI();
        this.tasks.addAll(Arrays.asList());

    }

    @Override
    public List<Task> tasks() {
        return this.tasks;
    }

    private void setupPaint() {
        if (this.paintHelper != null) {
            return;
        }
        this.paintHelper = new SkillScriptPaint(this);
    }

    private void setupGUI() {
        try {
            SkillScript script = this;
            SwingUtilities.invokeLater(() -> gui = new SkillUI(script));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProcess() {
        if (!started) {
            return;
        }
        if (ctx.viewport.zoomValue() != 0 || ctx.viewport.pitch(382)) {
            ctx.viewport.zoom(Viewport.CameraZoom.ZOOM_0);
            ctx.viewport.pitch(382);
            ctx.menuActions.sendAction(57, 1, 35913751, 1, "Look North", "");
        }
        if (RandomEvents.needsAction()) {
            totalRandomsDismissed += 1;
        }
        if (skill == "Thieving") {
            scriptController.addTask("Bank", new Bank());
            scriptController.addTask("Sell", new Sell());
            scriptController.addTask("Thieve", new Thieve());
            if (target == "Best") {
                thieveData = new ThievingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.THIEVING));
            } else {
                thieveData = new ThievingData().fromTaskName(stall);
            }
            updateCoinCount();
        }
        if (skill == "Woodcutting") {
            if (target == "Best") {
                woodcuttingTask = new WoodcuttingData().getBestTaskForLevel(ctx.skills.realLevel(Skills.WOODCUTTING));
            } else {
                woodcuttingTask = new WoodcuttingData().fromTaskName(stall);
            }
            woodcuttingTasks();
        }

    }

    public void drawAllAreas(Graphics2D g) {
        ctx.paint.drawWorldArea(g, LocationsData.HOME.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.HOME_MINING.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.HOME_MINING2.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.THIEVE.getWorldArea());
        ctx.paint.drawWorldArea(g, LocationsData.WOODCUTTING.getWorldArea());
    }

    private void woodcuttingTasks() {

    }

    private void updateCoinCount() {
        long currentCoins = getCoinCount();
        long coinsGained = currentCoins - previousCoinCount;

        if (coinsGained > 0) {
            totalCoinsGained += coinsGained;
        }
        previousCoinCount = currentCoins;
    }

    private long getCoinCount() {
        SimpleItem coins = ctx.inventory.populate().filter("Coins").next();
        return (coins != null) ? coins.getQuantity() : 0;
    }

    public long getTotalCoinsGained() {
        return this.totalCoinsGained;
    }

    public long getHourlyCoinsGained() {
        long elapsedTime = System.currentTimeMillis() - this.startTime;
        return (totalCoinsGained * 3600000) / elapsedTime;
    }

    long getDismissedCount() {
        return totalRandomsDismissed;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public String getScriptStatus() {
        return this.status;
    }

    public void setScriptStatus(String status) {
        this.status = status;
    }

    @Override
    public void onChatMessage(ChatMessage e) {
    }

    @Override
    public void paint(final Graphics g1) {
        if (this.paintHelper == null) {
            return;
        }
        final Graphics2D g = (Graphics2D) g1;
        this.paintHelper.drawPaint(g);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (this.paintHelper != null) {
            this.paintHelper.handleMouseClick(e);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }

    @Override
    public void onTerminate() {
        if (gui != null) {
            gui.onCloseGUI();
        }
    }

    @Override
    public int loopDuration() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loopDuration'");
    }

    @Override
    public boolean prioritizeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prioritizeTasks'");
    }

    @Override
    public List<simple.hooks.scripts.task.Task> tasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tasks'");
    }

}
