package Model.Stmt;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class newStmt implements IStmt {
    String var_name;
    Exp expr;

    public newStmt(String var_name, Exp expr) {
        this.var_name = var_name;
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> symTbl = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        if (symTbl.exists(var_name)) {
            Value v = expr.eval(symTbl, state.getHeap());
            Value v1 = symTbl.get_elem(var_name);
            int addr = state.getHeap().add(v.getCopy());
            RefValue ref = (RefValue) state.getSymTable().get_elem(var_name);
            ref.setAddress(addr);

        } else throw new MyException("Variable doesnt exist");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.get_elem(var_name);
        Type typexp = expr.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    public String toString() {
        return "new(" +
                var_name +
                ", " + expr.toString() +
                ')';
    }




}

