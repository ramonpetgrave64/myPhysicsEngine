/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.math;

public class Coordinate {
    private double[] dims;

    public Coordinate(double[] valuesArray) {
        this.dims = valuesArray;
    }

    public Coordinate(int indecies) {
        this.dims = new double[indecies];
    }

    public Coordinate(double x, double y, double z) {
        double[] newValues = new double[]{x, y, z};
        this.dims = newValues;
    }

    public Coordinate(double x, double y) {
        this(x, y, 0.0);
    }

    public Coordinate(Coordinate coord) {
        this.dims = coord.getValues();
    }

    public Coordinate() {
        this(0.0, 0.0);
    }

    public double[] getValues() {
        return this.dims;
    }

    public double getX() {
        return this.dims[0];
    }

    public double getY() {
        return this.dims[1];
    }

    public double getZ() {
        return this.dims[2];
    }

    public void set(double[] newValues) {
        this.dims = newValues;
    }

    public void set(double x, double y, double z) {
        this.dims[0] = x;
        this.dims[1] = y;
        this.dims[2] = z;
    }

    public void set(double x, double y) {
        this.set(x, y, 0.0);
    }

    public void setIndex(int index, double value) {
        this.dims[index] = value;
    }

    public void setX(double value) {
        this.setIndex(0, value);
    }

    public void setY(double value) {
        this.setIndex(1, value);
    }

    public void setZ(double value) {
        this.setIndex(2, value);
    }

    public void translate(double[] otherValues) {
        for (int i = 0; i < this.dims.length; ++i) {
            double[] arrd = this.dims;
            int n = i;
            arrd[n] = arrd[n] + otherValues[i];
        }
    }

    public void translate(double x, double y, double z) {
        double[] arrd = this.dims;
        arrd[0] = arrd[0] + x;
        double[] arrd2 = this.dims;
        arrd2[1] = arrd2[1] + y;
        double[] arrd3 = this.dims;
        arrd3[2] = arrd3[2] + z;
    }

    public void translate(double x, double y) {
        this.translate(x, y, 0.0);
    }

    public void translateDimIndex(int index, double value) {
        this.dims[index] = value;
    }

    public void dilate(double dilationFactor) {
        for (double val : this.dims) {
            val *= dilationFactor;
        }
    }

    public void reflectOnXAxis() {
        double[] arrd = this.dims;
        arrd[1] = arrd[1] * -1.0;
    }

    public void reflectOnYAxis() {
        double[] arrd = this.dims;
        arrd[0] = arrd[0] * -1.0;
    }

    public void reflectOnZAxis() {
        double[] arrd = this.dims;
        arrd[2] = arrd[2] * -1.0;
    }

    public void reflectOnOrigin() {
        this.reflectOnXAxis();
        this.reflectOnYAxis();
        this.reflectOnZAxis();
    }

    public int xyQuadrant() {
        double x = this.dims[0];
        double y = this.dims[1];
        if (x > 0.0 && y > 0.0) {
            return 1;
        }
        if (x < 0.0 && y > 0.0) {
            return 2;
        }
        if (x < 0.0 && y < 0.0) {
            return 3;
        }
        if (x > 0.0 && y < 0.0) {
            return 4;
        }
        return 0;
    }

    public double terminalAngle() {
        double x = this.dims[0];
        double y = this.dims[1];
        double angle = Math.atan2(y, x);
        return angle %= 6.283185307179586;
    }

    public double referenceAngle() {
        int quad = this.xyQuadrant();
        double angle = this.terminalAngle();
        double PI = 3.141592653589793;
        if (quad == 1) {
            return angle;
        }
        if (quad == 2) {
            return PI - angle;
        }
        if (quad == 3) {
            return angle - PI;
        }
        if (quad == 4) {
            return 2.0 * PI - angle;
        }
        return angle % (PI / 2.0);
    }

    public boolean equals(Coordinate coord) {
        double[] otherValues = coord.getValues();
        if (this.dims.length != otherValues.length) {
            return false;
        }
        for (int i = 0; i < this.dims.length; ++i) {
            if (this.dims[i] == otherValues[i]) continue;
            return false;
        }
        return true;
    }

    public double distanceTo(double[] otherValues) {
        double total = 0.0;
        for (int i = 0; i < this.dims.length; ++i) {
            double diff = this.dims[i] - otherValues[i];
            total += Math.pow(diff, 2.0);
        }
        return Math.sqrt(total);
    }

    public double distanceTo(Coordinate coord) {
        return this.distanceTo(coord.getValues());
    }

    public double xyzDistanceTo(double[] otherValues) {
        double dX = this.dims[0] - otherValues[0];
        double dY = this.dims[1] - otherValues[1];
        double dZ = this.dims[2] - otherValues[2];
        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    public double xyDistanceTo(double[] otherValues) {
        double dX = this.dims[0] - otherValues[0];
        double dY = this.dims[1] - otherValues[1];
        return Math.sqrt(dX * dX + dY * dY);
    }

    public double distanceToOrigin() {
        double[] origin = new double[this.dims.length];
        return this.distanceTo(origin);
    }

    public double arcLength() {
        return this.distanceToOrigin() * this.terminalAngle();
    }

    public String toString() {
        return "(" + this.dims[0] + ", " + this.dims[1] + ")";
    }
}

