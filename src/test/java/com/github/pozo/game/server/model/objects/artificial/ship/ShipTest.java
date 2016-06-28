package com.github.pozo.game.server.model.objects.artificial.ship;

import com.github.pozo.game.server.model.objects.artificial.ship.modelevent.ShipModelEventProducer;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.Route;
import com.github.pozo.game.server.model.unit.time.TimeUnits;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.artificial.ship.ShipBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pozo on 2016.06.05..
 */
public class ShipTest {

    public static final Coordinate ORIGO = new Coordinate(0, 0);
    public static final DistanceUnit DISTANCE_OFFSET = new DistanceUnit(3f);
    private Ship underTest;
    @Before
    public void setUp() {
        this.underTest = new ShipBuilder().setCoordinate(ORIGO)
                .setId("test")
                .setShipEventProducer(new ShipModelEventProducer())
                .setRoute(new Route()).createShip();
        this.underTest.setCurrentSpeed(DISTANCE_OFFSET);
    }
    @Test
    public void testMoveX() throws Exception {
        Coordinate destination = new Coordinate(10, 0);

        this.underTest.move(new TimeUnits(1), destination);

        double expectedDistance = 10 - DISTANCE_OFFSET.getValue();
        double actualDistance = destination.getDistance(this.underTest.getCoordinate());

        Assert.assertEquals(expectedDistance, actualDistance, 0.1);
    }
    @Test
    public void testMoveY() throws Exception {
        Coordinate destination = new Coordinate(0, 10);

        this.underTest.move(new TimeUnits(1), destination);

        double expectedDistance = 10 - DISTANCE_OFFSET.getValue();
        double actualDistance = destination.getDistance(this.underTest.getCoordinate());

        Assert.assertEquals(expectedDistance, actualDistance, 0.1);
    }
    @Test
    public void testMoveXandY() throws Exception {
        Coordinate destination = new Coordinate(10, 10);

        this.underTest.move(new TimeUnits(1), destination);

        double expectedDistance = 14.142136 - DISTANCE_OFFSET.getValue();
        double actualDistance = destination.getDistance(this.underTest.getCoordinate());

        Assert.assertEquals(expectedDistance, actualDistance, 0.1);
    }
    @Test
    public void testMoveXandYMinus() throws Exception {
        Coordinate destination = new Coordinate(-10, -10);

        this.underTest.move(new TimeUnits(1), destination);

        double expectedDistance = 14.142136 - DISTANCE_OFFSET.getValue();
        double actualDistance = destination.getDistance(this.underTest.getCoordinate());

        Assert.assertEquals(expectedDistance, actualDistance, 0.1);
    }
    @Test
    public void testConsume() throws Exception {

    }
}