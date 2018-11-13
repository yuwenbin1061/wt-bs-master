package wt.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.result.BaseResult;
import wt.bs.service.example.ReportService;

@Controller
@RequestMapping(value = "/report")
@Slf4j
public class ReportController extends MgrBaseController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/show")
    public ModelAndView report(Long id) {
        ModelAndView view = new ModelAndView("view/report");
        view.addObject("loginName", super.getUserName());
        view.addObject("problemId", id);
        view.addObject("identity", super.getIdentity());
        return view;
    }

    @RequestMapping(value = "getRightList")
    @ResponseBody
    public BaseResult getRightList(AnswerCriteria criteria, Integer offset, Integer limit) {
        return BaseResult.success(reportService.pageRightQuery(criteria));
    }

    @RequestMapping(value = "getIncompleteList")
    @ResponseBody
    public BaseResult pageIncompleteQuery(AnswerCriteria criteria, Integer offset, Integer limit) {
        return BaseResult.success(reportService.pageIncompleteQuery(criteria));
    }

    @RequestMapping(value = "getFailList")
    @ResponseBody
    public BaseResult pageFailQuery(AnswerCriteria criteria, Integer offset, Integer limit) {
        return BaseResult.success(reportService.pageFailQuery(criteria));
    }
}
