package com.bigeauofn.adventure.map.entities;

import com.bigeauofn.adventure.map.geometry.IIntPoint;

public interface IClickable {
    public boolean isClicked(int x, int y);

    public boolean isClicked(IIntPoint click);
}
