package com.springboot.zcssc.zcssc.common.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
