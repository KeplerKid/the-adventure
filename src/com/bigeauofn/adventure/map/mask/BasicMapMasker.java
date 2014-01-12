package com.bigeauofn.adventure.map.mask;

import com.bigeauofn.adventure.graphics.AGraphics;

public class BasicMapMasker implements IMapMasker {
    public BasicMapMasker() {
    }

    public void paint(AGraphics g) {
        // todo painting the masker.
        System.out.println("Pretending to paint a " + this.getClass().getName() + "!");
    }
}
