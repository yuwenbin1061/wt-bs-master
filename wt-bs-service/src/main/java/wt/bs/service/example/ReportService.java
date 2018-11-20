package wt.bs.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wt.bs.dao.example.AnswerDao;
import wt.bs.domain.base.Page;
import wt.bs.domain.criteria.AnswerCriteria;
import wt.bs.domain.entity.AnswerEntity;
import wt.bs.domain.entity.ReportEntity;

import java.util.*;

@Service
public class ReportService {

    @Autowired
    private AnswerDao answerDao;

    public Page<ReportEntity> pageRightQuery(AnswerCriteria criteria) {
        Page<ReportEntity> page = new Page();

        // 取得答案个数
        int count = answerDao.count(criteria);
        page.setTotal(count);
        if (count == 0)
            return page;
        // 取得所有答案
        List<AnswerEntity> answers = answerDao.selectList(criteria);
        // 统计回答正确答案
        List<ReportEntity> doneAnswersReport = new ArrayList<>();
        // 答案的种类
        Map<String, ReportEntity> answerMapper = new HashMap<>();

        for (AnswerEntity answer : answers) {
            // 正确答案不为空的情况
            if (!answer.getDoneAnswer().isEmpty()) {
                // 获得该生所有正确答案
                String[] splitedAnswers = answer.getDoneAnswer().split(",");
                for (String oneAnswer : splitedAnswers) {
                    if (answerMapper.keySet().contains(oneAnswer)) {
                        ReportEntity report = answerMapper.get(oneAnswer);
                        report.setCount(report.getCount() + 1);
                        report.setStdName(report.getStdName()+","+answer.getStdName());
                        answerMapper.put(oneAnswer, report);
                    } else {
                        ReportEntity report = new ReportEntity();
                        report.setCount(1);
                        report.setStdName(answer.getStdName());
                        answerMapper.put(oneAnswer, report);
                    }
                }
            }
        }

        // convert map to list
        for (String key : answerMapper.keySet()) {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setAnswer(key);
            reportEntity.setCount(answerMapper.get(key).getCount());
            reportEntity.setStdName(answerMapper.get(key).getStdName());
            doneAnswersReport.add(reportEntity);
        }

        // 按计数降序排序
        Collections.sort(doneAnswersReport, (o1, o2) -> o2.getCount().compareTo(o1.getCount()));

        page.setRows(doneAnswersReport);
        return page;
    }

    public Page<ReportEntity> pageIncompleteQuery(AnswerCriteria criteria) {
        Page<ReportEntity> page = new Page();

        // 取得答案个数
        int count = answerDao.count(criteria);
        page.setTotal(count);
        if (count == 0)
            return page;
        // 取得所有答案
        List<AnswerEntity> answers = answerDao.selectList(criteria);
        // 统计未正确答案
        List<ReportEntity> doingAnswersReport = new ArrayList<>();
        // 答案的种类
        Map<String, ReportEntity> answerMapper = new HashMap<>();

        for (AnswerEntity answer : answers) {
            if (!answer.getDoingAnswer().isEmpty()) {
                String[] splitedAnswers = answer.getDoingAnswer().split(",");
                for (String oneAnswer : splitedAnswers) {
                    if (answerMapper.keySet().contains(oneAnswer)) {
                        ReportEntity report = answerMapper.get(oneAnswer);
                        report.setCount(report.getCount() + 1);
                        report.setStdName(report.getStdName()+","+answer.getStdName());
                        answerMapper.put(oneAnswer, report);
                    } else {
                        ReportEntity report = new ReportEntity();
                        report.setCount(1);
                        report.setStdName(answer.getStdName());
                        answerMapper.put(oneAnswer, report);
                    }
                }
            }
        }

        // convert map to list
        for (String key : answerMapper.keySet()) {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setAnswer(key);
            reportEntity.setCount(answerMapper.get(key).getCount());
            reportEntity.setStdName(answerMapper.get(key).getStdName());
            doingAnswersReport.add(reportEntity);
        }

        // 按计数降序排序
        Collections.sort(doingAnswersReport, new Comparator<ReportEntity>() {
            @Override
            public int compare(ReportEntity o1, ReportEntity o2) {
                return o2.getCount().compareTo(o1.getCount());
            }
        });

        page.setRows(doingAnswersReport);
        return page;

    }

    public Page<ReportEntity> pageFailQuery(AnswerCriteria criteria) {
        Page<ReportEntity> page = new Page();

        // 取得答案个数
        int count = answerDao.count(criteria);
        page.setTotal(count);
        if (count == 0)
            return page;
        // 取得所有答案
        List<AnswerEntity> answers = answerDao.selectList(criteria);
        // 统计回答错误答案
        List<ReportEntity> failAnswersReport = new ArrayList<>();
        // 答案的种类
        Map<String, ReportEntity> answerMapper = new HashMap<>();

        for (AnswerEntity answer : answers) {
            if (!answer.getFailAnswer().isEmpty()) {
                String[] splitedAnswers = answer.getFailAnswer().split(",");
                for (String oneAnswer : splitedAnswers) {
                    if (answerMapper.keySet().contains(oneAnswer)) {
                        ReportEntity report = answerMapper.get(oneAnswer);
                        report.setCount(report.getCount() + 1);
                        report.setStdName(report.getStdName()+","+answer.getStdName());
                        answerMapper.put(oneAnswer, report);
                    } else {
                        ReportEntity report = new ReportEntity();
                        report.setCount(1);
                        report.setStdName(answer.getStdName());
                        answerMapper.put(oneAnswer, report);
                    }
                }
            }
        }

        // convert map to list
        for (String key : answerMapper.keySet()) {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setAnswer(key);
            reportEntity.setCount(answerMapper.get(key).getCount());
            reportEntity.setStdName(answerMapper.get(key).getStdName());
            failAnswersReport.add(reportEntity);
        }

        // 按计数降序排序
        Collections.sort(failAnswersReport, new Comparator<ReportEntity>() {
            @Override
            public int compare(ReportEntity o1, ReportEntity o2) {
                return o2.getCount().compareTo(o1.getCount());
            }
        });

        page.setRows(failAnswersReport);
        return page;

    }
}
