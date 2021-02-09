package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

class LogicExpr implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public Value eval(MyIDict<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new BoolValue(n1 && n2);
                if (op == 2) return new BoolValue(n1 || n2);
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType())) {
            if(typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("second operand is not a boolean");
        } else throw new MyException("first operand is not a boolean");
    }

    public String toString(){
        if(op==1)
            return e1.toString()+ "&&"+e2.toString();
        else
            return e1.toString()+ "||"+e2.toString();

    }
}