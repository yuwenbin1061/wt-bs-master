package wt.bs.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wt.bs.dao.user.TeacherDao;
import wt.bs.domain.criteria.TeacherCriteria;
import wt.bs.domain.entity.TeacherEntity;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public String getSerialNumber() {
        String maxTeacherCode = teacherDao.selectMaxTeacherCode();
        if (StringUtils.isEmpty(maxTeacherCode)) {
            return "T_1000";
        }
        String[] arr = maxTeacherCode.split("_");
        return "T_" + (Long.parseLong(arr[1]) + 1);
    }

    public boolean add(TeacherEntity entity) {
        return teacherDao.save(entity) > 0;
    }


    public TeacherEntity selectOne(Long userId){
        TeacherCriteria criteria = new TeacherCriteria();
        criteria.setUserId(userId);
        return teacherDao.selectOne(criteria);
    }


}
