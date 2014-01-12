package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.map.tile.ITileMap;

import java.awt.Point;
import java.util.Collection;

/*
* Handles higher level operations such as painting the background
*   and maintaining displacement for rendering
* */
public interface IMap extends IRenderable {
    /*
    * Sets the zoom level for the map.
    *
    * @param newZoom
    *  New zoom level.
    *
    * @return The old zoom level.
    * */
    public double setZoomLevel(double newZoom);

    /*
    * @return The currently set zoom level.
    * */
    public double getZoomLevel();

    /*
    * Sets the displacement from {0, 0} in the parent window to the upper
    *   left-hand corner of the rectangle containing the ITilesMap for this IMap.
    *
    * @param newPoint
    *   The new displacement from the origin of a parent window.
    *
    * @return The old displacement for this IMap.
    * */
    public Point setOffset(Point newPoint);

    /*
    * Gets the offset from {0, 0} in the parent window to the upper
    *   left-hand corner of the rectangle containing the ITilesMap for this IMap.
    *
    * @return The current displacement for this IMap.
    * */
    public Point getOffset();

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
    * Sets the IRenderable Collection to be used by this IMap.
    *
    * @param newContainer
    *   The new IRenderable Collection for this IMap to use.
    *
    * @return The old IRenderable Collection previously used by this IMap.
    * */
    public Collection<? extends IRenderable> setRenderables(Collection<? extends IRenderable> newRenderableList);

    /*
    * Gets the IRenderable Collection to be used by this IMap.
    *
    * @return The IRenderable Collection to be used by this IMap.
    * */
    public Collection<? extends IRenderable> getRenderables();

    /*
   * Base paint function for the map.
   *   Will be overloaded based on graphics plugin in an implementing class.
   * */
    public void paint(AGraphics g);
}
