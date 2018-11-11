package wt.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.ProblemCriteria;
import wt.bs.domain.entity.PapersEntity;
import wt.bs.domain.entity.ProblemEntity;
import wt.bs.domain.entity.TeacherEntity;
import wt.bs.exception.BsAssert;
import wt.bs.exception.BsException;
import wt.bs.param.ProblemParams;
import wt.bs.result.BaseResult;
import wt.bs.service.example.DialectService;
import wt.bs.service.example.PapersService;
import wt.bs.service.example.ProblemService;
import wt.bs.service.user.TeacherService;

import java.util.Date;

@Controller
@Slf4j
public class ProblemController extends MgrBaseController{

    @Autowired
    private ProblemService problemService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PapersService  papersService;
    @Autowired
    private DialectService dialectService;

    @RequestMapping(value = "/problems/list")
    public ModelAndView list(String batchNo) {
        ModelAndView view = new ModelAndView("view/problem");
        view.addObject("loginName", super.getUserName());
        view.addObject("identity", super.getIdentity());
        view.addObject("batchNo", batchNo);
        return view;
    }

    @RequestMapping(value = "/problems/getList")
    @ResponseBody
    public BaseResult getList(ProblemCriteria criteria, Integer offset, Integer limit) {
        if (super.getIdentity().equals("1")) {
            TeacherEntity teacherEntity = teacherService.selectOne(super.getUserId());
            criteria.setTeacherCode(teacherEntity.getCode());
        }
        Page<ProblemEntity> pageInfo = problemService.pageQuery(criteria, limit, offset);
        return BaseResult.success(pageInfo);
    }

    @RequestMapping(value = "/problems/isShow")
    @ResponseBody
    public BaseResult isShow(Long id) {
        try {
            BsAssert.notNull(id, "idは間違っています");
            ProblemEntity entity = problemService.selectOne(id);
            BsAssert.notNull(entity, "該当する問題はありません");
            entity.setIsShow(1);
            if (problemService.update(entity)) {
                return BaseResult.success("成功");
            }
            return BaseResult.failure("失敗");
        } catch (BsException e) {
            log.error("展示失败，id:{},失败信息:{}", id, e);
            return BaseResult.failure("失敗");
        } catch (Exception e) {
            log.error("展示错误，id:{},失败信息:{}", id, e);
            return BaseResult.failure("失敗");
        }
    }

    @RequestMapping(value = "/problems/add")
    public ModelAndView add(String batchNo) {

        if (StringUtils.isEmpty(batchNo)) {
            batchNo = "BN" + System.currentTimeMillis();
        }

        ModelAndView view = new ModelAndView("view/problemAdd");
        view.addObject("identity", super.getIdentity());
        view.addObject("loginName", super.getUserName());
        view.addObject("batchNo", batchNo);
        return view;
    }

    @RequestMapping(value = "/problems/save")
    @ResponseBody
    public BaseResult save(ProblemParams params) {
        try {
            BsAssert.isBlank(params.getBatchNo(), "番号は間違っています");
            BsAssert.isBlank(params.getDescs(), "テキストを入力してください");
            BsAssert.notNull(params.getLevels(), "どのグループに対して質問しますか");
            BsAssert.notNull(params.getScore(), "点数(完全一致)を入力してください");
            BsAssert.notNull(params.getPartScore(), "点数(部分一致)を入力してください");

            if (!papersService.exist(params.getBatchNo())) {
                PapersEntity entity = new PapersEntity();
                entity.setBatchNo(params.getBatchNo());
                TeacherEntity teacherEntity = teacherService.selectOne(super.getUserId());
                entity.setTeacherCode(teacherEntity.getCode());
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entity.setUpdateUser(teacherEntity.getName());
                entity.setCreateUser(teacherEntity.getName());
                papersService.save(entity);
            }

            ProblemEntity entity = new ProblemEntity();
            BeanUtils.copyProperties(params, entity);
            entity.setAnswer(dialectService.getAnswer(params.getDescs()));
            entity.setSysVersion(1);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            entity.setCreateUser("sys");
            entity.setUpdateUser("sys");
            entity.setBatchNo(params.getBatchNo());
            entity.setIsShow(0);
            TeacherEntity teacherEntity = teacherService.selectOne(super.getUserId());
            entity.setTeacherCode(teacherEntity.getCode());
            if (problemService.save(entity)){
                return BaseResult.success(entity.getBatchNo());
            }
            return BaseResult.failure("失敗");
        }catch (BsException e){
            return BaseResult.failure(e.getMsg());
        }catch (Exception e){
            return BaseResult.failure("失敗");
        }
    }
}
