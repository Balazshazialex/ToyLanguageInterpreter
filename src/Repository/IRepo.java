package Repository;

import Model.MyException;
import Model.PrgState;

import java.util.List;

public interface IRepo {
    void logPrgStateExec(PrgState current_prog);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> listy);

    PrgState getPrgStateId(int currentId);
}
