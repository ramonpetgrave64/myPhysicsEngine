/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.IntClasses;

import RamonPhysLib.math.Coordinate;

public class IntCoordinate { //for temporary compatibility
    private int[] dims;
    float PI = 3.1415927f;

    public IntCoordinate(int[] valuesArray) {
        this.dims = valuesArray;
    }

    public IntCoordinate(int indecies) {
        this.dims = new int[indecies];
    }

    public IntCoordinate(int x, int y, int z) {
        int[] newValues = new int[]{x, y, z};
        this.dims = newValues;
    }

    public IntCoordinate(int x, int y) {
        this(x, y, 0);
    }

    public IntCoordinate(IntCoordinate coord) {
        this.dims = coord.getValues();
    }

    public IntCoordinate() {
        this(0, 0);
    }

    public int[] getValues() {
        return this.dims;
    }

    public int getX() {
        return this.dims[0];
    }

    public int getY() {
        return this.dims[1];
    }

    public int getZ() {
        return this.dims[2];
    }

    public void set(int[] newValues) {
        this.dims = newValues;
    }

    public void set(int x, int y, int z) {
        this.dims[0] = x;
        this.dims[1] = y;
        this.dims[2] = z;
    }

    public void set(int x, int y) {
        this.set(x, y, 0);
    }

    public void setIndex(int index, int value) {
        this.dims[index] = value;
    }

    public void setX(int value) {
        this.setIndex(0, value);
    }

    public void setY(int value) {
        this.setIndex(1, value);
    }

    public void setZ(int value) {
        this.setIndex(2, value);
    }

    public void translate(int[] otherValues) {
        for (int i = 0; i < this.dims.length; ++i) {
            int[] arrn = this.dims;
            int n = i;
            arrn[n] = arrn[n] + otherValues[i];
        }
    }

    public void translate(int x, int y, int z) {
        int[] arrn = this.dims;
        arrn[0] = arrn[0] + x;
        int[] arrn2 = this.dims;
        arrn2[1] = arrn2[1] + y;
        int[] arrn3 = this.dims;
        arrn3[2] = arrn3[2] + z;
    }

    public void translate(int x, int y) {
        this.translate(x, y, 0);
    }

    public void translateDimIndex(int index, int value) {
        this.dims[index] = value;
    }

    public void dilate(float dilationFactor) {
        for (int val : this.dims) {
            val = Math.round((float)val * dilationFactor);
        }
    }

    public void reflectOnXAxis() {
        int[] arrn = this.dims;
        arrn[1] = arrn[1] * -1;
    }

    public void reflectOnYAxis() {
        int[] arrn = this.dims;
        arrn[0] = arrn[0] * -1;
    }

    public void reflectOnZAxis() {
        int[] arrn = this.dims;
        arrn[2] = arrn[2] * -1;
    }

    public void reflectOnOrigin() {
        this.reflectOnXAxis();
        this.reflectOnYAxis();
        this.reflectOnZAxis();
    }

    public float xyQuadrant() {
        int x = this.dims[0];
        int y = this.dims[1];
        if (x > 0 && y > 0) {
            return 1.0f;
        }
        if (x < 0 && y > 0) {
            return 2.0f;
        }
        if (x < 0 && y < 0) {
            return 3.0f;
        }
        if (x > 0 && y < 0) {
            return 4.0f;
        }
        if (x > 0 & y == 0) {
            return 0.5f;
        }
        if (x == 0 && y > 0) {
            return 1.5f;
        }
        if (x < 0 && y == 0) {
            return 2.5f;
        }
        if (x == 0 && y < 0) {
            return 3.5f;
        }
        return 0.0f;
    }

    public float terminalAngle() {
        int x = this.dims[0];
        int y = this.dims[1];
        float angle = (float)Math.atan2(y, x);
        angle += 2.0f * this.PI;
        return angle %= 2.0f * this.PI;
    }

    public float referenceAngle() {
        float quad = this.xyQuadrant();
        float angle = this.terminalAngle();
        if (quad == 1.0f) {
            return angle;
        }
        if (quad == 2.0f) {
            return this.PI - angle;
        }
        if (quad == 3.0f) {
            return angle - this.PI;
        }
        if (quad == 4.0f) {
            return 2.0f * this.PI - angle;
        }
        return angle % (this.PI / 2.0f);
    }

    public boolean equals(IntCoordinate coord) {
        int[] otherValues = coord.getValues();
        if (this.dims.length != otherValues.length) {
            return false;
        }
        for (int i = 0; i < this.dims.length; ++i) {
            if (this.dims[i] == otherValues[i]) continue;
            return false;
        }
        return true;
    }

    public float distanceTo(int[] otherValues) {
        double total = 0.0;
        for (int i = 0; i < this.dims.length; ++i) {
            int diff = this.dims[i] - otherValues[i];
            total += (double)(diff * diff);
        }
        return (float)Math.sqrt(total);
    }

    public float distanceTo(IntCoordinate coord) {
        return this.distanceTo(coord.getValues());
    }

    public float xyzDistanceTo(double[] otherValues) {
        double dX = (double)this.dims[0] - otherValues[0];
        double dY = (double)this.dims[1] - otherValues[1];
        double dZ = (double)this.dims[2] - otherValues[2];
        return (float)Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    public float xyDistanceTo(double[] otherValues) {
        double dX = (double)this.dims[0] - otherValues[0];
        double dY = (double)this.dims[1] - otherValues[1];
        return (float)Math.sqrt(dX * dX + dY * dY);
    }

    public float distanceToOrigin() {
        int[] origin = new int[this.dims.length];
        return this.distanceTo(origin);
    }

    public float arcLength() {
        return this.distanceToOrigin() * this.terminalAngle();
    }

    public String toString() {
        return "(" + this.dims[0] + ", " + this.dims[1] + ")";
    }
}

