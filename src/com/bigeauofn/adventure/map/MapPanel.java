package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AwtGraphics;
import com.bigeauofn.adventure.map.geometry.*;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class MapPanel extends JPanel implements MouseInputListener, MouseWheelListener, ComponentListener {
    protected static final double zoomInc = 0.125;
    protected boolean lmbPressed;
    protected IIntPoint pressed;
    protected boolean rmbPressed;
    protected IMap map;

    public MapPanel() {
        pressed = new IntPoint(0, 0);
    }

    public MapPanel(IMap map) {
        this();
        setMap(map);
        if (map == null) {
            System.err.println("IMap map was null!");
            setMap(new BasicMap());
        }
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        IIntDimension temp = map.getPanelDimensions();
        temp.setSize(this.getWidth(), this.getHeight());
        map.setPanelDimensions(temp);
    }

    public void componentShown(ComponentEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        double temp = zoomInc * -e.getPreciseWheelRotation();
        // double [] coords = new double[2];
        // AffineTransform xForm = new AffineTransform();
        IDoublePoint p = map.getScale();
        // coords[0] = temp;
        // coords[1] = temp;

        // xForm.setToTranslation(-map.getOffset().getX(), -map.getOffset().getY());
        // xForm.transform(coords, 0, coords, 0, 1);

        p.setLocation(p.getX() + temp, p.getY() + temp);
        map.setScale(p);
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }


    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        IIntPoint delta;
        IIntPoint current;
        double rotation;
        double[] coords = new double[2];
        AffineTransform xForm = new AffineTransform();

        if (lmbPressed || rmbPressed) {
            delta = new IntPoint(e.getX() - pressed.getX(), e.getY() - pressed.getY());
            pressed.setLocation(e.getX(), e.getY());
            rotation = map.getRotation();

            if (rmbPressed) {
                map.setRotation(rotation + Math.toRadians(delta.getY() / 2.0));
            }

            if (lmbPressed) {
                current = map.getOffset();

                coords[0] = delta.getX();
                coords[1] = delta.getY();

                xForm.setToIdentity();
                xForm.scale(1.0 / map.getScale().getX(), 1.0 / map.getScale().getY());
                xForm.rotate(-rotation);

                xForm.transform(coords, 0, coords, 0, 1);

                current.setLocation(current.getX() - coords[0], current.getY() - coords[1]);
                map.setOffset(current);
            }

            repaint();
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            lmbPressed = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rmbPressed = true;
        }
        if (lmbPressed || rmbPressed) {
            pressed.setLocation(e.getX(), e.getY());
        }

    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            lmbPressed = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rmbPressed = false;
        }
    }

    public IMap setMap(IMap newMap) {
        IMap ret = map;
        if (newMap == null) {
            ret = newMap;
        } else {
            map = newMap;
            map.setPanelDimensions(new IntDimension(this.getWidth(), this.getHeight()));
        }
        return ret;
    }

    public IMap getMap() {
        IMap ret = map;
        return ret;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // System.out.println(g.getClass().getName());
        // sun.java2d.SunGraphics2D
        map.paint(new AwtGraphics(g));
    }
}
