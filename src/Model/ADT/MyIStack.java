package Model.ADT;

import java.util.List;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    String toString();
    boolean isEmpty();
    int size();

    List<T> getAll();
}
