package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AGraphics;

import javax.swing.JPanel;
import java.awt.Color;

public class ColorBackground implements IBackground {
    protected JPanel panel;

    public ColorBackground() {
        // default ColorBackground;
        this(new JPanel());
    }

    public ColorBackground(JPanel p) {
        setPanel(p);
        if (panel == null) {
            System.err.println("JPanel p was null!");
            setPanel(new JPanel());
        }
    }

    public Color setColor(Color c) {
        Color ret = panel.getBackground();
        panel.setBackground(c);
        return ret;
    }

    public Color getColor() {
        Color ret = panel.getBackground();
        return ret;
    }

    public JPanel setPanel(JPanel p) {
        JPanel ret = panel;
        if (p == null) {
            ret = p;
        } else {
            panel = p;
        }
        return ret;
    }

    public JPanel getPanel() {
        JPanel ret = panel;
        return ret;
    }

    public void paint(AGraphics g) {
    }
}
