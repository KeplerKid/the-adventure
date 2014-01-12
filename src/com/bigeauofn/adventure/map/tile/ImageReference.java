package com.bigeauofn.adventure.map.tile;

public class ImageReference {
    protected orientation orient;
    protected boolean inverted;
    protected int imageIndex;

    public ImageReference(int index) {
        this(orientation.ZERO, false, index);
    }

    public ImageReference(orientation startOrientation, boolean inverted, int index) {
        setIndex(index);
        setInversion(inverted);
        setOrientation(startOrientation);
    }

    public static enum orientation {
        ZERO,
        NINTY,
        ONE_EIGHTY,
        TWO_SEVENTY
    }

    public static orientation intToOrientation(int i) {
        orientation ret;
        switch (i) {
            default:
            case 0:
                ret = orientation.ZERO;
                break;
            case 1:
                ret = orientation.NINTY;
                break;
            case 2:
                ret = orientation.ONE_EIGHTY;
                break;
            case 3:
                ret = orientation.TWO_SEVENTY;
                break;
        }
        return ret;
    }

    public static orientation nearestOrientationFromDegrees(double d) {
        orientation ret;
        d = Math.abs(d) % 360;
        if (d <= 45.0) {
            ret = orientation.ZERO;
        } else if (d <= 135.0) {
            ret = orientation.NINTY;
        } else if (d <= 225.0) {
            ret = orientation.ONE_EIGHTY;
        } else if (d <= 315.0) {
            ret = orientation.TWO_SEVENTY;
        } else {
            ret = orientation.ZERO;
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

    public orientation setOrientation(orientation o) {
        orientation ret = orient;
        orient = o;
        return ret;
    }

    public orientation getOrientation() {
        orientation ret = orient;
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
