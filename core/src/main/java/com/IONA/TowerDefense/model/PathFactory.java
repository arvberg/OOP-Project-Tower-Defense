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

    public static Path examplePath2() {
        return new Path(List.of(
            new Segment(new Vector2(0, 5), 3, Direction.EAST), // (2,6) -> (2,4) ner
            new Segment(new Vector2(3, 5), 2, Direction.NORTH),  // (2,4) -> (5,4) höger
            new Segment(new Vector2(3, 7), 2, Direction.EAST),
            new Segment(new Vector2(5, 7), 4, Direction.SOUTH), // (2,6) -> (2,4) ner
            new Segment(new Vector2(5, 3), 3, Direction.WEST),  // (2,4) -> (5,4) höger
            new Segment(new Vector2(2, 3), 1, Direction.SOUTH),
            new Segment(new Vector2(2, 2), 9, Direction.EAST),
            new Segment(new Vector2(11, 2), 3, Direction.NORTH),
            new Segment(new Vector2(11, 5), 2, Direction.WEST),
            new Segment(new Vector2(9, 5), 1, Direction.NORTH),
            new Segment(new Vector2(9, 6), 2, Direction.EAST),
            new Segment(new Vector2(11, 6), 1, Direction.NORTH),
            new Segment(new Vector2(11, 7), 3, Direction.WEST),
            new Segment(new Vector2(8, 7), 2, Direction.NORTH),
            new Segment(new Vector2(8, 9), 0, Direction.NORTH)


        ));
    }
}
