public class MyBodyNode {
    private CelestialBody body;
    private MyBodyNode next;

    public MyBodyNode(CelestialBody current) {
        this.body = current;
    }

    public CelestialBody getBody() {
        return body;
    }

    public void setNext(CelestialBody next) {
        this.next = new MyBodyNode(next);
    }

    public MyBodyNode next() {
        return next;
    }
}
