package com.bigeauofn.adventure.map.geometry;

public class DoublePoint implements IDoublePoint {
    protected double x;
    protected double y;

    public DoublePoint() {
        this(0.0, 0.0);
    }

    public DoublePoint(DoublePoint d) {
        this(d.getX(), d.getY());
    }

    public DoublePoint(double x, double y) {
        setLocation(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public IDoublePoint getLocation() {
        DoublePoint ret = new DoublePoint(x, y);
        return ret;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Number x, Number y) {
        this.x = x.doubleValue();
        this.y = y.doubleValue();
    }

    public void setLocation(IDoublePoint newLocation) {
        this.x = newLocation.getX();
        this.y = newLocation.getY();
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double dx, double dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    public boolean equals(Object obj) {
        IDoublePoint point;
        boolean ret = false;
        if (obj instanceof IDoublePoint) {
            point = (IDoublePoint) obj;
            if (point.getX() == this.x && point.getY() == this.y) {
                ret = true;
            }
        }
        return ret;
    }

    public String toString() {
        String ret = "{" + this.x + "," + this.y + "}";
        return ret;
    }
}
