package com.ywt.laike.hotel.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ywt
 */
@Setter
@Getter
@ToString
public abstract class BaseQuery {
    // 分页 当前页
    private Integer page;
    // 分页 每页数据数
    private Integer limit;
    // 分页 数据开始位置
    @JsonIgnore // 不进行序列化
    private Integer offset;
    // 排序 条件
    private String sortBy;
    // 排序 方式 升序/降序
    private String orderBy;
    // 时间 开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    // 时间 结束时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date estimateInTimeStart;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date estimateInTimeEnd;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date estimateOutTimeStart;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date estimateOutTimeEnd;

    /**
     * 当前宾馆 由controller提供
     * 已作废
     * @see #hotelNo
     */
    @JsonIgnore // 不进行序列化
    @Deprecated
    private String currentSessionHotelNo;

    /**
     * 当前宾馆 由controller提供
     */
    @JsonIgnore // 不进行序列化
    private String hotelNo;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit <= 0) {
            this.limit = 10;
        } else {
            this.limit = limit;
        }
    }

    public Integer getOffset() {
        if (limit != null && page != null) {
            return limit * (page - 1);
        }
        return offset;
    }

    public String getSortBy() {
        if("gmtCreate".equalsIgnoreCase(sortBy)){
            return "gmt_create";
        }
        if("realInTime".equalsIgnoreCase(sortBy)){
            return "real_in_time";
        }
        return null;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrderBy() {
        if("desc".equalsIgnoreCase(orderBy)){
            return "desc";
        }
        return "asc";
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        if (null != endTime && null != startTime && endTime.getTime() <= startTime.getTime()) {
            return null;
        }
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
