//TODO: change class definition below according to specification in 'Aufgabenblatt6'.

public class CelestialSystemIndexTreeVariantC implements CelestialSystemIndex, CelestialBodyIterable {

    private VariantCNode root;
    private CelestialBodyComparator comparator;
    private int size = 0;

    // Initialises this index with a 'comparator' for sorting
    // the keys of this index.
    public CelestialSystemIndexTreeVariantC(CelestialBodyComparator comparator) {
        this.comparator = comparator;
    }

    // Adds a system of bodies to the index.
    // Adding a system adds multiple (key, value) pairs to the
    // index, one for each body of the system, with the same
    // value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists
    // in the index leaves the index unchanged and the returned
    // value would be 'false'.
    // The method returns 'true' if the index was changed as a
    // result of the call and 'false' otherwise.
    public boolean add(CelestialSystem system) {

        if (system == null || system.size() == 0) {
            return false;
        }

        for (CelestialBody body : system) {
            if (contains(body))
                return false;
        }

        boolean changed = false;

        if (root == null) {
            root = new VariantCNode(system.get(0), system, null, null, comparator);
            changed = true;
            size++;
        } else {
            changed = root.add(new VariantCNode(system.get(0), system, null, null, comparator));
            if(changed)
                size++;
        }

        for (int i = 1; i < system.size(); i++) {
            changed |= root.add(new VariantCNode(system.get(i), system, null, null, comparator));
            if(changed)
                size++;
        }

        return changed;
    }

    // Returns the celestial system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.
    public CelestialSystem get(CelestialBody body) {
        if (root == null) {
            return null;
        }

        return root.get(body);
    }

    // Returns 'true' if the specified 'body' is listed
    // in the index.
    public boolean contains(CelestialBody body) {
        return get(body) != null;
    }

    // Returns a readable representation with (key, value) pairs sorted by the key.
    public String toString() {
        if (root == null) {
            return "{}";
        }

        return "{" + root.toString() + "}";
    }

    public int size() {
        return size;
    }

    // Returns a collection view of all entries of this index.
    public CelestialBodyCollection bodies() {
        return new MyCelestialBodySet(this);
    }

    // Returns all entries of this as a new collection.
    public CelestialSystem bodiesAsCelestialSystem() {
        CelestialSystem system = new CelestialSystem("IndexTreeVariantC");

        for (CelestialBody b : this)
            system.add(b);

        return system;
    }

    // Returns the comparator used in this index.
    public CelestialBodyComparator getComparator() {
        return comparator;
    }

    public CelestialBodyIterator iterator() {
        MyVariantCIterator iter = new MyVariantCIterator();
        if (root != null)
            root.iter(iter, false);
        return iter;
    }
}

class VariantCNode {
    private VariantCNode left;
    private VariantCNode right;
    private CelestialBody key;
    private CelestialSystem cs;
    private CelestialBodyComparator comparator;

    public VariantCNode(CelestialBody key, CelestialSystem cs, VariantCNode left, VariantCNode right,
                        CelestialBodyComparator comparator) {
        this.key = key;
        this.cs = cs;
        this.left = left;
        this.right = right;
        this.comparator = comparator;

    }

    public boolean add(VariantCNode node) {
        if (node.key.equals(this.key)) {
            return false;
        }

        if (this.comparator.compare(this.key, node.key) > 0) {
            if (left == null) {
                left = node;
                return true;
            } else {
                return left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
                return true;
            } else {
                return right.add(node);
            }
        }

    }

    public CelestialSystem get(CelestialBody body) {
        if (key.equals(body)) {
            return cs;
        }

        if (this.comparator.compare(this.key, body) > 0) {
            if (left == null) {
                return null;
            }
            return left.get(body);
        } else {
            if (right == null) {
                return null;
            }
            return right.get(body);
        }

    }

    public String toString() {
        String result;
        result = left == null ? "" : left.toString();
        result += result.isEmpty() ? "" : ",\n";
        result += this.key + " belongs to " + this.cs;
        result += right == null ? "" : ",\n" + right.toString();
        return result;

    }

    public CelestialBody iter(MyVariantCIterator iter, boolean next) {
        VariantCNode n = next ? right : this;
        while (n != null) {
            new MyVariantCIterator(n, iter);
            n = n.left;
        }
        return key;
    }

}

class MyVariantCIterator implements CelestialBodyIterator {
    private VariantCNode node;
    private MyVariantCIterator parent;

    MyVariantCIterator() {
    }

    MyVariantCIterator(VariantCNode n, MyVariantCIterator p) {
        node = p.node;
        p.node = n;
        parent = p.parent;
        p.parent = this;
    }

    public boolean hasNext() {
        return node != null;
    }

    public CelestialBody next() {
        if (node == null)
            return null;

        VariantCNode todo = node;
        node = parent.node;
        parent = parent.parent;
        return todo.iter(this, true);

    }
}

class MyCelestialBodySet implements CelestialBodyCollection {

    CelestialSystemIndexTreeVariantC tree;

    MyCelestialBodySet(CelestialSystemIndexTreeVariantC tree) {
        this.tree = tree;
    }

    public boolean add(CelestialBody body) {
        return false;
    }

    public int size() {
        return tree.size();
    }

    public CelestialBodyIterator iterator() {
        return tree.iterator();
    }

    public String toString() {
        return "MyCelestialBodySet{" +
                "tree=" + tree +
                '}';
    }
}

// 1. Wie verhalten sich die von `bodies()` und `bodiesAsCelestialSystem()`zurückgelieferten Objekte, wenn deren enthaltene Himmelskörper durch `move` bewegt werden? Werden dadurch die Himmelskörper des Suchbaums geändert? (Anmerkung: diesbezüglich gibt es im diesem Aufgabenblatt keine Vorgaben).
// Ja die Objekte verändert sich im Tree auch, weil es sich um Referenzen zu den Objekten
// 2. Wie verhalten sich Ihre Iteratoren, wenn Objekte geändert werden?
// die Iteratoren verwenden immer den aktuellen Zustand des objektes. Dh wenn ich das Objekt ändere und dann in einer For-Schleife verwende, dann wird der letzt Zustand des Objektes verwendet
