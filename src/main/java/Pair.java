/**
 * The Pair<T, S> class stores a pair of objects of type T and type S.
 */
public class Pair<T, S> {
    private T first;
    private S second;

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
    public String toString() {
        return String.format("(%s, %s)", this.first, this.second);
    }
}
