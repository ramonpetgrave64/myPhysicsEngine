/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.math;

import RamonPhysLib.IntClasses.IntCoordinate;

public class IntVector
extends IntCoordinate {
    public IntVector(int x, int y, int z) {
        super(x, y, z);
    }

    public IntVector(int x, int y) {
        super(x, y);
    }

    public IntVector(IntVector vec) {
        super(vec.getValues());
    }

    public IntVector() {
    }

    public IntVector projectOn(IntVector vec) {
        return this.projectOn(vec.angle());
    }

    public IntVector projectOn(float angle) {
        float angleDiff = this.angle() - angle;
        float projectionRadius = (float)Math.cos(angleDiff) * this.radius();
        IntVector projection = new IntVector(0, 0, 0);
        projection.setPolar(projectionRadius, angle);
        return projection;
    }

    public float radius() {
        return this.distanceToOrigin();
    }

    public float angle() {
        return this.terminalAngle();
    }

    public void setPolar(float radius, float angle) {
        this.set(Math.round(radius * (float)Math.cos(angle)), Math.round(radius * (float)Math.sin(angle)));
    }

    public void setRadius(float radius) {
        this.setPolar(radius, this.angle());
    }

    public void setAngle(float angle) {
        this.setPolar(this.radius(), angle);
    }

    public void rotate(float angle) {
        this.setAngle(this.angle() + angle);
    }

    public void add(IntVector vec) {
        this.translate(vec.getValues());
    }

    public void add(IntVector[] vectors) {
        for (IntVector vec : vectors) {
            this.add(vec);
        }
    }

    public void negate() {
        this.reflectOnOrigin();
    }

    public static IntVector resultantOf(IntVector vecA, IntVector vecB) {
        IntVector sum = new IntVector();
        sum.add(vecA);
        sum.add(vecB);
        return sum;
    }

    public static IntVector dilationOf(IntVector vec, float dilationFactor) {
        IntVector dil = new IntVector(vec);
        dil.dilate(dilationFactor);
        return dil;
    }

    public static IntVector negationOf(IntVector vec) {
        IntVector neg = new IntVector(vec);
        neg.negate();
        return neg;
    }
}

