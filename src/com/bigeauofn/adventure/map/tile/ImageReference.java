package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.map.Orientation;

public class ImageReference {
    protected Orientation orient;
    protected boolean inverted;
    protected int imageIndex;

    public ImageReference(int index) {
        this(Orientation.ZERO, false, index);
    }

    public ImageReference(Orientation startOrientation, boolean inverted, int index) {
        setIndex(index);
        setInversion(inverted);
        setOrientation(startOrientation);
    }

    public static Orientation intToOrientation(int i) {
        Orientation ret;
        switch (i) {
            default:
            case 0:
                ret = Orientation.ZERO;
                break;
            case 1:
                ret = Orientation.NINTY;
                break;
            case 2:
                ret = Orientation.ONE_EIGHTY;
                break;
            case 3:
                ret = Orientation.TWO_SEVENTY;
                break;
        }
        return ret;
    }

    public static Orientation nearestOrientationFromDegrees(double d) {
        Orientation ret;
        d = Math.abs(d) % 360;
        if (d <= 45.0) {
            ret = Orientation.ZERO;
        } else if (d <= 135.0) {
            ret = Orientation.NINTY;
        } else if (d <= 225.0) {
            ret = Orientation.ONE_EIGHTY;
        } else if (d <= 315.0) {
            ret = Orientation.TWO_SEVENTY;
        } else {
            ret = Orientation.ZERO;
        }
        return ret;
    }

    public boolean setInversion(boolean newInversion) {
        boolean ret = inverted;
        inverted = newInversion;
        return ret;
    }

    public boolean getInversion() {
        boolean ret = inverted;
        return ret;
    }

    public Orientation setOrientation(Orientation o) {
        Orientation ret = orient;
        orient = o;
        return ret;
    }

    public Orientation getOrientation() {
        Orientation ret = orient;
        return ret;
    }

    public int setIndex(int index) {
        int ret = imageIndex;
        imageIndex = index;
        return ret;
    }

    public int getIndex() {
        int ret = imageIndex;
        return ret;
    }
}
