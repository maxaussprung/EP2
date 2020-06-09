public class MyIndexNode {
    private final String key;
    private final CelestialSystem system;
    private MyIndexNode left;
    private MyIndexNode right;

    MyIndexNode(CelestialSystem system, CelestialBody body){
        this.key = body.getName();
        this.system = system;
    }

    MyIndexNode get(String name){
        int compare = key.compareTo(name);

        // equal
        if (compare == 0){
            return this;
        }
        // name < key
        else if(compare > 0){
            if(left == null)
                return null;
            return left.get(name);
        }
        // name > key
        else {
            if(right == null)
                return null;
            return right.get(name);
        }
    }

    MyIndexNode get(CelestialBody body){
        return get(body.getName());
    }

    MyIndexNode add(CelestialSystem system, CelestialBody body){
        int compare = key.compareTo(body.getName());

        // name < key
        if (compare > 0){
            if(left == null)
                return left = new MyIndexNode(system, body);
            return left.add(system, body);
        }
        // name > key
        else {
            if(right == null)
                return right = new MyIndexNode(system, body);
            return right.add(system, body);
        }
    }

    CelestialSystem getSystem(){
        return system;
    }
}
