package wt.bs.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wt.bs.dao.user.StudentDao;
import wt.bs.domain.criteria.StudentCriteria;
import wt.bs.domain.entity.StudentEntity;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public String getSerialNumber() {
        String maxStudentCode = studentDao.selectMaxStudentCode();
        if (StringUtils.isEmpty(maxStudentCode)) {
            return "ST_10000";
        }
        String[] arr = maxStudentCode.split("_");
        return "ST_" + (Long.parseLong(arr[1]) + 1);
    }

    public boolean add(StudentEntity entity) {
        return studentDao.save(entity) > 0;
    }

    public StudentEntity selectOne(Long userId){
        StudentCriteria criteria = new StudentCriteria();
        criteria.setUserId(userId);
        return studentDao.selectOne(criteria);
    }
}
