package org.scripts.august.scripts.tasks;

import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

public class SlayerTree extends Task {
    @Override
    public void run() {
        if (c.prayers.quickPrayers()) {
            c.prayers.quickPrayers(false);
        }
        if (RandomEventsHandler.needsAction()) {
            return;
        }

        if (aioSkill.slayerTask.getKillAmount() == 0 || aioSkill.slayerTask.getTaskName().isEmpty()) {
            aioSkill.getScriptController().setTask("GetSlayerTask");
            return;
        }

        if ((aioSkill.secondOption.equals("Prayer") && !c.inventory.populate().filterContains("prayer").isEmpty()) ||
                (aioSkill.secondOption.equals("Food")
                        && !c.inventory.populate().filterContains(aioSkill.foodString).isEmpty())) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        Integer killAmount = aioSkill.slayerTask.getKillAmount();

        if (killAmount == null || killAmount == 0) {
            aioSkill.getScriptController().setTask("GetSlayerTask");
            return;
        } else {
            aioSkill.getScriptController().setTask("Bank");
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "SlayerTree";
    }
}
