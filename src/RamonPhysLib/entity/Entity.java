/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.entity;

import RamonPhysLib.math.Coordinate;
import RamonPhysLib.math.Vector;

public abstract class Entity {
    private String name;
    private Coordinate loc;
    private Vector vel;
    private Vector acc;
    private Body body;
    private double angle;
    private double angularVelocity;

    public Entity(Coordinate location, Body body) {
        this.loc = location;
        this.body = body;
        this.body.moveTo(location);
        this.vel = new Vector();
        this.acc = new Vector();
    }

    public Coordinate getLoc() {
        return this.loc;
    }

    public Vector getVel() {
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

    public Vector getAcc() {
        return this.acc;
    }

    public abstract double radiusAt(double var1);

    public void setLoc(Coordinate c) {
        this.loc = c;
        this.body.moveTo(this.loc);
    }

    public void updateBody() {
        this.body.moveTo(this.loc);
    }

    public void setVel(Vector v) {
        this.vel = v;
    }

    public void setOrientation(double orientation) {
        this.angle = orientation;
    }

    public void setAcc(Vector acceleration) {
        this.acc = acceleration;
    }

    public String toString() {
        return this.name + " " + this.loc + " " + this.vel;
    }
}

