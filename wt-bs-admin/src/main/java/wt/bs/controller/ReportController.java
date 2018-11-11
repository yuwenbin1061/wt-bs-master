package wt.bs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.domain.entity.AnswerEntity;
import wt.bs.domain.entity.ProblemEntity;
import wt.bs.domain.entity.ReportEntity;
import wt.bs.domain.entity.StudentEntity;
import wt.bs.result.BaseResult;
import wt.bs.service.example.AnswerService;
import wt.bs.service.example.ProblemService;
import wt.bs.service.user.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/report")
@Slf4j
public class ReportController extends MgrBaseController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/list")
    public ModelAndView report() {
        ModelAndView view = new ModelAndView("view/report");
        view.addObject("loginName", super.getUserName());
        view.addObject("problemId", 83);
        view.addObject("identity", super.getIdentity());
        return view;
    }

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult getRightReport(AnswerCriteria criteria, Integer offset, Integer limit) {

        List<ReportEntity> rightReportList = new ArrayList<>();
        ReportEntity report1 = new ReportEntity();
        report1.setAnswer("答案1");
        report1.setCount(10);
        ReportEntity report2 = new ReportEntity();
        report2.setAnswer("答案2");
        report2.setCount(10);
        ReportEntity report3 = new ReportEntity();
        report3.setAnswer("答案3");
        report3.setCount(10);
        ReportEntity report4 = new ReportEntity();
        report4.setAnswer("答案4");
        report4.setCount(10);

        rightReportList.add(report1);
        rightReportList.add(report2);
        rightReportList.add(report3);
        rightReportList.add(report4);


        Page<ReportEntity> pageInfo = new Page<>();
        pageInfo.setTotal(rightReportList.size());
        pageInfo.setRows(rightReportList);
        return BaseResult.success(pageInfo);
    }

}
