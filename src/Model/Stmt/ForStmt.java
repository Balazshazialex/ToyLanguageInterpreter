package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.Expression.RelationalExpr;
import Model.Expression.VarExpr;
import Model.MyException;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.Type;

public class ForStmt implements IStmt {
    private String var;
    private Exp Initial;
    private Exp Condition;
    private Exp Modify;
    private IStmt exec;

    public ForStmt(String var, Exp initial, Exp condition, Exp modify, IStmt exec) {
        this.var = var;
        Initial = initial;
        Condition = condition;
        Modify = modify;
        this.exec = exec;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        IStmt statement = new CmpStmt(new VarDeclStmt(var, new IntType()),
                new CmpStmt(
                        new AssignStmt(var, Initial),
                        new whileStmt(new RelationalExpr(new VarExpr(var), Condition, 1), new CmpStmt(
                                exec, new AssignStmt(var, Modify)
                        ))

                ));
        exeStack.push(statement);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (Initial.typecheck(typeEnv).equals(new IntType()))
            if (Condition.typecheck(typeEnv).equals(new IntType()))
                if (Modify.typecheck(typeEnv).equals(new IntType()))
                    return typeEnv;
                else
                    throw new MyException("Modify exp is not oke");
            else
                throw new MyException("Condition exp is not oke");
        else
            throw new MyException("Initial exp is not oke");
    }

    @Override
    public String toString() {
        return "for( " + var + "=" + Initial.toString() + "; " + var + "<" + Condition.toString() + "; " + var + "=" + Modify.toString() + " ) \n " + exec.toString();
    }
}
