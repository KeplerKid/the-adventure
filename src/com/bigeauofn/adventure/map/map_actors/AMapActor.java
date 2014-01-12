package com.bigeauofn.adventure.map.map_actors;

import com.bigeauofn.adventure.utilities.ImageUtility;
import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.AMap;
import com.bigeauofn.adventure.map.IRenderable;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class AMapActor implements IRenderable /*, IGridEntity */ {
    protected BufferedImage image;

    public BufferedImage setAvatar(String filePath) {
        return setAvatar(ImageUtility.loadImage(filePath));
    }

    public BufferedImage setAvatar(BufferedImage newBufferedImage) {
        BufferedImage ret = image;
        if (newBufferedImage == null) {
            ret = newBufferedImage;
        } else {
            image = newBufferedImage;
        }
        return ret;
    }

    public BufferedImage getAvatar() {
        BufferedImage ret = image;
        return ret;
    }


    public abstract double setOrientation(double newOrientation);

    public abstract double getOrientation();


    public abstract Point setGridLocation(Point newGridLocation, AMap map);

    public abstract Point getGridLocation(AMap map);

    public abstract Point setLocation(Point newLocation);

    public abstract Point getLocation();

    public void paint(AGraphics g) {
        // To be reimplemented to handle rotation(orientation) as well as other useful things.
        g.drawImage(image, (int) getLocation().getX(), (int) getLocation().getY(), null);
    }
}
