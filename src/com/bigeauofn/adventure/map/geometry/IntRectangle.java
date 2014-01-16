package com.bigeauofn.adventure.map.geometry;

public class IntRectangle implements IIntRectangle {
    protected static final IIntPoint defaultPoint = new IntPoint(0, 0);
    protected static final IIntDimension defaultDimensions = new IntDimension(0, 0);

    protected IIntPoint corner;
    protected IIntDimension size;
    protected float rotation = 0.0f;

    public IntRectangle() {
        this(defaultPoint, defaultDimensions, 0.0f);
    }

    public IntRectangle(IIntPoint intPoint, IIntDimension intDimension, float rotation) {
        corner = new IntPoint();
        size = new IntDimension();
        if (intPoint != null) {
            setLocation(intPoint);
        } else {
            setLocation(new IntPoint(0, 0));
        }
        if (intDimension != null) {
            setSize(intDimension);
        } else {
            setSize(intDimension);
        }
        setRotation(rotation);
    }

    // protected float convertToDegrees;

    // public void add(int newx, int newy);
    // public void add(Point pt);
    // public void add(Rectangle r);

    public boolean contains(int x, int y) {
        boolean ret = false;
        return ret;
    }

    public boolean contains(IIntPoint point) {
        return contains(point.getX(), point.getY());
    }

    public boolean contains(IIntRectangle rectangle) {
        return false;
    }

    public int area() {
        return size.getWidth() * size.getHeight();
    }

    public int getX() {
        return corner.getX();
    }

    public int getY() {
        return corner.getY();
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }

    public float getRotation() {
        return rotation;
    }

    public IIntPoint getLocation() {
        return corner;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setLocation(int x, int y) {
        corner.setLocation(x, y);
    }

    public void setLocation(IIntPoint p) {
        corner.setLocation(p.getX(), p.getY());
    }

    public void setRect(Number x, Number y, Number width, Number height) {
        corner.setLocation(x, y);
        size.setSize(width, height);
    }

    public void setSize(int width, int height) {
        size.setSize(width, height);
    }

    public void setSize(IIntDimension d) {
        size.setSize(d.getWidth(), d.getHeight());
    }

    public void translate(int dx, int dy) {
        corner.translate(dx, dy);
    }

    public boolean isEmpty() {
        return (area() == 0);
    }

    public IIntRectangle intersection(IIntRectangle rect) {
        return null;
    }

    public boolean intersects(IIntRectangle rect) {
        return false;
    }

    public String toString() {
        String ret = "{" + corner.toString() + "},{" + size.toString() + "," + rotation + "}";
        return ret;
    }
}
