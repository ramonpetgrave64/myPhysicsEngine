/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.entity;

import RamonPhysLib.math.Coordinate;

public abstract class Body {
    Coordinate[] vertices;
    Coordinate center;

    public Body(Coordinate[] vertices) {
        this.vertices = vertices;
        this.setCenter(this.calculateCenter());
    }

    public Coordinate[] getVertices() {
        return this.vertices;
    }

    public void setVertices(Coordinate[] vertices) {
        this.vertices = vertices;
    }

    abstract Coordinate calculateCenter();

    private void setCenter(Coordinate center) {
        this.center = center;
    }

    public Coordinate getCenter() {
        return this.center;
    }

    public void moveTo(Coordinate newLocation) {
        double dispX = newLocation.getX() - this.center.getX();
        double dispY = newLocation.getY() - this.center.getY();
        this.center.set(newLocation.getX(), newLocation.getY());
        for (Coordinate vert : this.vertices) {
            vert.translate(dispX, dispY);
        }
    }
}

