package wt.bs.dao.base;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import wt.bs.domain.base.Criteria;
import wt.bs.domain.base.Page;
import wt.bs.domain.base.utils.BeanHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommonDao<M extends Criteria, C extends M, PK> extends SqlSessionDaoSupport implements CommonDao<M, C, PK> {
    private final String sql_select;
    private final String sql_select_one;
    private final String sql_count;
    private final String sql_insert;
    private final String sql_insert_batch;
    private final String sql_update;
    private final String sql_update_batch;
    private final String sql_delete;
    private final String sql_delete_one;
    private final String nameSpace;
    private final String tableName;

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){

        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public AbstractCommonDao() {
        TableDes myTable = (TableDes)this.getClass().getAnnotation(TableDes.class);
        Assert.notNull(myTable);
        this.tableName = myTable.tableName();
        Assert.notNull(this.tableName);
        String nameSpaceTmp = myTable.nameSpace();
        this.nameSpace = StringUtils.isEmpty(nameSpaceTmp)?"":nameSpaceTmp + ".";
        this.sql_select = "select" + this.tableName;
        this.sql_select_one = "select" + "One" + this.tableName;
        this.sql_insert = "insert" + this.tableName;
        this.sql_insert_batch = "insert" + this.tableName + "Batch";
        this.sql_update = "update" + this.tableName;
        this.sql_update_batch = "update" + this.tableName + "Batch";
        this.sql_delete = "delete" + this.tableName;
        this.sql_delete_one = "delete" + "One" + this.tableName;
        this.sql_count = "count" + this.tableName;
    }

    @Override
    public int save(M modelEntity) {
        return this.save(this.sql_insert, modelEntity);
    }

    @Override
    public M saveAndFind(M modelEntity) {
        this.save(this.sql_insert, modelEntity);
        return this.findOne(modelEntity);
    }

    @Override
    public int saveAll(List<M> entityList) {
        return this.saveAll(this.sql_insert_batch, entityList);
    }

    @Override
    public int update(M modelEntry, M modelQuery) {
        return this.update(this.sql_update, modelEntry, modelQuery);
    }

    @Override
    public int updateAll(List<M> entityList) {
        return this.updateAll(this.sql_update_batch, entityList);
    }

    @Override
    public int update(M modelEntry) {
        return this.update(this.sql_update, modelEntry);
    }

    @Override
    public int saveOrUpdate(M modelEntry) {
        return this.saveOrUpdate(this.sql_insert, this.sql_update, modelEntry);
    }

    @Override
    public int saveOrUpdate(List<M> entryList) {
        return this.saveOrUpdate(this.sql_insert, this.sql_update, entryList);
    }

    @Override
    public int delete(PK id) {
        return this.delete(this.sql_delete_one, id);
    }

    @Override
    public int delete(M modelQuery) {
        return this.delete(this.sql_delete, modelQuery);
    }

    @Override
    public M get(PK id) {
        return this.get(this.sql_select_one, id);
    }

    @Override
    public int count(M modelQuery) {
        return this.count(this.sql_count, modelQuery);
    }

    @Override
    public List<M> findList(M modelQuery) {
        return this.findList(this.sql_select, modelQuery);
    }

    @Override
    public List<M> findList(M modelQuery, int pn, int pageSize) {
        return this.findList(this.sql_select, modelQuery, pn, pageSize);
    }

    @Override
    public M findOne(M modelQuery) {
        return (M) this.findOne(this.sql_select, modelQuery);
    }

    @Override
    public boolean exists(PK id) {
        return this.exists(this.sql_select, id);
    }

    protected int save(String statement, M modelEntity) {
        return this.getSqlSession().insert(this.addNameSpace(statement), modelEntity);
    }

    private int saveOrUpdate(String insertStatement, String updateStatement, M modelEntry) {
        return this.getPrimaryFieldValue(modelEntry) == null?this.save(insertStatement, modelEntry):this.update(updateStatement, modelEntry);
    }

    private int saveOrUpdate(String insertStatement, String updateStatement, List<M> entityList) {
        int effectCount = 0;
        if(CollectionUtils.isEmpty(entityList)) {
            return effectCount;
        } else {
            List<M> addList = new ArrayList();
            List<M> updateList = new ArrayList();
            Iterator i$ = entityList.iterator();

            while(i$.hasNext()) {
                M modelEntry = (M) i$.next();
                if(this.getPrimaryFieldValue(modelEntry) == null) {
                    addList.add(modelEntry);
                } else {
                    updateList.add(modelEntry);
                }
            }

            if(CollectionUtils.isNotEmpty(addList)) {
                effectCount += this.saveAll(insertStatement, addList);
            }

            if(CollectionUtils.isNotEmpty(updateList)) {
                effectCount += this.updateAll(updateStatement, updateList);
            }

            return effectCount;
        }
    }

    private int count(String statement, Criteria criteria) {
        Integer result = (Integer)this.getSqlSession().selectOne(this.addNameSpace(statement), criteria);
        return result == null?0:result.intValue();
    }

    protected int update(String statement, M modelEntry, M modelQuery) {
        Map<String, Object> map = BeanHelper.modelToMap(modelEntry, "", "");
        map.putAll(BeanHelper.modelToMap(modelQuery, "_", ""));
        return this.getSqlSession().update(this.addNameSpace(statement), map);
    }

    private int updateAll(String statement, List<M> entityList) {
        return this.getSqlSession().update(this.addNameSpace(statement), entityList);
    }

    protected int update(String statement, M modelEntry) {
        Map<String, Object> map = BeanHelper.modelToMap(modelEntry, "", "");
        return this.getSqlSession().update(this.addNameSpace(statement), map);
    }

    protected M get(String statement, PK id) {
        return (M) this.getSqlSession().selectOne(this.addNameSpace(statement), id);
    }

    private List<M> findList(String statement, Criteria criteria) {
        return this.getSqlSession().selectList(this.addNameSpace(statement), criteria);
    }

    protected <T> List<T> findList(String statement, Map<String, Object> modelQuery) {
        return this.getSqlSession().selectList(this.addNameSpace(statement), modelQuery);
    }

    private List<M> findList(String statement, Criteria criteria, int pn, int pageSize) {
        return this.findList(statement, this.buildCriteria(criteria, pn, pageSize));
    }

    private List<M> findList4Offset(String statement, Criteria criteria, Integer offset, Integer limit) {
        return this.findList(statement, this.buildCriteria4Offset(criteria, offset, limit));
    }

    protected <T> List<T> findList(String statement, Object entity) {
        return this.getSqlSession().selectList(this.addNameSpace(statement), entity);
    }

    protected boolean exists(String statement, PK id) {
        return (this.get(statement, id) == null?Boolean.FALSE:Boolean.TRUE).booleanValue();
    }

    private int saveAll(String statement, List<M> list) {
        return this.getSqlSession().insert(this.addNameSpace(statement), list);
    }

    private int delete(String statement, Object modelQuery) {
        return this.getSqlSession().delete(this.addNameSpace(statement), modelQuery);
    }

    private <T> T findOne(String statement, T modelQuery) {
        return this.getSqlSession().selectOne(this.addNameSpace(statement), modelQuery);
    }

    private String addNameSpace(String statement) {
        return this.nameSpace + statement;
    }

    private Criteria buildCriteria(Criteria criteria, int pn, int pageSize) {
        Assert.notNull(criteria, "查询条件不能为空.");
        int skipResults = pn > 1?(pn - 1) * pageSize:0;
        int maxResults = pageSize > 0?pageSize:10;
        criteria.addExtField("start", Integer.valueOf(skipResults));
        criteria.addExtField("limit", Integer.valueOf(maxResults));
        return criteria;
    }

    private Criteria buildCriteria4Offset(Criteria criteria, Integer offset, Integer limit) {
        Assert.notNull(criteria, "查询条件不能为空.");
        criteria.addExtField("start", offset);
        criteria.addExtField("limit", limit);
        return criteria;
    }

    private Object getPrimaryFieldValue(M modelEntry) {
        Class clazz = modelEntry.getClass();
        Field field = this.findPrimaryField(clazz);
        if(field == null) {
            throw new RuntimeException("方法不支持该实体对象的[保存或更新]操作");
        } else {
            field.setAccessible(true);

            try {
                Object id = field.get(modelEntry);
                return id;
            } catch (IllegalAccessException var6) {
                throw new RuntimeException(var6);
            }
        }
    }

    private Field findPrimaryField(Class clazz) {
        while(clazz != Object.class) {
            Field[] fs = clazz.getDeclaredFields();
            Field[] arr$ = fs;
            int len$ = fs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Field field = arr$[i$];
                if("ID".equals(field.getName().toUpperCase())) {
                    return field;
                }
            }

            clazz = clazz.getSuperclass();
        }

        return null;
    }

    @Override
    public int totalCount(C modelQuery) {
        return this.count(this.sql_count, modelQuery);
    }

    @Override
    public M selectOne(C modelQuery) {
        return (M) this.findOne(this.sql_select, modelQuery);
    }

    @Override
    public List<M> selectList(C modelQuery) {
        return this.findList(this.sql_select, modelQuery);
    }

    @Override
    public List<M> selectList(C modelQuery, int pn, int pageSize) {
        return this.findList(this.sql_select, modelQuery, pn, pageSize);
    }

    @Override
    public Page<M> pageSelect(C modelQuery, Integer offset, Integer limit) {
        Page<M> page = new Page();
        Assert.notNull(modelQuery, "查询条件不能为空.");
        int count = this.count(this.sql_count, modelQuery);
        page.setTotal(count);
        if(count == 0) {
            page.setRows(new ArrayList<>());
            return page;
        } else {
            page.setRows(this.findList4Offset(this.sql_select, modelQuery,offset, limit));
            return page;
        }
    }
}