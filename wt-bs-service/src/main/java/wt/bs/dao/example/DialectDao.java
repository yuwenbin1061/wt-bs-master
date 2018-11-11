package wt.bs.dao.example;

import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.DialectCriteria;
import wt.bs.domain.criteria.UserCriteria;
import wt.bs.domain.entity.DialectEntity;
import wt.bs.domain.entity.UserEntity;


@TableDes(nameSpace = "dialectMapper", tableName = "Dialect")
@Repository
public class DialectDao extends AbstractCommonDao<DialectEntity, DialectCriteria, Long> {
}
