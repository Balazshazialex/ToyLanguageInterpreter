package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt{
    public NopStmt() {
    }

    @Override
    public String toString() {
        return "NopStmt()";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
    return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}