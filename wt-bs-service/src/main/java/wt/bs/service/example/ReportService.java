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

    public Page<ReportEntity> pageQuery(AnswerCriteria criteria) {
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
        Map<String, Integer> answerCounter = new HashMap<>();

        for (AnswerEntity answer : answers) {
            if (!answer.getDoneAnswer().isEmpty()) {
                String[] splitedAnswers = answer.getDoneAnswer().split(",");
                for (String oneAnswer : splitedAnswers) {
                    if (answerCounter.keySet().contains(oneAnswer)) {
                        Integer count1 = answerCounter.get(oneAnswer);
                        answerCounter.put(oneAnswer, ++count1);
                    } else {
                        answerCounter.put(oneAnswer, 1);
                    }
                }
            }
        }

        // convert map to list
        for (String key : answerCounter.keySet()) {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setAnswer(key);
            reportEntity.setCount(answerCounter.get(key));
            doneAnswersReport.add(reportEntity);
        }

        // 按计数降序排序
        Collections.sort(doneAnswersReport, new Comparator<ReportEntity>() {
            @Override
            public int compare(ReportEntity o1, ReportEntity o2) {
                return o2.getCount().compareTo(o1.getCount());
            }
        });

        page.setRows(doneAnswersReport);
        return page;

    }
}
