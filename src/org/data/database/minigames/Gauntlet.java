package org.data.database.minigames;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Gauntlet {

    public enum Areas {
        LOBBY(new WorldArea(new WorldPoint(3026, 6117, 1), new WorldPoint(3039, 6130, 1)));

        WorldArea area;
        Areas(WorldArea Area) {
            area = Area;
        }

        public WorldArea getArea() {
            return area;
        }
    }

    public static class Objects {
        public enum Lobby {
           ENTRANCE(37340),
            REWARDS(37341);

           int id;
           Lobby(int ID) {
               id = ID;
           }

           public int getID() {
               return id;
           }
        }
    }

    public enum Widgets {

        GAUNTLET_MODE(219, 1);

        public int main, sub;
        Widgets(int Main, int Sub) {
            main = Main;
            sub = Sub;
        }

        public int[] getID() {
            return new int[] {main, sub};
        }
        public SimpleWidget getWidget() {
            try {
                return ClientContext.instance().widgets.getWidget(main, sub);
            } catch (NullPointerException e) {
                return null;
            }
        }
    }
}
