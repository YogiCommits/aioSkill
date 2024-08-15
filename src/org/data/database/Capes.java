package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Capes {

    private static ClientContext c = ClientContext.instance();

    public enum Melee {
        FIRE_CAPE(6570);

        public int id;
        Melee(int ID) {id = ID;}

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(id);
        }
    }
}
