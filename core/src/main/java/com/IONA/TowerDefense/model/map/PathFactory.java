package com.IONA.TowerDefense.model.map;

import com.IONA.TowerDefense.model.Direction;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

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
            new Segment(new Vector2(3, 5), 3, Direction.NORTH),  // (2,4) -> (5,4) höger
            new Segment(new Vector2(3, 8), 2, Direction.EAST),
            new Segment(new Vector2(5, 8), 4, Direction.SOUTH), // (2,6) -> (2,4) ner
            new Segment(new Vector2(5, 4), 3, Direction.WEST),  // (2,4) -> (5,4) höger
            new Segment(new Vector2(2, 4), 2, Direction.SOUTH),
            new Segment(new Vector2(2, 2), 9, Direction.EAST),
            new Segment(new Vector2(11, 2), 2, Direction.NORTH),
            new Segment(new Vector2(11, 4), 4, Direction.WEST),
            new Segment(new Vector2(7, 4), 2, Direction.NORTH),
            new Segment(new Vector2(7, 6), 4, Direction.EAST),
            new Segment(new Vector2(11, 6), 2, Direction.NORTH),
            new Segment(new Vector2(11, 8), 4, Direction.NORTH)


        ));
    }
}
