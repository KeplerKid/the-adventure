package com.bigeauofn.adventure.graphics;

import java.awt.*;
import java.awt.image.ImageObserver;

public class AwtGraphics extends AGraphics {
    protected Graphics graphics;

    public AwtGraphics(Graphics g) {
        super();
        graphics = g;
    }

    protected Point negativePoint(final Point point) {
        Point ret = new Point((int) -point.getX(), (int) -point.getY());
        return ret;
    }

    protected void internalDrawString(String str, int x, int y) {
        graphics.drawString(str, x, y);
    }

    protected boolean internalDrawImage(Image img, int x, int y, int width, int height, Color color, ImageObserver imageObserver) {
        return graphics.drawImage(img, x, y, width, height, color, imageObserver);
    }

    protected void internalDrawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    protected void internalDrawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
    }

    protected void internalDrawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    protected void internalDrawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    protected void internalDrawPoly(int[] x, int[] y, int numPts) {
        graphics.drawPolyline(x, y, numPts);
    }

    protected Rectangle internalGetClipBounds(Rectangle rectangle) {
        graphics.getClipBounds(rectangle);
        rectangle.add(negativePoint(offset));
        return rectangle;
    }
}
