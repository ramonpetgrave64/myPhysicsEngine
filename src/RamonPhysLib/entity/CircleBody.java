/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.entity;

import RamonPhysLib.math.Coordinate;

public class CircleBody
extends Body {
    private double radius;

    public CircleBody(double radius) {
        super(new Coordinate[0]);
        this.radius = radius;
    }

    public Coordinate calculateCenter() {
        return new Coordinate();
    }
}

