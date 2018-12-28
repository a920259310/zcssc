package com.springboot.zcssc.session.bean;

import java.util.Comparator;

public class RuYiBeanComparator implements Comparator<RuYiBean> {

    @Override
    public int compare(RuYiBean o1, RuYiBean o2) {
        return o1.getTurnNum().compareTo(o2.getTurnNum());
    }
}
