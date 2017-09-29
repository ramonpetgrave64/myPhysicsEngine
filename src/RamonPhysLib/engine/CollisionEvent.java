/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.engine;

import RamonPhysLib.entity.Entity;
import RamonPhysLib.math.Vector;

public class CollisionEvent {
    public final Entity entA;
    public final Entity entB;
    public final Vector entAVel;
    public final Vector entBVel;
    public final double angle;

    public CollisionEvent(Entity entA, Entity entB, double angle) {
        this.entA = entA;
        this.entB = entB;
        this.entAVel = entA.getVel();
        this.entBVel = entB.getVel();
        this.angle = angle;
    }

    public String toString() {
        return "collision: " + this.entA.getName() + " " + this.entB.getName() + " " + this.angle;
    }
}

