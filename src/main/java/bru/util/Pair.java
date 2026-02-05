package bru.util;

/**
 * The Pair class stores a pair of objects of type T and type S.
 */
public class Pair<T, S> {
    private T first;
    private S second;

    /**
     * Instantiates a Pair with first object of type T and second object of type S.
     *
     * @param first  The first object.
     * @param second The second object.
     */
    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair<?, ?> p) {
            return (this.first.equals(p.first) && this.second.equals(p.second));
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.first, this.second);
    }
}
