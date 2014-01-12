package com.bigeauofn.adventure;

import com.bigeauofn.adventure.map.BasicMap;
import com.bigeauofn.adventure.map.ColorBackground;
import com.bigeauofn.adventure.map.IMap;
import com.bigeauofn.adventure.map.MapPanel;
import com.bigeauofn.adventure.map.grid.BasicGrid;
import com.bigeauofn.adventure.map.lighting.BasicLighting;
import com.bigeauofn.adventure.map.mask.BasicMapMasker;
import com.bigeauofn.adventure.map.tile.BasicTileMap;
import com.bigeauofn.adventure.models.Actor;
import com.bigeauofn.adventure.models.Power;
import com.bigeauofn.adventure.models.Weapon;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

/*
* // todo
* todo
* Get Actors to render in SuperMapGame.
* Finish ActorFileParser/Lexer so that StatFileParser can be eliminated.
* Fix Actor ctor output stream! GAHHHHH! So annoying.
* Start implementing action behavior for with new maps.
*   Implement selection and targeting for actors.
*   Implement attacking and ability use.
*   Implement Click and drag for actors.
*   Implement multiSelect for Click and drag actors.
*   Implement map movement.
* Test that offset things work when the map is moved around.
* Test that actors work with offsets when maps is moved around.
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
    protected static ArrayList<Actor> actors;
    protected static JFrame frame;
    protected static JLabel actorName, actorHP;
    protected static JButton attack;
    protected static MenuPanel menu;
    protected static JList<Weapon> weaponList;
    protected static JList<Power> abilityList;

    public SuperMapGame() {
        MapPanel mapPanel = new MapPanel();
        // create the world
        map = new BasicMap(new BasicTileMap(),
                new BasicGrid(),
                new ColorBackground(mapPanel),
                new BasicLighting(),
                new BasicMapMasker(),
                BasicMap.defaultRenderables);
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
