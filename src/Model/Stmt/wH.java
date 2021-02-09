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

public class wH implements IStmt {
    String var_name;
    Exp e1;

    public wH(String var_name, Exp e1) {
        this.var_name = var_name;
        this.e1 = e1;
    }

    @Override
    public String toString() {
        return "wH(" +
                  var_name +
                ", " + e1 +
                ')';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (state.getSymTable().exists(var_name)) {
            if (state.getSymTable().get_elem(var_name).getType() instanceof RefType) {
                int addr = ((RefValue) state.getSymTable().get_elem(var_name)).getAddress();
                if (state.getHeap().isDefined(addr)) {
                    Value v = e1.eval(state.getSymTable(), state.getHeap());
                    if (v.getType().equals(state.getHeap().get(addr).getType())) {
                        state.getHeap().update(addr, v);
                    }
                } else throw new MyException("Element not in Heap");
            } else throw new MyException("Variable is not a Reftype");
        } else throw new MyException("Not present in Symtbl");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!typeEnv.get_elem(var_name).equals(new RefType(e1.typecheck(typeEnv))))
            throw new MyException("EROROROROROROROROR");
        return typeEnv;
    }
}
