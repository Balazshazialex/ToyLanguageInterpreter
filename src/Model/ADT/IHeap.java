package Model.ADT;

import Model.MyException;
import sample.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface IHeap<Integer, Value> {


    HashMap<java.lang.Integer, Value> getHashMap();


    void setContent(Map<java.lang.Integer, Value> hashMap);

    int add(Value value);

    Value get(Integer v1) throws MyException;


    void remove(Integer v1);


    void update(Integer integer, Value val) throws MyException;


    Collection<Value> getValues();

    Iterable<Integer> getIntegers();

    boolean isDefined(Integer id);

    ArrayList<Pair<java.lang.Integer, Value>> getAll();
}