package wt.bs.service.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wt.bs.dao.example.DialectDao;
import wt.bs.domain.criteria.DialectCriteria;
import wt.bs.domain.entity.DialectEntity;

import java.util.List;

@Service
public class DialectService {

    @Autowired
    private DialectDao dialectDao;

    public String getAnswer(String problems) {
        List<DialectEntity> list = dialectDao.selectList(new DialectCriteria());
        StringBuilder answers = new StringBuilder();
        for (DialectEntity dialect : list) {
            if (problems.contains(dialect.getDialect())) {
                answers.append(",").append(dialect.getDialect());
            }
        }
        return answers.toString();
    }
}
