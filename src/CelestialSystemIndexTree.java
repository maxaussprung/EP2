public class CelestialSystemIndexTree {

    private int numberOfBodies;
    private int numberOfSystems;
    private MyIndexNode root;

    CelestialSystemIndexTree() {
        numberOfBodies = 0;
        numberOfSystems = 0;
    }

    // Adds a system of bodies to the tree. Since the keys of the tree are the names of bodies,
    // adding a system adds multiple (key, value) pairs to the tree, one for each body of the
    // system, with the same value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists in the tree
    // leaves the tree unchanged and the returned value would be 'false'.
    // For example, it is not allowed to have a system ("Earth", "Moon") and a system ("Jupiter",
    // "Moon") indexed by the tree, since "Moon" is not unique.
    // The method returns 'true' if the tree was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CelestialSystem system) {
        // check if its already exist
        if (root != null) {
            for (int i = 0; i < system.size(); i++) {
                MyIndexNode res = root.get(system.get(i));

                if (res != null) {
                    return false;
                }
            }
        }

        // insert
        for (int i = 0; i < system.size(); i++) {
            // init root
            if (root == null) {
                root = new MyIndexNode(system, system.get(i));
            }
            // insert everything else
            else {
                root.add(system, system.get(i));
            }
            numberOfBodies++;
        }
        numberOfSystems++;
        return true;
    }

    // Returns the celestial system in which a body with the specified name exists.
    // For example, if the specified name is "Europa", the system of Jupiter (Jupiter, Io,
    // Europa, Ganymed, Kallisto) will be returned.
    // If no such system is found, 'null' is returned.
    public CelestialSystem get(String name) {
        MyIndexNode result = root.get(name);

        if (result == null)
            return null;

        return result.getSystem();
    }

    // Returns the overall number of bodies indexed by the tree.
    public int numberOfBodies() {
        return numberOfBodies;
    }

    // Returns the overall number of systems indexed by the tree.
    public int numberOfSystems() {
        return numberOfSystems;
    }
}

// Zusatzfragen
// Wie müsste man die Methoden von CelestialSystemIndex ändern, wenn man im Baum nicht nur mit Namen von Himmelskörpern,
// sondern auch mit Namen von Systemen suchen will?

// Ich würde einen zweiten Baum aufbauen welcher nur die Planeten Systeme enthält (Neben dem Tree mit den Bodys).
// Ich würde dann methoden overloading machen und CelestialBody mit CelestialSystem ersetzen.

