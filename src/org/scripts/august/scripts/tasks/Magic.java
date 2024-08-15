package org.scripts.august.scripts.tasks;

import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleItem;

public class Magic extends Task {

    private SimpleItem alchitem;

    @Override
    public void run() {
        alchitem = c.inventory.populate().filterContains(aioSkill.alchItem).next();
        c.menuActions.sendAction(25, -1, 1428692, 0, "Cast", "<col=00ff00>High Level Alchemy</col>");
        c.menuActions.sendAction(58, 0, 9764864, 0, "cast",
                "<col=00ff00>High Level Alchemy</col><col=ffffff> -> <col=ff9040>" + alchitem.getName() + "</col>");
        c.sleep(600, 1200);

    }

    @Override
    public String DebugTaskDescription() {
        return "Magic";
    }

}
