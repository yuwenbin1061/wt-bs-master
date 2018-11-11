package wt.bs.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wt.bs.dao.example.ProblemDao;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.ProblemCriteria;
import wt.bs.domain.entity.ProblemEntity;

@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    public Page<ProblemEntity> pageQuery(ProblemCriteria criteria, Integer offset, Integer limit) {
        if (!StringUtils.isEmpty(criteria.getDescs())){
            criteria.setDescs("%" + criteria.getDescs() + "%");
        }
        return problemDao.pageSelect(criteria, limit, offset);
    }

    public ProblemEntity selectOne(Long id){
        ProblemCriteria criteria = new ProblemCriteria();
        criteria.setId(id);
        return problemDao.findOne(criteria);
    }

    public boolean update(ProblemEntity entity){
        return problemDao.update(entity) > 0;
    }

    public boolean save(ProblemEntity entity){
        return problemDao.save(entity) > 0;
    }
}
