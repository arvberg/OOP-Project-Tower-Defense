package com.IONA.TowerDefense.model;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.List;
import java.util.Vector;

public class PathFactory {

    public static Path examplePath1() {
        return new Path(List.of(
            new Segment(new Vector2(4, 9), 4, Direction.SOUTH), // (2,6) -> (2,4) ner
            new Segment(new Vector2(4, 5), 6, Direction.EAST),  // (2,4) -> (5,4) höger
            new Segment(new Vector2(10, 5), 6, Direction.SOUTH)  // (5,4) -> (5,-2) ner
        ));
    }
}
