package com.springboot.zcssc.zcssc.bean.db;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cp_open_data")
public class CpOpenData {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 期号
     */
    @Column(name = "open_trunk_number")
    private String openTrunkNumber;

    /**
     * 位置
     */
    @Column(name = "open_index")
    private String openIndex;

    /**
     * 号码
     */
    @Column(name = "open_number")
    private String openNumber;

    /**
     * 开奖时间
     */
    @Column(name = "open_time")
    private Date openTime;

    /**
     * 开奖日期
     */
    @Column(name = "open_date")
    private String openDate;

    /**
     * 插入时间
     */
    @Column(name = "insert_time")
    private Date insertTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取期号
     *
     * @return open_trunk_number - 期号
     */
    public String getOpenTrunkNumber() {
        return openTrunkNumber;
    }

    /**
     * 设置期号
     *
     * @param openTrunkNumber 期号
     */
    public void setOpenTrunkNumber(String openTrunkNumber) {
        this.openTrunkNumber = openTrunkNumber == null ? null : openTrunkNumber.trim();
    }

    /**
     * 获取位置
     *
     * @return open_index - 位置
     */
    public String getOpenIndex() {
        return openIndex;
    }

    /**
     * 设置位置
     *
     * @param openIndex 位置
     */
    public void setOpenIndex(String openIndex) {
        this.openIndex = openIndex == null ? null : openIndex.trim();
    }

    /**
     * 获取号码
     *
     * @return open_number - 号码
     */
    public String getOpenNumber() {
        return openNumber;
    }

    /**
     * 设置号码
     *
     * @param openNumber 号码
     */
    public void setOpenNumber(String openNumber) {
        this.openNumber = openNumber == null ? null : openNumber.trim();
    }

    /**
     * 获取开奖时间
     *
     * @return open_time - 开奖时间
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * 设置开奖时间
     *
     * @param openTime 开奖时间
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    /**
     * 获取开奖日期
     *
     * @return open_date - 开奖日期
     */
    public String getOpenDate() {
        return openDate;
    }

    /**
     * 设置开奖日期
     *
     * @param openDate 开奖日期
     */
    public void setOpenDate(String openDate) {
        this.openDate = openDate == null ? null : openDate.trim();
    }

    /**
     * 获取插入时间
     *
     * @return insert_time - 插入时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置插入时间
     *
     * @param insertTime 插入时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openTrunkNumber=").append(openTrunkNumber);
        sb.append(", openIndex=").append(openIndex);
        sb.append(", openNumber=").append(openNumber);
        sb.append(", openTime=").append(openTime);
        sb.append(", openDate=").append(openDate);
        sb.append(", insertTime=").append(insertTime);
        sb.append("]");
        return sb.toString();
    }
}