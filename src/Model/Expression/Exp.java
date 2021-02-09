package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;
import Model.Type.Type;
import Model.Value.Value;


public interface Exp {
    Value eval(MyIDict<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException;
    Type typecheck(MyIDict<String,Type> typeEnv) throws MyException;
}
