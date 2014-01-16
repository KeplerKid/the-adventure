package com.bigeauofn.adventure.map.geometry;


/*
* Geometry point to contain an integer coordinate.
*
* This class is being created to be used instead of the
*   java.awt.IntPoint which casts its x and y coordinates to
*   double before returning them.
*
*   This class usually achieves between a 75% and 90% speedup
*   when compared to java.awt.IntPoint. See GeometryUtility for testing
*   and more information.
*
* While this behavior might be useful in some cases, each other
*   implement in the maps package currently uses integers as well.
*   If we decide refactor and use floats or doubles
*   at a later point it shouldn't be too hard to simply change the
*   underlying frame of the geometry package or replace it with
*   AWT geometry.
*
* @author Nik
* */

public class IntPoint implements IIntPoint {
    protected int x;
    protected int y;

    public IntPoint() {
        this(0, 0);
    }

    public IntPoint(IntPoint p) {
        this(p.getX(), p.getY());
    }

    public IntPoint(int x, int y) {
        setLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public IntPoint getLocation() {
        IntPoint ret = new IntPoint(this.x, this.y);
        return ret;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Number x, Number y) {
        this.x = x.intValue();
        this.y = y.intValue();
    }

    public void setLocation(IntPoint newLocation) {
        this.x = newLocation.getX();
        this.y = newLocation.getY();
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    public boolean equals(Object obj) {
        IIntPoint point;
        boolean ret = false;
        if (obj instanceof IIntPoint) {
            point = (IIntPoint) obj;
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
