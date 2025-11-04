package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface Sprite {
    ArrayList<Sprite> getSprites();

    /*
    TODO: I excluded this as I don't know how the graphics library works yet. We'll figure that out later.
    ArrayList<BufferedImage> Sprites = new ArrayList();

    default void addSprites(List<BufferedImage> images){ Sprites.addAll(images); }
    default void addImage(BufferedImage, Image, int width, int height){ Sprites.add(image); }

     */
}
