package org.data.database;

public enum Animations {
    FISHING(621),
    FLETCHING(1248);

    private final int animationId;

    Animations(int animationId) {
        this.animationId = animationId;
    }

    public int getAnimationId() {
        return animationId;
    }
}

