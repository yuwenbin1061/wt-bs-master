package wt.bs.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wt.bs.dao.example.PapersDao;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.PapersCriteria;
import wt.bs.domain.entity.PapersEntity;

@Service
public class PapersService {

    @Autowired
    private PapersDao papersDao;

    public Page<PapersEntity> pageQuery(PapersCriteria criteria, Integer offset, Integer limit) {
        return papersDao.pageSelect(criteria, offset, limit);
    }

    public boolean save(PapersEntity entity) {
        return papersDao.save(entity) > 0;
    }

    public boolean exist(String batchNo) {
        PapersCriteria criteria = new PapersCriteria();
        criteria.setBatchNo(batchNo);
        return papersDao.findOne(criteria) != null;
    }
}
