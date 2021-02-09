package Model.ADT;

import sample.Pair;

import java.util.Collection;

public interface ILock {
    int put(int value);

    boolean exists(int index);

    int get(int key);

    void put(int key, int value);

    Collection<? extends Pair<Integer, Integer>> getAll();
}
