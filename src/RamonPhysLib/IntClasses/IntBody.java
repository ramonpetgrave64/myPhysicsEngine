/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

import RamonPhysLib.IntClasses.IntCoordinate;

public abstract class IntBody {
    IntCoordinate[] vertices;
    IntCoordinate center;

    public IntBody(IntCoordinate[] vertices) {
        this.vertices = vertices;
        this.setCenter(this.calculateCenter());
    }

    public IntCoordinate[] getVertices() {
        return this.vertices;
    }

    public void setVertices(IntCoordinate[] vertices) {
        this.vertices = vertices;
    }

    abstract IntCoordinate calculateCenter();

    private void setCenter(IntCoordinate center) {
        this.center = center;
    }

    public IntCoordinate getCenter() {
        return this.center;
    }

    public void moveTo(IntCoordinate newLocation) {
        int dispX = newLocation.getX() - this.center.getX();
        int dispY = newLocation.getY() - this.center.getY();
        this.center.set(newLocation.getX(), newLocation.getY());
        for (IntCoordinate vert : this.vertices) {
            vert.translate(dispX, dispY);
        }
    }
}

