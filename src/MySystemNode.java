public class MySystemNode {
    private CelestialSystem system;
    private MySystemNode next;

    public MySystemNode(CelestialSystem current) {
        this.system = current;
    }

    public CelestialSystem getSystem() {
        return system;
    }

    public void setNext(CelestialSystem next) {
        this.next = new MySystemNode(next);
    }

    public MySystemNode next() {
        return next;
    }
}
