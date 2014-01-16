package com.bigeauofn.adventure.map.geometry;

public interface IFloatDimension {
    public float getWidth();

    public float getHeight();

    public void setSize(float x, float y);

    public void setSize(Number x, Number y);

    public void setSize(IFloatDimension newSize);

    // public void scale(float factor);
}
