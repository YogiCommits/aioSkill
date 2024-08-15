package org.data.database;

import simple.robot.api.ClientContext;

public class Bank {

    private static ClientContext c = ClientContext.instance();

    public static void openBank() {

        int[] bankIds = new int[] { 29321 };

        try {
            c.objects.populate().filter(bankIds).nextNearest().menuAction("Banking");
        } catch (Exception e) {
            System.out.println("Failed to open bank!");
        }

    }

    public static void closeBank() {
        c.menuActions.sendAction(57, 11, 786434, 1, "Close", "");
    }

    public static void depositInventory() {
        c.menuActions.sendAction(57, -1, 786474, 1, "Deposit inventory", "");
    }

    public static void depositEquipment() {
        c.menuActions.sendAction(57, -1, 786476, 1, "Deposit worn items", "");
    }

    public static void toggleNote() {
        c.menuActions.sendAction(57, -1, 786456, 1, "Note", "");
    }

    public static void toggleItem() {
        c.menuActions.sendAction(57, -1, 786454, 1, "Item", "");
    }

    public static void openTab(int id) {
        switch (id) {
            case 0:
                c.menuActions.sendAction(57, 10, 786443, 1, "View all items", "");
                break;

            case 1:
                c.menuActions.sendAction(57, 11, 786443, 1, "View tab", "<col=ff9040>1</col>");
                break;

            case 2:
                c.menuActions.sendAction(57, 13, 786443, 1, "View tab", "<col=ff9040>2</col>");
                break;

            case 3:
                c.menuActions.sendAction(57, 13, 786443, 1, "View tab", "<col=ff9040>3</col>");
                break;

            case 4:
                c.menuActions.sendAction(57, 14, 786443, 1, "View tab", "<col=ff9040>4</col>");
                break;

            case 5:
                c.menuActions.sendAction(57, 15, 786443, 1, "View tab", "<col=ff9040>5</col>");
                break;

            case 6:
                c.menuActions.sendAction(57, 16, 786443, 1, "View tab", "<col=ff9040>6</col>");
                break;

            case 7:
                c.menuActions.sendAction(57, 17, 786443, 1, "View tab", "<col=ff9040>7</col>");
                break;

            case 8:
                c.menuActions.sendAction(57, 18, 786443, 1, "View tab", "<col=ff9040>8</col>");
                break;

            case 9:
                c.menuActions.sendAction(57, 19, 786443, 1, "View tab", "<col=ff9040>9</col>");
                break;
        }
    }

    @SuppressWarnings("incomplete-switch")
    public static void depositItem(String name, Quantity quantity) {
        try {
            switch (quantity) {
                case ALL:
                    c.inventory.populate().filter(name).next().menuAction("Deposit-All");
                    break;

                case ONE:
                    c.inventory.populate().filter(name).next().menuAction("Deposit-1");
                    break;

                case FIVE:
                    c.inventory.populate().filter(name).next().menuAction("Deposit-5");
                    break;

                case TEN:
                    c.inventory.populate().filter(name).next().menuAction("Deposit-10");
                    break;

                case X:
                    c.inventory.populate().filter(name).next().menuAction("Deposit-X");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Failed to deposit item {" + name + "( + " + quantity.toString() + ")}!");
        }
    }

    public static void withdrawItem(String name, Quantity quantity) {
        try {
            switch (quantity) {
                case ALL:
                    c.inventory.populate().filter(name).next().menuAction("Withdraw-All");
                    break;

                case ONE:
                    c.inventory.populate().filter(name).next().menuAction("Withdraw-1");
                    break;

                case FIVE:
                    c.inventory.populate().filter(name).next().menuAction("Withdraw-5");
                    break;

                case TEN:
                    c.inventory.populate().filter(name).next().menuAction("Withdraw-10");
                    break;

                case X:
                    c.inventory.populate().filter(name).next().menuAction("Withdraw-X");
                    break;

                case ALL_BUT_ONE:
                    c.inventory.populate().filter(name).next().menuAction("Withdraw-All-but-1");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Failed to withdraw item {" + name + "( + " + quantity.toString() + ")}!");
        }
    }

    public static void withdrawItem(int id, Quantity quantity) {
        try {
            switch (quantity) {
                case ALL:
                    c.inventory.populate().filter(id).next().menuAction("Withdraw-All");
                    break;

                case ONE:
                    c.inventory.populate().filter(id).next().menuAction("Withdraw-1");
                    break;

                case FIVE:
                    c.inventory.populate().filter(id).next().menuAction("Withdraw-5");
                    break;

                case TEN:
                    c.inventory.populate().filter(id).next().menuAction("Withdraw-10");
                    break;

                case X:
                    c.inventory.populate().filter(id).next().menuAction("Withdraw-X");
                    break;

                case ALL_BUT_ONE:
                    c.inventory.populate().filter(id).next().menuAction("Withdraw-All-but-1");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Failed to withdraw item {" + id + "( + " + quantity.toString() + ")}!");
        }
    }

    public enum Quantity {
        ALL(),
        ONE(),
        FIVE(),
        TEN(),
        X(),
        ALL_BUT_ONE()
    }

}
