package org.data.database;

import simple.hooks.filters.SimpleEquipment;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Outfits {
    private static ClientContext c = ClientContext.instance();

    public enum Gloves {
        GOLDSMITHING(776),
        ICE(1580);

        int id;
        Gloves(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }
        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(id);
        }
        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(id);
        }
    }

    public enum Angler {
        ANGLER_HAT(13258),
        ANGLER_TOP(13259),
        ANGLER_WADERS(13260),
        ANGLER_BOOTS(13261);

        private final int id;

        Angler(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Lumberjack {
        LUMBERJACK_HAT(10941),
        LUMBERJACK_TOP(10939),
        LUMBERJACK_LEGS(10940),
        LUMBERJACK_BOOTS(10933);

        private final int id;

        Lumberjack(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Prospector {
        PROSPECTOR_HELMET(12013),
        PROSPECTOR_JACKET(12014),
        PROSPECTOR_LEGS(12015),
        PROSPECTOR_BOOTS(12016);

        private final int id;

        Prospector(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Farmers {
        FARMERS_STRAWHAT(13640),
        FARMERS_JACKET(13642),
        FARMERS_BOROUGH(13644),
        FARMERS_BOOTS(13646);

        private final int id;

        Farmers(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Rogue {
        ROGUE_MASK(5554),
        ROGUE_TOP(5553),
        ROGUE_TROUSERS(5555),
        ROGUE_GLOVES(5556),
        ROGUE_BOOTS(5557);

        private final int id;

        Rogue(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Pyromancer {
        PYROMANCER_HOOD(20704),
        PYROMANCER_GARB(20706),
        PYROMANCER_ROBE(20708),
        PYROMANCER_BOOTS(20710);

        private final int id;

        Pyromancer(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Graceful {
        GRACEFUL_HOOD(11850),
        GRACEFUL_TOP(11852),
        GRACEFUL_LEGS(11854),
        GRACEFUL_GLOVES(11856),
        GRACEFUL_BOOTS(11858),
        GRACEFUL_CAPE(11860);

        private final int id;

        Graceful(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum CraftingGuildMaster {
        CRAFTING_GUILD_HAT(12439),
        CRAFTING_GUILD_TOP(12441),
        CRAFTING_GUILD_LEGS(12443),
        CRAFTING_GUILD_BOOTS(12445);

        private final int id;

        CraftingGuildMaster(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Construction {
        CONSTRUCTOR_HAT(19911),
        CONSTRUCTOR_TOP(19913),
        CONSTRUCTOR_LEGS(19915),
        CONSTRUCTOR_BOOTS(19917);

        private final int id;

        Construction(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

}
