package com.bigeauofn.adventure.map.map_actors;

import com.bigeauofn.adventure.map.AMap;
import com.bigeauofn.adventure.models.Actor;

import java.awt.Point;

public class BasicMapActor extends AMapActor {
    protected static final String defaultFilePath = "res/avatars/Asmodeus";
    protected Actor actor;

    public BasicMapActor(Actor actor, String filePath) {
        setActor(actor);
        setAvatar(filePath);

        if (actor == null) {
            System.err.println("Actor actor was null!");
            setActor(new Actor());
        }

        if (image == null) {
            System.err.println("Unable to load image from \"" + filePath + "\"");
            setAvatar(defaultFilePath);
        }
    }

    public BasicMapActor(Actor actor) {
        this(actor, null);
    }


    public Actor setActor(Actor newActor) {
        Actor ret = actor;
        if (newActor == null) {
            ret = newActor;
        } else {
            actor = newActor;
        }
        return ret;
    }

    public Actor getActor() {
        Actor ret = actor;
        return ret;
    }

    public double setOrientation(double newOrientation) {
        return 0.0;
    }

    public double getOrientation() {
        return 0.0;
    }


    public Point setGridLocation(Point newGridLocation, AMap map) {
        return null;
    }

    public Point getGridLocation(AMap map) {
        return null;
    }

    public Point setLocation(Point newLocation) {
        return null;
    }

    public Point getLocation() {
        return null;
    }
}
