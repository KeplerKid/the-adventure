package com.bigeauofn.adventure.graphics;

import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.geometry.IntPoint;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Stack;

public abstract class AGraphics implements IGraphics {
    protected IIntPoint offset;
    protected Stack<IIntPoint> offsets;

    public AGraphics() {
        this.offset = new IntPoint(0, 0);
        this.offsets = new Stack<>();
    }

    protected int offX(int x) {
        return x + offset.getX();
    }

    protected int offY(int y) {
        return y + offset.getY();
    }

    protected int deOffX(int x) {
        return x - offset.getX();
    }

    protected int deOffY(int y) {
        return y - offset.getY();
    }

    protected abstract void internalDrawString(String str, int x, int y);

    public void drawString(String str, int x, int y) {
        internalDrawString(str, offX(x), offY(y));
    }

    protected abstract boolean internalDrawImage(Image img, int x, int y, int width, int height, Color color, ImageObserver imageObserver);

    public boolean drawImage(Image img, int x, int y, int width, int height, Color color, ImageObserver imageObserver) {
        return internalDrawImage(img, offX(x), offY(y), width, height, color, imageObserver);
    }

    public boolean drawImage(Image img, int x, int y, ImageObserver imageObserver) {
        return drawImage(img, x, y, img.getWidth(imageObserver), img.getHeight(imageObserver), null, imageObserver);
    }

    public boolean drawImage(Image img, int x, int y, Color color, ImageObserver imageObserver) {
        return drawImage(img, x, y, img.getWidth(imageObserver), img.getHeight(imageObserver), color, imageObserver);
    }

    protected abstract void internalDrawLine(int x, int y, int z, int a);

    public void drawLine(IIntPoint p1, IIntPoint p2) {
        drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        internalDrawLine(offX(x1), offY(y1), offX(x2), offY(y2));
    }

    protected abstract void internalDrawOval(int x, int y, int width, int height);

    public void drawOval(int x, int y, int width, int height) {
        internalDrawOval(offX(x), offY(y), width, height);
    }

    protected abstract void internalDrawPoly(int[] x, int[] y, int nIIntPoints);

    public void drawPolyline(int[] xIIntPoints, int[] yIIntPoints, int nIIntPoints) {
        if (xIIntPoints == null || yIIntPoints == null) {
            System.err.println("Null IIntPoint arrays passed to Graphics.drawPolyline");
            return;
        }
        if (!(xIIntPoints.length >= nIIntPoints)) {
            nIIntPoints = xIIntPoints.length;
        }
        if (!(yIIntPoints.length >= nIIntPoints)) {
            nIIntPoints = yIIntPoints.length;
        }
        for (int i = 0; i < nIIntPoints; i++) {
            xIIntPoints[i] = offX(xIIntPoints[i]);
            yIIntPoints[i] = offY(yIIntPoints[i]);
        }
        internalDrawPoly(xIIntPoints, yIIntPoints, nIIntPoints);
    }

    protected abstract void internalDrawRect(int x, int y, int width, int height);

    public void drawRect(int x, int y, int width, int height) {
        internalDrawRect(offX(x), offY(y), width, height);
    }

    protected abstract void internalDrawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        internalDrawRoundRect(offX(x), offY(y), width, height, arcWidth, arcHeight);
    }

    protected abstract Rectangle internalGetClipBounds(Rectangle rectangle);

    public Rectangle getClipBounds(Rectangle r) {
        Rectangle ret = internalGetClipBounds(r);
        ret.setLocation(deOffX((int) ret.getX()), deOffY((int) ret.getY()));
        return ret;
    }

    public Rectangle getClipBounds() {
        return getClipBounds(new Rectangle());
    }

    protected abstract void internalSetColor(Color c);

    public Color setColor(Color c) {
        Color ret = getColor();
        internalSetColor(c);
        return ret;
    }

    public void applyOffset(IIntPoint newOffset) {
        offset.setLocation(offset.getX() + newOffset.getX(), offset.getY() + newOffset.getY());
        offsets.push(newOffset);
    }

    public IIntPoint popLastOffset() {
        IIntPoint remove = offsets.pop();
        offset.setLocation(offset.getX() - remove.getX(), offset.getY() - remove.getY());
        return remove;
    }
}
