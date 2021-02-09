package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.Expression.RelationalExpr;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public class SwitchStmt implements IStmt{
    private Exp checkexp;
    private Exp exp1;
    private IStmt stmt1;
    private Exp exp2;
    private IStmt stmt2;
    private IStmt stmt3;

    public SwitchStmt(Exp checkexp, Exp exp1, IStmt stmt1, Exp exp2, IStmt stmt2, IStmt stmt3) {
        this.checkexp = checkexp;
        this.exp1 = exp1;
        this.stmt1 = stmt1;
        this.exp2 = exp2;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exestack=state.getExeStack();
        IStmt statement=new IfStmt(new RelationalExpr(checkexp,exp1,3),stmt1,
                new IfStmt(new RelationalExpr(checkexp,exp2,3),stmt2,stmt3));
        exestack.push(statement);
        state.setExeStack(exestack);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        stmt1.typecheck(typeEnv.clone_dict());
        stmt2.typecheck(typeEnv.clone_dict());
        stmt3.typecheck(typeEnv.clone_dict());
        if (exp1.typecheck(typeEnv).equals(checkexp.typecheck(typeEnv)))
            if (exp2.typecheck(typeEnv).equals(checkexp.typecheck(typeEnv)))
                return typeEnv;
            else
                throw new MyException("Check expression and second expression dont match in type");
        else
            throw new MyException("Check expression and first expression dont match in type");

    }

    @Override
    public String toString(){
        return "switch( " + checkexp.toString() + ") \n(case( " + exp1.toString() + " ) " + stmt1.toString() + ")\n(case( " + exp2.toString() + " ) " + stmt2.toString() + ")\n(default " + stmt3.toString() + " )";
    }
}
