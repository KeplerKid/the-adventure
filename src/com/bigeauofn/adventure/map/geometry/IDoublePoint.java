package com.bigeauofn.adventure.map.geometry;

public interface IDoublePoint {
    public double getX();

    public double getY();

    public IDoublePoint getLocation();

    public void setLocation(double x, double y);

    public void setLocation(Number x, Number y);

    public void setLocation(IDoublePoint newLocation);

    public void move(double x, double y);

    public void translate(double dx, double dy);
}
