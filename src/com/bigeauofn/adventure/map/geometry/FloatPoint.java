package com.bigeauofn.adventure.map.geometry;

public class FloatPoint implements IFloatPoint {
    protected float x;
    protected float y;

    public FloatPoint() {
        this(0.0f, 0.0f);
    }

    public FloatPoint(FloatPoint d) {
        this(d.getX(), d.getY());
    }

    public FloatPoint(float x, float y) {
        setLocation(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public IFloatPoint getLocation() {
        FloatPoint ret = new FloatPoint(x, y);
        return ret;
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Number x, Number y) {
        this.x = x.floatValue();
        this.y = y.floatValue();
    }

    public void setLocation(IFloatPoint newLocation) {
        this.x = newLocation.getX();
        this.y = newLocation.getY();
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    public boolean equals(Object obj) {
        IFloatPoint point;
        boolean ret = false;
        if (obj instanceof IFloatPoint) {
            point = (IFloatPoint) obj;
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
