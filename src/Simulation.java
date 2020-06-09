public class Simulation {
    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.
    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        // check for 2 arguments
        if (args.length != 2) {
            System.out.println("Error: wrong number of arguments (2 arguments needed).");
            System.exit(0);
        }

        // assign arguments to vars
        String path = args[0];
        int days = Integer.parseInt(args[1]);

        // ceck days
        if(!(0 < days && days < 367)){
            System.out.println("Error: day is out of range (1-366) :" + days);
            System.exit(0);
        }

        // trying to create system
        CelestialSystem system = null;

        try {
            system = ReadDataUtil.initialize(path, days);
        } catch (FileNotFoundException | FileFormatException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }

        // old code
        Vector3[] forceOnBody = new Vector3[system.size()];

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2 * AU, 2 * AU);
        StdDraw.setYscale(-2 * AU, 2 * AU);
        double pixelWidth = 4 * AU / 500;
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        double seconds = 0;

        // simulation loop
        while (true) {
            seconds++; // each iteration computes the movement of the celestial bodies within one second.
            // for each body (with index i): compute the total force exerted on it.
            for (int i = 0; i < system.size(); i++) {
                forceOnBody[i] = new Vector3(); // begin with zero
                for (int j = 0; j < system.size(); j++) {
                    if (i == j) continue;
                    Vector3 forceToAdd = system.get(i).gravitationalForce(system.get(j));
                    forceOnBody[i] = forceOnBody[i].plus(forceToAdd);
                }
            }
            // now forceOnBody[i] holds the force vector exerted on body with index i.
            // for each body (with index i): move it according to the total force exerted on it.
            for (int i = 0; i < system.size(); i++) {
                system.get(i).move(forceOnBody[i]);
            }

            // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
            if (seconds % (3 * 3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                StdDraw.clear(StdDraw.BLACK);
                // draw new positions
                for (int i = 0; i < system.size(); i++) {
                    system.get(i).draw();
                }
                // show new positions
                StdDraw.show();
            }
        }
    }
}

/*
 * 1. Was versteht man unter Datenkapselung?
 * Zusammenfassung von allen zusammengehörigen Variablen und Methoden in eine Klasse.
 * 2. Was versteht man unter _data hiding_?
 * Keinen direckten Zugriff auf die inneren Daten von außen (andere Klassen) zulassen.
 * */

