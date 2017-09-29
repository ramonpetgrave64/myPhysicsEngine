/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

public class IntCircleEntity
extends IntEntity {
    private double radius;

    public IntCircleEntity(IntCoordinate location, double radius) {
        super(location, new IntCircleBody(radius));
        this.radius = radius;
    }

    public double radiusAt(double angle) {
        return this.radius;
    }

    public double getRadius() {
        return this.radius;
    }
}

