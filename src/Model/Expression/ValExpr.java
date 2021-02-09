package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class ValExpr implements Exp {
    Value e;

    public ValExpr(Value v) {
        e=v;
    }
    public String toString(){
        return e.toString();
    }
    public Value eval(MyIDict<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        return e;
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
}