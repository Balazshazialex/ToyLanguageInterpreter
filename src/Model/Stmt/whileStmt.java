package Model.Stmt;

import Model.ADT.MyIDict;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class whileStmt implements IStmt
{
    private Exp e1;
    private IStmt stmt;

    public whileStmt(Exp e, IStmt s)
    {
        e1 = e;
        stmt = s;
    }

    @Override
    public PrgState execute(PrgState prgState)
    {
        try
        {
            if (e1.eval(prgState.getSymTable(), prgState.getHeap()).equals(new BoolValue(true)))
            {
                prgState.getExeStack().push(new whileStmt(e1, stmt));
                prgState.getExeStack().push(stmt);
                return null;
            }
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typexp=e1.typecheck(typeEnv);
        if (typexp.equals(new BoolType())){
            stmt.typecheck(typeEnv.clone_dict());
            return typeEnv;
        }
        else throw new MyException("The condition of while has not the type bool");
    }

    @Override
    public String toString()
    {
        return "while(" + e1.toString() + "){" + stmt.toString() + "}";
    }
}
