package com.bigeauofn.adventure.graphics;

import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.map.geometry.IIntRectangle;
import com.bigeauofn.adventure.map.geometry.IIntShape;

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

public interface IGraphics {
    /*  java.awt.Graphics2D  */
    public void clip(IIntShape shape);

    public void draw(IIntShape shape);

    public void draw3DRect(int x, int y, int width, int height, boolean raised);

    public void drawGlyphVector(GlyphVector g, float x, float y);

    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y);

    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs);

    public void drawRenderableImage(RenderableImage img, AffineTransform xform);

    public void drawRenderedImage(RenderedImage img, AffineTransform xform);

    public void drawString(AttributedCharacterIterator iterator, float x, float y);

    public void drawString(String str, float x, float y);

    public void drawString(String str, int x, int y);

    public void fill(IIntShape s);

    public void fill3DRect(int x, int y, int width, int height, boolean raised);

    public Color getBackground();

    public Composite getComposite();

    public GraphicsConfiguration getDeviceConfiguration();

    public FontRenderContext getFontRenderContext();

    public Paint getPaint();

    public Object getRenderingHint(RenderingHints.Key hintKey);

    public RenderingHints getRenderingHints();

    public Stroke getStroke();

    public AffineTransform getTransform();

    public boolean hit(IIntRectangle rect, IIntShape shape, boolean onStroke);

    public void rotate(double theta);

    public void rotate(double theta, double x, double y);

    public void scale(double sx, double sy);

    public Color setBackground(Color color);

    public void setComposite(Composite comp);

    public void setPaint(Paint paint);

    public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue);

    public void setRenderingHints(Map<?, ?> hints);

    public void setStroke(Stroke s);

    public AffineTransform setTransform(AffineTransform Tx);

    public void translate(double dx, double dy);

    public void translate(int x, int y);

    /*  java.awt.Graphics  */

    public boolean drawImage(Image img, int x, int y, int width, int height, Color color, ImageObserver imageObserver);

    public boolean drawImage(Image img, int x, int y, ImageObserver imageObserver);

    public boolean drawImage(Image img, int x, int y, Color color, ImageObserver imageObserver);

    public void drawLine(IIntPoint p1, IIntPoint p2);

    public void drawLine(int x1, int y1, int x2, int y2);

    public void drawOval(int x, int y, int width, int height);

    public void drawPolyline(int[] xIIntPoints, int[] yIIntPoints, int nIIntPoints);

    public void drawRect(int x, int y, int width, int height);

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

    public Rectangle getClipBounds(Rectangle r);

    public Rectangle getClipBounds();

    public Color setColor(Color c);

    public Color getColor();

    // public void set

    public void applyOffset(IIntPoint newOffset);

    public IIntPoint popLastOffset();
}
