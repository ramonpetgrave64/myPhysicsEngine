/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

public class IntCircleBody
extends IntBody {
    private double radius;

    public IntCircleBody(double radius) {
        super(new IntCoordinate[0]);
        this.radius = radius;
    }

    public IntCoordinate calculateCenter() {
        return new IntCoordinate();
    }
}

