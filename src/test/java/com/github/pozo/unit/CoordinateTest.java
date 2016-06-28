package com.github.pozo.unit;

import com.github.pozo.game.server.model.unit.Coordinate;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pozo on 2016.06.05..
 */
public class CoordinateTest {
    public static final Coordinate ZERO = new Coordinate(0, 0);
    @Test
    public void testGetDistance() throws Exception {
        Coordinate coordinate = new Coordinate(0,1);

        double expectedDistance = 1;
        double actualDistance = coordinate.getDistance(ZERO);

        Assert.assertEquals(expectedDistance, actualDistance, 0.1);
    }
    @Test
    public void testGetAngleY0() throws Exception {
        Coordinate coordinate = new Coordinate(1,0);

        double expedtedAngle = 0;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
    @Test
    public void testGetAngleY45() throws Exception {
        Coordinate coordinate = new Coordinate(1,1);

        double expedtedAngle = 45;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
    @Test
    public void testGetAngleY90() throws Exception {
        Coordinate coordinate = new Coordinate(0, 1);

        double expedtedAngle = 90;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
    @Test
    public void testGetAngleY135() throws Exception {
        Coordinate coordinate = new Coordinate(-1,1);

        double expedtedAngle = 135;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
    @Test
    public void testGetAngleY180() throws Exception {
        Coordinate coordinate = new Coordinate(-1,0);

        double expedtedAngle = 180;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
    @Test
    public void testGetAngleY225() throws Exception {
        Coordinate coordinate = new Coordinate(-1,-1);

        double expedtedAngle = 225;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
    @Test
    public void testGetAngleY315() throws Exception {
        Coordinate coordinate = new Coordinate(1,-1);

        double expedtedAngle = 315;
        double actualAngle = Math.toDegrees(ZERO.getAngleY(coordinate));

        Assert.assertEquals(expedtedAngle, actualAngle, 0.1);
    }
}