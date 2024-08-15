package org.scripter;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;

public abstract class InterruptableTask extends Task {
    public ClientContext c = ClientContext.instance();
    public SimplePlayer p;
    public WorldPoint loc;
    public int anim;
    public int graph;
    public Task previousTask;

    public abstract boolean interruptCondition();
    public abstract void interruptAction();

    @Override
    public abstract void run();

    public void runtime() {
        p = c.players.getLocal();
        loc = p.getLocation();
        anim = p.getAnimation();
        graph = p.getGraphic();
        if (interruptCondition()) {
            interruptAction();
        }
        run();
    }

    @Override
    public abstract String DebugTaskDescription();

    public void setPreviousTask(Task t) {
        previousTask = t;
    }

    public Task getPreviousTask() {
        return previousTask;
    }
}
