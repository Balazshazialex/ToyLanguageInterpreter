package Model.ADT;

import sample.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public interface MyIDict<K,T> {
    void add(K k1,T t1);
    void remove(K k1);
    void update(K k1,T t1);
    String toString();
    T get_elem(K k1);
    boolean exists(K k1);
    MyIDict<K, T> clone_dict();
    HashMap<K, T> getValtable();

    ArrayList<Pair<K, T>> getAll();
    ArrayList<K> getListView();
}
