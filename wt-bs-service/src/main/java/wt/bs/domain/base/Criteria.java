package wt.bs.domain.base;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Criteria implements Serializable {
    public static final String SORT_DIRECTION_ASC = "ASC";
    public static final String SORT_DIRECTION_DESC = "DESC";
    public static final String TABLE_NAME = "TABLE_NAME";
    private List<KeyValue<String, String>> sortItemMap;
    private Map<String, Object> extFields;

    public Criteria() {
    }

    public <C extends Criteria> C addExtField(String fieldName, Object filedValue) {
        if(this.extFields == null) {
            this.extFields = new HashMap();
        }

        this.extFields.put(fieldName, filedValue);
        return (C) this;
    }

    public Map<String, Object> getExtFields() {
        return this.extFields;
    }

    public void setExtFields(Map<String, Object> extFields) {
        this.extFields = extFields;
    }

    public List<KeyValue<String, String>> getSortItemMap() {
        return this.sortItemMap;
    }

    public void setSortItemMap(List<KeyValue<String, String>> sortItemMap) {
        this.sortItemMap = sortItemMap;
    }
}
