package wt.bs.service.example;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wt.bs.common.SpecialDialectEnum;
import wt.bs.common.SpecialDialectHandler;
import wt.bs.dao.example.DialectDao;
import wt.bs.domain.criteria.DialectCriteria;
import wt.bs.domain.entity.AnswerDetialEntity;
import wt.bs.domain.entity.DialectEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class DialectService {

    @Autowired
    private DialectDao dialectDao;

    public String getAnswer(String problems) {

        List<DialectEntity> list = dialectDao.selectList(new DialectCriteria());
        StringBuilder answers = new StringBuilder();
        int i = 0;

        // 先判断特殊词汇
        List<String> specialAnswers = SpecialDialectHandler.getAnswers(problems);
        if (CollectionUtils.isNotEmpty(specialAnswers)){
            for (String specialAnswer : specialAnswers) {
                if (i == 0) {
                    answers.append(specialAnswer);
                    i++;
                } else {
                    answers.append(",").append(specialAnswer);
                }
            }
        }

        // 再判断数据库中固定方言
        for (DialectEntity dialect : list) {
            if (problems.contains(dialect.getDialect())) {
                if (i == 0) {
                    answers.append(dialect.getDialect());
                    i++;
                } else {
                    answers.append(",").append(dialect.getDialect());
                }
            }

            if (problems.contains(dialect.getKanji())) {
                if (i == 0) {
                    answers.append(dialect.getKanji());
                    i++;
                } else {
                    answers.append(",").append(dialect.getKanji());
                }
            }
        }

        return answers.toString();
    }

    public List<AnswerDetialEntity> getAnswerDetials(String[] answers){
        List<AnswerDetialEntity> resultList = new ArrayList<>();
        DialectCriteria criteria = new DialectCriteria();
        for (String answer : answers){

            Boolean isSpecial = false;

            for (SpecialDialectEnum dialectTarget : SpecialDialectEnum.values())
            {
                if (StringUtils.equals(answer, dialectTarget.getDialect()))
                {
                    isSpecial = true;
                    AnswerDetialEntity answerDetial = new AnswerDetialEntity();
                    answerDetial.setDialect(dialectTarget.getDialect());
                    answerDetial.setKanji(null);
                    answerDetial.setNominal(dialectTarget.getNominal());
                    answerDetial.setTranslation(dialectTarget.getTranslation());
                    resultList.add(answerDetial);
                }
            }

            if (!isSpecial) {
                criteria.setDialect(answer);
                criteria.setKanji(null);
                List<DialectEntity> list = dialectDao.selectList(criteria);

                if (CollectionUtils.isEmpty(list))
                {
                    criteria.setDialect(null);
                    criteria.setKanji(answer);
                    list = dialectDao.selectList(criteria);
                }

                AnswerDetialEntity answerDetial = new AnswerDetialEntity();
                answerDetial.setDialect(list.get(0).getDialect());
                answerDetial.setKanji(list.get(0).getKanji());
                answerDetial.setNominal(list.get(0).getNominal());
                answerDetial.setTranslation(list.get(0).getTranslation() + "（例文：" + list.get(0).getExample() + "）");
                resultList.add(answerDetial);
            }
        }

        return resultList;
    }
}
