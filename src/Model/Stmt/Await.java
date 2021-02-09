package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;

public class Await implements IStmt {
    private String var;
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();

    public Await(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        if(state.getSymTable().exists(var)){
            int index = ((IntValue)state.getSymTable().get_elem(var)).getVal();
            Pair<Value, ArrayList<Integer>> barrier = state.getBarrier().get(index);
            ArrayList<Integer> threadsWaiting = barrier.getSecond();
            Value barrierThreshold = barrier.getFirst();
            if (threadsWaiting.size() < ((IntValue)barrierThreshold).getVal()){
                if (!threadsWaiting.contains(state.getId_thread())){
                    threadsWaiting.add(state.getId_thread());
                    state.getBarrier().put(index, new Pair<>( barrierThreshold,threadsWaiting));
                }
                state.getExeStack().push(this);
            }
        }
        else throw new MyException("Var not in symtable");
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }
}
