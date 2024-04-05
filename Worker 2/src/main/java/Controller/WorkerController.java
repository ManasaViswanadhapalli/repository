package Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import model.Worker;
import service.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    // Insert Worker
    @PostMapping
    public ResponseEntity<Void> insertWorker(@RequestBody Worker worker) {
        workerService.insert();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update Worker
    @PutMapping("/{workerId}")
    public ResponseEntity<Void> updateWorker(@PathVariable Long workerId, @RequestBody Worker workerDetails) {
        workerDetails.setId(workerId);
        workerService.update(workerDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete Worker
    @DeleteMapping("/{workerId}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long workerId) {
        workerService.delete(workerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get Worker by ID
    @GetMapping("/{workerId}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long workerId) {
        Worker worker = workerService.getByWorkerId(workerId);
        if (worker != null) {
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All Workers
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = workerService.getAllWorkers();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }
}
