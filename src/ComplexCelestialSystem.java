public class ComplexCelestialSystem {

    private MySystemNode first;

    // Initializes this system as an empty system with a name.
    public ComplexCelestialSystem(String name) {
    }

    // Adds a subsystem of bodies to this system if there are no bodies in the subsystem
    // with the same name as a body or subsystem of this system.
    // The method returns 'true' if the collection was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CelestialSystem subsystem) {
        if (first == null) {
            first = new MySystemNode(subsystem);
            return true;
        }

        if (get(subsystem.getName()) == null) {
            MySystemNode current = first;

            while (current.next() != null) {
                current = current.next();
            }

            current.setNext(subsystem);
            return true;
        }
        return false;
    }

    // Returns the single body or subsystem with 'name' or 'null' if no such body or subsystem 
    // exists in this system. In case of a single body, the body is returned as a subsystem of 
    // one body, with the same name as the body.
    public CelestialSystem get(String name) {
        MySystemNode currentNode = first;

        while (currentNode != null) {
            CelestialSystem subsystem = currentNode.getSystem();

            if (name.equals(subsystem.getName())) {
                return subsystem;
            }

            CelestialBody body = subsystem.get(name);

            if (body != null) {
                CelestialSystem singleBodySubsystem = new CelestialSystem(name);
                singleBodySubsystem.add(body);
                return singleBodySubsystem;
            }

            currentNode = currentNode.next();
        }

        return null;
    }

    // Returns the number of bodies of the entire system.
    public int size() {
        MySystemNode currentNode = first;
        int sum = 0;

        while (currentNode != null) {
            sum += currentNode.getSystem().size();
            currentNode = currentNode.next();
        }

        return sum;
    }
}


