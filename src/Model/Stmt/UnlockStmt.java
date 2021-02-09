package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class UnlockStmt implements IStmt{

    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private String var;

    public UnlockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        System.out.println(String.valueOf(state.getId_thread() + "-atomicUnlockSTART " + var));
        try {
            Value v = state.getSymTable().get_elem(var);
            int lockID=((IntValue)v).getVal();
            if (state.getLock().get(lockID) == state.getId_thread()) {
                state.getLock().put(lockID, -1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(String.valueOf(state.getId_thread() + "-atomicUnlockEND " + var));
            lock.unlock();
        }
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }
    @Override
    public String toString() {
        return "unlock(" + var + ")";
    }
}
