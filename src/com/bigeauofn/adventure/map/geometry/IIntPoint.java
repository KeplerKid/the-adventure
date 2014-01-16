package com.bigeauofn.adventure.map.geometry;

public interface IIntPoint {

    public int getX();

    public int getY();

    public IIntPoint getLocation();

    public void setLocation(int x, int y);

    public void setLocation(Number x, Number y);

    public void setLocation(IntPoint newLocation);

    public void move(int x, int y);

    public void translate(int dx, int dy);
}
