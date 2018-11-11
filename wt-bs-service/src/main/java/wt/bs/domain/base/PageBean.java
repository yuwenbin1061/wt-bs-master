package wt.bs.domain.base;


import java.io.Serializable;
import java.util.Collection;

public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int PAGE_SIZE_MAX = 100;
    private long pageNo = 1L;
    private int pageSize = 20;
    private int maxPageSize = 100;
    private long totalCount;
    private Collection<T> resultList;

    public PageBean() {
    }

    public PageBean(long pageNo, int pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    public PageBean(long totalCount, Collection<T> resultList) {
        this.totalCount = totalCount;
        this.resultList = resultList;
    }

    public long getTotalPage() {
        long totalPage = 0L;
        if(this.totalCount > 0L) {
            totalPage = this.totalCount % (long)this.pageSize == 0L?this.totalCount / (long)this.pageSize:this.totalCount / (long)this.pageSize + 1L;
        }

        return totalPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize < 1) {
            pageSize = 20;
        } else if(pageSize > this.maxPageSize) {
            pageSize = this.maxPageSize;
        }

        this.pageSize = pageSize;
    }

    public long getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(long pageNo) {
        if(pageNo < 1L) {
            pageNo = 1L;
        }

        this.pageNo = pageNo;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        if(totalCount < 0L) {
            totalCount = 0L;
        }

        this.totalCount = totalCount;
    }

    public <C extends Collection<T>> C getResultList() {
        return this.resultList == null?null: (C) this.resultList;
    }

    public void setResultList(Collection<T> resultList) {
        this.resultList = resultList;
    }

    /** @deprecated */
    @Deprecated
    public void setPage(int page) {
        this.setPageNo((long)page);
    }

    /** @deprecated */
    @Deprecated
    public long getPage() {
        return this.pageNo;
    }

    public int getMaxPageSize() {
        return this.maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize < 0?100:maxPageSize;
    }
}
