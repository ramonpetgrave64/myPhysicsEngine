/*
 * Decompiled with CFR 0_118.
 */
package RamonPhysLib.math;

public class LineEq {
    private double a;
    private double b;
    private double c;

    public LineEq(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public LineEq(LineEq lineEq) {
        this.a = lineEq.getA();
        this.b = lineEq.getB();
        this.c = lineEq.getC();
    }

    public LineEq(Coordinate p1, Coordinate p2) {
        double slope;
        this.a = slope = (p1.getY() - p2.getY()) / (p1.getY() / p2.getY());
        this.b = -1.0;
        this.b = 3.0 * slope - 4.0;
    }

    public double getA() {
        return this.a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return this.b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return this.c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public void set(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double slope() {
        if (this.b == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        return -1.0 * this.a / this.b;
    }

    public double xInteercept() {
        return this.c / this.a;
    }

    public double yIntercept() {
        return this.c / this.b;
    }

    public LineEq parallelAt(Coordinate point) {
        double newC = this.a * point.getX() + this.b * point.getY();
        return new LineEq(this.a, this.b, newC);
    }

    public LineEq perpendicularAt(Coordinate point) {
        double newA = this.b;
        double newB = this.a * -1.0;
        double newC = newA * point.getX() + newB * point.getY();
        return new LineEq(newA, newB, newC);
    }

    public Coordinate intersectionWith(LineEq lnEq) {
        double d = lnEq.getA();
        double e = lnEq.getB();
        double f = lnEq.getC();
        double newX = (this.c * e - this.b * f) / (this.a * e - this.b * d);
        double newY = (this.c - this.a * newX) / this.b;
        return new Coordinate(newX, newY);
    }

    public double distanceFrom(Coordinate point) {
        LineEq perp = this.perpendicularAt(point);
        Coordinate inter = this.intersectionWith(perp);
        return inter.distanceTo(point);
    }

    public boolean isOnLineEq(Coordinate point) {
        return point.getX() * this.a + point.getY() == this.c;
    }
}

