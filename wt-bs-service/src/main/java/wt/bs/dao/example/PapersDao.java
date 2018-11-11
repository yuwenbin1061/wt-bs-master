package wt.bs.dao.example;

import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.PapersCriteria;
import wt.bs.domain.criteria.ProblemCriteria;
import wt.bs.domain.entity.PapersEntity;
import wt.bs.domain.entity.ProblemEntity;

@TableDes(nameSpace = "papersMapper", tableName = "Papers")
@Repository
public class PapersDao extends AbstractCommonDao<PapersEntity, PapersCriteria, Long> {
}
