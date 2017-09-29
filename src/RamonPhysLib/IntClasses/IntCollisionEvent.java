/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

import RamonPhysLib.IntClasses.IntEntity;
import RamonPhysLib.math.IntVector;

public class IntCollisionEvent {
    public final IntEntity entA;
    public final IntEntity entB;
    public final IntVector entAVel;
    public final IntVector entBVel;
    public final float angle;

    public IntCollisionEvent(IntEntity entA, IntEntity entB, float angle) {
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

