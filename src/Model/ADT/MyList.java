package Model.ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    ArrayList<T> outlist;
    public MyList(){
       outlist=new ArrayList<T>();
    }
    @Override
    public void add(T elem) {
        outlist.add(elem);
    }

    @Override
    public void remove(T elem) {
        outlist.remove(elem);
        }


    @Override
    public T get_elem(int poz) {
        return outlist.get(poz);
    }

    @Override
    public String toString() {
        StringBuilder messsage= new StringBuilder();
        for (T t : outlist) {
            messsage.append(t.toString());
            messsage.append('\n');
        }
        return String.valueOf(messsage);
    }

    @Override
    public ArrayList<T> getAll() {
        ArrayList<T> a=new ArrayList<>();
        for (T i:outlist
             ) {
            a.add(i);
        }
        return a;
    }


}
