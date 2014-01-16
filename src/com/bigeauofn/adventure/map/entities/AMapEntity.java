package com.bigeauofn.adventure.map.entities;

import com.bigeauofn.adventure.graphics.AGraphics;
import com.bigeauofn.adventure.map.errors.ErrorReporter;
import com.bigeauofn.adventure.map.geometry.*;
import com.bigeauofn.adventure.map.grid.IGrid;
import com.bigeauofn.adventure.utilities.ImageUtility;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class AMapEntity implements IMapEntity {
    protected static final String defaultFilePath = "res/avatars/Asmodeus.png";
    protected static final boolean defaultRender = true;
    protected static final float defaultRotation = 0.0f;
    protected static final float defaultDepth = 0.5f;
    protected static final IIntPoint defaultLocation = new IntPoint(0, 0);
    protected static final IntRectangle defaultRectangle = new IntRectangle(new IntPoint(0, 0), new IntDimension(64, 64), 0.0f);

    protected boolean render;
    // protected boolean renderRect;
    protected BufferedImage image;
    protected float zDepth;
    protected IntRectangle rectangle;


    public AMapEntity(BufferedImage image, IIntPoint location, boolean render, float rotation, float depth) {
        rectangle = new IntRectangle();
        setAvatar(image);
        if (this.image == null) {
            ErrorReporter.println("BufferedImage image was null!", this.getClass());
            setAvatar(defaultFilePath);
        }
        setLocation(location);
        setRender(render);
        setRotation(rotation);
        setDepth(depth);
    }

    public AMapEntity(String avatarFilePath, IIntPoint location, boolean render, float rotation, float depth) {
        this(ImageUtility.loadImage(avatarFilePath), location, render, rotation, depth);
    }

    public AMapEntity(BufferedImage image, IIntPoint location) {
        this(image, location, defaultRender, defaultRotation, defaultDepth);
    }

    public AMapEntity(String avatarFilePath, IIntPoint location) {
        this(avatarFilePath, location, defaultRender, defaultRotation, defaultDepth);
    }

    public AMapEntity(BufferedImage image) {
        this(image, defaultLocation, defaultRender, defaultRotation, defaultDepth);
    }

    public AMapEntity(String avatarFilePath) {
        this(avatarFilePath, defaultLocation, defaultRender, defaultRotation, defaultDepth);
    }

    public BufferedImage setAvatar(String filePath) {
        return setAvatar(ImageUtility.loadImage(filePath));
    }

    public BufferedImage setAvatar(BufferedImage newBufferedImage) {
        BufferedImage ret = image;
        if (newBufferedImage == null) {
            ret = newBufferedImage;
        } else {
            image = newBufferedImage;
            rectangle.setSize(image.getWidth(), image.getHeight());
        }
        return ret;
    }

    public BufferedImage getAvatar() {
        BufferedImage ret = image;
        return ret;
    }

    public float setRotation(float newRotation) {
        float ret = rectangle.getRotation();
        rectangle.setRotation(newRotation);
        return ret;
    }

    public float getRotation() {
        float ret = rectangle.getRotation();
        return ret;
    }

    public IIntDimension setDimensions(IIntDimension dimension) {
        IIntDimension ret = new IntDimension(rectangle.getWidth(), rectangle.getHeight());
        rectangle.setSize(dimension);
        return ret;
    }


    public IIntPoint setGridLocation(IIntPoint newGridLocation, IGrid grid) {
        IIntPoint ret = rectangle.getLocation();
        if (newGridLocation == null) {
            ret = newGridLocation;
        } else {
            // location = map.translateToWorldCoordinates(newGridCoordinates);
        }
        return ret;
    }

    public IIntPoint getGridLocation(IGrid grid) {
        // IntIIntPoint ret = map.translateToGridCoordinates(location);
        return null;
    }

    public IIntPoint setLocation(IIntPoint newLocation) {
        IIntPoint ret = rectangle.getLocation();
        if (newLocation == null) {
            ret = newLocation;
        } else {
            rectangle.setLocation(newLocation);
        }
        return ret;
    }

    public IIntPoint getLocation() {
        IIntPoint ret = rectangle.getLocation();
        return ret;
    }

    public float setDepth(float newDepth) {
        float ret = zDepth;
        zDepth = newDepth;
        return ret;
    }

    public float getDepth() {
        float ret = zDepth;
        return ret;
    }

    public boolean setRender(boolean newRender) {
        boolean ret = render;
        render = newRender;
        return ret;
    }

    public boolean getRender() {
        boolean ret = render;
        return ret;
    }

    public boolean isClicked(int x, int y) {
        return rectangle.contains(x, y);
    }

    public boolean isClicked(IIntPoint click) {
        return rectangle.contains(click);
    }

    public int compareTo(IMapEntity mapEntity) {
        int ret;
        if (this.getDepth() < mapEntity.getDepth()) {
            ret = -1;
        } else if (this.getDepth() == mapEntity.getDepth()) {
            ret = 0;
        } else {
            ret = 1;
        }
        return ret;
    }

    protected static AffineTransform xForm = new AffineTransform();

    public void paint(AGraphics g) {
        if (render) {
            xForm.setToTranslation(rectangle.getX(), rectangle.getY());
            xForm.rotate(rectangle.getRotation(), image.getWidth() / 2.0, image.getHeight() / 2.0);
            // xForm.concatenate(g.getTransform());
            g.drawImage(image, xForm, null);
        }
    }
}
