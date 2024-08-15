package org.data.path;

import net.runelite.api.coords.WorldPoint;

public class Node implements Comparable<Node> {
    WorldPoint point;
    Node parent;
    double g; // Cost from start
    double f; // Total cost

    Node(WorldPoint point, Node parent, double g, double f) {
        this.point = point;
        this.parent = parent;
        this.g = g;
        this.f = f;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.f, o.f);
    }
}
