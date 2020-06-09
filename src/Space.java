// Space is the actual program (executable class) using objects of class 'Body'.
public class Space {

    // Some constants helpful for the simulation (particularly the 'falling ball' example).
    public static final double G = 6.6743e-11; // gravitational constant
    public static final double MASS_OF_EARTH = 5.972e24; // kg
    public static final double MASS_OF_BALL = 1; // kg
    public static final double RADIUS_OF_EARTH = 6.371e6; // m (meters)

    // On the surface of earth the gravitational force on the ball weighing 1kg is
    // approximately as follows:
    public static final double GRAVITATIONAL_FORCE_ON_BALL = G * MASS_OF_EARTH * MASS_OF_BALL / (RADIUS_OF_EARTH * RADIUS_OF_EARTH); // kg*m/sec² ... F = mass * acc
    // This means each second its speed increases about 9.82 meters per second.

    //TODO: further variables, if needed.

    public static final int ROCKET_MASS = 100000;
    public static final int ROCKET_FUEL_PS = 20000;

    public static final double WIND = 0.0001;


    // The main simulation method using instances of other classes.
    public static void main(String[] args) {
        //One test case is given:
        //Results for the falling ball on the surface of earth are as follows:
        //Height 10m: 2 (sec = number of move(fx,fy,fz) calls)
        //Height 100m: 5 (sec = number of move(fx,fy,fz) calls)

        Body ball1 = new Body();
        ball1.setPosition(0, 0, 100); // 100m height.
        ball1.setVelocity(0, 0, 0);
        ball1.setMass(1); // 1 kg
        System.out.println("Ball 1 air time 100m: " + fallToGround(ball1)); // 5

        ball1.setPosition(0, 0, 10); // 10m height.
        ball1.setVelocity(0, 0, 0);
        System.out.println("Ball 1 air time 10m: " + fallToGround(ball1)); // 2

        Body ball2 = new Body();
        ball2.setPosition(0, 0, 100); // 100m height.
        ball2.setVelocity(0, 0, 0);
        ball2.setMass(15); // 15 kg
        System.out.println("Ball 2 air time 100m: " + fallToGround(ball2)); // 5

        //Further examples are to be tested (body in empty space, rocket, feather).

        // body in empty space
        Body flyingObject = new Body();
        flyingObject.setMass(1);
        flyingObject.setPosition(0, 0, 100);
        flyingObject.setVelocity(1, 1, 1);

        moveBody(flyingObject, 10);

        System.out.println(String.format("flying object position after 10sec: %s", flyingObject.getPosition().toString())); // 10, 10, 110

        // rocket
        Body rocket = new Body();
        rocket.setMass(200000);
        rocket.setPosition(0, 0, 0);
        rocket.setVelocity(0, 0, 0);


        int rocketStart = startRocket(rocket);
        String rocketPosistion = rocket.getPosition().toString();

        System.out.println(String.format("rocket position after %s sec: %s", rocketStart, rocketPosistion)); // 753.27

        // feather
        Body feather = new Body();
        feather.setMass(0.000008);
        feather.setPosition(0, 0, 100);
        feather.setVelocity(0, 0, 0);

        System.out.println(String.format("feather position after %s sec: %s ", moveBodyRandomForce(feather, 10), feather.getPosition().toString())); // random
    }

    // Returns the number of move(fx,fy,fz) calls needed for 'b' hitting the ground, i.e.,
    // the method reduces the z-coordinate of 'b' until it becomes 0 or negative.
    public static int fallToGround(Body b) {

        double currentZ = b.getPosition().getZ();

        if (currentZ <= 0)
            return 0;

        b.move(0, 0, -GRAVITATIONAL_FORCE_ON_BALL * b.getMass());

        return 1 + fallToGround(b);
    }

    //TODO: Define further methods as needed.

    private static void moveBody(Body b, int seconds) {
        if (seconds > 0) {
            b.move();
            moveBody(b, seconds - 1);
        }
    }

    private static int startRocket(Body rocket) {
        if (rocket.getMass() >= ROCKET_MASS) {
            rocket.setMass(rocket.getMass() - ROCKET_FUEL_PS);
            rocket.move(0, 0, 5e6);
            return 1 + startRocket(rocket);
        } else {
            return 0;
        }
    }

    private static int moveBodyRandomForce(Body b, int seconds) {
        if (b.getPosition().getZ() >= 0 && seconds > 0) {
            double windX = Math.random() * Math.pow(-1, (int) (Math.random() * 10)) * WIND;
            double windY = Math.random() * Math.pow(-1, (int) (Math.random() * 10)) * WIND;
            double windZ = Math.random() * Math.pow(-1, (int) (Math.random() * 10)) * WIND;

            b.move(windX, windY, -GRAVITATIONAL_FORCE_ON_BALL * b.getMass() + windZ);
            return 1 + moveBodyRandomForce(b, seconds - 1);
        } else {
            return 0;
        }
    }
}



