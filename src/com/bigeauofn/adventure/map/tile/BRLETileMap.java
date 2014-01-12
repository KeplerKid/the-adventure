package com.bigeauofn.adventure.map.tile;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class BRLETileMap extends ATileMap {
    // public ;

    public BRLETileMap() {
        super(new ArrayTileSheet(), new Dimension(0, 0), new Point(0, 0));
    }

    public BufferedImage tile(int x, int y) {
        return null;
    }
}
