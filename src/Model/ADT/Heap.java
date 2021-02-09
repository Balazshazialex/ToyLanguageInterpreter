package Model.ADT;


import sample.Pair;

import java.util.*;

public class Heap<Value> implements IHeap<Integer, Value> {
    private HashMap<Integer, Value> hashMap;
    private int nextInteger = 1;


    public Heap(HashMap<Integer, Value> hashMap) {
        this.hashMap = hashMap;

    }

    @Override
    public HashMap<Integer, Value> getHashMap() {
        return hashMap;
    }

    @Override
    public void setContent(Map<Integer, Value> hashMap) {
        this.hashMap = (HashMap<Integer, Value>) hashMap;

    }

    @Override
    public int add(Value value) {
        hashMap.put(nextInteger, value);
        return nextInteger++;
    }

    @Override
    public Value get(Integer v1) throws NullPointerException {
        return hashMap.get(v1);
    }

    @Override
    public void remove(Integer v1) {

    }

    @Override
    public void update(Integer integer, Value val) throws NullPointerException {
        hashMap.put(integer, val);
    }


    @Override
    public Collection<Value> getValues() {
        return null;
    }

    @Override
    public Iterable<Integer> getIntegers() {
        return null;
    }

    @Override
    public boolean isDefined(Integer id) {
        return hashMap.containsKey(id);
    }
    public String toString() {
        StringBuilder message = new StringBuilder();
        Set<Integer> keys = hashMap.keySet();
        for (Integer key : keys) {
            String thingy = key.toString();
            thingy += "<-";
            thingy += this.get(key).toString();
            message.append(thingy);
            message.append('\n');
            thingy = "";
        }
        return String.valueOf(message);
    }
    @Override
    public ArrayList<Pair<Integer, Value>> getAll() {
        ArrayList<Pair<Integer,Value>> a=new ArrayList<>();
        hashMap.forEach((k, v) ->{
                    Pair p=new Pair(k,v);
                    a.add(p);
                }
        );
        return  a;
    }
}