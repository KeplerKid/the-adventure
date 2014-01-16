package com.bigeauofn.adventure.map.geometry;

public interface IIntRectangle {
    public int area();

    public boolean contains(int x, int y);

    public boolean contains(IIntPoint point);

    public boolean contains(IIntRectangle rectangle);

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public float getRotation();

    public IIntPoint getLocation();

    public void setRotation(float rotation);

    public void setLocation(int x, int y);

    public void setLocation(IIntPoint p);

    public void setRect(Number x, Number y, Number width, Number height);

    public void setSize(int width, int height);

    public void setSize(IIntDimension d);

    public void translate(int dx, int dy);

    public boolean isEmpty();

    public IIntRectangle intersection(IIntRectangle rect);

    public boolean intersects(IIntRectangle rect);
}
