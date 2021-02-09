package Model.Stmt;

import Model.ADT.MyIDict;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;

public class newBarrier implements IStmt {
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private String var;
    private Exp exp1;

    public newBarrier(String var, Exp exp1) {
        this.var = var;
        this.exp1 = exp1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        Value v = exp1.eval(state.getSymTable(), state.getHeap());
        int number = ((IntValue) v).getVal();
        int index = state.getBarrier().put(new Pair<>( v,new ArrayList<Integer>()));
        state.getSymTable().add(var, new IntValue(index));
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }
}
