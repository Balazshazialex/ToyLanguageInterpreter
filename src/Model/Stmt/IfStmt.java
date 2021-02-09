package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;


public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        MyIDict<String, Value> tbl=state.getSymTable();

        Value val=exp.eval(tbl,state.getHeap());
        BoolValue bool=new BoolValue(true);
        if(val.getType().equals(new BoolType())){
            if(val.equals(bool))
                stk.push(thenS);
            else
                stk.push(elseS);
        }
        else  throw new MyException("Expression must be a bool value");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone_dict());
            elseS.typecheck(typeEnv.clone_dict());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
}