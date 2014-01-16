package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.entities.ActorEntity;
import com.bigeauofn.adventure.map.entities.IMapEntity;
import com.bigeauofn.adventure.map.errors.ErrorReporter;
import com.bigeauofn.adventure.map.geometry.IDoublePoint;
import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.geometry.IntPoint;
import com.bigeauofn.adventure.map.grid.BasicGrid;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.map.lighting.BasicLighting;
import com.bigeauofn.adventure.map.lighting.ILighting;
import com.bigeauofn.adventure.map.mask.BasicMapMasker;
import com.bigeauofn.adventure.map.mask.IMapMasker;
import com.bigeauofn.adventure.map.tile.BasicTileMap;
import com.bigeauofn.adventure.map.tile.ITileMap;
import com.bigeauofn.adventure.models.Actor;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Collection;

public class BasicMap extends AMap {

    public static Collection<? extends IMapEntity> defaultEntities = Arrays.asList(
            new ActorEntity(new Actor("actors/Tikquor_full.txt"), "res/avatars/Tiamat.png", new IntPoint(64, 64), true, (float) (Math.PI / 4.0), 0.5f),
            new ActorEntity(new Actor("actors/Gobby.txt"), "res/avatars/Vecna.png", new IntPoint(128, 128), true, (float) Math.PI, 0.5f));

    public BasicMap() {
        this(defaultPoint, defaultScale, 0.0, new BasicTileMap(), new BasicGrid(), new ColorBackground(), new BasicLighting(), new BasicMapMasker(), defaultEntities);
    }

    public BasicMap(IIntPoint offset, IDoublePoint scale, double rotation, ITileMap tileMap, IGrid grid, IBackground background, ILighting lights, IMapMasker mask, Collection<? extends IMapEntity> entities) {
        super(offset, scale, rotation);
        setTileMap(tileMap);
        setGrid(grid);
        setBackground(background);
        setLighting(lights);
        setMasker(mask);
        setEntities(entities);

        if (tileMap == null) {
            ErrorReporter.println("TileMap tileMap was null!", this.getClass());
            setTileMap(new BasicTileMap());
        }
        if (grid == null) {
            ErrorReporter.println("IGrid grid was null!", this.getClass());
            setGrid(new BasicGrid());
        }
        if (background == null) {
            ErrorReporter.println("IBackground background was null!", this.getClass());
            setBackground(new ColorBackground());
        }
        if (lighting == null) {
            ErrorReporter.println("ILighting lights was null!", this.getClass());
            setLighting(new BasicLighting());
        }
        if (mask == null) {
            ErrorReporter.println("IMapMasker mask was null!", this.getClass());
            setMasker(new BasicMapMasker());
        }
        if (mapEntities == null) {
            ErrorReporter.println("Collection<? extends IMapEntity> entities was null!", this.getClass());
            setEntities(defaultEntities);
        }

    }

    public void paint(AGraphics g) {
        // Apply {rotate, scale, offset} transform to graphics objects so that children use them.
        // No fudge needed!
        AffineTransform xForm = new AffineTransform();
        AffineTransform oldForm = g.getTransform();

        xForm.setToIdentity();
        xForm.translate(panelDimensions.getWidth() / 2.0, panelDimensions.getHeight() / 2.0);
        xForm.rotate(rotation);
        xForm.scale(scale.getX(), scale.getY());
        xForm.translate(-offset.getX() - panelDimensions.getWidth() / 2.0, -offset.getY() - panelDimensions.getHeight() / 2.0);

        /*
        xForm.setToTranslation(offset.getX(), offset.getY());
        xForm.scale(scale.getX(), scale.getY());
        xForm.rotate(rotation, panelDimensions.getWidth() / 2.0, panelDimensions.getHeight() / 2.0);
        */

        // System.out.println();
        g.setTransform(xForm);

        if (background != null) {
            background.paint(g);
        }
        if (tiles != null) {
            tiles.paint(g);
        }
        if (mapEntities != null) {
            for (IMapEntity e : mapEntities) {
                if (e != null) {
                    e.paint(g);
                }
            }
        }
        if (grid != null) {
            grid.paint(g);
        }

        g.setTransform(oldForm);

        Color old = g.getColor();
        g.setColor(Color.CYAN);
        g.drawLine(offset.getX(), offset.getY(), offset.getX(), offset.getY());
        g.setColor(Color.GREEN);
        g.drawLine(panelDimensions.getWidth() / 2, panelDimensions.getHeight() / 2, panelDimensions.getWidth() / 2, panelDimensions.getHeight() / 2);
        g.setColor(old);
    }
}
