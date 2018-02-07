package com.free.common.dao.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;
/**
 * 
 * @author      ludm  
 * @date        2017年12月29日上午9:32:54 
 * @description tk.mabatis 通用mapper
 * @param <T>
 */
public interface CommonMapper<T> extends BaseMapper<T>,
										 ExampleMapper<T>,
										 ConditionMapper<T>,
										 RowBoundsMapper<T>,
										 MySqlMapper<T>,
										 Marker{

}
