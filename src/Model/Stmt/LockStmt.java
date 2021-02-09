package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class LockStmt implements IStmt{
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private String var;

    public LockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        if(state.getSymTable().exists(var))
        {
            Value v=state.getSymTable().get_elem(var);
            int index=((IntValue) v).getVal();
            if(state.getLock().exists(index)){
                if (state.getLock().get(index) == -1){
                    state.getLock().put(index, state.getId_thread());
                }else{
                    state.getExeStack().push(this);
                }
            }
            else{
                throw new MyException("Lock inexistent");
            }
        }
        else throw new MyException("Lock not in table");
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }
    @Override
    public String toString() {
        return "lock(" + var + ")";
    }
}
