package Model.ADT;

import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CyclicBarrier implements ICyclicBarrier {
    private Map<Integer, Pair<Value, ArrayList<Integer>>> content;
    private int adress;

    public CyclicBarrier() {
        content=new HashMap<>();
        adress=0;
    }

    @Override
    public int put(Pair<Value,ArrayList<Integer>> value) {
        content.put(++adress, value);
        return adress;
    }

    @Override
    public void put(int key, Pair<Value,ArrayList<Integer>> value) {
        content.put(key, value);
    }

    @Override
    public Pair<Value,ArrayList<Integer>> get(int key) {
        return content.get(key);
    }
}
