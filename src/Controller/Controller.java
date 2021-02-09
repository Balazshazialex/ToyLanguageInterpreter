package Controller;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.MyException;
import Model.PrgState;
import Model.Stmt.IStmt;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepo repository;

    public IRepo getRepository() {
        return repository;
    }

    ExecutorService executor;

    public Controller(IRepo repo) {
        repository = repo;
        executor = Executors.newFixedThreadPool(2);
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        List<Integer> heapTableAddressedAddresses = heap.values().stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> {
                    RefValue v1 = (RefValue) value;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
        heapTableAddressedAddresses.addAll(symTableAddr);

        return heap.entrySet().stream()
                .filter(e -> heapTableAddressedAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    Map<Integer, Value> conservativeMofoGarbageCollector(List<MyIDict<String,Value>> symTables, Map<Integer, Value> heap) {
        List<Integer> heapTableAddressedAddresses = heap.values().stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> {
                    RefValue v1 = (RefValue) value;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
        symTables.forEach(symTable->{
            heapTableAddressedAddresses.addAll(getAddrFromSymTable(symTable.getValtable().values()));
        });


        return heap.entrySet().stream()
                .filter(e -> heapTableAddressedAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException {
        prgList.forEach(prg -> repository.logPrgStateExec(prg));
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
                try {
                    return future.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            prgList.addAll(newPrgList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        prgList.forEach(prg -> repository.logPrgStateExec(prg));
        repository.setPrgList(prgList);
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while (prgList.size() > 0) {
            try {
                conservativeMofoGarbageCollector(prgList.stream().map(PrgState::getSymTable).collect(Collectors.toList()),prgList.get(0).getHeap().getHashMap());
                oneStepForAllPrg(prgList);
            } catch (MyException e) {
                e.printStackTrace();
            }
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        repository.setPrgList(prgList);
    }

}
