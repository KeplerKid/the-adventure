package com.bigeauofn.adventure.map.geometry;

public interface IDoubleDimension {
    public double getWidth();

    public double getHeight();

    public void setSize(double x, double y);

    public void setSize(Number x, Number y);

    public void setSize(IDoubleDimension newSize);

    // public void scale(double factor);
}
