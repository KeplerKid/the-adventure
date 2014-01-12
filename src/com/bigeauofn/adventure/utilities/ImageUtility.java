package com.bigeauofn.adventure.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtility {

    public static BufferedImage loadImage(String path) {

        BufferedImage im = null;
        try {
            im = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error: Could not load image from: " + path);
        }
        return im;

    }
}
