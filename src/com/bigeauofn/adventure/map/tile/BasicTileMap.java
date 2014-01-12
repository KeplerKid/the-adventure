package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.map.tile.ImageReference.orientation;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class BasicTileMap extends ATileMap {
    protected static final ImageReference[][] defaultMap = new ImageReference[][]{{ir(0), ir(1), ir(2), ir(3)},
            {ir(3), ir(0), ir(1), ir(2)},
            {ir(2), ir(3), ir(0), ir(1)},
            {ir(1), ir(2), ir(3), ir(0)}};
    protected static final ATileSheet defaultTileSheet = new ArrayTileSheet();

    protected ImageReference[][] map;

    protected static ImageReference ir(orientation orient, boolean inversion, int index) {
        return new ImageReference(orient, inversion, index);
    }

    protected static ImageReference ir(int index) {
        return ir(orientation.ZERO, false, index);
    }

    public BasicTileMap() {
        this(defaultMap, defaultTileSheet, new Point(0, 0));
    }

    public BasicTileMap(ImageReference[][] firstMap, ATileSheet tileSheet, Point offset) {
        super(tileSheet, new Dimension(firstMap.length, firstMap[0].length), offset);
        setMap(firstMap);
        if (map == null) {
            System.err.println("ImageReference [][] firstMap is null!");
            setMap(defaultMap);
        }
    }

    public ImageReference[][] setMap(ImageReference[][] newMap) {
        ImageReference[][] ret = map;
        if (newMap == null) {
            ret = newMap;
        } else {
            map = newMap;
            setMapSize(new Dimension(map.length, map[0].length));
        }
        return ret;
    }

    public ImageReference[][] getMap() {
        ImageReference[][] ret = map;
        return ret;
    }

    protected ImageReference[][] transferOldMap(ImageReference[][] oldMap, int newWidth, int newHeight) {
        ImageReference[][] ret = new ImageReference[newWidth][newHeight];
        if (oldMap != null) {
            System.arraycopy(oldMap, 0, ret, 0, newWidth * newHeight);
            /*
            for (int x = 0; x < newWidth; x++) {
                for (int y = 0; y < newHeight; y++) {
                    ret[x][y] = oldMap[x][y];
                }
            }
            */
        } else {
            for (int x = 0; x < newWidth; x++) {
                for (int y = 0; y < newHeight; y++) {
                    // populate with new ImageReferences
                    ret[x][y] = new ImageReference(orientation.ZERO, false, -1);
                }
            }
        }
        return ret;
    }

    public Dimension setMapSize(Dimension dimension) {
        Dimension ret = null;
        if (dimension != null) {
            map = transferOldMap(map, (int) dimension.getWidth(), (int) dimension.getHeight());
            ret = super.setMapSize(dimension);
        }
        return ret;
    }

    public BufferedImage tile(int x, int y) {
        return tileSheet.get(map[x][y].getIndex());
    }
}
