package com.springboot.zcssc.session.dao;

public interface WebDriverDao {
    /**
     * 初始化
     */
    public void init() throws InterruptedException;

    /**
     * 进入开奖结果视图
     */
    public void gotoResultPage() throws InterruptedException;
}
