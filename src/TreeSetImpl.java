import java.util.*;

public class TreeSetImpl<T extends Comparable<T>> implements TreeSet<T> {
    private Node<T> root;
    private int size;
    private Comparator<T> comparator;
    private int traversalLimit = Integer.MAX_VALUE;

    public void setTraversalLimit(int limit) {
        this.traversalLimit = limit;
    }
    private class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        int height;

        public Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }

    public TreeSetImpl() {
        this.comparator = null;
    }

    public TreeSetImpl(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null element");
        }
        if (contains(element)) {
            return false;
        }
        root = addRec(root, element);
        size++;
        return true;
    }

    private Node<T> addRec(Node<T> node, T element) {
        if (node == null) {
            return new Node<>(element);
        }
        int comparisonResult;
        if (comparator != null) {
            comparisonResult = comparator.compare(element, node.value);
        } else {
            comparisonResult = element.compareTo(node.value);
        }
        if (comparisonResult < 0) {
            node.left = addRec(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = addRec(node.right, element);
        } else {
            return node;
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return balance(node);
    }
    private Node<T> balance(Node<T> node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getHeight(node.left.right) > getHeight(node.left.left)) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        else if (balanceFactor < -1) {
            if (getHeight(node.right.left) > getHeight(node.right.right)) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    private int getBalanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null element");
        }
        if (!contains(element)) {
            return false;
        }
        root = removeRec(root, element);
        size--;
        return true;
    }

    private Node<T> removeRec(Node<T> node, T element) {
        if (node == null) {
            return null;
        }

        int comparisonResult = element.compareTo(node.value);

        if (comparisonResult < 0) {
            node.left = removeRec(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = removeRec(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T> minNode = findMin(node.right);
                node.value = minNode.value;
                node.right = removeRec(node.right, minNode.value);
            }
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return balance(node);
    }


    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean contains(T element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<T> node, T element) {
        if (node == null) {
            return false;
        }
        if (element.equals(node.value)) {
            return true;
        }

        int comparisonResult = element.compareTo(node.value);
        if (comparisonResult < 0) {
            return containsRec(node.left, element);
        } else {
            return containsRec(node.right, element);
        }
    }

    @Override
    public T find(T element) {
        return findRec(root, element);
    }

    private T findRec(Node<T> node, T element) {
        if (node == null) {
            return null;
        }

        int comparisonResult = element.compareTo(node.value);

        if (comparisonResult == 0) {
            return node.value;
        } else if (comparisonResult < 0) {
            return findRec(node.left, element);
        } else {
            return findRec(node.right, element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterable<T> inOrderTraversal() {
        return new InOrderTraversalIterable();
    }

    private class InOrderTraversalIterable implements Iterable<T> {
        @Override
        public Iterator<T> iterator() {
            return new InOrderTraversalIterator();
        }
    }

    private class InOrderTraversalIterator implements Iterator<T> {
        private Node<T> current = root;
        private MyList<Node<T>> stack = new MyList<>();
        private int count = 0;

        private void pushLeftNodes(Node<T> node) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
        }

        public InOrderTraversalIterator() {
            pushLeftNodes(current);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() && count < traversalLimit;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> node = stack.removeLast();
            T value = node.value;
            pushLeftNodes(node.right);
            count++;
            return value;
        }
    }
}
