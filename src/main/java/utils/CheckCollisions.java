package utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.ObjectIdentifier.*;

public class CheckCollisions {

    List<Collision> collisions;

    public CheckCollisions() {
        this.collisions = Stream.of(
                new Collision(BED, TANK_PLAYER_1),
                new Collision(BED, TANK_PLAYER_2),
                new Collision(HOMING_MISSILE, TANK_PLAYER_1),
                new Collision(HOMING_MISSILE, TANK_PLAYER_2),
                new Collision(TANK_PLAYER_1, FLAG),
                new Collision(TANK_PLAYER_2, FLAG),
                new Collision(TANK_PLAYER_1, POWER_UP),
                new Collision(TANK_PLAYER_2, POWER_UP),
                new Collision(BULLET, OBSTACLE),
                new Collision(HOMING_MISSILE, OBSTACLE),
                new Collision(BED, HOMING_MISSILE))
                .collect(Collectors.toList());
    }

    public List<Collision> getCollisions() {
        return this.collisions;
    }
}
