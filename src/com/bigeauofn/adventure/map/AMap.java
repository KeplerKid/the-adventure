package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.map.lighting.ILighting;
import com.bigeauofn.adventure.map.mask.IMapMasker;
import com.bigeauofn.adventure.map.tile.ITileMap;

import java.awt.Point;
import java.util.Collection;

/*
* Implements some of the base functionality of the IMap
*   such as zooming, lighting, and tiles
* */
public abstract class AMap implements IMap {
    protected static final Point defaultPoint = new Point(0, 0);

    // Zoom is currently unimplemented
    protected double zoomLevel;

    // Measures displacement from upper left-hand corner of
    // map to pixel (0, 0) inside parent window.
    protected Point offset;

    protected ITileMap tiles;
    protected IBackground background;
    protected IGrid grid;
    protected ILighting lighting;
    protected IMapMasker mask;

    protected Collection<? extends IRenderable> renderable;

    public AMap() {
        this(defaultPoint, 0.0);
    }

    public AMap(Point cOffset, double cZoomLevel) {
        setZoomLevel(cZoomLevel);
        setOffset(cOffset);
        if (offset == null) {
            System.err.println("Point cOffset was null!");
            setOffset(new Point(0, 0));
        }
    }

    /*
    * Sets the zoom level for the map.
    *
    * @param newZoom
    *  New zoom level.
    *
    * @return The old zoom level.
    * */
    public double setZoomLevel(double newZoom) {
        double ret = zoomLevel;
        zoomLevel = newZoom;
        return ret;
    }

    /*
    * @return The currently set zoom level.
    * */
    public double getZoomLevel() {
        double ret = zoomLevel;
        return ret;
    }

    /*
    * Sets the offset for this Map.
    *
    * @param newOffset
    *   Value to set offset to.
    *
    * @return The previously used offset.
    *       When newOffset is null, null is returned and the previous offset remains in use.
    * */
    public Point setOffset(Point newOffset) {
        Point ret = offset;
        if (newOffset == null) {
            ret = newOffset;
        } else {
            offset = newOffset;
        }
        return ret;
    }

    /*
    * Gets the offset for this Map.
    *
    * @return The current offset.
    * */
    public Point getOffset() {
        Point ret = offset;
        return ret;
    }

    /*
    * Sets the ITileMap object to be used by the IMap.
    *
    * @param newTiles
    *
    * @return The ITileMap object previously used by the Map.
    *   When newTiles is null, nothing is done
    * */
    public ITileMap setTileMap(ITileMap newTiles) {
        ITileMap ret = tiles;
        if (newTiles == null) {
            ret = null;
        } else {
            tiles = newTiles;
        }
        return ret;
    }

    /*
    * @return The ITileMap object to be rendered by the map.
    * */
    public ITileMap getTileMap() {
        ITileMap ret = tiles;
        return ret;
    }

    /*
    * Sets the background for the map
    *
    * @param newBackground
    *   Sets the background used for the IMap to newBackground
    *   Does nothing if newBackground is null
    * @return The old background for the Map when newBackground is not null
    *   returns null when newBackground is null
    * */
    public IBackground setBackground(IBackground newBackground) {
        IBackground ret = background;
        if (newBackground == null) {
            ret = newBackground;
        } else {
            background = newBackground;
        }
        return ret;
    }

    /*
    * @return The current IBackground.
    * */
    public IBackground getBackground() {
        IBackground ret = background;
        return ret;
    }

    /*
    * Sets the IGrid object to be used by the IMap.
    *
    * @param newTiles
    *
    * @return The IGrid object previously used by the Map.
    *   When newTiles is null, nothing is done
    * */
    public IGrid setGrid(IGrid newGrid) {
        IGrid ret = grid;
        if (newGrid == null) {
            ret = null;
        } else {
            grid = newGrid;
        }
        return ret;
    }

    /*
    * @return The IGrid object to be rendered by the map.
    * */
    public IGrid getGrid() {
        IGrid ret = grid;
        return ret;
    }

    /*
    * Sets the lighting for this Map.
    *
    * @param newLighting
    *   Sets the lighting used for the IMap to newLighting.
    *   Does nothing if newLighting is null.
    *
    * @return The old lighting for the Map when newLighting is not null.
    *   Returns null when newLighting is null.
    * */
    public ILighting setLighting(ILighting newLighting) {
        ILighting ret = lighting;
        if (newLighting == null) {
            ret = newLighting;
        } else {
            lighting = newLighting;
        }
        return ret;
    }

    /*
    * @return The current ILighting.
    * */
    public ILighting getLighting() {
        ILighting ret = lighting;
        return ret;
    }

    /*
    * Sets the current IMapMasker.
    *
    * @param newMask
    *   New mask to be used by this Map.
    *
    * @return Old IMapMasker previously used by this Map.
    * */
    public IMapMasker setMasker(IMapMasker newMasker) {
        IMapMasker ret = mask;
        if (newMasker == null) {
            ret = newMasker;
        } else {
            mask = newMasker;
        }
        return ret;
    }

    /*
    * @return The current Masker used by this Map.
    * */
    public IMapMasker getMasker() {
        IMapMasker ret = mask;
        return ret;
    }

    /*
    * Sets the current Collection of IRenderables.
    *
    * @param newRenderables
    *   The new Collection of Irenderables to use for this Map.
    *
    * @return The previously used Collection of IRenderables.
    *   Returns null when newRenderables is null and the Collection is not changed.
    * */
    public Collection<? extends IRenderable> setRenderables(Collection<? extends IRenderable> newRenderables) {
        Collection<? extends IRenderable> ret = renderable;
        if (newRenderables == null) {
            ret = newRenderables;
        } else {
            // todo set this to put all elements of newRenderables into a new ArrayList stored as renderable
            ret = renderable;
        }
        return ret;
    }

    /*
    * @return The current Collection of IRenderables
    * */
    public Collection<? extends IRenderable> getRenderables() {
        Collection<? extends IRenderable> ret = renderable;
        return ret;
    }

    // todo add 'addRenderable' and 'addRenderables' methods to IMap and AMap.

    /*
    * Paints the map.
    *
    * @param g
    *   graphics object used to render the map.
    * */
    public void paint(AGraphics g) {
        /*
        * Paint default background
        *   ie. what lies beyond the IGrid this object contains
        * Paint tiles
        *   For non-present tiles paint default tile
        * Paint grid
        *   Can IGrid paint itself?
        * */

        g.applyOffset(offset);
        background.paint(g);
        tiles.paint(g);
        for (IRenderable r : renderable) {
            continue;
            // r.paint();
        }
        mask.paint(g);
        grid.paint(g);
        g.popLastOffset();


    }
}
