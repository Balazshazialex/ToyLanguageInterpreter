package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;
import Model.Type.Type;
import Model.Value.Value;

public class VarExpr implements Exp {
    String id;

    public VarExpr(String v) {
        id=v;
    }

    public Value eval(MyIDict<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        return tbl.get_elem(id);
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv.get_elem(id);
    }

    public String toString(){
        return id;
    }
}