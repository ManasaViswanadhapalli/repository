package service;
import com.DAO.WorkerDao;
import model.Worker;
import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	@Service
	public class WorkerServiceImpl implements WorkerService {

	    @Autowired
	    private WorkerDao workerDao;

	    @Override
	    public void insert() {
	        workerDao.insert(null);
	    }

	    @Override
	    public void update(Worker worker) {
	        workerDao.update(worker);
	    }

	    @Override
	    public void delete(Long workerId) {
	        workerDao.delete(workerId);
	    }

	    @Override
	    public Worker getByWorkerId(Long workerId) {
	        return workerDao.getWorkerById(workerId);
	    }

	    @Override
	    public List<Worker> getAllWorkers() {
	        return workerDao.getAllWorkers();
	    }
	}
