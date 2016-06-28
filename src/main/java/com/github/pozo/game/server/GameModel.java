package com.github.pozo.game.server;

import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerId;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.artificial.ship.ShipBuilder;
import com.github.pozo.game.server.model.objects.artificial.ship.modelevent.ShipModelEventProducer;
import com.github.pozo.game.server.model.objects.natural.DefaultAstronomicalObject;
import com.github.pozo.game.server.model.objects.natural.Planet;
import com.github.pozo.game.server.model.objects.natural.PlanetBuilder;
import com.github.pozo.game.server.model.objects.natural.Star;
import com.github.pozo.game.server.model.objects.natural.StarBuilder;
import com.github.pozo.game.server.model.objects.natural.StarSystem;
import com.github.pozo.game.server.model.objects.natural.StarSystemBuilder;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;
import com.github.pozo.game.server.model.unit.Route;
import com.github.pozo.game.server.model.unit.time.TimeUnits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pozo on 2016.06.06..
 */
public class GameModel {
    private List<ModelEventConsumer> modelEventConsumers = new ArrayList<ModelEventConsumer>();
    private Map<PlayerId, Player> players = new HashMap<PlayerId,Player>();

    GameModel() {
        Player NPC = Player.getNPC();
        Player player = new Player(new PlayerId("Jozsi"));
        Player player2 = new Player(new PlayerId("Bela"));

        players.put(NPC.getPlayerId(), NPC);
        players.put(player.getPlayerId(), player);

        Coordinate gridOrigo = new Coordinate(0, 0);
        Coordinate coordinateMercury = new Coordinate(3, 1);
        Coordinate coordinateVenus = new Coordinate(2, 4);

        Star star = new StarBuilder().setCoordinate(gridOrigo)
                .setDiameter(new DistanceUnit(5))
                .setMass(new MassUnit(2))
                .setId("Sun")
                .createStar();
        modelEventConsumers.add(star);

        Planet mercury = new PlanetBuilder().setDefaultAstronomicalObjects(new ArrayList<DefaultAstronomicalObject>())
                .setCoordinate(coordinateMercury)
                .setDiameter(new DistanceUnit(2))
                .setMass(new MassUnit(2))
                .setId("Earth")
                .setOrbitalPeriod(new TimeUnits(1))
                .setRotationPeriod(new TimeUnits(1))
                .setOwner(NPC)
                .createPlanet();
        modelEventConsumers.add(mercury);

        Planet venus = new PlanetBuilder().setDefaultAstronomicalObjects(new ArrayList<DefaultAstronomicalObject>())
                .setCoordinate(coordinateVenus)
                .setDiameter(new DistanceUnit(3))
                .setMass(new MassUnit(3))
                .setId("Pluto")
                .setOrbitalPeriod(new TimeUnits(1))
                .setRotationPeriod(new TimeUnits(1))
                .setOwner(player)
                .createPlanet();
        modelEventConsumers.add(venus);

        StarSystem startSystem = new StarSystemBuilder().setStar(star)
                .withAstronomicalObject(new DistanceUnit(10), mercury)
                .withAstronomicalObject(new DistanceUnit(12), venus)
                .createStarSystem();
        modelEventConsumers.add(startSystem);

        Coordinate planetXCoordinate = new Coordinate(3, 1);
        Star star2 = new StarBuilder().setCoordinate(gridOrigo)
                .setDiameter(new DistanceUnit(6))
                .setMass(new MassUnit(3))
                .setId("Sun")
                .createStar();
        modelEventConsumers.add(star2);

        Planet planetX = new PlanetBuilder().setDefaultAstronomicalObjects(new ArrayList<DefaultAstronomicalObject>())
                .setCoordinate(planetXCoordinate)
                .setDiameter(new DistanceUnit(4))
                .setMass(new MassUnit(4))
                .setId("Pluto")
                .setOrbitalPeriod(new TimeUnits(1))
                .setRotationPeriod(new TimeUnits(1))
                .createPlanet();
        modelEventConsumers.add(planetX);

        StarSystem startSystem2 = new StarSystemBuilder().setStar(star2)
                .withAstronomicalObject(new DistanceUnit(100000), planetX)
                .createStarSystem();
        modelEventConsumers.add(startSystem2);

        ShipModelEventProducer shipEventProducer = new ShipModelEventProducer();
        final Ship ship = new ShipBuilder().setCoordinate(new Coordinate(0, 0))
                .setShipEventProducer(shipEventProducer)
                .setRoute(new Route())
                .setId("Horty")
                .setOwner(player)
                .createShip();
        final Ship ship2 = new ShipBuilder().setCoordinate(new Coordinate(0, 0))
                .setShipEventProducer(shipEventProducer)
                .setRoute(new Route())
                .setId("HortyTwo")
                .setOwner(player2)
                .createShip();
        modelEventConsumers.add(ship);

        player.addArtificialObject(ship);
        shipEventProducer.addConsumer(player);
    }
    public List<ModelEventConsumer> getModelEventConsumers() {
        return modelEventConsumers;
    }

    public Player getPlayer(PlayerId playerId) {
        return players.get(playerId);
    }
}
