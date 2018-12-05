package wt.bs.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.common.SpecialDialectHandler;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.domain.entity.AnswerDetialEntity;
import wt.bs.domain.entity.AnswerEntity;
import wt.bs.domain.entity.ProblemEntity;
import wt.bs.domain.entity.StudentEntity;
import wt.bs.exception.BsException;
import wt.bs.result.BaseResult;
import wt.bs.service.example.AnswerService;
import wt.bs.service.example.DialectService;
import wt.bs.service.example.ProblemService;
import wt.bs.service.user.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/answer")
@Slf4j
public class AnswerController extends MgrBaseController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private DialectService dialectSrvice;

    @RequestMapping(value = "/list")
    public ModelAndView answer(Long id) {

        ProblemEntity problemEntity = problemService.selectOne(id);
        List<AnswerDetialEntity> answerDetialList = dialectSrvice.getAnswerDetials(problemEntity.getAnswer().split(","));

        ModelAndView view = new ModelAndView("view/answer");
        view.addObject("loginName", super.getUserName());
        view.addObject("problemId", id);
        view.addObject("identity", super.getIdentity());
        view.addObject("descs", problemEntity.getDescs());
        view.addObject("answer", problemEntity.getAnswer());
        view.addObject("answerDetials", answerDetialList);
        return view;
    }


    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult getList(AnswerCriteria criteria, Integer offset, Integer limit) {
        String identity = super.getIdentity();
        ProblemEntity problemEntity = problemService.selectOne(criteria.getProblemId());
        if (problemEntity.getIsShow() == 0 && "2".equals(identity)) {
            StudentEntity studentEntity = studentService.selectOne(super.getUserId());
            criteria.setStudentCode(studentEntity.getCode());
        }
        Page<AnswerEntity> pageInfo = answerService.pageQuery(criteria, offset, limit);
        return BaseResult.success(pageInfo);
    }

    @RequestMapping(value = "/answerPage")
    public ModelAndView answerPage(Long id) {
        ProblemEntity entity = problemService.selectOne(id);
        ModelAndView view = new ModelAndView("view/answerAdd");
        view.addObject("loginName", super.getUserName());
        view.addObject("problemId", id);
        view.addObject("identity", super.getIdentity());
        view.addObject("descs", entity.getDescs());
        view.addObject("batchNo",entity.getBatchNo());
        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(String answer, Long problemId) {
        try {
            String identity = super.getIdentity();
            if (!"2".equals(identity)) {
                throw new BsException("教師はこの質問に回答できません");
            }
            StudentEntity studentEntity = studentService.selectOne(super.getUserId());
            AnswerEntity answerEntity = answerService.selectOne(studentEntity.getCode(), problemId);
            if (answerEntity != null) {
                throw new BsException("回答するチャンスは一回だけです");
            }
            ProblemEntity problemEntity = problemService.selectOne(problemId);

            AnswerEntity saveAnswer = new AnswerEntity();
            saveAnswer.setAnswer(answer);
            saveAnswer.setBatchNo(problemEntity.getBatchNo());
            saveAnswer.setProblemId(problemId);
            saveAnswer.setDescs(problemEntity.getDescs());
            saveAnswer.setLevels(problemEntity.getLevels());
            saveAnswer.setScore(problemEntity.getScore());
            saveAnswer.setStudentCode(studentEntity.getCode());
            saveAnswer.setCreateTime(new Date());
            saveAnswer.setUpdateTime(new Date());
            saveAnswer.setCreateUser("sys");
            saveAnswer.setUpdateUser("sys");
            saveAnswer.setSysVersion(1);

            // 设置为题答案
            String answers = problemEntity.getAnswer();
            // 答案
            List<String> answersList = new ArrayList<>(Arrays.asList(answers.split(",")));
            // 学生回答
            List<String> answersListParams = new ArrayList<>(Arrays.asList(answer.split(",")));
            List<String> doingAnswerList = new ArrayList<>();
            List<String> doneAnswerList = new ArrayList<>();
            List<String> failAnswerList = new ArrayList<>();

            // 取未回答答案
            for (String exceptAnswer : answersList) {
                boolean flag = true;
                for (String realAnswer : answersListParams) {
                    if (realAnswer.contains(exceptAnswer)) {
                        flag = false;
                        // 利用api判断答案是否正确
                        if (SpecialDialectHandler.isDoneAnswer(problemEntity.getDescs(), realAnswer, exceptAnswer)) {
                            doneAnswerList.add(exceptAnswer);
                        }
                        break;
                    }
                }

                if (flag) {
                    doingAnswerList.add(exceptAnswer);
                }
            }

            // 取得回答错误答案
            for (String realAnswer : answersListParams) {
                boolean flag = true;
                for (String exceptAnswer : answersList) {
                    if (realAnswer.contains(exceptAnswer)) {
                        // 利用api判断答案是否正确
                        if (SpecialDialectHandler.isDoneAnswer(problemEntity.getDescs(), realAnswer, exceptAnswer)) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    failAnswerList.add(realAnswer);
                }
            }


            saveAnswer.setDoingAnswer(StringUtils.join(doingAnswerList, ","));
            saveAnswer.setDoneAnswer(StringUtils.join(doneAnswerList, ","));
            saveAnswer.setFailAnswer(StringUtils.join(failAnswerList, ","));

            // 设置分数
            Long score = 0L;
            if (answer.equals(problemEntity.getAnswer())) {
                score = problemEntity.getScore();
            } else if (!org.springframework.util.StringUtils.isEmpty(saveAnswer.getDoneAnswer())) {
                score = problemEntity.getPartScore();
            }
            saveAnswer.setCurrentScore(score);
            answerService.add(saveAnswer);
            return BaseResult.success(score);
        } catch (BsException e) {
            return BaseResult.failure(e.getMsg());
        } catch (Exception e) {
            return BaseResult.failure("エラー");
        }
    }
}
