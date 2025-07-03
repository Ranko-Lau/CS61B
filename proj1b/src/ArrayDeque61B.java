import java.util.List;
import java.lang.Math

public class ArrayDeque61B <T> implements Deque61B{
    private int First;
    private int Final;
    private T[] array;
    private int capacity;
    private int size;

    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        First = 0;
        Final = 0;
        capacity = 8;
        size = 0;
    }

    @Override
    public void addFirst(Object x) {

    }

    @Override
    public void addLast(Object x) {

    }

    @Override
    public List toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object removeFirst() {
        return null;
    }

    @Override
    public Object removeLast() {
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object getRecursive(int index) {
        return null;
    }
}
