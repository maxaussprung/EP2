import java.awt.*;

// This class represents vectors in a 3D vector space.
public class Vector3 {

    private double x;
    private double y;
    private double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    // Returns the sum of this vector and vector 'v'.
    public Vector3 plus(Vector3 v) {

        Vector3 result = new Vector3();
        result.x = this.x + v.x;
        result.y = this.y + v.y;
        result.z = this.z + v.z;

        return result;
    }

    // Returns the product of this vector and 'd'.
    public Vector3 times(double d) {

        Vector3 result = new Vector3();
        result.x = this.x * d;
        result.y = this.y * d;
        result.z = this.z * d;

        return result;
    }

    // Returns the sum of this vector and -1*v.
    public Vector3 minus(Vector3 v) {
        Vector3 result = new Vector3();
        result.x = this.x - v.x;
        result.y = this.y - v.y;
        result.z = this.z - v.z;

        return result;
    }

    // Returns the Euclidean distance of this vector
    // to the specified vector 'v'.
    public double distanceTo(Vector3 v) {
        double dX = this.x - v.x;
        double dY = this.y - v.y;
        double dZ = this.z - v.z;

        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    // Returns the length (norm) of this vector.
    public double length() {
        return this.distanceTo(new Vector3());
    }

    // Normalizes this vector: changes the length of this vector such that it becomes one.
    // The direction and orientation of the vector is not affected.
    public void normalize() {
        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
    }

    // Draws a filled circle with the center at (x,y) coordinates of this vector
    // in the existing StdDraw canvas. The z-coordinate is not used.
    public void drawAsDot(double radius, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(this.x, this.y, 1e9 * Math.log10(radius));
        // use log10 because of large variation of radii.
    }

    // Returns the coordinates of this vector in brackets as a string
    // in the form "[x,y,z]", e.g., "[1.48E11,0.0,0.0]".
    public String toString() {
        return String.format("[%s, %s, %s]", this.x, this.y, this.z);
    }

    // Prints the coordinates of this vector in brackets to the console (without newline)
    // in the form [x,y,z], e.g.,
    // [1.48E11,0.0,0.0]
    public void print() {
        System.out.print(this.toString());
    }
}

