package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.Expression.VarExpr;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp expr ) {
        exp=expr;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
       state.getOut().add(exp.eval(state.getSymTable(),state.getHeap()));
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}