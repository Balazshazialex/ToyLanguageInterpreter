package Model.ADT;

import sample.Pair;

import java.util.*;

public class MyDict<K, T> implements MyIDict<K, T> {
    HashMap<K, T> valtable;

    public MyDict() {
        valtable = new HashMap<K, T>();
    }

    @Override
    public void add(K k1, T t1) {
        valtable.put(k1, t1);
    }

    @Override
    public void remove(K k1) {
        valtable.remove(k1);
    }

    @Override
    public void update(K k1, T t1) {
        valtable.put(k1, t1);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        Set<K> keys = valtable.keySet();
        for (K key : keys) {
            String thingy = key.toString();
            thingy += "<-";
            thingy += this.get_elem(key).toString();
            message.append(thingy);
            message.append('\n');
            thingy = "";
        }
        return String.valueOf(message);
    }

    @Override
    public T get_elem(K k1) {
        return valtable.get(k1);
    }

    @Override
    public boolean exists(K k1) {
        return valtable.containsKey(k1);
    }

    @Override
    public HashMap<K, T> getValtable() {
        return valtable;
    }

    public MyIDict<K, T> clone_dict() {
        MyIDict<K, T> di = new MyDict<>();
        for(K key : valtable.keySet())
            di.add(key, valtable.get(key));
        return di;
    }
    @Override
    public ArrayList<Pair<K, T>> getAll() {
        ArrayList<Pair<K,T>> a=new ArrayList<>();
        valtable.forEach((k, v) ->{
                       Pair p=new Pair(k,v);
                       a.add(p);
                }
                );
        return  a;
    }

    @Override
    public ArrayList<K> getListView() {
        ArrayList<K> a=new ArrayList<>();
        valtable.forEach((k, v) ->{
                    a.add(k);
                }
        );
        return  a;
    }
}
