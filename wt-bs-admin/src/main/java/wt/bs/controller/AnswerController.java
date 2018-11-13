package wt.bs.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wt.bs.controller.base.MgrBaseController;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.domain.entity.AnswerEntity;
import wt.bs.domain.entity.ProblemEntity;
import wt.bs.domain.entity.StudentEntity;
import wt.bs.exception.BsException;
import wt.bs.result.BaseResult;
import wt.bs.service.example.AnswerService;
import wt.bs.service.example.ProblemService;
import wt.bs.service.user.StudentService;

import java.util.*;

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

    @RequestMapping(value = "/list")
    public ModelAndView answer(Long id) {

        ProblemEntity problemEntity = problemService.selectOne(id);

        ModelAndView view = new ModelAndView("view/answer");
        view.addObject("loginName", super.getUserName());
        view.addObject("problemId", id);
        view.addObject("identity", super.getIdentity());
        view.addObject("descs", problemEntity.getDescs());
        view.addObject("answer", problemEntity.getAnswer());
        return view;
    }


    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult getList(AnswerCriteria criteria, Integer offset, Integer limit) {
        String identity = super.getIdentity();
        ProblemEntity problemEntity = problemService.selectOne(criteria.getProblemId());
        if (problemEntity.getIsShow() == 0 && "2".equals(identity)){
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
        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(String answer, Long problemId) {
        try {
            String identity = super.getIdentity();
            if (!"2".equals(identity)){
                throw new BsException("教師はこの質問に回答できません");
            }
            StudentEntity studentEntity = studentService.selectOne(super.getUserId());
            AnswerEntity answerEntity = answerService.selectOne(studentEntity.getCode(), problemId);
            if (answerEntity != null){
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
            List<String> answersList = new ArrayList<>(Arrays.asList(answers.split(",")));
            List<String> answersListParams =  new ArrayList<>(Arrays.asList(answer.split(",")));
            answersList.removeAll(answersListParams);
            saveAnswer.setDoingAnswer(StringUtils.join(answersList, ","));

            List<String> answersList1 = new ArrayList<>(Arrays.asList(answers.split(",")));
            List<String> answersListParams1 =  new ArrayList<>(Arrays.asList(answer.split(",")));
            answersList1.retainAll(answersListParams1);
            saveAnswer.setDoneAnswer(StringUtils.join(answersList1, ","));

            List<String> answersList2 = new ArrayList<>(Arrays.asList(answers.split(",")));
            List<String> answersListParams2 =  new ArrayList<>(Arrays.asList(answer.split(",")));
            answersListParams2.removeAll(answersList2);
            saveAnswer.setFailAnswer(StringUtils.join(answersListParams2, ","));

            // 设置分数
            Long score = 0L;
            if (answer.equals(problemEntity.getAnswer())){
                score = problemEntity.getScore();
            }else if (!org.springframework.util.StringUtils.isEmpty(saveAnswer.getDoneAnswer())){
                score = problemEntity.getPartScore();
            }
            saveAnswer.setCurrentScore(score);
            answerService.add(saveAnswer);
            return BaseResult.success(score);
        }catch (BsException e){
            return BaseResult.failure(e.getMsg());
        }catch (Exception e){
            return BaseResult.failure("エラー");
        }
    }
}
