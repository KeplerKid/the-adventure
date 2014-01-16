package com.bigeauofn.adventure.map.geometry;

public class IntegerPoint {
    protected Integer x;
    protected Integer y;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setLocation(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setLocationCast(Number x, Number y) {
        this.x = x.intValue();
        this.y = y.intValue();
    }

}
