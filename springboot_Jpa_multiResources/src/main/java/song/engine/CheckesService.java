package song.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckesService {

    @Autowired
    private ICheckesDao checkesDao;

    public Checkes save(Checkes entity) {
        return checkesDao.save(entity);
    }

    public List<Checkes> findAll() {
        return checkesDao.findAll();
    }

}
