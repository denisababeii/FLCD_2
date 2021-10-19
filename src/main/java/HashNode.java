public class HashNode<V> {
    V value;
    int hashCode;
    HashNode<V> next;

    public HashNode(V value, int hashCode) {
        this.value = value;
        this.hashCode = hashCode;
    }
}
