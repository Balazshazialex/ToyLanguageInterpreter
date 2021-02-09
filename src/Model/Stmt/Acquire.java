package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;

public class Acquire implements IStmt {
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private String var;

    public Acquire(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        try {
            Value v = state.getSymTable().get_elem(var);
            Pair<Integer, ArrayList<Integer>> semaphore_content = state.getSemaphore().get(((IntValue) v).getVal());
            ArrayList<Integer> threadsRunning = semaphore_content.getSecond();
            Integer semaphoreThreshold = semaphore_content.getFirst();

            if (threadsRunning.size() < (semaphoreThreshold)) {
                if (!threadsRunning.contains(state.getId_thread())) {
                    threadsRunning.add(state.getId_thread());
                    state.getSemaphore().put(((IntValue) v).getVal(), new Pair<>( semaphoreThreshold,threadsRunning));
                }
            } else {
                state.getExeStack().push(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return "acquire(" + var + ")";
    }
}
