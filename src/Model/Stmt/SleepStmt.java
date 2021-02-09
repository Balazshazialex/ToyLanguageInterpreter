package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public class SleepStmt implements IStmt {
    Integer value;

    public SleepStmt(Integer value) {
        this.value = value;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(value!=0)
        {
            MyIStack<IStmt> executionStack = state.getExeStack();
            executionStack.push(new SleepStmt(value - 1));
            state.setExeStack(executionStack);
        }
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "sleep( " + value.toString() + " )";
    }
}
