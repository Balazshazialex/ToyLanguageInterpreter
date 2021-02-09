package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public class ConditionalAssign implements IStmt {
    private String var;
    private Exp e1;
    private Exp TrueCond;
    private Exp FalseCond;

    public ConditionalAssign(String var, Exp e1, Exp trueCond, Exp falseCond) {
        this.var = var;
        this.e1 = e1;
        TrueCond = trueCond;
        FalseCond = falseCond;
    }

    @Override
    public String toString() {
        return var + " = " + e1.toString() + " ? " + TrueCond.toString() + " : " + FalseCond.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> executionStack = state.getExeStack();
        IStmt statement=new IfStmt(e1,new AssignStmt(var,TrueCond),new AssignStmt(var,FalseCond));
        executionStack.push(statement);
        state.setExeStack(executionStack);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }


}
