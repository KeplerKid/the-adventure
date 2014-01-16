package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.entities.IMapEntity;
import com.bigeauofn.adventure.map.geometry.IDoublePoint;
import com.bigeauofn.adventure.map.geometry.IIntDimension;
import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.map.tile.ITileMap;

import java.util.Collection;

/*
* Handles higher level operations such as painting the background
*   and maintaining displacement for rendering
* */
public interface IMap extends IRenderable {
    /*
    * Sets the scale for this map.
    *
    * @param scale
    *  New scale.
    *
    * @return The scale.
    * */
    public IDoublePoint setScale(IDoublePoint scale);

    /*
    * @return The currently set scale.
    * */
    public IDoublePoint getScale();

    /*
    * Sets the displacement from {0, 0} in the parent window to the upper
    *   left-hand corner of the rectangle containing the ITilesMap for this IMap.
    *
    * @param newIIntPoint
    *   The new displacement from the origin of a parent window.
    *
    * @return The old displacement for this IMap.
    * */
    public IIntPoint setOffset(IIntPoint newIIntPoint);

    /*
    * Gets the offset from {0, 0} in the parent window to the upper
    *   left-hand corner of the rectangle containing the ITilesMap for this IMap.
    *
    * @return The current displacement for this IMap.
    * */
    public IIntPoint getOffset();

    /**
     * Sets the rotation of the map.
     *
     * @param rotation The new rotation for the map.
     * @return double
     * The old rotation for the map.
     */
    public double setRotation(double rotation);

    /**
     * Gets the rotation of this map.
     *
     * @return double
     * The rotation for this map.
     */
    public double getRotation();

    /*
    * Sets the IBackground this map will use.
    *
    * @param newBackground
    *   IBackground to be used by this map.
    *
    * @return The previously used IBackground for this map.
    * */
    public IBackground setBackground(IBackground newBackground);

    /*
    * Gets the currently used IBackground for this IMap.
    *
    * @return The currently used IBackground for this map.
    * */
    public IBackground getBackground();

    /*
    * Sets the ITileMap object to be used by the map.
    *
    * @param newTiles
    *
    * @return The ITileMap object previously used by the Map.
    * */
    public ITileMap setTileMap(ITileMap newTiles);

    /*
    * @return The ITileMap object to be rendered by the map.
    * */
    public ITileMap getTileMap();

    /*
    * Sets the IGrid object to be used by this IMap.
    *
    * @param newGrid
    *   The new IGrid for this IMap to use.
    *
    * @return The IGrid this IMap previously used.
    * */
    public IGrid setGrid(IGrid newGrid);

    /*
    * Gets the IGrid object currently used by this IMap.
    *
    * @return The IGrid used by this IMap.
    * */
    public IGrid getGrid();

    /*
    * Re-initialises the  the MapEntity Collection used by this IMap and adds all the Elements of newEntityList.
    *
    * @param newEntityList
    *   The new IMapEntity Collection for this IMap to use.
    *
    * @return The old IMapEntity Collection previously used by this IMap.
    * */
    public Collection<? extends IMapEntity> setEntities(Collection<? extends IMapEntity> newEntityList);

    /*
    * Gets the MapEntity Collection to be used by this IMap.
    *
    * @return The MapEntity Collection to be used by this IMap.
    * */
    public Collection<? extends IMapEntity> getEntities();


    /*
    * Adds the MapEntity Collection to the current MapEntity Collection used by this IMap.
    *
    * @param newEntities
    *   The MapEntity Collection to add to the current Collection.
    * */
    public void addEntities(Collection<? extends IMapEntity> newEntities);

    /*
    * Adds the single MapEntity to the current MapEntity Collection used by this IMap.
    *
    * @param newEntity
    *   The MapEntity to be added to the current Collection of MapEntities.
    * */
    public void addEntity(IMapEntity newEntity);

    public IIntDimension setPanelDimensions(IIntDimension panel);

    public IIntDimension getPanelDimensions();

    /*
    * Base paint function for the map.
    *   Will be overloaded based on graphics plugin in an implementing class.
    * */
    public void paint(AGraphics g);
}
