package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Release implements IStmt{
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private String var;

    public Release(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();

        try{
            Value v = state.getSymTable().get_elem(var);
            int index=((IntValue) v).getVal();
            Pair<Integer,ArrayList<Integer>> semaphore = state.getSemaphore().get(index);
            ArrayList<Integer> threadsRunning = semaphore.getSecond();
            Integer semaphoreThreshold = semaphore.getFirst();

            if (threadsRunning.contains(state.getId_thread())){
                threadsRunning = threadsRunning.stream().filter(id -> !id.equals(state.getId_thread())).collect(Collectors.toCollection(ArrayList::new));
                state.getSemaphore().put(index, new Pair<>( semaphoreThreshold,threadsRunning));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
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
        return "release(" + var + ")\n";
    }
}
