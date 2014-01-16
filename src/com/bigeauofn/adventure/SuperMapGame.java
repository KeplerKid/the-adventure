package com.bigeauofn.adventure;

import com.bigeauofn.adventure.map.BasicMap;
import com.bigeauofn.adventure.map.ColorBackground;
import com.bigeauofn.adventure.map.IMap;
import com.bigeauofn.adventure.map.MapPanel;
import com.bigeauofn.adventure.map.geometry.DoublePoint;
import com.bigeauofn.adventure.map.geometry.IntPoint;
import com.bigeauofn.adventure.map.grid.BasicGrid;
import com.bigeauofn.adventure.map.lighting.BasicLighting;
import com.bigeauofn.adventure.map.mask.BasicMapMasker;
import com.bigeauofn.adventure.map.tile.BasicTileMap;
import com.bigeauofn.adventure.models.Power;
import com.bigeauofn.adventure.models.Weapon;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/*
* todo
* todo
* Finish ActorFileParser/Lexer so that StatFileParser can be eliminated.
* Start implementing action behavior for/with new maps.
*   Implement selection and targeting for actors.
*   Implement attacking and ability use.
*   Implement Click and drag for actors.
*   Implement multiSelect for Click and drag actors.
* Implement Grids.
* Implement Masks.
* Implement Buffered rendering so that lighting can be Applied.
* Implement Lighting.
* todo
* todo
* */

public class SuperMapGame /* implements KeyboardListener, MouseListener*/ {
    protected static final String noActorSelected = "No actor selected!", title = "The Adventure";
    protected static IMap map;

    protected static JFrame frame;
    protected static JLabel actorName, actorHP;
    protected static JButton attack;
    protected static MenuPanel menu;
    protected static JList<Weapon> weaponList;
    protected static JList<Power> abilityList;

    public SuperMapGame() {

        MapPanel mapPanel = new MapPanel();
        mapPanel.addMouseWheelListener(mapPanel);
        mapPanel.addMouseListener(mapPanel);
        mapPanel.addMouseMotionListener(mapPanel);
        mapPanel.addComponentListener(mapPanel);
        // create the world
        ColorBackground colorBackground = new ColorBackground(mapPanel);
        colorBackground.setColor(Color.DARK_GRAY);

        BasicTileMap tileMap = new BasicTileMap();

        map = new BasicMap(new IntPoint(0, 0),
                new DoublePoint(1.25, 1.25),
                0.0,
                tileMap,
                new BasicGrid(),
                colorBackground,
                new BasicLighting(),
                new BasicMapMasker(),
                BasicMap.defaultEntities);

        mapPanel.setPreferredSize(new Dimension(640, 480));
        mapPanel.setMap(map);

        // actors.addAll(map.getRenderables());

        // setup the frame
        // gameFrame.setSize(boardSideLength, boardSideLength + infoAreaHeight);

        frame = defaultJFrame();
        frame.add(mapPanel, BorderLayout.CENTER);
        // frame.addMouseListener();

        actorName = new JLabel(noActorSelected);
        actorHP = new JLabel("");
        attack = new JButton("Attack"); // create attack AbstractAction

        menu = getMenuPanel(actorName, actorHP, attack);

        weaponList = defaultWeaponList();
        abilityList = defaultAbilityList();
        // weaponList.setListData(world.getSelectedActor().getWeaponList());
        // abilityList.setListData(world.getSelectedActor().getAbilityList());

        menu.add(getJScrollPane(weaponList, 250, 80));
        menu.add(getJScrollPane(abilityList, 250, 80));

        frame.add(menu, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    protected static JFrame defaultJFrame() {
        JFrame ret = new JFrame(title);
        ret.setLayout(new BorderLayout());

        ret.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return ret;
    }

    protected static JList<Weapon> defaultWeaponList() {
        JList<Weapon> ret = new JList<>();
        ret.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ret.setLayoutOrientation(JList.VERTICAL);
        ret.setVisibleRowCount(-1);
        return ret;
    }

    protected static JList<Power> defaultAbilityList() {
        JList<Power> ret = new JList<>();
        ret.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ret.setLayoutOrientation(JList.VERTICAL);
        ret.setVisibleRowCount(-1);
        return ret;
    }

    protected static MenuPanel getMenuPanel(JLabel l1, JLabel l2, JButton button) {
        MenuPanel ret = new MenuPanel();
        ret.add(l1);
        ret.add(l2);
        ret.add(button);
        return ret;
    }

    protected static JScrollPane getJScrollPane(JList list, int pWidth, int pHeight) {
        JScrollPane ret = new JScrollPane(list);
        ret.setPreferredSize(new Dimension(pWidth, pHeight));
        return ret;
    }
}
