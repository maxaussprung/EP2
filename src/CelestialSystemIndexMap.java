class CelestialSystemIndexMap implements CelestialSystemIndex {

    private CelestialSystem[][] array;
    private CelestialBody[][] keys;
    private int size;

    public CelestialSystemIndexMap() {
        array = new CelestialSystem[31][24]; // 24 is 0.75 of 31
        keys = new CelestialBody[31][24];
        size = 0;
    }

    private int h(CelestialBody body) {
        return Math.abs(body.hashCode() % keys.length);
    }

    public boolean add(CelestialSystem system) {

        for (int i = 0; i < system.size(); i++)
            if (contains(system.get(i)))
                return false;


        for (int i = 0; i < system.size(); i++) {

            if (size + 1 >= keys.length * 0.75)
                doubleMap();

            add(system.get(i), system);
        }

        return true;
    }

    private void add(CelestialBody body, CelestialSystem system) {
        int hash = h(body);
        int index = firstNullIndex(keys[hash]);

        keys[hash][index] = body;
        array[hash][index] = system;

        size++;
    }

    private int firstNullIndex(Object[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == null)
                return i;

        return -1;
    }

    public CelestialSystem get(CelestialBody body) {
        int hash = h(body);

        for (int i = 0; i < keys[hash].length; i++) {
            CelestialBody currentBody = keys[hash][i];

            if (currentBody == null)
                return null;

            if (body.equals(currentBody))
                return array[hash][i];
        }

        return null;
    }

    public boolean contains(CelestialBody body) {
        return get(body) != null;
    }

    private void doubleMap() {
        CelestialSystem[][] temp_array = array;
        CelestialBody[][] temp_keys = keys;

        int length1 = keys.length * 2;
        int length2 = keys[0].length * 2;

        array = new CelestialSystem[length1][length2];
        keys = new CelestialBody[length1][length2];
        size = 0;

        for (int hash = 0; hash < temp_keys.length; hash++) {
            int index = 0;

            while (temp_array[hash][index] != null) {
                add(temp_keys[hash][index], temp_array[hash][index]);
                index++;
            }
        }
    }

    public String toString() {
        String string = "";

        for (int hash = 0; hash < keys.length; hash++) {
            int index = 0;
            while (keys[hash][index] != null) {
                string += keys[hash][index].getName() + ": " + array[hash][index].getName() + "; ";
                index++;
            }
        }

        return string;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        CelestialSystemIndexMap otherMap = (CelestialSystemIndexMap) o;

        for (CelestialBody[] key : keys) {
            int index = 0;
            while (key[index] != null) {
                if (!otherMap.contains(key[index]))
                    return false;
                index++;
            }
        }
        return true;
    }

    public int hashCode() {
        int hash = 0;

        for (CelestialBody[] key : keys) {
            int index = 0;
            while (key[index] != null) {
                hash += key[index].hashCode();
                index++;
            }
        }

        return hash;
    }
}

//1. Wie ändert sich das Verhalten von `CelestialSystemIndexMap`, wenn Sie in Ihrer Lösung die Implementierung der Methoden `equals` und `hashCode` aus der Klasse `CelestialBody` löschen?
// Ich denke meine Lösung würde noch funktionieren aber bei der Methode "get" würde man immer nur den ersten Body mit dem Namen finden. (Durch das entfernen von Equal sind auch Bodies mit selben Namen erlaubt).
//2. Was passiert, wenn Sie nur `hashCode` löschen?
// Dann sollte meine Lösung noch funktionieren.
//3. Welche Bedingungen gelten allgemein für die Methoden `equals` und `hashCode`?
// Scriptum Page 85:
//- Für x != null liefert x.equals(null) stets false.
//– Für x != null liefert x.equals(x) stets true (reflexiv).
//– Für x != null und y != null liefert x.equals(y) stets das gleiche Ergebnis wie y.equals(x) (symmetrisch).
//– Wenn x.equals(y) und y.equals(z) beide true liefern, dann tut dies auch x.equals(z) (transitiv).
//4. Gilt in Ihrer Lösung,dass `x.toString().equals(y.toString())` den Wert `true`liefert, wenn `x.equals(y)` den Wert `true` liefert? Welche Probleme können entstehen, wenn diese Bedingung nicht erfüllt ist? (Anmerkung: Es war in diesem Aufgabenblatt nicht verlangt, dass Ihre Lösung die Bedingung erfüllen muss.)
// Nein gilt in meiner Lösung nicht, weil ich nur die Namen miteinander vergleiche. Wenn ich in mit meiner Lösung toString verwenden würden, dann könnte wie oben schon gesagt die "get" Methode immer nur den ersten Body finden.
//5. Was könnte man ändern, damit neben `CelestialSystemIndexMap` auch `CelestialSystemIndexTree` das Interface `CelestialSystemIndex`implementieren kann?
// Entweder die Methoden contains & equals entfernen oder auch diese auch im Tree implementieren.