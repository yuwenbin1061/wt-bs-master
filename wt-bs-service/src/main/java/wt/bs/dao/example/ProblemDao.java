package wt.bs.dao.example;

import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.ProblemCriteria;
import wt.bs.domain.entity.ProblemEntity;

@TableDes(nameSpace = "problemMapper", tableName = "Problem")
@Repository
public class ProblemDao extends AbstractCommonDao<ProblemEntity, ProblemCriteria, Long> {
}
