/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

import RamonPhysLib.entity.Entity;
import RamonPhysLib.math.IntVector;

import java.util.ArrayList;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IntEngine {
    float PI = 3.1415927f;
    long timeNow;
    long timeBefore;
    long timeDiff;
    int scale = 500;
    ArrayList<IntCollisionEvent> eventList;
    ArrayList<IntEntity> entityList;
    ArrayList<IntEntity> moveList;
    public static int collisionCount;

    public IntEngine(ArrayList<IntEntity> entities) {
        this.entityList = entities;
        this.eventList = new ArrayList();
        this.moveList = new ArrayList();
    }

    public void go() {
        this.processCollisions();
        this.processWallCollisions();
        this.moveEntities();
        this.detectCollisions();
        this.updateTime();
    }

    public void printTotalVel() {
        double total = 0.0;
        for (IntEntity ent : this.entityList) {
            total += (double)ent.getVel().radius();
        }
        System.out.println("total velocity: " + total);
    }

    private void processWallCollisions() {
        int northBound = 0;
        int westBound = 0;
        int southBound = 600;
        int eastBound = 600;
        for (IntEntity ent : this.entityList) {
            double y;
            if ((double)ent.getLoc().getY() - ent.radiusAt(1.5707963267948966) < (double)northBound) {
                double radius = ent.radiusAt(1.5707963267948966);
                int y2 = ent.getLoc().getY();
                ent.getLoc().setY((int)((double)northBound + radius));
                ent.getVel().setY(ent.getVel().getY() * -1);
            }
            if ((double)ent.getLoc().getY() + ent.radiusAt(4.71238898038469) > (double)southBound) {
                y = ent.getLoc().getY();
                double radius = ent.radiusAt(4.71238898038469);
                ent.getLoc().setY((int)((double)southBound - radius));
                ent.getVel().setY(ent.getVel().getY() * -1);
            }
            if ((double)ent.getLoc().getX() - ent.radiusAt(3.141592653589793) < (double)westBound) {
                y = ent.getLoc().getY();
                double radius = ent.radiusAt(3.141592653589793);
                ent.getLoc().setX((int)((double)westBound + radius));
                ent.getVel().setX(ent.getVel().getX() * -1);
            }
            if ((double)ent.getLoc().getX() + ent.radiusAt(0.0) <= (double)eastBound) continue;
            y = ent.getLoc().getY();
            double radius = ent.radiusAt(0.0);
            ent.getLoc().setX((int)((double)eastBound - radius));
            ent.getVel().setX(ent.getVel().getX() * -1);
        }
    }

    private void updateTime() {
        if (this.timeNow == 0) {
            this.timeNow = System.currentTimeMillis();
        }
        this.timeBefore = this.timeNow;
        this.timeNow = System.currentTimeMillis();
        this.timeDiff = this.timeNow - this.timeBefore;
    }

    private void moveEntities() {
        float t = (float)this.timeDiff / (float)this.scale;
        for (IntEntity ent : this.entityList) {
            IntVector vel = ent.getVel();
            IntVector acc = ent.getAcc();
            int dX = Math.round((float)vel.getX() * t + (float)acc.getX() * t * t / 2.0f);
            int dY = Math.round((float)vel.getY() * t + (float)acc.getY() * t * t / 2.0f);
            ent.getLoc().translate(dX, dY);
            ent.updateBody();
            int vX = Math.round((float)vel.getX() + (float)acc.getX() * t);
            int vY = Math.round((float)vel.getY() + (float)acc.getY() * t);
            vel.set(vX, vY);
        }
    }

    private void processCollisions() {
        for (IntCollisionEvent event : this.eventList) {
            IntEntity A = event.entA;
            IntEntity B = event.entB;
            float angle = event.angle;
            IntVector AVel = event.entAVel;
            IntVector BVel = event.entBVel;
            int buffer = (int)((A.radiusAt(angle) + B.radiusAt(angle - this.PI) - (double)A.getLoc().distanceTo(B.getLoc())) / 2.0) + 1;
            IntVector ADisp = new IntVector();
            ADisp.setPolar(buffer, angle - this.PI);
            IntVector BDisp = new IntVector();
            BDisp.setPolar(buffer, angle);
            A.getLoc().translate(ADisp.getValues());
            B.getLoc().translate(BDisp.getValues());
            A.updateBody();
            B.updateBody();
            int mA = 1;
            int mB = 1;
            IntVector ACollVel = AVel.projectOn(angle);
            IntVector BCollVel = BVel.projectOn(angle);
            IntVector ADeflVel = AVel.projectOn(angle + this.PI / 2.0f);
            IntVector BDeflVel = BVel.projectOn(angle + this.PI / 2.0f);
            int vAX = ACollVel.getX();
            int vAY = ACollVel.getY();
            int vBX = BCollVel.getX();
            int vBY = BCollVel.getY();
            int vAXPrime = Math.round((vAX * (mA - mB) + 2 * mB * vBX) / (mA + mB));
            int vAYPrime = Math.round((vAY * (mA - mB) + 2 * mB * vBY) / (mA + mB));
            int vBXPrime = Math.round((vBX * (mB - mA) + 2 * mA * vAX) / (mA + mB));
            int vBYPrime = Math.round((vBY * (mB - mA) + 2 * mA * vAY) / (mA + mB));
            IntVector vAPrime = new IntVector(vAXPrime, vAYPrime);
            IntVector vBPrime = new IntVector(vBXPrime, vBYPrime);
            A.setVel(IntVector.resultantOf(ADeflVel, vAPrime));
            B.setVel(IntVector.resultantOf(BDeflVel, vBPrime));
        }
        this.eventList.clear();
    }

    private void detectCollisions() {
        for (int i = 0; i < this.entityList.size() - 1; ++i) {
            IntEntity A = this.entityList.get(i);
            for (int j = i + 1; j < this.entityList.size(); ++j) {
                IntEntity B = this.entityList.get(j);
                int dX = B.getLoc().getX() - A.getLoc().getX();
                int dY = B.getLoc().getY() - A.getLoc().getY();
                float angle = (float)Math.atan2(dY, dX);
                if (angle < 0.0f) {
                    angle += 2.0f * this.PI;
                }
                double radA = A.radiusAt(angle);
                double radB = B.radiusAt(angle - 180.0f);
                double disp = A.getLoc().distanceTo(B.getLoc());
                if (disp >= radA + radB) continue;
                IntCollisionEvent newColl = new IntCollisionEvent(A, B, angle);
                this.eventList.add(newColl);
            }
        }
    }

    public boolean areOpps(int x, int y) {
        return x < 0 && y > 0 || x > 0 && y < 0;
    }

    public void printEntity(Entity e) {
        String p = e.getLoc().toString();
        String v = e.getVel().toString();
        String a = e.getAcc().toString();
        System.out.println("Position:  " + p + "  Velocity:  " + v + "  Acceleration:  " + a);
    }
}

