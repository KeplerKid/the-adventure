package com.bigeauofn.adventure.map.tile;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.Orientation;
import com.bigeauofn.adventure.map.geometry.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.Random;

public class BasicTileMap extends ATileMap {
    protected static final Random random = new Random();
    protected static final ImageReference[][] defaultMap = new ImageReference[][]{{irRO(0), irRO(1), irRO(2), irRO(3)},
            {irRO(3), irRO(0), irRO(1), irRO(2)},
            {irRO(2), irRO(3), irRO(0), irRO(1)},
            {irRO(1), irRO(2), irRO(3), irRO(0)}};
    protected static final ATileSheet defaultTileSheet = new ArrayTileSheet();

    protected ImageReference[][] map;

    protected static Orientation orientationMap(int i) {
        Orientation ret;
        switch (i) {
            default:
            case 0:
                ret = Orientation.ZERO;
                break;
            case 1:
                ret = Orientation.NINTY;
                break;
            case 2:
                ret = Orientation.ONE_EIGHTY;
                break;
            case 3:
                ret = Orientation.TWO_SEVENTY;
                break;
        }
        return ret;
    }

    protected static ImageReference ir(Orientation orient, boolean inversion, int index) {
        return new ImageReference(orient, inversion, index);
    }

    // RO == Random Orientation;
    protected static ImageReference irRO(int index) {
        return ir(orientationMap(Math.abs(random.nextInt()) % 4), false, index);
    }

    protected static ImageReference ir(int index) {
        return ir(Orientation.ZERO, false, index);
    }

    public BasicTileMap() {
        this(defaultMap, defaultTileSheet, new IntPoint(0, 0));
    }

    public BasicTileMap(ImageReference[][] firstMap, ATileSheet tileSheet, IIntPoint offset) {
        super(tileSheet, new IntDimension(firstMap.length, firstMap[0].length), offset);
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
            setMapSize(new IntDimension(map.length, map[0].length));
        }
        return ret;
    }

    public ImageReference[][] getMap() {
        ImageReference[][] ret = map;
        return ret;
    }

    public static void printMap(ImageReference[][] map, PrintStream out) {
        printMap(map, out, new char[]{'[', ']'}, new char[]{'{', ':', '}', ','});
    }

    public static void printMap(ImageReference[][] map, PrintStream out, char[] arrayDelims, char[] elementDelims) {
        out.print(arrayDelims[0]);
        for (int x = 0; x < map.length; x++) {
            out.print(arrayDelims[0]);
            for (int y = 0; y < map[x].length - 1; y++) {
                out.print(map[x][y].getIndex());
                out.print(elementDelims[3]);
            }
            out.print(map[x][map[x].length - 1].getIndex());
            out.print(arrayDelims[1]);
            if (x != map.length - 1) {
                out.print(elementDelims[3]);
            }
        }
        out.print(arrayDelims[1]);
    }

    protected ImageReference[][] transferOldMap(ImageReference[][] oldMap, int newWidth, int newHeight) {
        ImageReference[][] ret = new ImageReference[newWidth][newHeight];
        int min;
        if (oldMap != null) {
            min = Math.min(newWidth, oldMap[0].length);
            for (int x = 0; x < min; x++) {
                System.arraycopy(oldMap[x], 0, ret[x], 0, Math.min(ret[x].length, oldMap[x].length));
                /*
                for (int y = 0; y < newHeight; y++) {
                    ret[x][y] = oldMap[x][y];
                }
                */
            }
        } else {
            for (int x = 0; x < newWidth; x++) {
                for (int y = 0; y < newHeight; y++) {
                    // populate with new ImageReferences
                    ret[x][y] = new ImageReference(Orientation.ZERO, false, -1);
                }
            }
        }
        return ret;
    }

    public IIntDimension setMapSize(IIntDimension dimension) {
        IIntDimension ret = null;
        if (dimension != null) {
            map = transferOldMap(map, dimension.getWidth(), dimension.getHeight());
            ret = super.setMapSize(dimension);
        }
        return ret;
    }

    public BufferedImage tile(int x, int y) {
        return tileSheet.get(map[x][y].getIndex());
    }

    protected static double RAD_NINTY = Math.PI / 2.0;
    protected static double RAD_ONE_EIGHTY = Math.PI;
    protected static double RAD_TWO_SEVENTY = 3.0 * Math.PI / 2.0;

    protected static double angle(Orientation o) {
        double ret;
        switch (o) {
            default:
            case ZERO:
                ret = 0.0;
                break;
            case NINTY:
                ret = RAD_NINTY;
                break;
            case ONE_EIGHTY:
                ret = RAD_ONE_EIGHTY;
                break;
            case TWO_SEVENTY:
                ret = RAD_TWO_SEVENTY;
                break;
        }
        return ret;
    }

    @Override
    public void paint(AGraphics g) {
        BufferedImage nullTest;
        IIntRectangle loopRectangle = new IntRectangle();

        AffineTransform xFinal = new AffineTransform();
        AffineTransform xTranslate = new AffineTransform();
        AffineTransform xOrientation = new AffineTransform();
        double imageAnchorX = tileSheet.getTileSize().getWidth() / 2.0, imageAnchorY = tileSheet.getTileSize().getHeight() / 2.0;

        // Don't check clipping bounds for now since I'm working
        // on map transforms in the graphics package.
        // Rectangle clippingBounds = new Rectangle();
        // g.getClipBounds(clippingBounds);
        // **transform clippingBounds onto the map coordinates**
        xTranslate.translate(offset.getX(), offset.getY());
        for (int y = 0; y < mapSize.getHeight(); y++) {
            for (int x = 0; x < mapSize.getWidth(); x++) {
                nullTest = tile(x, y);
                if (nullTest != null) {
                    // Check if tile is in the clipping bounds of the screen.
                    // if so complete its transforms
                    // draw it
                    // else
                    // skip it

                    xOrientation.setToRotation(angle(map[x][y].getOrientation()), imageAnchorX, imageAnchorY);
                    // xTranslate.translate(scaleX(x) + offset.getX(), scaleY(y) + offset.getY());
                    xFinal.setTransform(xTranslate);
                    xFinal.concatenate(xOrientation);
                    // xFinal.concatenate(g.getTransform());
                    g.drawImage(nullTest, xFinal, null);
                }
                // Update translation transform for next tile. No multiplication!
                xTranslate.translate(tileSheet.getTileSize().getWidth(), 0.0);
            }
            // New row!
            // Reset/update translation transform for next row. No multiplication!
            xTranslate.setToTranslation(offset.getX(), xTranslate.getTranslateY() + tileSheet.getTileSize().getHeight());
        }
    }
}
