package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;

import Model.Type.IntType;

import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationalExpr implements Exp {
    Exp e1;
    Exp e2;
    int op;

    public RelationalExpr(Exp ex1, Exp ex2, int opi) {
        e1 = ex1;
        e2 = ex2;
        op = opi;
    }

    public String toString() {
        if (op == 1)
            return e1.toString() + "<" + e2.toString();
        if (op == 2)
            return e1.toString() + "<=" + e2.toString();
        if (op == 3)
            return e1.toString() + "==" + e2.toString();
        if (op == 4)
            return e1.toString() + "!=" + e2.toString();
        if (op == 5)
            return e1.toString() + ">" + e2.toString();
        if(op==6)
            return e1.toString()+ ">="+e2.toString();
        else
            return null;

    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new BoolValue(n1 < n2);
                if (op == 2) return new BoolValue(n1 <= n2);
                if (op == 3) return new BoolValue(n1 == n2);
                if (op == 4) return new BoolValue(n1 != n2);
                if (op == 5) return new BoolValue(n1 > n2);
                if (op == 6) return new BoolValue(n1 >= n2);
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())) {
            if(typ2.equals(new IntType())) {
                return new IntType();
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");
    }
}

