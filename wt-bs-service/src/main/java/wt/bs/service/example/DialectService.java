package wt.bs.service.example;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        for (DialectEntity dialect : list) {
            if (problems.contains(dialect.getDialect())) {
                if (i==0){
                    answers.append(dialect.getDialect());
                    i++;
                }
                answers.append(",").append(dialect.getDialect());
            }
        }

        return answers.toString();
    }

    public List<AnswerDetialEntity> getAnswerDetials(String[] answers){
        List<AnswerDetialEntity> resultList = new ArrayList<>();
        DialectCriteria criteria = new DialectCriteria();
        for (String answer : answers){

            criteria.setDialect(answer);
            List<DialectEntity> list = dialectDao.selectList(criteria);

            AnswerDetialEntity answerDetial = new AnswerDetialEntity();
            answerDetial.setDialect(list.get(0).getDialect());
            answerDetial.setKanji(list.get(0).getKanji());
            answerDetial.setNominal(list.get(0).getNominal());
            answerDetial.setTranslation(list.get(0).getTranslation());
            resultList.add(answerDetial);
        }

        return resultList;
    }
}
