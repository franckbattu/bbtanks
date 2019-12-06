package utils;

public enum ObjectIdentifier {
    ANY(0),
    BED(1),
    BULLET(1),
    GRENADE(1),
    PULSE(1),
    TANK_PLAYER_1(2),
    TANK_PLAYER_2(4),
    FLAG(8),
    BASE(8),
    POWER_UP(16),
    OBSTACLE(32),
    HOMING_MISSILE(64);

    public int id;

    ObjectIdentifier(int id) {
        this.id = id;
    }

}
