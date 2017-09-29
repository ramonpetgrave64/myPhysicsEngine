/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.engine;

import RamonPhysLib.entity.Entity;
import RamonPhysLib.math.Vector;

import java.util.ArrayList;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Engine {
    long timeNow;
    long timeBefore;
    long timeDiff;
    int scale = 1000;
    ArrayList<CollisionEvent> eventList;
    ArrayList<Entity> entityList;
    ArrayList<Entity> moveList;

    public Engine(ArrayList<Entity> entities) {
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
        for (Entity ent : this.entityList) {
            total += ent.getVel().radius();
        }
        System.out.println("total velocity: " + total);
    }

    private void processWallCollisions() {
        int northBound = 0;
        int westBound = 0;
        int southBound = 600;
        int eastBound = 600;
        for (Entity ent : this.entityList) {
            double radius;
            double y;
            if (ent.getLoc().getY() - ent.radiusAt(1.5707963267948966) < (double)northBound) {
                double radius2 = ent.radiusAt(1.5707963267948966);
                double y2 = ent.getLoc().getY();
                ent.getLoc().setY((double)northBound + radius2);
                ent.getVel().setY(ent.getVel().getY() * -1.0);
            }
            if (ent.getLoc().getY() + ent.radiusAt(4.71238898038469) > (double)southBound) {
                y = ent.getLoc().getY();
                radius = ent.radiusAt(4.71238898038469);
                ent.getLoc().setY((double)southBound - radius);
                ent.getVel().setY(ent.getVel().getY() * -1.0);
            }
            if (ent.getLoc().getX() - ent.radiusAt(3.141592653589793) < (double)westBound) {
                y = ent.getLoc().getY();
                radius = ent.radiusAt(3.141592653589793);
                ent.getLoc().setX((double)westBound + radius);
                ent.getVel().setX(ent.getVel().getX() * -1.0);
            }
            if (ent.getLoc().getX() + ent.radiusAt(0.0) <= (double)eastBound) continue;
            y = ent.getLoc().getY();
            radius = ent.radiusAt(0.0);
            ent.getLoc().setX((double)eastBound - radius);
            ent.getVel().setX(ent.getVel().getX() * -1.0);
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
        double t = (double)this.timeDiff / (double)this.scale;
        for (Entity ent : this.entityList) {
            Vector vel = ent.getVel();
            Vector acc = ent.getAcc();
            double dX = vel.getX() * t + acc.getX() * t * t / 2.0;
            double dY = vel.getY() * t + acc.getY() * t * t / 2.0;
            ent.getLoc().translate(dX, dY);
            ent.updateBody();
            double vX = vel.getX() + acc.getX() * t;
            double vY = vel.getY() + acc.getY() * t;
            vel.set(vX, vY);
        }
    }

    private void processCollisions() {
        for (CollisionEvent event : this.eventList) {
            Entity A = event.entA;
            Entity B = event.entB;
            double angle = event.angle;
            Vector AVel = event.entAVel;
            Vector BVel = event.entBVel;
            double buffer = (A.radiusAt(angle) + B.radiusAt(angle - 3.141592653589793) - A.getLoc().distanceTo(B.getLoc())) / 2.0;
            Vector ADisp = new Vector();
            ADisp.setPolar(buffer, angle - 3.141592653589793);
            Vector BDisp = new Vector();
            BDisp.setPolar(buffer, angle);
            A.getLoc().translate(ADisp.getValues());
            B.getLoc().translate(BDisp.getValues());
            A.updateBody();
            B.updateBody();
            double Amass = 1.0;
            double Bmass = 1.0;
            Vector ACollVel = AVel.projectOn(angle);
            Vector BCollVel = BVel.projectOn(angle);
            Vector ADeflVel = AVel.projectOn(angle + 1.5707963267948966);
            Vector BDeflVel = BVel.projectOn(angle + 1.5707963267948966);
            A.setVel(Vector.resultantOf(ADeflVel, BCollVel));
            B.setVel(Vector.resultantOf(BDeflVel, ACollVel));
        }
        this.eventList.clear();
    }

    private void detectCollisions() {
        for (int i = 0; i < this.entityList.size() - 1; ++i) {
            Entity A = this.entityList.get(i);
            for (int j = i + 1; j < this.entityList.size(); ++j) {
                Entity B = this.entityList.get(j);
                double dX = B.getLoc().getX() - A.getLoc().getX();
                double dY = B.getLoc().getY() - A.getLoc().getY();
                double angle = Math.atan2(dY, dX);
                double radA = A.radiusAt(angle);
                double radB = B.radiusAt(angle - 180.0);
                double disp = A.getLoc().distanceTo(B.getLoc());
                if (disp >= radA + radB) continue;
                CollisionEvent newColl = new CollisionEvent(A, B, angle);
                this.eventList.add(newColl);
            }
        }
    }

    public void printEntity(Entity e) {
        String p = e.getLoc().toString();
        String v = e.getVel().toString();
        String a = e.getAcc().toString();
        System.out.println("Position:  " + p + "  Velocity:  " + v + "  Acceleration:  " + a);
    }
}

