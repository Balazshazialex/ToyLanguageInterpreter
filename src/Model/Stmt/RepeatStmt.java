package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.Expression.NotExpression;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import com.sun.glass.events.WheelEvent;

public class RepeatStmt implements IStmt {
    private IStmt stmt1;
    private Exp exp1;

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exestack=state.getExeStack();
        IStmt stmt=new CmpStmt(stmt1,
                new whileStmt(new NotExpression(exp1),stmt1));
        exestack.push(stmt);
        state.setExeStack(exestack);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }

    public RepeatStmt(IStmt stmt1, Exp exp1) {
        this.stmt1 = stmt1;
        this.exp1 = exp1;
    }

    @Override
    public String toString() {
        return "Repeat " +
                  stmt1.toString() +
                " until " + exp1.toString() ;
    }
}
