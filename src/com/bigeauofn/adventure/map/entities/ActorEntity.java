package com.bigeauofn.adventure.map.entities;

import com.bigeauofn.adventure.map.geometry.IIntPoint;
import com.bigeauofn.adventure.models.Actor;
import com.bigeauofn.adventure.utilities.ImageUtility;

import java.awt.image.BufferedImage;

public class ActorEntity extends AMapEntity {
    protected Actor actor;

    public ActorEntity(Actor actor, BufferedImage image, IIntPoint location, boolean render, float rotation, float depth) {
        super(image, location, render, rotation, depth);
        setActor(actor);

        if (this.actor == null) {
            System.err.println("Actor actor is null!");
            setActor(new Actor());
        }
    }

    public ActorEntity(Actor actor, String avatarFilePath, IIntPoint location, boolean render, float rotation, float depth) {
        this(actor, ImageUtility.loadImage(avatarFilePath), location, render, rotation, depth);
    }

    public ActorEntity(Actor actor, BufferedImage image) {
        this(actor, image, defaultLocation, defaultRender, defaultRotation, defaultDepth);
    }

    public ActorEntity(Actor actor, String filePath) {
        this(actor, filePath, defaultLocation, defaultRender, defaultRotation, defaultDepth);
    }

    public ActorEntity(Actor actor) {
        this(actor, defaultFilePath, defaultLocation, defaultRender, defaultRotation, defaultDepth);
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
}
