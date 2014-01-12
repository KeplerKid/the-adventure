package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.graphics.AGraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class ATileMap implements ITileMap {
    protected Point offset;
    protected Dimension mapSize;
    protected ATileSheet tileSheet;

    public ATileMap(ATileSheet tileSheet, Dimension mapSize, Point offset) {
        setOffset(offset);
        setMapSize(mapSize);
        setTileSheet(tileSheet);

        if (this.offset == null) {
            System.err.println("Point offset is null!");
            setOffset(new Point(0, 0));
        }

        if (this.mapSize == null) {
            System.err.println("Dimension mapSize is null!");
            setMapSize(new Dimension(0, 0));
        }

        if (this.tileSheet == null) {
            System.err.println("ATileSheet tileSheet is null!");
            setTileSheet(new ArrayTileSheet());
        }
    }

    public Point setOffset(Point newOffset) {
        Point ret = offset;
        offset = newOffset;
        return ret;
    }

    public Point getOffset() {
        Point ret = offset;
        return ret;
    }

    public Dimension setMapSize(Dimension newMapSize) {
        Dimension ret = mapSize;
        mapSize = newMapSize;
        return ret;
    }

    public Dimension getMapSize() {
        Dimension ret = mapSize;
        return ret;
    }

    public ATileSheet setTileSheet(ATileSheet newTileSheet) {
        ATileSheet ret = tileSheet;
        tileSheet = newTileSheet;
        return ret;
    }

    abstract public BufferedImage tile(int x, int y);

    protected int scaleX(int x) {
        int ret = x * (int) tileSheet.getTileSize().getWidth();
        return ret;
    }

    protected int scaleY(int y) {
        int ret = y * (int) tileSheet.getTileSize().getHeight();
        return ret;
    }

    public Rectangle tileBoundries(int x, int y) {
        return tileBoundries(new Rectangle(), x, y);
    }

    public Rectangle tileBoundries(Rectangle rectangle, int x, int y) {
        rectangle.setBounds(scaleX(x) + (int) offset.getX(),
                scaleY(y) + (int) offset.getY(),
                (int) tileSheet.getTileSize().getWidth(),
                (int) tileSheet.getTileSize().getHeight());
        return rectangle;
    }

    public void paint(AGraphics g) {
        BufferedImage nullTest;
        Rectangle loopRectangle = new Rectangle();
        Rectangle clippingBounds = new Rectangle();
        g.getClipBounds(clippingBounds);
        for (int x = 0; x < mapSize.getWidth(); x++) {
            for (int y = 0; y < mapSize.getHeight(); y++) {
                tileBoundries(loopRectangle, x, y);
                if (clippingBounds.intersects(loopRectangle)) {
                    // Tile is within the clipping bounds, draw it
                    nullTest = tile(x, y);
                    if (nullTest != null) {
                        g.drawImage(nullTest, (int) loopRectangle.getX(),
                                (int) loopRectangle.getY(),
                                (int) loopRectangle.getWidth(),
                                (int) loopRectangle.getHeight(),
                                Color.WHITE, null);
                    }
                }
            }
        }
    }
}

/*
::REMOVE::
code for compiling several buffered images into a single buffered image
    NOT COMPLETED
Might want this later for a CompiledTileSheet class.

protected static BufferedImage compileBufferedImages(Collection<BufferedImage> images, boolean properlySized, boolean resize) {
    BufferedImage compiledImage;
    int compiledWidth;
    int compiledHeight;
    if (images != null) {
        if (images.size() >= 1) {
            if (images.get(0) != null) {
                compiledHeight = images.get(0).getHeight();
            }
        }
    }
    for (BufferedImage i : images) {
        if ()
    }
}
*/
