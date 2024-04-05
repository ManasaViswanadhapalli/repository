package com.DAO;

import java.util.List;
import model.Worker;

public interface WorkerDao {
    void insert(Worker worker);
    void update(Worker worker);
    void delete(Long workerId);
    Worker getWorkerById(Long workerId);
    List<Worker> getAllWorkers();
}

