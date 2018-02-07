package com.free.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import com.free.common.dao.util.SqlProvider;

/**
 * <p>BaseMapper的基础CRUD,分页高级查询接口<p>
 * @author      ludm  
 * @date        2017年12月29日上午9:38:13 
 * @description Mapper<T>单表操作,不需要写mapper映射文件
 * @param <T>
 */
public interface BaseMapper<T> extends CommonMapper<T>{
	
	/**
	 * sql拼接查询list
	 * @param sql
	 * @return
	 */
	@SelectProvider(type = SqlProvider.class, method = "excute")
	List<Map<String, Object>> queryList(String sql);
	
	/**
	 * sql拼接查询一个
	 * @param sql
	 * @return
	 */
	@SelectProvider(type = SqlProvider.class, method = "excute")
	Map<String, Object> getOne(String sql);
	
	/**
	 * sql拼接执行一条语句
	 * @param sql
	 * @return
	 */
	@SelectProvider(type = SqlProvider.class, method = "excute")
	void excute(String sql);
	
}
