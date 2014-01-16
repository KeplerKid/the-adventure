package com.bigeauofn.adventure.map.geometry;

public interface IFloatPoint {
    public float getX();

    public float getY();

    public IFloatPoint getLocation();

    public void setLocation(float x, float y);

    public void setLocation(Number x, Number y);

    public void setLocation(IFloatPoint newLocation);

    public void move(float x, float y);

    public void translate(float dx, float dy);
}
