package org.data.handler;

import net.runelite.api.coords.WorldPoint;
import org.data.database.Items;
import org.data.database.Objects;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class CannonHandler {

    private ClientContext c = ClientContext.instance();
    private WorldPoint loc;

    private SimpleObject cannon;
    @SuppressWarnings("unused")
    private SimpleItem cannonBase;
    public CState CANNON_STATUS = null;

    @SuppressWarnings("unused")
    private WorldPoint location;

    private int remainingAmmo = 0;
    private boolean autoReload = false;

    public void setup() {
        loc = c.players.getLocal().getLocation();
        Items.Cannon.BASE.clickP("Set-up");
        c.sleepCondition(() -> remainingAmmo > 20, 6500);
        if (!Objects.Cannon.FULL.getQuery().isEmpty()
                && Objects.Cannon.FULL.getQuery().nextNearest().getLocation().distanceTo(loc) < 5) {
            cannon = Objects.Cannon.FULL.getQuery().nextNearest();
        }
    }

    public int getRemainingAmmo() {
        return remainingAmmo = c.varpbits.varpbit(3);
    }

    public void update() {
        loc = c.players.getLocal().getLocation();

        if (getRemainingAmmo() < 10 && autoReload) {
            System.out.println(
                    "\t<CannonHandler> Auto Reload Enabled : Remaining: " + getRemainingAmmo() + " : Reloading Cannon");
            reload();
        }

        if (getRemainingAmmo() == 0 && cannon != null && autoReload) {
            System.out.println(
                    "\t<CannonHandler> Auto Reload Enabled : Remaining: " + getRemainingAmmo() + " : Reloading Cannon");
            reload();
        }
    }

    public void reload() {
        if (cannon == null) {
            try {
                cannon = Objects.Cannon.FULL.getQuery().nextNearest();
                System.out.println("The Cannon was null... But we found he cannon!");
            } catch (Exception e) {
                System.out.println("We can't find the cannon object :/");
                return;
            }
        }

        cannon.click(0);
        c.sleepCondition(() -> getRemainingAmmo() > 20, 2500);
    }

    public void pickup() {
        if (cannon == null) {
            try {
                cannon = Objects.Cannon.FULL.getQuery().nextNearest();
                System.out.println("The Cannon was null... But we found he cannon!");
            } catch (Exception e) {
                System.out.println("We can't find the cannon object :/");
                return;
            }
        }

        cannon.menuAction("Pick-up");
        c.sleepCondition(() -> !Items.Cannon.BASE.getInvQuery().isEmpty(), 2500);
    }

    public void setAutoReload(boolean set) {
        autoReload = set;
    }

    public boolean getAutoReload() {
        return autoReload;
    }

    public enum CState {
        IN_INVENTORY,
        STOOD;
    }

}
