package folien;

enum Planet {
    MER(2440, 3.70), VE(6052, 8.87), EA(6378, 9.78),
    MA(3397, 3.69), JU(71493, 23),
    SA(60267, 8.8), UR(25559, 8.6), NE(24764, 11);
    private final int radius;
    private final double acceleration;

    Planet(int radius, double acceleration) {
        this.radius = radius;
        this.acceleration = acceleration;
    }

    int getRadius() {
        return radius;
    }

    double getAcceleration() {
        return acceleration;
    }
    boolean gasPlanet() {
        return switch (this) {
            case JU, SA, UR, NE -> {
                yield true;
            }
            default -> {
                yield  false;
            }
        };
    }
}
