package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyStack;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public class forkStmt implements IStmt {
    IStmt statement;

    public forkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        PrgState newprg= new PrgState(new MyStack<>(),state.getSymTable().clone_dict(),state.getOut(),state.getFiletable(), state.getHeap(),statement,state.getLast_id()+10,
                state.getSemaphore(), state.getLock(),state.getBarrier());
        state.setLast_id(state.getLast_id()+10);
        return newprg;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return statement.typecheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork(" +
                  statement.toString() +
                ')';
    }
}
