package wt.bs.dao.user;

import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.UserCriteria;
import wt.bs.domain.entity.UserEntity;


@TableDes(nameSpace = "userMapper", tableName = "User")
@Repository
public class UserDao extends AbstractCommonDao<UserEntity, UserCriteria, Long> {
}
