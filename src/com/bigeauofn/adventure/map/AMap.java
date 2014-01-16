package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.entities.IMapEntity;
import com.bigeauofn.adventure.map.errors.ErrorReporter;
import com.bigeauofn.adventure.map.geometry.*;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.map.lighting.ILighting;
import com.bigeauofn.adventure.map.mask.IMapMasker;
import com.bigeauofn.adventure.map.tile.ITileMap;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;

/*
* Implements some of the base functionality of the IMap
*   such as zooming, lighting, and tiles
* */
public abstract class AMap implements IMap {
    protected static final IIntPoint defaultPoint = new IntPoint(0, 0);
    protected static final IDoublePoint defaultScale = new DoublePoint(1.0, 1.0);

    // Measures displacement from upper left-hand corner of
    // map to pixel (0, 0) inside parent window.
    protected IIntPoint offset;
    protected double rotation;
    protected IDoublePoint scale;

    protected IIntDimension panelDimensions;

    protected ITileMap tiles;
    protected IBackground background;
    protected IGrid grid;
    protected ILighting lighting;
    protected IMapMasker mask;

    protected Collection<IMapEntity> mapEntities;

    public AMap() {
        this(defaultPoint, defaultScale, 0.0);
    }

    public AMap(IIntPoint offset, IDoublePoint scale, double rotation) {
        setRotation(rotation);
        setScale(scale);
        setOffset(offset);
        if (offset == null) {
            ErrorReporter.println("IIntPoint cOffset was null!", this.getClass());
            setOffset(defaultPoint);
        }

        if (scale == null) {
            ErrorReporter.println("IDoublePoint scale was null!", this.getClass());
            setScale(defaultScale);
        }
    }

    /*
    * Sets the scale for this map.
    *
    * @param scale
    *  New scale.
    *
    * @return The map scale.
    * */
    public IDoublePoint setScale(IDoublePoint scale) {
        IDoublePoint ret = this.scale;
        this.scale = scale;
        return ret;
    }

    /*
    * @return The currently set scale.
    * */
    public IDoublePoint getScale() {
        IDoublePoint ret = this.scale;
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
    public IIntPoint setOffset(IIntPoint newOffset) {
        IIntPoint ret = offset;
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
    public IIntPoint getOffset() {
        IIntPoint ret = offset;
        return ret;
    }

    /**
     * Sets the rotation of the map relative to the center of its tiles.
     *
     * @param newRotation The new Rotation for the map.
     * @return double
     * The old rotation.
     */
    public double setRotation(double newRotation) {
        double ret = this.rotation;
        this.rotation = newRotation;
        return ret;
    }

    /**
     * Gets the current rotation of the map relative to the center of its tiles.
     *
     * @return double
     * The current rotation of the map.
     */
    public double getRotation() {
        double ret = this.rotation;
        return rotation;
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
    * Sets the current Collection of MapEntities.
    *
    * @param newEntities
    *   The new Collection of Mapentities to use for this Map.
    *
    * @return The previously used Collection of MapEntities.
    *   Returns null when newEntities is null and the Collection is not changed.
    * */
    public Collection<? extends IMapEntity> setEntities(Collection<? extends IMapEntity> newEntities) {
        Collection<? extends IMapEntity> ret = mapEntities;
        if (newEntities == null) {
            ret = newEntities;
        } else {
            mapEntities = new ArrayList<IMapEntity>(newEntities);
        }
        return ret;
    }

    /*
    * @return The current Collection of MapEntities
    * */
    public Collection<IMapEntity> getEntities() {
        Collection<IMapEntity> ret = mapEntities;
        return ret;
    }

    /*
    * Adds newEntities to the current Collection of MapEntities.
    *
    * @param newEntities
    *   The Collection of MapEntities to add to this Maps Collection of MapEntities.
    * */
    public void addEntities(Collection<? extends IMapEntity> newEntities) {
        this.mapEntities.addAll(newEntities);
    }

    public void addEntity(IMapEntity newEntity) {
        this.mapEntities.add(newEntity);
    }

    public IIntDimension setPanelDimensions(IIntDimension panel) {
        IIntDimension ret = panelDimensions;
        panelDimensions = panel;
        return ret;
    }

    public IIntDimension getPanelDimensions() {
        IIntDimension ret = panelDimensions;
        return ret;
    }

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
        AffineTransform xForm = new AffineTransform();
        AffineTransform oldForm = g.getTransform();

        xForm.setToTranslation(offset.getX(), offset.getY());
        xForm.scale(scale.getX(), scale.getY());
        xForm.rotate(rotation, panelDimensions.getWidth() / 2.0, panelDimensions.getHeight());
        g.setTransform(xForm);

        background.paint(g);
        tiles.paint(g);
        for (IMapEntity r : mapEntities) {
            r.paint(g);
        }
        mask.paint(g);
        grid.paint(g);

        g.setTransform(oldForm);
    }
}
