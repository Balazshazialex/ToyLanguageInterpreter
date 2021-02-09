package Model.ADT;

import Model.Value.Value;
import sample.Pair;
import sample.TPair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ISemaphore {


    int put(Pair<Integer, ArrayList<Integer>> valueArrayListPair);

    Pair<Integer, ArrayList<Integer>> get(int key);

    void put(Integer key, Pair<Integer, ArrayList<Integer>> value);

    Collection<? extends TPair<Integer, Integer, ArrayList<Integer>>> getAll();
}
