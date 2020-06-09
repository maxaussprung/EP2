import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {

    private static CelestialBody a;
    private static CelestialBody a_clone;
    private static CelestialBody b;
    private static CelestialBody c;

    private static CelestialSystem sys;
    private static CelestialSystem sys_other;
    private static CelestialSystem sys_clone;

    @BeforeClass
    public static void testSetup() {
        a = new CelestialBody("a", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        a_clone = new CelestialBody("a", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        b = new CelestialBody("b", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        c = new CelestialBody("c", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody d = new CelestialBody("d", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody e = new CelestialBody("e", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);

        sys = new CelestialSystem("test");
        sys.add(a);
        sys.add(b);
        sys.add(2, c);

        sys_other = new CelestialSystem("test2");
        sys_other.add(d);
        sys_other.add(e);

        sys_clone = new CelestialSystem("test3");
        sys_clone.add(c);
        sys_clone.add(a);
        sys_clone.add(b);
    }


    @Test
    public void reverseSystem() {
        String normal = sys.toString().replaceAll("; ", "").substring(6);
        String reverse_normal = (new StringBuilder(normal)).reverse().toString();

        CelestialSystem res = sys.reverse();
        String reverse = res.toString().replaceAll("; ", "").substring(9);

        assertEquals("Result", reverse_normal, reverse);
    }

    @Test
    public void system() {
        assertEquals("Result", "test: a; b; c; ", sys.toString());
        assertEquals("Result", 3, sys.size());
        assertEquals("Result", sys.get(0), a);
        assertEquals("Result", sys.get(c.getName()), c);

        for (CelestialBody body : sys)
            System.out.println(body.toString());

        CelestialSystem sys_empty = new CelestialSystem("empty");

        for (CelestialBody body : sys_empty)
            System.out.println(body.toString());
    }

    @Test
    public void tree() {
        CelestialSystemIndexTree tree = new CelestialSystemIndexTree();
        tree.add(sys);
        tree.add(sys_other);

        assertEquals("Result", 2, tree.numberOfSystems());
        assertEquals("Result", 5, tree.numberOfBodies());
    }

    @Test
    public void hashNormal() {
        assertEquals("Result", 314, a.hashCode());
        assertEquals("Result", a, a_clone);
        assertNotEquals("Result", a, b);
        assertEquals("Result", sys, sys_clone);
        assertNotEquals("Result", sys, sys_other);
    }

    @Test
    public void hashTable() {
        CelestialBody a = new CelestialBody("pb", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody b = new CelestialBody("bp", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody c = new CelestialBody("c", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody d = new CelestialBody("d", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody e = new CelestialBody("e", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody f = new CelestialBody("f", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody g = new CelestialBody("g", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody h = new CelestialBody("h", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody i = new CelestialBody("i", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody j = new CelestialBody("j", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody k = new CelestialBody("k", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody l = new CelestialBody("l", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody m = new CelestialBody("m", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody n = new CelestialBody("n", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody o = new CelestialBody("o", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody p = new CelestialBody("p", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody q = new CelestialBody("q", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody r = new CelestialBody("r", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody s = new CelestialBody("s", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody t = new CelestialBody("t", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody u = new CelestialBody("u", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody v = new CelestialBody("v", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody w = new CelestialBody("w", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody x = new CelestialBody("x", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody y = new CelestialBody("y", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
        CelestialBody z = new CelestialBody("z", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);

        CelestialSystem system = new CelestialSystem("test3");

        system.add(a);
        system.add(b);
        system.add(c);
        system.add(d);
        system.add(e);
        system.add(f);
        system.add(g);
        system.add(h);
        system.add(i);
        system.add(j);
        system.add(k);
        system.add(l);
        system.add(m);
        system.add(n);
        system.add(o);
        system.add(p);
        system.add(q);
        system.add(r);
        system.add(s);
        system.add(t);
        system.add(u);
        system.add(v);
        system.add(w);
        system.add(x);
        system.add(y);
        system.add(z);

        CelestialSystemIndexMap hashMap = new CelestialSystemIndexMap();

        hashMap.add(system);

        System.out.println(hashMap.toString());

        assertFalse("Result", hashMap.add(sys_clone));
        assertTrue("Result", hashMap.contains(a));
        assertEquals("Result", hashMap, hashMap);
        assertNull("Result", hashMap);
        assertEquals("Result", 28034, hashMap.hashCode());
    }

    @Test
    public void TreeC() {
        CelestialBodyNameComparator comparator = new CelestialBodyNameComparator();
        CelestialSystemIndexTreeVariantC tree = new CelestialSystemIndexTreeVariantC(comparator);

        assertTrue("Result", tree.add(sys));
        assertFalse("Result", tree.add(sys_clone));
        assertEquals("Result", tree.get(a), sys);
        assertTrue("Result", tree.contains(a));
        assertEquals("Result", tree.size(), 3);

        System.out.println(tree.bodies().toString());
        System.out.println(tree.bodiesAsCelestialSystem().toString());
    }
    //    CelestialSystem test_subsystem1 = ReadDataUtil.initialize(60);
    //    CelestialSystem test_subsystem2 = ReadDataUtil.initialize(60);
    //    ComplexCelestialSystem test_test = new ComplexCelestialSystem("test");
    //    test_test.add(test_subsystem1);
    //    test_test.add(test_subsystem2);
    //
    //    System.out.println(test_test.get("Earth").getName());
    //    System.out.println(test_test.size());
    //
    //    CelestialBodyComparator comparator = new CelestialBodyNameComparator();
    //    CelestialSystemIndexTreeVariantC tree = new CelestialSystemIndexTreeVariantC(comparator);
    //
    //    CelestialBody test_a = new CelestialBody("pb", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
    //    CelestialBody test_b = new CelestialBody("bp", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
    //    CelestialBody test_c = new CelestialBody("c", 1, 1, new Vector3(), new Vector3(), StdDraw.WHITE);
    //
    //    CelestialSystem test_system2 = new CelestialSystem("test3");
    //
    //    test_system2.add(test_a);
    //    test_system2.add(test_b);
    //    test_system2.add(test_c);
    //
    //    tree.add(test_system2);
    //
    //    System.out.println(tree.toString());

    // More Tests in MyTestClass
} 