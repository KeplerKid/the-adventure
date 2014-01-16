package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.geometry.IIntDimension;
import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.geometry.IntDimension;
import com.bigeauofn.adventure.map.geometry.IntPoint;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class ATileMap implements ITileMap {
    protected IIntPoint offset;
    protected IIntDimension mapSize;
    protected ATileSheet tileSheet;

    public ATileMap(ATileSheet tileSheet, IIntDimension mapSize, IIntPoint offset) {
        setOffset(offset);
        setMapSize(mapSize);
        setTileSheet(tileSheet);

        if (this.offset == null) {
            System.err.println("IntPoint offset is null!");
            setOffset(new IntPoint(0, 0));
        }

        if (this.mapSize == null) {
            System.err.println("Dimension mapSize is null!");
            setMapSize(new IntDimension(0, 0));
        }

        if (this.tileSheet == null) {
            System.err.println("ATileSheet tileSheet is null!");
            setTileSheet(new ArrayTileSheet());
        }
    }

    public IIntPoint setOffset(IIntPoint newOffset) {
        IIntPoint ret = offset;
        offset = newOffset;
        return ret;
    }

    public IIntPoint getOffset() {
        IIntPoint ret = offset;
        return ret;
    }

    public IIntDimension setMapSize(IIntDimension newMapSize) {
        IIntDimension ret = mapSize;
        mapSize = newMapSize;
        return ret;
    }

    public IIntDimension getMapSize() {
        IIntDimension ret = mapSize;
        return ret;
    }

    public ATileSheet setTileSheet(ATileSheet newTileSheet) {
        ATileSheet ret = tileSheet;
        tileSheet = newTileSheet;
        return ret;
    }

    public ATileSheet getTileSheet() {
        ATileSheet ret = tileSheet;
        return ret;
    }

    public IIntPoint getDimensions() {
        IIntPoint ret = new IntPoint(mapSize.getWidth() * tileSheet.getTileSize().getWidth(), mapSize.getHeight() * tileSheet.getTileSize().getHeight());
        return ret;
    }

    abstract public BufferedImage tile(int x, int y);

    protected int scaleX(int x) {
        int ret = x * tileSheet.getTileSize().getWidth();
        return ret;
    }

    protected int scaleY(int y) {
        int ret = y * tileSheet.getTileSize().getHeight();
        return ret;
    }

    public Rectangle tileBoundries(int x, int y) {
        return tileBoundries(new Rectangle(), x, y);
    }

    public Rectangle tileBoundries(Rectangle rectangle, int x, int y) {
        rectangle.setSize(tileSheet.getTileSize().getWidth(),
                tileSheet.getTileSize().getHeight());
        rectangle.setLocation(scaleX(x) + offset.getX(),
                scaleY(y) + offset.getY());
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
