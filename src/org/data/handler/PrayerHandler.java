package org.data.handler;

import org.data.database.Prayer;
import simple.robot.api.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;

public class PrayerHandler {

    ClientContext c = ClientContext.instance();
    ArrayList<Prayer> activePrayers = new ArrayList<>();
    int state = 0;


    public PrayerHandler(Prayer... initalPrayers) {
        for (Prayer p : initalPrayers) {
            activePrayers.add(p);
        }
    }

    public void replace(Prayer Old, Prayer New) {
        System.out.println("<PrayerHandler> Replacing " + Old.name() + " with " + New.name());
        while (state != 0) {
            c.sleep(10);
        }
        activePrayers.remove(Old);
        if (!activePrayers.contains(New)) {
            activePrayers.add(New);
        }
    }

    public void add(Prayer... prayers) {
        System.out.println("<PrayerHandler> Adding " + prayers.length + " Prayers");
        activePrayers.addAll(Arrays.asList(prayers));
        for (Prayer p : prayers) {
            System.out.println("<PrayerHandler> Enabling " + p.name());
            p.enableP();
        }
    }

    public void remove(Prayer... prayers) {
        for (Prayer p : prayers) {
            System.out.println("<PrayerHandler> Removing " + p.name());
            p.disableP();
        }
        activePrayers.removeAll(Arrays.asList(prayers));
    }

    public void removeAll() {
        System.out.println("<PrayerHandler> Removing All Prayers");
        for (Prayer p : activePrayers) {
            p.disableP();
        }
        activePrayers.removeAll(activePrayers);
    }

    public void onTickFlick() {
        if (activePrayers == null || activePrayers.isEmpty()) { return; }
        for (Prayer p : activePrayers) {
            p.disableP();
        }

        for (Prayer p : activePrayers) {
            p.enableP();
        }
    }

    public int getCount() {
        return activePrayers.size();
    }

    public boolean contains(Prayer... prayers) {
        int counter = 0;
        for (Prayer p : prayers) {
            System.out.print(p.toString());
            if (activePrayers.contains(p)) {
                counter++;
                System.out.println(":CHECK");
            }
        }
        return counter == prayers.length;
    }
}
