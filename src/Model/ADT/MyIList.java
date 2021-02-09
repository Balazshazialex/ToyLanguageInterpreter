package Model.ADT;

import java.util.ArrayList;

public interface MyIList<T> {
    void add(T elem);
    void remove(T elem);
    T get_elem(int poz);
    String toString();
    ArrayList<T> getAll();
}
