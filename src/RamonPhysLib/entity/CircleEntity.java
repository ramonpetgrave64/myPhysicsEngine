/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.entity;

import RamonPhysLib.math.Coordinate;

public class CircleEntity
extends Entity {
    private double radius;

    public CircleEntity(Coordinate location, double radius) {
        super(location, new CircleBody(radius));
        this.radius = radius;
    }

    public double radiusAt(double angle) {
        return this.radius;
    }

    public double getRadius() {
        return this.radius;
    }
}

