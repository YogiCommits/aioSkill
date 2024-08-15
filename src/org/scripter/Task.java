package org.scripter;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;

public abstract class Task {

    public ClientContext c = ClientContext.instance();
    public SimplePlayer p;
    public WorldPoint loc;
    public int anim;
    public int graph;

    public abstract void run();

    public void runtime() {
        p = c.players.getLocal();
        loc = p.getLocation();
        anim = p.getAnimation();
        graph = p.getGraphic();
        run();
    }

    public abstract String DebugTaskDescription();

}
