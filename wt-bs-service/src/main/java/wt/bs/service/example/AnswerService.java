package wt.bs.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wt.bs.dao.example.AnswerDao;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.domain.entity.AnswerEntity;

@Service
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    public Page<AnswerEntity> pageQuery(AnswerCriteria criteria, Integer offset, Integer limit) {
        return answerDao.pageSelect(criteria, offset, limit);
    }

    public boolean add(AnswerEntity entity) {
        return answerDao.save(entity) > 0;
    }

    public AnswerEntity selectOne(String studentCode, Long problemId) {
        AnswerCriteria criteria = new AnswerCriteria();
        criteria.setStudentCode(studentCode);
        criteria.setProblemId(problemId);
        return answerDao.selectOne(criteria);
    }
}
