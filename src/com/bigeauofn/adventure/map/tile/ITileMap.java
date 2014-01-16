package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.map.IRenderable;
import com.bigeauofn.adventure.map.geometry.IIntDimension;
import com.bigeauofn.adventure.map.geometry.IIntPoint;

import java.awt.image.BufferedImage;

public interface ITileMap extends IRenderable {

    public IIntPoint setOffset(IIntPoint newOffset);

    public IIntPoint getOffset();

    public IIntDimension setMapSize(IIntDimension newMapSize);

    public IIntDimension getMapSize();

    public ATileSheet setTileSheet(ATileSheet newTileSheet);

    public BufferedImage tile(int x, int y);
}
