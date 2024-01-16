public interface TreeSet <T extends Comparable<T>>{
    boolean add(T element);
    boolean remove(T element);
    boolean contains(T element);
    T find(T element);
    int size();
    boolean isEmpty();
    Iterable<T> inOrderTraversal();

}
