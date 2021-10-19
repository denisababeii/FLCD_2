import java.util.ArrayList;
import java.util.Objects;

public class SymbolTable<V> {
    private ArrayList<HashNode<V>> table;
    private int size;
    private int capacity;

    public SymbolTable() {
        table = new ArrayList<>();
        capacity = 10;
        size = 0;

        for(int i = 0; i < capacity; i++)
            table.add(null);
    }

    private int getHashCode (V value) {
        return Objects.hashCode(value);
    }

    private int getIndex(V value) {
        int hashCode = getHashCode(value);
        int index = hashCode % capacity;
        if (index < 0)
            index = -index;
        return index;
    }

    public int get(V value) {
        int index = getIndex(value);
        int hashCode = getHashCode(value);

        HashNode<V> head = table.get(index);

        while (head != null) {
            index++;
            if (head.value.equals(value) && head.hashCode == hashCode)
                return index;
            head = head.next;
        }

        return -1;
    }

    public void add(V value)
    {
        int index = getIndex(value);
        int hashCode = getHashCode(value);
        HashNode<V> head = table.get(index);

        while (head != null) {
            if (head.value.equals(value) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = table.get(index);
        HashNode<V> newNode
                = new HashNode<V>(value, hashCode);
        newNode.next = head;
        table.set(index, newNode);

        // Double the hash table size if load factor of 0.6 is exceeded
        if ((1.0 * size) / capacity >= 0.6) {
            ArrayList<HashNode<V> > temp = table;
            table = new ArrayList<>();
            capacity = 2 * capacity;
            size = 0;
            for (int i = 0; i < capacity; i++)
                table.add(null);

            for (HashNode<V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
