import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B <T> implements Deque61B<T>{
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
    public void addFirst(T x) {
        First = Math.floorMod(First - 1, capacity);
        array[First] = x;
        size++;
    }

    @Override
    public void addLast(T x) {
        array[Final] = x;
        Final = Math.floorMod(Final + 1, capacity);
        size++;
    }

    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return (size != 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        else{
           return array[Math.floorMod(First + index - 1, capacity)];
        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
