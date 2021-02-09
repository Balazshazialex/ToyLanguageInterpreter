package Model.Stmt;

import Model.ADT.ISemaphore;
import Model.ADT.MyIDict;
import Model.ADT.Semaphore;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import sample.Pair;

import java.util.ArrayList;

public class CreateSemaphore implements IStmt {
    String var;
    Exp exp1;
    private static java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    public CreateSemaphore(String var, Exp exp1) {
        this.var = var;
        this.exp1 = exp1;
    }

    public String getVar() {
        return var;
    }

    public Exp getExp1() {
        return exp1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        if(exp1.eval(state.getSymTable(),state.getHeap())!=null){
           Value v1= exp1.eval(state.getSymTable(),state.getHeap());
           int number1= ((IntValue) v1).getVal();
           int index = state.getSemaphore().put(new Pair<>(number1,new ArrayList<Integer>()));
           if(state.getSymTable().exists(var)) {
               state.getSymTable().update(var, new IntValue(index));
           }
           else throw new MyException("Not a good variable");
        }
        else throw new MyException("Error at evaluating expression");
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "newSemaphore(" + var + ", " + exp1.toString() + ")";
    }
}
