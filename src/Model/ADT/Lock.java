package Model.ADT;

import sample.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Lock implements ILock{
    private Map<Integer,Integer> lock_map;
    int adress;

    public Lock() {
        lock_map=new HashMap<>();
        adress=0;
    }

    @Override
    public int put(int value) {
        lock_map.put(++adress, value);
        return adress;
    }

    @Override
    public boolean exists(int index){
        return lock_map.containsKey(index);
    }

    @Override
    public int get(int key) {
        return lock_map.get(key);
    }

    @Override
    public void put(int key, int value) {
        lock_map.put(key, value);
    }

    @Override
    public Collection<? extends Pair<Integer, Integer>> getAll() {
        ArrayList<Pair<Integer,Integer>> a=new ArrayList<>();
       lock_map.forEach((k, v) ->{
                    Pair p=new Pair(k,v);
                    a.add(p);
                }
        );
        return  a;
    }
}
