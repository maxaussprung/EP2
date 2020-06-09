public class MyBodyDLL {
    private CelestialBody body;
    private MyBodyDLL prev, next;

    MyBodyDLL(CelestialBody body) {
        this.body = body;
        prev = next = this;
    }

    private MyBodyDLL(CelestialBody body, MyBodyDLL prev, MyBodyDLL next) {
        this.body = body;
        this.prev = prev;
        this.next = next;
    }

    public CelestialBody value() {
        return body;
    }

    public MyBodyDLL next() {
        return next;
    }

    public MyBodyDLL previous() {
        return prev;
    }

    public void setValue(CelestialBody body) {
        this.body = body;
    }

    public MyBodyDLL add(CelestialBody body) {
        return next = next.prev = new MyBodyDLL(body, this, next);
    }

    public MyBodyDLL remove() {
        prev.next = next;
        next.prev = prev;
        return this;
    }
}
