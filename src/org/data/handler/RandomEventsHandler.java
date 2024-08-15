package org.data.handler;

import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class RandomEventsHandler {
    private static ClientContext ctx = ClientContext.instance();

    private static String[] randomIDs = {
            "Rick Turpentine",
            "Sandwich lady",
            "Spirit of Seren"
    };

    public static boolean needsAction() {
        SimpleNpc random = ctx.npcs.populate().filter(randomIDs).filter(
                n -> n != null && n.getInteracting() != null
                        && n.getInteracting().equals(ctx.players.getLocal().getActor()))
                .next();

        if (random != null) {
            ctx.sleep(600, 1200);
            if (random.getName().contains("Spirit of Seren")) {
                random.menuAction("Claim");
            } else {
                random.menuAction("Dismiss");
            }
            return true;
        }
        return false;
    }

}