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
    private SimpleObject closestOre;
    private String oreName;

    @Override
    public void run() {

        boolean atDonatorMine = p.within(LocationsData.MINING_DONATOR_ZONE.getWorldArea())
                || p.within(LocationsData.BARROWS_MINING2.getWorldArea());
        boolean atBarrowMine = p.within(LocationsData.BARROWS_MINING.getWorldArea())
                || p.within(LocationsData.BARROWS_MINING2.getWorldArea());
        boolean atHomeMine = p.within(LocationsData.HOME_MINING.getWorldArea())
                || p.within(LocationsData.HOME_MINING2.getWorldArea());
        boolean atHome = p.within(LocationsData.HOME.getWorldArea());

        if (aioSkill.best) {
            aioSkill.miningData = new MiningData().getBestTaskForLevel(c.skills.realLevel(Skills.MINING));
        }

        if (RandomEventsHandler.needsAction()) {
            return;
        }

        if (c.inventory.getFreeSlots() <= 7
                && (aioSkill.getFirstOption().equals("Coal") || aioSkill.getFirstOption().equals("Luminite Ore"))) {
            hammerOres();
            return;
        }

        oreName = aioSkill.miningData.getTaskName().toLowerCase();
        String taskName = aioSkill.miningData.getTaskName().toLowerCase();

        if (atHome && taskName.contains("rune essence") && !c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("EssenceMine");
            return;
        }

        if (taskName.contains("oxi ore")) {
            if (c.inventory.inventoryFull()) {
                aioSkill.getScriptController().setTask("Transport");
            } else if (atBarrowMine) {
                aioSkill.getScriptController().setTask("MineWalk");
            } else {
                aioSkill.getScriptController().setTask("Transport");
            }
            return;
        }

        if (atHome && !taskName.contains("oxi ore") && !taskName.contains("luminite ore")) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        locateClosestOre(oreName, atBarrowMine, atHomeMine, atDonatorMine);

        if (c.inventory.inventoryFull()
                && (!oreName.equalsIgnoreCase("Coal") && !oreName.equalsIgnoreCase("Luminite Ore"))) {
            aioSkill.getScriptController().setTask("MineBank");
            return;
        }

        if (c.players.getLocal().isAnimating()) {
            return;
        }

        if (closestOre != null) {
            mineOre();
        } else {
            c.sleep(600, 1800);
        }
    }

    private void locateClosestOre(String oreName, boolean atBarrowMine, boolean atHomeMine, boolean atDonatorMine) {
        SimpleObject ore1, ore2;
        if (atDonatorMine) {
            ore1 = c.objects.populate().filter(LocationsData.MINING_DONATOR_ZONE.getWorldArea()).filterContains(oreName)
                    .nextNearest();
            ore2 = c.objects.populate().filter(LocationsData.MINING_DONATOR_ZONE.getWorldArea()).filterContains(oreName)
                    .nextNearest();
        } else if (atBarrowMine) {
            ore1 = c.objects.populate().filter(LocationsData.BARROWS_MINING.getWorldArea()).filterContains(oreName)
                    .nextNearest();
            ore2 = c.objects.populate().filter(LocationsData.BARROWS_MINING2.getWorldArea()).filterContains(oreName)
                    .nextNearest();
        } else if (atHomeMine) {
            ore1 = c.objects.populate().filter(LocationsData.HOME_MINING.getWorldArea()).filterContains(oreName)
                    .nextNearest();
            ore2 = c.objects.populate().filter(LocationsData.HOME_MINING2.getWorldArea()).filterContains(oreName)
                    .nextNearest();
        } else {
            ore1 = ore2 = null;
        }

        if (ore1 != null && ore2 != null) {
            closestOre = (ore1.distanceTo(c.players.getLocal()) <= ore2.distanceTo(c.players.getLocal())) ? ore1 : ore2;
        } else {
            closestOre = (ore1 != null) ? ore1 : ore2;
        }
    }

    private void mineOre() {
        closestOre.menuAction("Mine");
        c.sleepCondition(c.players.getLocal()::isAnimating);
        aioSkill.status = "Mining " + closestOre.getName();
        c.onCondition(() -> !c.players.getLocal().isAnimating(), 600, 10);
    }

    private void hammerOres() {
        aioSkill.status = "Hammering Ore";
        SimpleItem hammer = c.inventory.populate().filter("hammer").next();
        SimpleItem oreItem = c.inventory.populate()
                .filter(item -> item.getName().equals("Luminite Ore") || item.getName().equals("Coal")).next();

        if (hammer != null && oreItem != null) {
            oreItem.menuAction("Use");
            double x = hammer.getClickBounds().getCenterX();
            double y = hammer.getClickBounds().getCenterY();
            int intX = (int) x;
            int intY = (int) y;
            c.mouse.moveMouse(intX, intY);
            c.mouse.click(intX, intY, true);
            c.sleep(1200, 1600);
            c.keyboard.clickKey(32);
            c.onCondition(() -> c.inventory.getFreeSlots() == 25, 600, 20);
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "Mine";
    }
}
