package wt.bs.dao.user;

import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.TeacherCriteria;
import wt.bs.domain.entity.TeacherEntity;

@TableDes(nameSpace = "teacherMapper", tableName = "Teacher")
@Repository
public class TeacherDao extends AbstractCommonDao<TeacherEntity, TeacherCriteria, Long> {

    public String selectMaxTeacherCode(){
        return this.getSqlSession().selectOne("teacherMapper.selectMaxTeacherCode");
    }
}

