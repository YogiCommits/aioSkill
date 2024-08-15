package org.data.handler;

import java.util.Map;
import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;

public class TeleporterHandler {
    private static final ClientContext ctx = ClientContext.instance();

    private static final Map<String, Integer> MODERN_LOCATIONS = Map.of(
            "Lumbridge Home Teleport", 14286855);

    private static final Map<String, Integer> SECTION_PARAMS = Map.of(
            "Training", 131399701,
            "Slayer", 131399705,
            "Bosses", 131399709,
            "Skilling", 131399713,
            "Minigames", 131399717,
            "Misc", 131399721);

    private static final int WIDGET_ID = 2005;
    private static final int CHILD_WIDGET_ID = 56;

    public static SimpleObject getPortalNexus() {
        return ctx.objects.populate().filter("Portal Nexus").nextNearest();
    }

    public static SimpleWidget getPortalNexusWidget() {
        return ctx.widgets.getWidget(WIDGET_ID, 0);
    }

    public static int findChildIndexByText(String text) {
        SimpleWidget parentWidget = ctx.widgets.getWidget(WIDGET_ID, CHILD_WIDGET_ID);
        if (parentWidget != null) {
            SimpleWidget[] children = parentWidget.getDynamicChildren();
            for (int i = 0; i < children.length; i++) {
                SimpleWidget child = children[i];
                if (child != null && text.equals(child.getName())) {
                    return i;
                }
            }
        } else {
            ctx.log("Parent widget is null");
        }
        return -1;
    }

    public static void teleport(String section, String location) {
        ctx.log("Section: " + section);
        ctx.log("Location: " + location);

        if ("Modern".equalsIgnoreCase(section)) {
            Integer locationParam = MODERN_LOCATIONS.get(location);
            if (locationParam == null) {
                throw new IllegalArgumentException("Invalid location: " + location);
            }
            ctx.menuActions.sendAction(57, -1, locationParam, 1, "Cast", "<col=00ff00>" + location + "</col>");
            ctx.prayers.disableAll();
            return;
        }

        SimpleObject portalNexus = getPortalNexus();
        if (portalNexus == null) {
            ctx.log("Portal Nexus not found");
            return;
        }

        portalNexus.menuAction("Teleport");
        if (!ctx.onCondition(() -> getPortalNexusWidget() != null, 600, 10)) {
            ctx.log("Failed to open Portal Nexus interface");
            return;
        }

        Integer sectionParam = SECTION_PARAMS.get(section);
        if (sectionParam == null) {
            throw new IllegalArgumentException("Invalid section: " + section);
        }

        ctx.menuActions.sendAction(57, -1, sectionParam, 1, "Select", "");
        ctx.sleep(500, 1400);

        int childIndex = findChildIndexByText("<col=ff981f>" + location);
        if (childIndex == -1) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
        ctx.menuActions.sendAction(57, childIndex, 131399736, 1, "Teleport-to", "<col=ff981f>" + location);
    }
}
