package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.SlayerData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import net.runelite.api.Point;
import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;

public class Combat extends Task {

    private SimpleItem prayerPotion;
    private SimpleNpc superiorTaskNpc;
    private SimpleNpc taskNpc;
    private SimpleGroundItem loot;
    private SimpleItem boost;

    @Override
    public void run() {
        SimpleItem loopKey = c.inventory.populate().filterContains("Loop half of key").next();
        SimpleItem toothKey = c.inventory.populate().filterContains("Tooth half of key").next();

        if (loopKey != null && toothKey != null) {
            loopKey.menuAction("use");
            Point pos = getClickPosition(toothKey);
            c.mouse.moveMouse(pos.getX(), pos.getY());
            c.mouse.click(pos);
            c.sleep(1000, 1500);
        }

        if (!p.within(LocationsData.HOME.getWorldArea())) {
            handleLooting();
        }

        if (aioSkill.slayerTask.getTaskName().equals("Cave Kraken")
                && !c.inventory.populate().filterContains(aioSkill.secondaryWeaponString).isEmpty()) {
            SimpleItem staff = c.inventory.populate().filterContains(aioSkill.secondaryWeaponString).next();
            if (staff != null) {
                staff.menuAction("Wield");
            }
        }

        if (c.equipment.populate().filterContains(aioSkill.primaryWeaponString).isEmpty()
                && !aioSkill.slayerTask.getTaskName().contains("Cave Kraken")) {
            SimpleItem staff = c.inventory.populate().filterContains(aioSkill.primaryWeaponString).next();
            if (staff != null) {
                staff.menuAction("Wield");
            }
        }

        if (p.within(LocationsData.HOME.getWorldArea())) {
            c.prayers.quickPrayers(false);
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (RandomEventsHandler.needsAction()) {
            return;
        }

        if (aioSkill.slayerTask.getKillAmount() == 0) {
            aioSkill.getScriptController().setTask("SlayerTree");
            return;
        }

        handleCombat();
        if (taskNpc != null && taskNpc.getActor() != null && p.getInteracting() != null
                && p.getInteracting().equals(taskNpc.getActor())) {
            return;
        }

        findAndAttackNpc();

    }

    private Point getClickPosition(SimpleItem item) {
        double x = item.getClickBounds().getCenterX();
        double y = item.getClickBounds().getCenterY();
        return new Point((int) x, (int) y);
    }

    private void handleLooting() {
        if (!c.groundItems.populate().filterContains(SlayerData.LOOT).isEmpty() && !c.inventory.inventoryFull()) {
            for (String item : SlayerData.LOOT) {
                loot = c.groundItems.populate().filterContains(item).filterWithin(5).next();
                if (loot != null && !loot.getName().contains("bolt tips") && !loot.getName().contains("Uncut")) {
                    loot.menuAction("Take");
                    c.onCondition(() -> c.groundItems.populate().filterContains(item).nextNearest() == null, 1000, 5);
                }
            }
        }
    }

    private void handleCombat() {
        if (c.combat.inCombat() && "Prayer".equals(aioSkill.health)
                && !aioSkill.slayerTask.getTaskName().equals("Cave Kraken")
                && (!p.within(LocationsData.SLAYER_BLACK_DRAGON.getWorldArea()))) {
            if (!c.prayers.quickPrayers()) {
                c.prayers.quickPrayers(true);
            }

            if (c.inventory.populate().filterContains("pray").isEmpty()) {
                aioSkill.getScriptController().setTask("Bank");
            } else if (c.prayers.points() <= 30 && havePrayer()) {
                drinkPrayer();
            }
        }
        if (c.combat.inCombat() && "Food".equals(aioSkill.health)) {
            if (c.inventory.populate().filterContains(aioSkill.foodString).isEmpty()) {
                aioSkill.getScriptController().setTask("Bank");
            } else if (c.combat.health() <= 20 && haveFood()) {
                eatFood();
            }
        }
        if (!aioSkill.boost.isEmpty() && c.skills.shouldBoost(Skills.STRENGTH)) {
            useBoost();
        }
    }

    private void findAndAttackNpc() {
        taskNpc = getNewTaskNpc();
        attackNpc(taskNpc, aioSkill.slayerTask.getTaskName());
    }

    private void attackNpc(SimpleNpc npc, String taskName) {
        if (taskNpc != null) {
            aioSkill.status = "Attacking " + taskName;
            taskNpc.click("Attack");
            taskNpc.menuAction("Attack");
            c.onCondition(() -> p.getInteracting().equals(taskNpc.getActor()), 600, 10);
            return;
        }
        taskNpc = getNewTaskNpc();
    }

    private boolean havePrayer() {
        return !c.inventory.populate().filterContains("prayer").isEmpty();
    }

    private boolean haveFood() {
        return !c.inventory.populate().filterContains(aioSkill.foodString).isEmpty();
    }

    private void drinkPrayer() {
        prayerPotion = c.inventory.populate().filterContains("Prayer Potion").next();
        if (prayerPotion != null) {
            prayerPotion.menuAction("Drink");
        }
    }

    private void useBoost() {
        boost = c.inventory.populate().filterContains(aioSkill.boost).next();
        if (boost != null) {
            boost.menuAction("Drink");
        }
    }

    private void eatFood() {
        prayerPotion = c.inventory.populate().filterContains(aioSkill.foodString).next();
        if (prayerPotion != null) {
            prayerPotion.menuAction("Eat");
        }
    }

    private SimpleNpc getNewTaskNpc() {
        String superiorNpcName = aioSkill.slayerTask.getSuperiorTaskNpcName();
        SimpleNpc treasureGoblin = c.npcs.hintArrowNpc();
        if (treasureGoblin != null) {
            return treasureGoblin;
        }
        if (aioSkill.superiorUp && superiorNpcName != null) {
            SimpleNpc superiorTaskNpc = c.npcs.populate()
                    .filterContains(superiorNpcName)
                    .nextNearest();

            if (superiorTaskNpc != null) {
                aioSkill.superiorUp = false;
                return superiorTaskNpc;
            }
        }
        SimpleNpc taskNpc = c.npcs.populate()
                .filterContains(aioSkill.slayerTask.getTaskName())
                .filterHasAction("Attack")
                .filter(npc -> npc != null && npc.getInteracting() != null
                        && npc.getInteracting().equals(c.players.getLocal().getActor()))
                .nextNearest();
        if (taskNpc == null) {
            taskNpc = c.npcs.populate()
                    .filterContains(aioSkill.slayerTask.getTaskName())
                    .filterHasAction("Attack")
                    .filter(npc -> npc != null && !npc.isDead()
                            && !npc.inCombat() && npc.getInteracting() == null)
                    .nextNearest();
        }
        return taskNpc;
    }

    @Override
    public String DebugTaskDescription() {
        return "Combat";
    }
}
