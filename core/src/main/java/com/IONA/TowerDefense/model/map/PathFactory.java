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
            new Segment(new Vector2(0, 5), 3, Direction.EAST),
            new Segment(new Vector2(3, 5), 2, Direction.NORTH),
            new Segment(new Vector2(3, 7), 2, Direction.EAST),
            new Segment(new Vector2(5, 7), 3.5f, Direction.SOUTH),
            new Segment(new Vector2(5, 3.5f), 3, Direction.WEST),
            new Segment(new Vector2(2, 3.5f), 1.5f, Direction.SOUTH),
            new Segment(new Vector2(2, 2), 8.0f, Direction.EAST),
            new Segment(new Vector2(10, 2), 2, Direction.NORTH),
            new Segment(new Vector2(10, 4), 3.0f, Direction.WEST),
            new Segment(new Vector2(7, 4), 2, Direction.NORTH),
            new Segment(new Vector2(7, 6), 4.5f, Direction.EAST),
            new Segment(new Vector2(11.5f, 6), 4, Direction.SOUTH),
            new Segment(new Vector2(11.5f, 2), 2.2f, Direction.EAST),
            new Segment(new Vector2(13.7f, 2), 4.5f, Direction.NORTH),
            new Segment(new Vector2(13.7f, 6.5f), 2.5f, Direction.NORTH)


        ));
    }
}
