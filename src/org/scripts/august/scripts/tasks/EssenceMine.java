package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;

public class EssenceMine extends Task {

    private SimpleNpc aubury;

    @Override
    public void run() {
        boolean atHome = p.within(LocationsData.HOME.getWorldArea());
        aubury = c.npcs.populate().filterContains("Aubury").next();
        SimpleObject runeEssence = c.objects.populate()
                .filter(obj -> obj.getName().contains("Rune Essence") && !obj.isImposter() && obj.visibleOnScreen())
                .next();

        if (RandomEventsHandler.needsAction() || c.players.getLocal().isAnimating()) {
            return;
        }

        if (aubury != null && atHome) {
            aubury.menuAction("Teleport");
            c.onCondition(() -> !atHome, 600, 10);
            return;
        }

        if (runeEssence != null) {
            if (!c.inventory.inventoryFull()) {
                runeEssence.click("Mine");
            } else {
                aioSkill.getScriptController().setTask("Transport");
            }
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "EssenceMine";
    }
}
