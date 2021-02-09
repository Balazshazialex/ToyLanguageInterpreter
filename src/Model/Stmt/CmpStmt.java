package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public class CmpStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CmpStmt(IStmt firsty, IStmt secondy) {
        first = firsty;
        snd = secondy;
    }

    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return snd.typecheck(first.typecheck(typeEnv));
    }
}

