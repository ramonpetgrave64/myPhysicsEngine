/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.math;

public class Vector
extends Coordinate {
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector(double x, double y) {
        super(x, y);
    }

    public Vector(Vector vec) {
        super(vec.getValues());
    }

    public Vector() {
    }

    public Vector projectOn(Vector vec) {
        return this.projectOn(vec.angle());
    }

    public Vector projectOn(double angle) {
        double angleDiff = this.angle() - angle;
        double projectionRadius = Math.cos(angleDiff) * this.radius();
        Vector projection = new Vector(0.0, 0.0, 0.0);
        projection.setPolar(projectionRadius, angle);
        return projection;
    }

    public double radius() {
        return this.distanceToOrigin();
    }

    public double angle() {
        return this.terminalAngle();
    }

    public void setPolar(double radius, double angle) {
        this.set(radius * Math.cos(angle), radius * Math.sin(angle));
    }

    public void setRadius(double radius) {
        this.setPolar(radius, this.angle());
    }

    public void setAngle(double angle) {
        this.setPolar(this.radius(), angle);
    }

    public void rotate(double angle) {
        this.setAngle(this.angle() + angle);
    }

    public void add(Vector vec) {
        this.translate(vec.getValues());
    }

    public void add(Vector[] vectors) {
        for (Vector vec : vectors) {
            this.add(vec);
        }
    }

    public void negate() {
        this.reflectOnOrigin();
    }

    public static Vector resultantOf(Vector vecA, Vector vecB) {
        Vector sum = new Vector();
        sum.add(vecA);
        sum.add(vecB);
        return sum;
    }

    public static Vector dilationOf(Vector vec, double dilationFactor) {
        Vector dil = new Vector(vec);
        dil.dilate(dilationFactor);
        return dil;
    }

    public static Vector negationOf(Vector vec) {
        Vector neg = new Vector(vec);
        neg.negate();
        return neg;
    }
}

