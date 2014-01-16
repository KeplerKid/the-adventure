package com.bigeauofn.adventure.map.geometry;

public class IntDimension implements IIntDimension {
    protected int width;
    protected int height;

    public IntDimension() {
        this(0, 0);
    }

    public IntDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(Number width, Number height) {
        this.width = width.intValue();
        this.height = height.intValue();
    }

    public void setSize(IIntDimension newSize) {
        this.width = newSize.getWidth();
        this.height = newSize.getHeight();
    }

    // public void scale(int factor);
}
