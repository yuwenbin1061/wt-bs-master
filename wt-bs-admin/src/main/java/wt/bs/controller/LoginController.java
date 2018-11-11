package wt.bs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.entity.StudentEntity;
import wt.bs.domain.entity.TeacherEntity;
import wt.bs.domain.entity.UserEntity;
import wt.bs.exception.BsAssert;
import wt.bs.exception.BsException;
import wt.bs.param.RegisterParams;
import wt.bs.result.BaseResult;
import wt.bs.service.user.StudentService;
import wt.bs.service.user.TeacherService;
import wt.bs.service.user.UserService;
import wt.bs.utils.MD5Utils;
import wt.bs.utils.SysConstant;
import wt.bs.utils.TokenUtils;
import wt.bs.vo.MgrSession;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/")
public class LoginController extends MgrBaseController {

    @Resource
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/")
    public String login() {
        return "view/login";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResult login(String loginName, String password) {
        try {
            UserEntity entity = userService.findOne(loginName);
            BsAssert.notNull(entity, "正しく入力してください");

            String md5Password = MD5Utils.md5(password + SysConstant.LOGIN_SALT);
            BsAssert.notEqual(md5Password, entity.getPwd(), "パスワードは間違っています");
            LOGGER.info("==>登陆接口:login,成功,电话:{},密码:{}", loginName, password);
            super.writeSession(new MgrSession(entity.getId(), entity.getIdentity(), loginName));
            return BaseResult.success("ログインできました");
        } catch (BsException e) {
            LOGGER.error("==>登陆接口:login,失败,电话:{},密码:{},异常:{}", loginName, password, e);
            return BaseResult.failure(e);
        } catch (Exception e) {
            LOGGER.error("登陆失败,原因:", e);
            LOGGER.error("==>登陆接口:login,失败,电话:{},密码:{},异常:{}", loginName, password, e);
            return BaseResult.failure(e.getMessage());
        }
    }

    @RequestMapping(value = "index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("view/index");
        view.addObject("loginName", super.getUserName());
        view.addObject("identity", super.getIdentity());
        return view;
    }

    @RequestMapping(value = "/logOut")
    public ModelAndView logOut() {
        try {
            this.delSession();
            return new ModelAndView("view/login");
        } catch (BsException e) {
            LOGGER.error("==>用戶注销:login,失败,异常:{}", e);
            return new ModelAndView("error");
        } catch (Exception e) {
            LOGGER.error("用戶注销错误,原因:", e);
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/signUp")
    public String signUp() {
        return "view/sign_up";
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public BaseResult register(RegisterParams params) {

        try {
            BsAssert.notNull(params.getIdentity(), "教師か学生かどちらかを選んでください");
            BsAssert.notNull(params.getLoginName(), "アカウント名は間違っています");
            BsAssert.notNull(params.getName(), "ユーザー名は間違っています");
            BsAssert.notNull(params.getPwd(), "パスワードは間違っています");
            BsAssert.notNull(params.getSPwd(), "もう一度パスワードを入力してください");
            BsAssert.notNull(params.getSex(), "性別を選んでください");
            BsAssert.notEqual(params.getPwd(), params.getSPwd(), "パスワードは同じではありません");

            UserEntity userEntity = new UserEntity();
            String pwd =  MD5Utils.md5(params.getPwd() + SysConstant.LOGIN_SALT);
            userEntity.setLoginName(params.getLoginName());
            userEntity.setIdentity(params.getIdentity());
            userEntity.setPwd(pwd);
            userEntity.setCreateTime(new Date());
            userEntity.setUpdateTime(new Date());
            userEntity.setCreateUser("sys");
            userEntity.setUpdateUser("sys");

            UserEntity saveUserEntity = userService.saveAndFind(userEntity);

            if (params.getIdentity() == 1){
                TeacherEntity teacherEntity = new TeacherEntity();
                teacherEntity.setCode(teacherService.getSerialNumber());
                teacherEntity.setLoginName(params.getLoginName());
                teacherEntity.setName(params.getName());
                teacherEntity.setPwd(pwd);
                teacherEntity.setSex(params.getSex());
                teacherEntity.setUserId(saveUserEntity.getId());
                teacherEntity.setCreateTime(new Date());
                teacherEntity.setUpdateTime(new Date());
                teacherEntity.setCreateUser("sys");
                teacherEntity.setUpdateUser("sys");
                teacherService.add(teacherEntity);
            }else if (params.getIdentity() == 2){
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setLevels(1);
                studentEntity.setCode(studentService.getSerialNumber());
                studentEntity.setLoginName(params.getLoginName());
                studentEntity.setName(params.getName());
                studentEntity.setPwd(pwd);
                studentEntity.setSex(params.getSex());
                studentEntity.setUserId(saveUserEntity.getId());
                studentEntity.setCreateTime(new Date());
                studentEntity.setUpdateTime(new Date());
                studentEntity.setCreateUser("sys");
                studentEntity.setUpdateUser("sys");
                studentService.add(studentEntity);
            }
            return BaseResult.success("成功");
        }catch (BsException e){
            LOGGER.error("==>注册接口:login,失败,注册信息:{},异常:{}", params, e);
            return BaseResult.failure("操作失败");
        }catch (Exception e){
            LOGGER.error("==>注册接口:login,失败,注册信息:,异常:{}", params, e);
            return BaseResult.failure("操作错误");
        }
    }

}

