package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.MiningData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;

public class Mine extends Task {
    SimpleObject ore;
    private String oreName;
    private SimpleObject ore2;
    private SimpleObject ore1;

    @Override
    public void run() {
        boolean atBarrowMine = p.within(LocationsData.BARROWS_MINING.getWorldArea())
                || p.within(LocationsData.BARROWS_MINING2.getWorldArea());
        boolean atHomeMine = p.within(LocationsData.HOME_MINING.getWorldArea())
                || p.within(LocationsData.HOME_MINING2.getWorldArea());
        boolean atHome = p.within(LocationsData.HOME.getWorldArea());
        String taskName = aioSkill.miningData.getTaskName().toLowerCase();
        if (aioSkill.best) {
            aioSkill.miningData = new MiningData().getBestTaskForLevel(c.skills.realLevel(Skills.MINING));
        }
        if (RandomEventsHandler.needsAction()) {
            return;
        }
        if (atHome && taskName.contains("rune essence") && !c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("EssenceMine");
            return;
        }
        if (atBarrowMine && taskName.contains("oxi ore") && c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }
        if (atBarrowMine && taskName.contains("oxi ore") && c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }
        if (!atBarrowMine && taskName.contains("oxi ore") && !c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("MineWalk");
            return;
        }
        if (atHomeMine && taskName.contains("oxi ore") && !c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (atHome && (!taskName.contains("oxi ore") || !taskName.contains("luminite ore"))) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        RandomEventsHandler.needsAction();
        oreName = aioSkill.miningData.getTaskName();

        if (c.inventory.inventoryFull()
                && (!oreName.equals("Coal") || !oreName.equals("Luminite Ore"))) {
            aioSkill.getScriptController().setTask("Bank");
            return;
        }

        if (c.players.getLocal().isAnimating()) {
            return;
        }
        oreName = aioSkill.miningData.getTaskName();

        switch (oreName) {
            case "Rune Essence":
                aioSkill.getScriptController().setTask("EssenceMine");
                break;
            case "Luminite Ore":
                ore1 = c.objects.populate().filter(LocationsData.BARROWS_MINING.getWorldArea())
                        .filterContains(oreName)
                        .nextNearest();
                ore2 = c.objects.populate().filter(LocationsData.BARROWS_MINING2.getWorldArea())
                        .filterContains(oreName)
                        .nextNearest();
                break;
            case "Oxi Ore":
                ore1 = c.objects.populate().filter(LocationsData.BARROWS_MINING.getWorldArea())
                        .filterContains(oreName)
                        .nextNearest();
                ore2 = c.objects.populate().filter(LocationsData.BARROWS_MINING2.getWorldArea())
                        .filterContains(oreName)
                        .nextNearest();
                break;
            default:
                ore1 = c.objects.populate().filter(LocationsData.HOME_MINING.getWorldArea())
                        .filterContains(oreName)
                        .nextNearest();
                ore2 = c.objects.populate().filter(LocationsData.HOME_MINING2.getWorldArea())
                        .filterContains(oreName)
                        .nextNearest();
                break;
        }

        SimpleObject closestOre = null;
        if (ore1 != null && ore2 != null) {
            closestOre = (ore1.distanceTo(c.players.getLocal()) <= ore2.distanceTo(c.players.getLocal())) ? ore1 : ore2;
        } else if (ore1 != null) {
            closestOre = ore1;
        } else if (ore2 != null) {
            closestOre = ore2;
        }

        if (closestOre != null) {
            closestOre.menuAction("Mine");
            c.sleepCondition(c.players.getLocal()::isAnimating);
            aioSkill.status = "Mining " + closestOre.getName();
            c.onCondition(() -> !c.players.getLocal().isAnimating(), 600, 10);

            if ((closestOre.getName().equals("Coal") || closestOre.getName().equals("Luminite Ore"))
                    && c.inventory.getFreeSlots() < 10) {
                aioSkill.status = "Hammering Ore";
                SimpleItem hammer = c.inventory.populate().filter("hammer").next();
                SimpleItem oreItem = c.inventory.populate()
                        .filter(item -> item.getName().equals("Luminite Ore") || item.getName().equals("Coal")).next();

                oreItem.menuAction("Use");
                double x = hammer.getClickBounds().getCenterX();
                double y = hammer.getClickBounds().getCenterY();
                int intX = (int) x;
                int intY = (int) y;
                c.mouse.moveMouse(intX, intY);
                c.mouse.click(intX, intY, true);
                c.sleep(1200, 1600);
                c.keyboard.clickKey(32);
                c.onCondition(() -> c.inventory.getFreeSlots() == 25, 600, 10);
            }
        } else {
            c.sleep(600, 1800);
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "Mine";
    }

}
