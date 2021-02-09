package Model.ADT;

import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;
import java.util.List;

public interface ICyclicBarrier {
    int put(Pair<Value, ArrayList<Integer>> value);

    void put(int key, Pair<Value, ArrayList<Integer>> value);

    Pair<Value,ArrayList<Integer>> get(int key);
}
