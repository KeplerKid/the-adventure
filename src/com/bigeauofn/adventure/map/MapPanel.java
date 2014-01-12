package com.bigeauofn.adventure.map;

import com.bigeauofn.adventure.graphics.AwtGraphics;

import javax.swing.JPanel;
import java.awt.Graphics;

public class MapPanel extends JPanel {
    protected IMap map;

    public MapPanel() {
    }

    public MapPanel(IMap map) {
        setMap(map);
        if (map == null) {
            System.err.println("IMap map was null!");
            setMap(new BasicMap());
        }
    }

    public IMap setMap(IMap newMap) {
        IMap ret = map;
        if (newMap == null) {
            ret = newMap;
        } else {
            map = newMap;
        }
        return ret;
    }

    public IMap getMap() {
        IMap ret = map;
        return ret;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.paint(new AwtGraphics(g));
    }
}
