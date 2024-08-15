package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.SlayerData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.wrappers.SimpleNpc;

public class GetSlayerTask extends Task {

    @Override
    public void run() {
        if (aioSkill.best) {
            aioSkill.slayerMaster = SlayerData.getBestTaskForLevel(c.skills.realLevel(Skills.SLAYER));
        }
        if (RandomEventsHandler.needsAction()) {
            return;
        }

        if (c.players.getLocal().within(LocationsData.HOME.getWorldArea())
                && (aioSkill.slayerTask.getKillAmount() == 0 || aioSkill.slayerTask.getTaskName().isEmpty())) {
            SimpleNpc slayerMaster = c.npcs.populate().filter(aioSkill.slayerMaster).nextNearest();
            if (slayerMaster != null) {

                slayerMaster.menuAction("Assignment");
                c.onCondition(() -> c.dialogue.dialogueOpen(), 300, 10);
                String slayerTaskDialogue = c.dialogue.getDialogueTitleAndMessage()[1];
                aioSkill.slayerTask = SlayerData.fromDialogue(slayerTaskDialogue);
            }
        } else {

            aioSkill.getScriptController().setTask("Transport");
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "GetSlayerTask";
    }
}
