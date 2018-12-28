package com.springboot.zcssc.zcssc.bean.db;

import java.util.Comparator;

public class CpOpenDataComparator implements Comparator<CpOpenData> {
    @Override
    public int compare(CpOpenData o1, CpOpenData o2) {
        return o1.getOpenTrunkNumber().compareTo(o2.getOpenTrunkNumber());
    }
}
