package wt.bs.dao.user;


import org.springframework.stereotype.Repository;
import wt.bs.dao.base.AbstractCommonDao;
import wt.bs.dao.base.TableDes;
import wt.bs.domain.criteria.StudentCriteria;
import wt.bs.domain.entity.StudentEntity;

@TableDes(nameSpace = "studentMapper", tableName = "Student")
@Repository
public class StudentDao extends AbstractCommonDao<StudentEntity, StudentCriteria, Long> {

    public String selectMaxStudentCode(){
        return this.getSqlSession().selectOne("studentMapper.selectMaxStudentCode");
    }
}

