package wt.bs.dao.example;

import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.domain.entity.AnswerEntity;

@TableDes(nameSpace = "answerMapper", tableName = "Answer")
@Repository
public class AnswerDao extends AbstractCommonDao<AnswerEntity, AnswerCriteria, Long> {
}
