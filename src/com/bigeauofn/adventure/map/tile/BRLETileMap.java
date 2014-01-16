package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.map.geometry.IntDimension;
import com.bigeauofn.adventure.map.geometry.IntPoint;

import java.awt.image.BufferedImage;

public class BRLETileMap extends ATileMap {
    // public ;

    public BRLETileMap() {
        super(new ArrayTileSheet(), new IntDimension(0, 0), new IntPoint(0, 0));
    }

    public BufferedImage tile(int x, int y) {
        return null;
    }
}
