package Repository;

import Model.MyException;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Repo implements IRepo {
    ArrayList<PrgState> all_programs;
    String logFilePath;
    int k = 0;

    public Repo(PrgState state, String logFilePathy) {
        all_programs = new ArrayList<PrgState>();
        all_programs.add(state);
        logFilePath = logFilePathy;
    }



    @Override
    public void logPrgStateExec(PrgState current_prog) {
        int ok = 0;
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(PrgState.getId());
            logFile.println("ExeStack");
            logFile.println(current_prog.getExeStack().toString());
            logFile.println("SymTable");
            logFile.println(current_prog.getSymTable().toString());
            logFile.println("OutTable");
            logFile.println(current_prog.getOut().toString());
            logFile.println("FileTable");
            logFile.println(current_prog.getFiletable().toString());
            logFile.println("Heap");
            logFile.println(current_prog.getHeap().toString());
            logFile.println("Semaphore Table");
            logFile.println(current_prog.getSemaphore().toString());
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return all_programs;
    }



    @Override
    public void setPrgList(List<PrgState> listy) {
        all_programs = (ArrayList<PrgState>) listy;
    }

    @Override
    public PrgState getPrgStateId(int currentId) {
        for(int i=0;i<all_programs.size();i++){
            if(all_programs.get(i).getId_thread()==currentId)
                return all_programs.get(i);
        }
        return null;
    }
}
