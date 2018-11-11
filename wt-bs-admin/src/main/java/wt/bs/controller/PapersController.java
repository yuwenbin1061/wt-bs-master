package wt.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.PapersCriteria;
import wt.bs.domain.entity.PapersEntity;
import wt.bs.domain.entity.TeacherEntity;
import wt.bs.result.BaseResult;
import wt.bs.service.example.PapersService;
import wt.bs.service.user.TeacherService;


@Controller
@Slf4j
public class PapersController extends MgrBaseController {

    @Autowired
    private PapersService  papersService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/papers/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("view/papers");
        view.addObject("loginName", super.getUserName());
        view.addObject("identity", super.getIdentity());
        return view;
    }

    @RequestMapping(value = "/papers/getList")
    @ResponseBody
    public BaseResult getList(PapersCriteria criteria, Integer offset, Integer limit) {
        if (super.getIdentity().equals("1")) {
            TeacherEntity teacherEntity = teacherService.selectOne(super.getUserId());
            criteria.setTeacherCode(teacherEntity.getCode());
        }
        Page<PapersEntity> pageInfo = papersService.pageQuery(criteria, offset, limit);
        return BaseResult.success(pageInfo);
    }
}
