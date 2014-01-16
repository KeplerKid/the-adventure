package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.map.geometry.IIntDimension;
import com.bigeauofn.adventure.map.geometry.IntDimension;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayTileSheet extends ATileSheet {
    protected static final IIntDimension defaultTileSize = new IntDimension(64, 64);

    protected ArrayList<BufferedImage> images;

    public ArrayTileSheet() {
        this(defaultTileSize, Arrays.asList(tiles[0], tiles[1], tiles[2], tiles[3]));
    }

    public ArrayTileSheet(IIntDimension tileSize, Collection<BufferedImage> imageCollection) {
        this(tileSize, imageCollection, false);
    }

    public ArrayTileSheet(IIntDimension dim, Collection<BufferedImage> imageCollection, boolean properlySized) {
        setTileSize(dim);
        if (properlySized) {
            setProperlySizedImages(imageCollection);
        } else {
            setImages(imageCollection);
        }
        if (tileSize == null) {
            setTileSize(defaultTileSize);
        }
        if (images == null) {
            if (properlySized) {
                setProperlySizedImages(defaultImages());
            } else {
                setImages(defaultImages());
            }
        }
    }

    protected static List<BufferedImage> defaultImages() {
        List<BufferedImage> ret = Arrays.asList(tiles[0], tiles[1], tiles[2], tiles[3]);
        return ret;
    }

    public BufferedImage get(int i) {
        BufferedImage ret = null;
        if (i >= 0 && i < images.size()) {
            ret = images.get(i);
        }
        return ret;
    }

    public int getTileCount() {
        int ret = images.size();
        return ret;
    }

    public void addImages(Collection<BufferedImage> imageCollection) {
        if (imageCollection != null) {
            for (BufferedImage bi : imageCollection) {
                if (bi != null) {
                    images.add(bi);
                }
            }
        }
    }

    public void addProperlySizedImages(Collection<BufferedImage> imageCollection) {
        if (imageCollection != null) {
            for (BufferedImage bi : imageCollection) {
                if (bi != null
                        && bi.getWidth() == tileSize.getWidth()
                        && bi.getHeight() == tileSize.getHeight()) {
                    images.add(bi);
                }
            }
        }
    }

    public void setImages(Collection<BufferedImage> imageCollection) {
        if (imageCollection == null) {
            images = new ArrayList<>();
        } else {
            images = new ArrayList<>(imageCollection.size());
            for (BufferedImage bi : imageCollection) {
                images.add(bi);
            }
        }
    }

    public void setProperlySizedImages(Collection<BufferedImage> imageCollection) {
        if (imageCollection == null) {
            images = new ArrayList<>();
        } else {
            images = new ArrayList<>(imageCollection.size());
            for (BufferedImage bi : imageCollection) {
                if (bi != null
                        && bi.getWidth() == tileSize.getWidth()
                        && bi.getHeight() == tileSize.getHeight()) {
                    images.add(bi);
                }
            }
        }
    }
}
