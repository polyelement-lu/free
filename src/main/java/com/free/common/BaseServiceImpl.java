package com.free.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.free.common.dao.mapper.BaseMapper;
import com.free.common.dao.util.ReflectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.mapperhelper.EntityHelper;


/**
 * 
 * @author      ludm  
 * @date        2017年12月29日上午9:51:06 
 * @description service实现类
 * @param <T>
 * @param <ID>
 */
public class BaseServiceImpl<T,ID> implements BaseService<T,ID> {
	/**
	 * 日志对象,子类直接调用记录日志信息
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		Class<?> clz = getClass();
		Type type = clz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		}
	}
	
	@Autowired
	private BaseMapper<T> mapper;
	
	@Override
	public int save(T t) throws Exception{
		return mapper.insertSelective(t);
	}
	
	@Override
	public int saveWithUUID(T t) throws Exception {
		Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(entityClass);
		pkColumns.forEach((column)->{
			String property = column.getProperty();
			if(String.class.equals(column.getJavaType())){
				ReflectUtil.setFieldValue(t, property, UUID.randomUUID().toString().replace("-","").toUpperCase());
			}
		});
		return mapper.insertSelective(t);
	}

	@Override
	public int update(T t) throws Exception {
		return mapper.updateByPrimaryKey(t);
	}
	
	@Override
	public int updateNotNull(T t) throws Exception {
		return mapper.updateByPrimaryKeySelective(t);
	}
	
	@Override
	public int updateByExample(T t, Example example) throws Exception {
		return mapper.updateByExampleSelective(t, example);
	}

	@Override
	public int deleteByKey(ID id) throws Exception {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int delete(T t) throws Exception {
		return mapper.delete(t);
	}
	
	@Override
	public int deleteByExample(Example example) throws Exception {
		return mapper.deleteByExample(example);
	}
	
	@Override
	public int deleteByCondition(Condition condition) throws Exception {
		return mapper.deleteByCondition(condition);
	}
	
	@Override
	public T get(ID id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public boolean checkIfExist(ID id) {
		return mapper.existsWithPrimaryKey(id);
	}
	
	@Override
	public boolean checkIfExist(String property,Object value) {
		List<T> list = findByPointedProperty(property, value);
		if(list.size()==0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean checkIfExist(String[] properties,Object[] values) {
		List<T> list = findByPointedProperty(properties, values);
		if(list.size()==0){
			return false;
		}
		return true;
	}

	@Override
	public List<T> getAll() {
		return mapper.selectAll();
	}
	
	@Override
	public List<T> query(T t) {
		return mapper.select(t);
	}

	@Override
	public List<T> querySingleTableByPage(BaseQuery<T> baseQuery) {
		Page<Object> page =null;
		try{
			page = PageHelper.startPage(baseQuery.getStart(), baseQuery.getLimit());
			return mapper.selectByExample(baseQuery.getExample());
		} finally {
			baseQuery.setTotal(page.getTotal());	//最终设置总数
		}
	}
	
	@Override
	public List<T> queryByExample(Example example) {
		return mapper.selectByExample(example);
	}

	@Override
	public int getTotal(Example example) {
		return mapper.selectCountByExample(example);
	}
	
	@Override
	public int getTotal(BaseQuery<T> baseQuery) {
		return mapper.selectCountByExample(baseQuery.getExample());
	}
	
	@Override
	public List<T> findByPointedProperty(String property, Object value) {
		Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(property, value);
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<T> findByPointedPropertyAsc(String property, Object value,String orderProperty) {
		return findByPointedPropertyOrderBy(property, value, orderProperty, " asc");
	}
	
	@Override
	public List<T> findByPointedPropertyDesc(String property, Object value,String orderProperty) {
		return findByPointedPropertyOrderBy(property, value, orderProperty, " desc");
	}
	
	@Override
	public List<T> findByPointedPropertyNot(String property, Object value) {
		Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo(property, value);
		return mapper.selectByExample(example);
	}

	@Override
	public List<T> findByPointedProperty(String[] properties, Object[] values) {
		if(properties.length!=values.length){
			throw new RuntimeException("属性与值数量不相同");
		}
		Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		for (int i = 0; i < properties.length; i++) {
			criteria.andEqualTo(properties[i], values[i]);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public int updateByPointedProperty(ID id, String property, Object value) {
		Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(entityClass);
		String pk = "";
		for (EntityColumn entityColumn : pkColumns) {
			pk = entityColumn.getProperty();
		}
		T newInstance =null;
		try {
			newInstance = entityClass.newInstance();
			ReflectUtil.invokeSetter(newInstance, pk, id);
			ReflectUtil.invokeSetter(newInstance, property, value);
			return mapper.updateByPrimaryKeySelective(newInstance);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateByPointedProperty(ID id, String[] properties, Object[] values) {
		if(properties.length!=values.length){
			throw new RuntimeException("属性与值数量不相同");
		}
		Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(entityClass);
		String pk = "";
		for (EntityColumn entityColumn : pkColumns) {
			pk = entityColumn.getProperty();
		}
		T newInstance =null;
		try {
			newInstance = entityClass.newInstance();
			ReflectUtil.invokeSetter(newInstance, pk, id);
			for (int i = 0; i < properties.length; i++) {
				ReflectUtil.invokeSetter(newInstance, properties[i], values[i]);
			}
			return mapper.updateByPrimaryKeySelective(newInstance);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<T> findByPointedPropertyLike(String property, Object value) {
		Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		criteria.andLike(property, "%".concat(value.toString()).concat("%"));
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<T> findByPointedPropertyAsc(String[] properties, Object[] values,String orderProperty) {
		return findByPointedPropertyOrderBy(properties,values,orderProperty," asc");
	}

	@Override
	public List<T> findByPointedPropertyDesc(String[] properties, Object[] values,String orderProperty) {
		return findByPointedPropertyOrderBy(properties,values,orderProperty," desc");
	}
	
	private List<T> findByPointedPropertyOrderBy(String property, Object value,String orderProperty,String sort) {
		Example example = new Example(entityClass);
		example.orderBy(orderProperty);
		example.setOrderByClause(example.getOrderByClause().concat(sort));
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(property, value);
		return mapper.selectByExample(example);
	}
	
	private List<T> findByPointedPropertyOrderBy(String[] properties, Object[] values,String orderProperty,String sort) {
		if(properties.length!=values.length){
			throw new RuntimeException("属性与值数量不相同");
		}
		Example example = new Example(entityClass);
		example.orderBy(orderProperty);
		example.setOrderByClause(example.getOrderByClause().concat(sort));
		Criteria criteria = example.createCriteria();
		for (int i = 0; i < properties.length; i++) {
			criteria.andEqualTo(properties[i], values[i]);
		}
		return mapper.selectByExample(example);
	}
	@Override
	public int getTotal() {
		return mapper.selectCountByExample(null);
	}

	@Override
	public T getOne(String property, Object value) throws IllegalArgumentException {
		List<T> list = findByPointedProperty(property, value);
		if(list.isEmpty()){
			return null;
		}else if(list.size()==1){
			return list.get(0);
		}else{
			throw new IllegalArgumentException("查询结果包含多条数据!");
		}
	}
	
	@Override
	public int getCount(String property, Object value){
		Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(property, value);
		return getTotal(example);
	}

	@Override
	public int getCount(String[] properties, Object[] values) {
		if(properties.length!=values.length){
			throw new RuntimeException("属性与值数量不相同");
		}
		Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		for (int i = 0; i < properties.length; i++) {
			criteria.andEqualTo(properties[i], values[i]);
		}
		return getTotal(example);
	}

	@Override
	public List<T> findPageRows(int offset, int limit, Example example) {
		return mapper.selectByExampleAndRowBounds(example, new RowBounds(offset,limit));
	}

	@Override
	public T getOne(String[] properties, Object[] values) throws IllegalArgumentException {
		List<T> list = findByPointedProperty(properties, values);
		if(list.isEmpty()){
			return null;
		}else if(list.size()==1){
			return list.get(0);
		}else{
			throw new IllegalArgumentException("查询结果包含多条数据!");
		}
	}

	@Override
	public void insertList(List<T> list) {
		mapper.insertList(list);
	}
	
}
