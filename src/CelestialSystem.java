public class CelestialSystem implements CelestialBodyCollection {

    //TODO: Define variables.
    private final String name;
    private final MyBodyDLL nil;

    // Initialises this system as an empty system with a name.
    public CelestialSystem(String name) {
        this.name = name;
        nil = new MyBodyDLL(null);
    }

    // Adds 'body' to the list of bodies of the system if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(CelestialBody body) {
        if (get(body.getName()) == null) {
            MyBodyDLL current = nil.next();

            while (current.next().value() != null) {
                current = current.next();
            }

            current.add(body);
            return true;
        }
        return false;
    }

    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    public CelestialBody get(int i) {
        assert i < size();

        MyBodyDLL body = nil.next();

        for (int current_i = 1; current_i <= i; current_i++) {
            body = body.next();
        }

        return body.value();
    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public CelestialBody get(String name) {
        MyBodyDLL current = nil.next();

        while (current.value() != null) {
            if (name.equals(current.value().getName())) {
                return current.value();
            }
            current = current.next();
        }
        return null;
    }

    // returns the number of entries of the list.
    public int size() {
        int count = 0;
        MyBodyDLL current = nil.next();

        while (current.value() != null) {
            current = current.next();
            count++;
        }

        return count;
    }

    // Inserts the specified 'body' at the specified position
    // in this list if 'i' is a valid index and there is no body
    // in the list with the same name as that of 'body'.
    // Shifts the element currently at that position (if any) and
    // any subsequent elements to the right (adds one to their
    // indices). The first element of the list has the index 0.
    // Returns 'true' if the list was changed as a result of
    // the call, 'false' otherwise.
    public boolean add(int i, CelestialBody body) {
        MyBodyDLL current = nil;

        for (int current_i = 0; current_i < i; current_i++) {
            current = current.next();
        }

        current.add(body);
        return true;
    }

    // Returns a new list that contains the same elements as this
    // list in reverse order. The list 'this' is not changed and
    // bodies are not duplicated (shallow copy).
    public CelestialSystem reverse() {
        CelestialSystem reverse = new CelestialSystem("reverse");

        MyBodyDLL current = nil.previous();
        while (current.value() != null) {
            reverse.add(current.value());
            current = current.previous();
        }

        return reverse;
    }

    public String getName() {
        return name;
    }

    // Returns a readable representation with the name of the
    // system and all bodies in respective order of the list.
    public String toString() {
        String text = name.concat(": ");

        MyBodyDLL current = nil.next();

        while (current.value() != null) {
            String name = current.value().getName();
            text += name + "; ";
            current = current.next();
        }

        return text;
    }

    public boolean equals(Object o) {
        if (o != null && o.getClass() == CelestialSystem.class) {
            CelestialSystem other = (CelestialSystem) o;

            if (size() != other.size()) {
                return false;
            }

            MyBodyDLL current = nil.next();

            while (current.value() != null) {
                if (other.get(current.value().getName()) == null)
                    return false;

                current = current.next();
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        MyBodyDLL current = nil.next();
        int hash = 0;

        while (current.value() != null) {
            hash += current.value().hashCode();
            current = current.next();
        }
        return hash;
    }

    public CelestialBodyIterator iterator() {
        return new MyCelestialBodyIterator(nil.next());
    }
}

class MyCelestialBodyIterator implements CelestialBodyIterator {
    MyBodyDLL current;

    public MyCelestialBodyIterator(MyBodyDLL head) {
        this.current = head;
    }

    public boolean hasNext() {
        return current.value() != null;
    }

    public CelestialBody next() {
        CelestialBody body = current.value();
        current = current.next();
        return body;
    }
}
