package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;

public class NewLock implements IStmt {
    String var;
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();


    public NewLock(String var) {
        this.var = var;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        int index=state.getLock().put(-1);
        state.getSymTable().add(var, new IntValue(index));
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }
    @Override
    public String toString() {
        return "newLock(" + "\"" + var + "\"" + ")";
    }
}
