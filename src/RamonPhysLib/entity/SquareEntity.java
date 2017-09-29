/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.entity;

import RamonPhysLib.math.Coordinate;

public class SquareEntity
extends Entity {
    private double sideLength;

    public SquareEntity(Coordinate location, double sideLength) {
        super(location, new RectangleBody(sideLength, sideLength));
        this.sideLength = sideLength;
    }

    public double radiusAt(double angle) {
        return this.sideLength / 2.0 / Math.sin(angle);
    }
}

