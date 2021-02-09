package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.Expression.ValExpr;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String v, Exp valExpr) {
        id=v;
        exp=valExpr;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDict<String, Value> symTbl = state.getSymTable();
        if (symTbl.exists(id)) {
            Value val = exp.eval(symTbl,state.getHeap());
            Type typId = symTbl.get_elem(id).getType();

            if ((val.getType()).equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable" + id + " and type of  the assigned expression do not match");
        } else
            throw new MyException("the used variable" + id + " was not declared before");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.get_elem(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}


