package wt.bs.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wt.bs.dao.user.UserDao;
import wt.bs.domain.criteria.UserCriteria;
import wt.bs.domain.entity.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据登录名获取用户信息
     * @param loginName 登录名称
     * @return 用户信息
     */
    public UserEntity findOne(String loginName){
        UserCriteria criteria = new UserCriteria();
        criteria.setLoginName(loginName);
        return userDao.findOne(criteria);
    }

    public UserEntity saveAndFind(UserEntity entity) {
        userDao.save(entity);
        return entity;
    }
}
