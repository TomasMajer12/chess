package cz.cvut.fel.pjv.gui;

import javafx.scene.image.Image;

public class Utils {

    public static Image loadImage(String resource, int width, int height) {
        return new Image(Utils.class.getClassLoader().getResourceAsStream(resource));
    }
}
