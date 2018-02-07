package com.free.common;

import java.util.List;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author      ludm  
 * @date        2017年12月29日上午9:50:08 
 * @description 单表mapper配置通用Service接口
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T,ID> {

	/**
	 * <p>保存对象的所有属性(不包含null属性)
	 * <p>会保存主键信息(若主键不为空)
	 * <p>保存UUID主键需要在model主键字段标记,@GeneratedValue(generator="UUID"),并且model对象主键为null,这种方式不支持回写id
	 * <p>底层-效率相对反射高
	 * @param t
	 */
	int save(T t) throws Exception;
	
	/**
	 * <p>保存对象的所有属性(不包含null属性)
	 * <p>并且所有主键采用32位UUID(自动生成)
	 * <p>主键支持回写,不需要回写时,使用save方法
	 * <p>可以不需要注解@GeneratedValue(generator="UUID")
	 * <p>反射-效率相对底层低,但回写UUID
	 * @param t
	 */
	int saveWithUUID(T t) throws Exception;
	
	/**
	 * 更新对象所有属性,对象中必须包含主键信息
	 * @param t
	 */
	int update(T t) throws Exception;
	
	/**
	 * 更新对象所有not null属性,对象中必须包含主键信息
	 * @param t
	 */
	int updateNotNull(T t) throws Exception;
	/**
	 * 根据Example条件更新实体 t 包含的不是null的属性值
	 * @param t
	 * @param example
	 * @return
	 * @throws Exception
	 */
	int updateByExample(T t, Example example) throws Exception;
	/**
	 * 根据主键 删除一个对象,方法参数必须包含完整的主键属性
	 * @param id
	 */
	int deleteByKey(ID id) throws Exception;
	
	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 * @param t
	 */
	int delete(T t) throws Exception;
	
	/**
	 * 根据Example条件删除数据
	 * @param example
	 * @return
	 * @throws Exception
	 */
	int deleteByExample(Example example) throws Exception;
	
	/**
	 * 根据condition条件删除数据
	 * @param condition
	 * @return
	 */
	int deleteByCondition(Condition condition) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param t
	 * @return
	 */
	T get(ID id);
	
	/**
	 * 根据主键判断对象是否存在
	 * @param t
	 * @return 对象存在true,不存在false
	 */
	boolean checkIfExist(ID id);
	
	/**
	 * 根据属性判断对象是否存在
	 * @param t
	 * @return 对象存在true,不存在false
	 */
	boolean checkIfExist(String property,Object value);
	
	/**
	 * 根据属性判断对象是否存在
	 * @param t
	 * @return 对象存在true,不存在false
	 */
	boolean checkIfExist(String[] properties,Object[] values);
	
	/**
	 * 单表分页高级查询
	 * @param baseQuery
	 * @return 返回分页高级查询结果
	 */
	List<T> querySingleTableByPage(BaseQuery<T> baseQuery);
	
	/**
	 * 根据指定属性和指定属性值 查询对象 = 
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<T> findByPointedProperty(String property,Object value);
	
	/**
	 * 根据指定属性和指定属性值 查询对象 !=
	 * @param property
	 * @param value
	 * @return
	 */
	List<T> findByPointedPropertyNot(String property, Object value);
	
	/**
	 * 根据指定属性和指定属性值 模糊查询对象
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<T> findByPointedPropertyLike(String property,Object value);
	
	/**
	 * 根据指定属性和指定属性值 更新对象
	 * <p>反射操作,效率偏原生低
	 * <p>传入对象的主键不能为空
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	int updateByPointedProperty(ID id,String property,Object value);
	
	/**
	 * 根据指定属性和指定属性值集合 更新对象
	 * <p>反射操作,效率偏原生低
	 * <p>传入对象的主键不能为空
	 * <p>属性和values长度必须相同
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	int updateByPointedProperty(ID id,String[] properties,Object[] values);
	
	/**
	 * 根据指定属性和指定属性值 查询对象
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<T> findByPointedProperty(String[] properties,Object[] values);
	
	/**
	 * 升序
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<T> findByPointedPropertyAsc(String[] properties,Object[] values,String orderProperty);
	
	/**
	 * 升序
	 * @param t
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<T> findByPointedPropertyDesc(String[] properties,Object[] values,String orderProperty);
	
	/**
	 * 查询单表所有对象
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * 根据对象属性  查询所有相同属性的对象
	 * @param t
	 * @return
	 */
	List<T> query(T t);
	
	/**
	 * 根据条件查询
	 * @param t
	 * @return
	 */
	List<T> queryByExample(Example example);
	
	/**
	 * 根据查询条件查询总记录数
	 * @param baseQuery
	 * @return
	 */
	int getTotal(BaseQuery<T> baseQuery);
	
	/**
	 * 根据查询条件查询总记录数
	 * @param example
	 * @return
	 */
	int getTotal(Example example);
	
	/**
	 * 升序
	 * @param property
	 * @param value
	 * @param orderProperty
	 * @return
	 */
	List<T> findByPointedPropertyAsc(String property, Object value,String orderProperty);
	
	/**
	 * 降序
	 * @param property
	 * @param value
	 * @param orderProperty
	 * @return
	 */
	List<T> findByPointedPropertyDesc(String property, Object value,String orderProperty);
	
	/**
	 * 获取所有记录数
	 * @return
	 */
	int getTotal();
	
	/**
	 * 根据指定属性获取唯一记录,不存在返回null
	 * @return
	 * @throws IllegalArgumentException 多条记录
	 */
	T getOne(String property, Object value) throws IllegalArgumentException;
	
	/**
	 * 根据指定属性获取唯一记录,不存在返回null
	 * @return
	 * @throws IllegalArgumentException 多条记录
	 */
	T getOne(String[] properties, Object[] values) throws IllegalArgumentException;
	
	/**
	 * 根据指定属性获取记录数
	 * @param property
	 * @param value
	 * @return
	 */
	int getCount(String property, Object value);
	
	/**
	 * 根据指定属性获取记录数
	 * @param property
	 * @param value
	 * @return
	 */
	int getCount(String[] properties, Object[] values);
	
	/**
	 * 分页高级查询-- add 2017-9-5 yangz
	 * @param offset	分页开始位置
	 * @param limit		页大小
	 * @param example	查询条件
	 */
	List<T> findPageRows(int offset,int limit,Example example);
	
	/**
	 * 批量插入自动生成id,注意只能主键自增
	 */
	void insertList(List<T> list);
	
}
