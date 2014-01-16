package com.bigeauofn.adventure.graphics;

import com.bigeauofn.adventure.map.errors.ErrorReporter;
import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.geometry.IIntRectangle;
import com.bigeauofn.adventure.map.geometry.IIntShape;
import com.bigeauofn.adventure.map.geometry.IntPoint;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class AwtGraphics extends AGraphics {
    protected Graphics2D graphics;

    public AwtGraphics(Graphics g) {
        super();
        if (g instanceof Graphics2D) {
            graphics = (Graphics2D) g;
        }
    }

    /*  java.awt.Graphics2D  */
    public void clip(IIntShape shape) {
        ErrorReporter.println("Clip un-implemented", this.getClass());
        // Un-implemented; Must convert to Shape.
        // graphics.clip(shape);
        return;
    }

    public void draw(IIntShape shape) {
        ErrorReporter.println("Draw un-implemented", this.getClass());
        // Un-Implemented; Must convert to Shape.
        // graphics.draw(shape);
        return;
    }

    public void draw3DRect(int x, int y, int width, int height, boolean raised) {
        graphics.draw3DRect(x, y, width, height, raised);
    }

    public void drawGlyphVector(GlyphVector g, float x, float y) {
        graphics.drawGlyphVector(g, x, y);
    }

    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        graphics.drawImage(img, op, x, y);
    }

    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        return graphics.drawImage(img, xform, obs);
    }

    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        graphics.drawRenderableImage(img, xform);
    }

    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        graphics.drawRenderedImage(img, xform);
    }

    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        graphics.drawString(iterator, x, y);
    }

    public void drawString(String str, float x, float y) {
        graphics.drawString(str, x, y);
    }

    public void drawString(String str, int x, int y) {
        graphics.drawString(str, x, y);
    }

    public void fill(IIntShape s) {
        ErrorReporter.println("Fill un-implemented", this.getClass());
        // Un-Implemented; Must convert to Shape;
        // graphics.fill(s);
        return;
    }

    public void fill3DRect(int x, int y, int width, int height, boolean raised) {
        graphics.fill3DRect(x, y, width, height, raised);
    }

    public Color getBackground() {
        return graphics.getBackground();
    }

    public Composite getComposite() {
        return graphics.getComposite();
    }

    public GraphicsConfiguration getDeviceConfiguration() {
        return graphics.getDeviceConfiguration();
    }

    public FontRenderContext getFontRenderContext() {
        return graphics.getFontRenderContext();
    }

    public Paint getPaint() {
        return graphics.getPaint();
    }

    public Object getRenderingHint(RenderingHints.Key hintKey) {
        return graphics.getRenderingHint(hintKey);
    }

    public RenderingHints getRenderingHints() {
        return graphics.getRenderingHints();
    }

    public Stroke getStroke() {
        return graphics.getStroke();
    }

    public AffineTransform getTransform() {
        return graphics.getTransform();
    }

    public boolean hit(IIntRectangle rect, IIntShape shape, boolean onStroke) {
        ErrorReporter.println("hit un-implemented", this.getClass());
        // return graphics.hit(rect, shape, onStroke);
        return false;
    }

    public void rotate(double theta) {
        graphics.rotate(theta);
    }

    public void rotate(double theta, double x, double y) {
        graphics.rotate(theta, x, y);
    }

    public void scale(double sx, double sy) {
        graphics.scale(sx, sy);
    }

    public Color setBackground(Color color) {
        Color ret = graphics.getBackground();
        graphics.setBackground(color);
        return ret;
    }

    public void setComposite(Composite comp) {
        graphics.setComposite(comp);
    }

    public void setPaint(Paint paint) {
        graphics.setPaint(paint);
    }

    public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
        graphics.setRenderingHint(hintKey, hintValue);
    }

    public void setRenderingHints(Map<?, ?> hints) {
        graphics.setRenderingHints(hints);
    }

    public void setStroke(Stroke s) {
        graphics.setStroke(s);
    }

    public AffineTransform setTransform(AffineTransform tx) {
        AffineTransform ret = graphics.getTransform();
        graphics.setTransform(tx);
        return ret;
    }

    public void translate(double dx, double dy) {
        graphics.translate(dx, dy);
    }

    public void translate(int x, int y) {
        graphics.translate(x, y);
    }


    protected IIntPoint negativePoint(final IIntPoint intPoint) {
        IIntPoint ret = new IntPoint(-intPoint.getX(), -intPoint.getY());
        return ret;
    }


    /*  java.awt.Graphics  */
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
        return rectangle;
    }

    public void setSomething() {
    }

    protected void internalSetColor(Color c) {
        graphics.setColor(c);
    }

    public Color getColor() {
        return graphics.getColor();
    }
}
