package com.isd.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) DAO's for your
 * domain objects.
 *
 * @author Andy Zeng
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericDAO <T, PK extends Serializable> {
    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> findAll();
    
    List<T> findByProperty(String key, Object value);
    
    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);
    
    /**
     * Generic method to get an object based on entity name and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param entityName an entity name to disambiguate between instances of the multiple mapped entities
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(String entityName, PK id);

    
    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T findById(PK id);
    
    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);
    
    /**
     * Checks for existence of an object of type T using the id arg.
     * @param entityName an entity name to disambiguate between instances of the multiple mapped entities
     * @param id the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(String entityName, PK id);

    /**
     * Generic method to save an object
     * @param object the new object to save
     * @return the PK
     */
    PK save(T object);
    
    /**
     * Generic method to save an object
     * @param entityName an entity name to disambiguate between instances of the multiple mapped entities
     * @param object the new object to save
     * @return the persisted object
     */
    PK save(String entityName, T object);
    
    
    /**
     * Generic method to save an object
     * @param object the object to update
     */
    void update(T object);
    
    /**
     * Generic method to save an object
     * @param entityName an entity name to disambiguate between instances of the multiple mapped entities
     * @param object the object to update
     */
    void update(String entityName, T object);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     */
    void delete(PK id);
    
    /**
     * Generic method to delete an object based on key and value
     */
    void delete(String key, Object value);
    
    /**
     * Generic method to delete an object
     * @param object the object to remove
     */
    void delete(T object);
    
  
    //执行批量删除或修改操作
	void executeByHql(final String hql);  
	
    //执行批量删除或修改操作
	void executeBySql(final String sql); 
	
    /**
     * 使用HQL语句进行查询
     */
    List<T> findByHql(String hql);
   
    /**
     * 使用SQL语句进行查询
     */
    List findBySql(String sql);
    
    /**
     * 使用SQL语句进行查询
     */
    @SuppressWarnings("rawtypes")
	List<T> findBySql(String sql, Class entity);
    
    /**
     *根据sql，查询分页结果
     */
    List findPageBySql(String hql, Class entity, int offset, int length);
    
    /**
     *根据hql，查询分页结果
     */
    List findPageByHql(String hql, int offset, int length);
    
    
    /**
     * 根据hql，获得记录总数
     */
    int getCountByHql(String hql); 
    
    Long getCountLongByHql(String hql);
    
    /**
     * 根据sql，获得记录总数
     */
    int getCountBySql(String sql); 
    Long getCountLongBySql(String sql); 
    
    //update时需要flush
    public void flush();
    
}