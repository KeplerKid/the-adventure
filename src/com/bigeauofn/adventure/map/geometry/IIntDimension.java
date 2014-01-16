package com.bigeauofn.adventure.map.geometry;

public interface IIntDimension {
    public int getWidth();

    public int getHeight();

    public void setSize(int x, int y);

    public void setSize(Number x, Number y);

    public void setSize(IIntDimension newSize);

    // public void scale(int factor);
}
