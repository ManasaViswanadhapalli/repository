package service;

import model.Worker;
import java.util.List;

public interface WorkerService {
    void insert();
    void update(Worker worker);
    void delete(Long workerId);
    Worker getByWorkerId(Long workerId);
    List<Worker> getAllWorkers();
}

