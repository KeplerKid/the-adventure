package com.bigeauofn.adventure.map.entities;

import com.bigeauofn.adventure.map.IRenderable;
import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.grid.IGrid;

import java.awt.image.BufferedImage;

public interface IMapEntity extends IRenderable, IClickable, Comparable<IMapEntity> {
    public BufferedImage setAvatar(String filePath);

    public BufferedImage setAvatar(BufferedImage newBufferedImage);

    public BufferedImage getAvatar();

    public float setRotation(float newRotation);

    public float getRotation();

    public IIntPoint setGridLocation(IIntPoint newGridLocation, IGrid grid);

    public IIntPoint getGridLocation(IGrid grid);

    public IIntPoint setLocation(IIntPoint newLocation);

    public IIntPoint getLocation();

    public float setDepth(float newDepth);

    public float getDepth();

    public boolean setRender(boolean newRender);

    public boolean getRender();
}
