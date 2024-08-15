package org.data;

import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;
import org.data.database.Widgets;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.simplebot.Keyboard;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.lang.reflect.Method;

public class Methods {

    /*
     * Author: unix
     * Discord: x_unix
     */

    private static ClientContext c = ClientContext.instance();
    private static ClientThread clientThread;

    public static void teleport(String options) {
        c = ClientContext.instance();
        if (options.equals("home")) {
            c.magic.castHomeTeleport();
            c.sleep(1000);
            return;
        }
        if (Widgets.x.TELEPORT_MAIN.get() == null) {
            Widgets.x.TELEPORT_BUTTON.get().click(0);
            c.sleepCondition(
                    () -> (Widgets.x.TELEPORT_MAIN.get() != null && Widgets.x.TELEPORT_MAIN.get().visibleOnScreen()),
                    1000);
        }

        if (Widgets.x.TELEPORT_MAIN.get().getText().equals("Teleport Panel")) {
            char[] l = options.toCharArray();
            for (int i = 0; i < l.length; i++) {
                c.keyboard.clickKey(l[i]);
                c.sleep(250);
            }
        }

    }

    public static void equipGear(int[] itemList) {
        for (int i : itemList) {
            c.inventory.populate().filter(i).next().menuAction("Wear");
            c.sleep(50);
        }
    }

    public static void equipWeapon(int item) {
        c.inventory.populate().filter(item).next().menuAction("Wield");
        c.sleep(50);
    }

    /*
     * Usage:
     * type:
     * 0 = Objects
     * 1 = NPCs
     * 2 = Items
     * 3 = GroundItems
     * 
     * id:
     * The ID of the entity your interacting with
     * 
     * interaction:
     * The text of the interaction, i.e. "Pick up", "Steal", "Talk-to"
     * - this is CaSE-sENsItIvE!
     */

    public static void walk(WorldPoint tile) {
        ClientContext c = ClientContext.instance();
        c.menuActions.step(tile);

    }

    public static String formatTimeRunning(long start_time) {
        long running = System.currentTimeMillis() - start_time;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    public static String formatGained(int start_xp, int current_xp) {
        int gained = current_xp - start_xp;
        String output = "";
        if (gained > 1000 && gained < 10000) {
            output = String.valueOf(gained).substring(0, 1) + "K";
        } else if (gained > 9999 && gained < 100000) {
            output = String.valueOf(gained).substring(0, 2) + "K";
        } else if (gained > 99999 && gained < 1000000) {
            output = String.valueOf(gained).substring(0, 3) + "K";
        } else if (gained > 999999 && gained < 10000000) {
            output = String.valueOf(gained).substring(0, 1) + "M";
        } else if (gained > 9999999) {
            output = String.valueOf(gained).substring(0, 2) + "M";
        } else {
            output = String.valueOf(gained);
        }

        return output;
    }

    public static boolean standingOnTile(WorldPoint[] tiles) {
        for (WorldPoint t : tiles) {
            if (c.players.getLocal().getLocation().equals(t)) {
                return true;
            }
        }
        return false;
    }

}
