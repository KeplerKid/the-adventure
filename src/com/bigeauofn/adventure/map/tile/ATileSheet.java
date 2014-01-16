package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.map.geometry.IIntDimension;
import com.bigeauofn.adventure.utilities.ImageUtility;

import java.awt.image.BufferedImage;
import java.util.Collection;

public abstract class ATileSheet {
    public static final BufferedImage[] tiles = new BufferedImage[]{ImageUtility.loadImage("res/tiles/basic_gray_dungeon_64.png"),
            ImageUtility.loadImage("res/tiles/bricked_dungeon_64.png"),
            ImageUtility.loadImage("res/tiles/moldy_dungeon_64.png"),
            ImageUtility.loadImage("res/tiles/cracked_gray_dungeon_64.png")};

    protected IIntDimension tileSize;

    public IIntDimension setTileSize(IIntDimension newTileSize) {
        IIntDimension ret = tileSize;
        tileSize = newTileSize;
        return ret;
    }

    public IIntDimension getTileSize() {
        IIntDimension ret = tileSize;
        return ret;
    }

    abstract public int getTileCount();

    abstract public void addImages(Collection<BufferedImage> imageCollection);

    public void addProperlySizedImages(Collection<BufferedImage> imageCollection) {
        for (BufferedImage bi : imageCollection) {
            if (bi.getWidth() != tileSize.getWidth()
                    || bi.getHeight() != tileSize.getHeight()) {
                imageCollection.remove(bi);
            }
        }
        this.addImages(imageCollection);
    }

    abstract public void setImages(Collection<BufferedImage> imageCollection);

    public void setProperlySizedImages(Collection<BufferedImage> imageCollection) {
        for (BufferedImage bi : imageCollection) {
            if (bi.getWidth() != tileSize.getWidth()
                    || bi.getHeight() != tileSize.getHeight()) {
                imageCollection.remove(bi);
            }
        }
        this.setImages(imageCollection);
    }

    abstract public BufferedImage get(int index);
}
