/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

import RamonPhysLib.math.IntVector;

public abstract class IntEntity {
    private String name;
    private IntCoordinate loc;
    private IntVector vel;
    private IntVector acc;
    private IntBody body;
    private double angle;
    private double angularVelocity;

    public IntEntity(IntCoordinate location, IntBody body) {
        this.loc = location;
        this.body = body;
        this.body.moveTo(location);
        this.vel = new IntVector();
        this.acc = new IntVector();
    }

    public IntCoordinate getLoc() {
        return this.loc;
    }

    public IntVector getVel() {
        return this.vel;
    }

    public double getOrientation() {
        return this.angle;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntVector getAcc() {
        return this.acc;
    }

    public abstract double radiusAt(double var1);

    public void setLoc(IntCoordinate c) {
        this.loc = c;
        this.body.moveTo(this.loc);
    }

    public void updateBody() {
        this.body.moveTo(this.loc);
    }

    public void setVel(IntVector v) {
        this.vel = v;
    }

    public void setOrientation(double orientation) {
        this.angle = orientation;
    }

    public void setAcc(IntVector acceleration) {
        this.acc = acceleration;
    }

    public String toString() {
        return this.name + " " + this.loc + "" + this.vel;
    }
}

