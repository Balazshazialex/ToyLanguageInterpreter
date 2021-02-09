package Model.ADT;

import Model.Value.Value;
import sample.Pair;
import sample.TPair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Semaphore implements ISemaphore {
    private Map<Integer, Pair<Integer, ArrayList<Integer>>> table;
    private int id;

    public Semaphore() {
        table = new HashMap<>();
        id = 0;
    }

    @Override
    public int put(Pair<Integer, ArrayList<Integer>> valueArrayListPair) {
        table.put(++id, valueArrayListPair);
        return id;
    }

    @Override
    public Pair<Integer, ArrayList<Integer>> get(int key) {
        return table.get(key);
    }

    @Override
    public void put(Integer key, Pair<Integer, ArrayList<Integer>> value) {
       table.put(key, value);
    }

    @Override
    public Collection<? extends TPair<Integer,Integer, ArrayList<Integer>>> getAll() {
        ArrayList<TPair<Integer,Integer,ArrayList<Integer>>> a=new ArrayList<>();
        table.forEach((k, v) ->{
                    TPair p=new TPair(k,v.getFirst(),v.getSecond());
                    a.add(p);
                }
        );
        return  a;
    }
}
