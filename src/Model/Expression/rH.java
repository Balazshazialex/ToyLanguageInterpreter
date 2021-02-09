package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class rH implements Exp {
    Exp expr;

    public rH(Exp expresion) {
        expr = expresion;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, IHeap<Integer, Value> hp) throws MyException {
        Value v = expr.eval(tbl, hp);
        if (v.getType() instanceof RefType) {
            int addr = ((RefValue) v).getAddress();
            if (hp.isDefined(addr)) {
                return hp.get(addr);
            } else throw new MyException("Key not in heap");
        } else throw new MyException("The expression is not a refValue");
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typ = expr.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else throw new MyException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "rH(" +
                 expr +
                ')';
    }
}
