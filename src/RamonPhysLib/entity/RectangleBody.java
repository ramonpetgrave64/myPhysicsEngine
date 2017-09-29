/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.entity;

import RamonPhysLib.math.Coordinate;

public class RectangleBody
extends Body {
    double width;
    double height;

    public RectangleBody(double width, double height) {
        super(null);
        Coordinate[] verts = new Coordinate[]{new Coordinate(-1.0 * width / 2.0, -1.0 * height / 2.0), new Coordinate(-1.0 * width / 2.0, height / 2.0), new Coordinate(width / 2.0, height / 2.0), new Coordinate(width / 2.0, -1.0 * height / 2.0)};
        this.setVertices(verts);
    }

    Coordinate calculateCenter() {
        return new Coordinate(0.0, 0.0);
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
}

