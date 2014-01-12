package com.bigeauofn.adventure.graphics;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Stack;

public abstract class AGraphics {
    protected Point offset;
    protected Stack<Point> offsets;

    public AGraphics() {
        this.offset = new Point(0, 0);
        this.offsets = new Stack<>();
    }

    protected int offX(int x) {
        return x + (int) offset.getX();
    }

    protected int offY(int y) {
        return y + (int) offset.getY();
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
        return drawImage(img, x, y, img.getWidth(imageObserver), img.getHeight(imageObserver), Color.WHITE, imageObserver);
    }

    public boolean drawImage(Image img, int x, int y, Color color, ImageObserver imageObserver) {
        return drawImage(img, x, y, img.getWidth(imageObserver), img.getHeight(imageObserver), color, imageObserver);
    }

    protected abstract void internalDrawLine(int x, int y, int z, int a);

    public void drawLine(Point p1, Point p2) {
        drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        internalDrawLine(offX(x1), offY(y1), offX(x2), offY(y2));
    }

    protected abstract void internalDrawOval(int x, int y, int width, int height);

    public void drawOval(int x, int y, int width, int height) {
        internalDrawOval(offX(x), offY(y), width, height);
    }

    protected abstract void internalDrawPoly(int[] x, int[] y, int nPoints);

    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        if (xPoints == null || yPoints == null) {
            System.err.println("Null point arrays passed to Graphics.drawPolyline");
            return;
        }
        if (!(xPoints.length >= nPoints)) {
            nPoints = xPoints.length;
        }
        if (!(yPoints.length >= nPoints)) {
            nPoints = yPoints.length;
        }
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = offX(xPoints[i]);
            yPoints[i] = offY(yPoints[i]);
        }
        internalDrawPoly(xPoints, yPoints, nPoints);
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
        return internalGetClipBounds(r);
    }

    public Rectangle getClipBounds() {
        return getClipBounds(new Rectangle());
    }

    public void applyOffset(Point newOffset) {
        offset.setLocation((int) (offset.getX() + newOffset.getX()),
                (int) (offset.getY() + newOffset.getY()));
        offsets.push(newOffset);
    }

    public Point popLastOffset() {
        Point remove = offsets.pop();
        offset.setLocation((int) (offset.getX() - remove.getX()),
                (int) (offset.getY()) - remove.getY());
        return remove;
    }
}
