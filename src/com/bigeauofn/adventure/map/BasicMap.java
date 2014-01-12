package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.grid.BasicGrid;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.map.lighting.BasicLighting;
import com.bigeauofn.adventure.map.lighting.ILighting;
import com.bigeauofn.adventure.map.map_actors.BasicMapActor;
import com.bigeauofn.adventure.map.mask.BasicMapMasker;
import com.bigeauofn.adventure.map.mask.IMapMasker;
import com.bigeauofn.adventure.map.tile.BasicTileMap;
import com.bigeauofn.adventure.map.tile.ITileMap;
import com.bigeauofn.adventure.models.Actor;

import java.util.Arrays;
import java.util.Collection;

public class BasicMap extends AMap {
    public static final Collection<? extends IRenderable> defaultRenderables = Arrays.asList(
            new BasicMapActor(new Actor("actors/Tikquor_full.txt"), "res/avatars/Tiamat.png"),
            new BasicMapActor(new Actor("actors/Gobby.txt"), "res/avatars/Vecna.png"));

    public BasicMap() {
        this(new BasicTileMap(), new BasicGrid(), new ColorBackground(), new BasicLighting(), new BasicMapMasker(), defaultRenderables);
    }

    public BasicMap(ITileMap tileMap, IGrid grid, IBackground background, ILighting lights, IMapMasker mask, Collection<? extends IRenderable> renderables) {
        super();
        setTileMap(tileMap);
        setGrid(grid);
        setBackground(background);
        setLighting(lights);
        setMasker(mask);
        setRenderables(renderables);

        if (tileMap == null) {
            System.err.println("TileMap tileMap was null!");
            setTileMap(new BasicTileMap());
        }
        if (grid == null) {
            System.err.println("IGrid grid was null!");
            setGrid(new BasicGrid());
        }
        if (background == null) {
            System.err.println("IBackground background was null!");
            setBackground(new ColorBackground());
        }
        if (lighting == null) {
            System.err.println("ILighting lights was null!");
            setLighting(new BasicLighting());
        }
        if (mask == null) {
            System.err.println("IMapMasker mask was null!");
            setMasker(new BasicMapMasker());
        }
        if (renderable == null) {
            System.err.println("Collection<? extends IRenderable> renderables was null!");
            setRenderables(defaultRenderables);
        }

    }

    public void paint(AGraphics g) {
        // Apply offset to graphics object
        // No fudge needed!
        g.applyOffset(offset);

        if (background != null) {
            background.paint(g);
        }
        if (tiles != null) {
            tiles.paint(g);
        }
        if (renderable != null) {
            for (IRenderable r : renderable) {
                if (r != null) {
                    continue;
                }
                // r.paint();
            }
        }
        if (grid != null) {
            grid.paint(g);
        }

        // Don't forget to remove offset so future users don't have any issues!
        g.popLastOffset();
    }
}
