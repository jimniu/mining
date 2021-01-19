package com.isd.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.Assert;





/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.synnex.kwo.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.synnex.kwo.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:andyz@synnex.com">Andy Zeng</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK>   {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
	private SessionFactory sessionFactory;
    private Class<T> entityClass;
//    private Session session;

    /**
     * Constructor that takes in a class to see which type of entity to persist
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDAOImpl(final Class<T> persistentClass) {
        this.entityClass = persistentClass;
    }
    
    public Object execute(HibernateCallback action){  
    	Object result = null;
    	Assert.notNull(action, "Callback object 对象不能为 Null ");  
        try{
	        Session session = sessionFactory.getCurrentSession();  
	        Transaction transaction = session.beginTransaction();  
	        result = action.doInHibernate(session);  
	        transaction.commit();  
        }catch(Exception e){
        	e.printStackTrace();
        }
        return result;  
    }
 
    /** 
     * 创建一个Query对象。 
     * @param hql 
     * @param values 
     * @return 
     */  
    public Query createQuery(String hql, Object...values){  
        Assert.hasText(hql);  
        Session session = sessionFactory.getCurrentSession();;  
        Query query = session.createQuery(hql);  
        for(int i = 0;i<values.length;i++){  
            query.setParameter(i, values[i]);  
        }          
        return query;  
    } 
    
    /** 
     * 创建一个Query对象。 
     * @param hql 
     * @param values 
     * @return 
     */  
    public SQLQuery createSqlQuery(String sql, Object...values){  
        Assert.hasText(sql);  
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);  
        for(int i = 0;i<values.length;i++){  
            query.setParameter(i, values[i]);  
        }   
        return query;  
    }     
    
	/**
     * {@inheritDoc}
     */
    public List<T> findAll() {
    	return (List<T>)execute(new HibernateCallback(){  
             public Object doInHibernate(Session session) {  
                 Criteria criteria = session.createCriteria(entityClass);  
                 return criteria.list();  
             }  
         }); 
    }
    
/*    
    /**
     * {@inheritDoc}
     */
    public T get(final PK id) {
        return (T) execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                return session.get(entityClass, id);  
            }  
        });      
    }
    
    /**
     * {@inheritDoc}
     */
    public T get(final Class<T> className, final PK id) {
        return (T) execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                return session.get(className, id);  
            }  
        });    
    }
    

    /**
     * {@inheritDoc}
     */
    public T get(final String entityName, final PK id) {
        return (T) execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                return session.get(entityName, id);  
            }  
        });    
    }
    

    /**
     * {@inheritDoc}
     */
    public T findById(final PK id) {
        return (T) execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                return session.get(entityClass, id);  
            }  
        });     
    }
 
    /**
     * {@inheritDoc}
     */
    public List<T> findByProperty(final String key, final Object value) {
    	String queryString = "from "+this.entityClass.getName()+" as model where model."
			+ key + "= ?";
    	return this.createQuery(queryString, value).list();
    }   

    
    /**
     * {@inheritDoc}
     */
    public boolean exists(final PK id) {
        T entity = (T) this.get(entityClass, id);
        return entity != null;
    }
    /**
     * {@inheritDoc}
     */
    public boolean exists(final String entityName, final PK id){
        T entity = (T) this.get(entityName, id);
        return entity != null;
    }
    
    /**
     * {@inheritDoc}
     */
    public PK save(final T object) {
        return (PK)execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                return (PK)session.save(object);  
            }  
        }); 
    }
    
    /**
     * {@inheritDoc}
     */
    public PK save(final String entityName, final T object) {
    	return (PK) execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                return (PK)session.save(entityName, object);  
            }  
        });     	
    }
    
    /**
     * {@inheritDoc}
     */
    public void update(final T object) {
    	Session session = sessionFactory.getCurrentSession();
    	session.update(object);
    	session.flush();
    }
    
    /**
     * {@inheritDoc}
     */
    public void update(final String entityName, final T object) {
        execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                session.update(entityName, object);  
                return null;
            }  
        });     
    }

    /**
     * {@inheritDoc}
     */
    public void delete(PK id) {
    	final T object = this.get(id);
    	this.delete(object);
        /*execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                session.delete(object);
                return null;
            }  
        });*/    
    }
    
    /**
     * {@inheritDoc}
     */
    public void delete(String key, Object value) {
    	String hql = "delete from "+this.entityClass.getName()+" as model where model."
		+ key + "= '"+value.toString()+"'";
    	this.executeByHql(hql);
    }
    
    /**
     * {@inheritDoc}
     */
    public void delete(final T object) {
    	Session session = sessionFactory.getCurrentSession();
    	session.delete(object);
    	session.flush();
        /*execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) {  
                session.delete(object);
                return null;
            }  
        });*/ 
    }
    
   
    /**
     * 使用HQL语句进行查询
     */
	public List findByHql(String hql) {	
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createQuery(hql);
		List list = (List)query.list();
		return list;
	}
	
    /**
     * 使用SQL语句进行查询
     */
	public List findBySql(final String sql) {
		Session session = sessionFactory.getCurrentSession();;
		SQLQuery query = session.createSQLQuery(sql);
		List list = (List)query.list();
		return list;
	}
	
    /**
     * 使用SQL语句进行查询
     */
	public List findBySql(final String sql, final Class entity) {
		Session session = sessionFactory.getCurrentSession();;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(entity);
		List list = query.list();
		return list;
	}
	
    /**
     * 使用SQL语句进行查询
     */
	public List findPageBySql(final String sql, final Class entity, final int offset, final int length) {	
		Session session = sessionFactory.getCurrentSession();;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(entity);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		List list = query.list();
		return list;
	}	
  
    /**
     * 使用SQL语句进行查询
     */
	public List findPageBySql(final String sql, final int offset, final int length) {
		Session session = sessionFactory.getCurrentSession();;
		SQLQuery query = session.createSQLQuery(sql);
//		query.addEntity(entityClass);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		List list = query.list();
		return list;
	}
	
	//按照分页信息提取列表
	public List findPageByHql(final String hql, final int offset, final int length){
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		List list = query.list();
		return list;
	}
	
	//执行批量删除或修改操作
	public void executeByHql(final String hql){
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
	
	//执行批量删除或修改操作
	public void executeBySql(final String sql){
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	
	public int getCountByHql(String hql){
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createQuery(hql);
		List list = query.list();
		if(list.get(0)!=null){
			return (Integer.parseInt(list.get(0).toString()));
		}else{
			return 0;
		}		
	}
	
	public Long getCountLongByHql(String hql){
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createQuery(hql);
		List list = query.list();
		if(list.get(0)!=null){
			return (Long.parseLong(list.get(0).toString()));
		}else{
			return 0l;
		}		
	}
	
    /**
     * 根据sql，获得记录总数
     */
    public int getCountBySql(final String sql){
    	Session session = sessionFactory.getCurrentSession();;
		SQLQuery query = session.createSQLQuery(sql);
		List list = query.list();
		if(list.get(0)!=null){
			return (Integer.parseInt(list.get(0).toString()));
		}else{
			return 0;
		}	
    }
    
    public Long getCountLongBySql(final String sql){
    	Session session = sessionFactory.getCurrentSession();;
		SQLQuery query = session.createSQLQuery(sql);
		List list = query.list();
		if(list.get(0)!=null){
			return (Long.parseLong(list.get(0).toString()));
		}else{
			return 0l;
		}	
    }    
    
    public void flush() {
    	Session session = sessionFactory.getCurrentSession();
    	session.flush();
    }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
