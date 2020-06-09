// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).

/**
 * class Body: Body in a 3D space, with mass and velocity.
 * The mass is given in the unit kilogram and is initially 1.
 * The position in 3D space is represented in a 3 dimensional vector.
 * The velocity is also represented as a 3 dimensional vector.
 *
 * public methods :
 * void move():
 * The body changes its position by adding the velocity vector to the current position vector.
 *
 * void move(double fx, double fy, double fz):
 * The body will be moved by the new velocity vector.
 * Formula for the velocity vector: current velocity vector + new velocity vector / mass
 *
 * void setMass (double mass),
 * sets Mass
 *
 * void setPosition (double x, double y, double z)
 * sets positions vector
 *
 * void setVelocity (double x, double y, double z)
 * sets velocity vector
 *
 * void getMass, getPosition, getVelocity
 * returns mass, positions vector & velocity vector
 **/

public class Body {
    private double mass = 1;
    private MyVector position = new MyVector();
    private MyVector velocity = new MyVector();

    public void move() {
        setPosition(position.getX() + velocity.getX(), position.getY() + velocity.getY(), position.getZ() + velocity.getZ());
    }

    public void move(double fx, double fy, double fz) {
        setVelocity(velocity.getX() + (fx / mass), velocity.getY() + (fy / mass), velocity.getZ() + (fz / mass));
        move();
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setPosition(double x, double y, double z) {
        this.position.setX(x);
        this.position.setY(y);
        this.position.setZ(z);
    }

    public void setVelocity(double x, double y, double z) {
        this.velocity.setX(x);
        this.velocity.setY(y);
        this.velocity.setZ(z);
    }

    public double getMass() {
        return mass;
    }

    public MyVector getPosition() {
        return position;
    }

    //    Zusatzfrage 1:
    //    private void move(int seconds, double fx, double fy, double fz){
    //        if(seconds > 0){
    //            move(seconds - 1, fx, fy, fz);
    //        }
    //    }
}
