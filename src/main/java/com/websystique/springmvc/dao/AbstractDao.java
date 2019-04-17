package com.websystique.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	public void persist(List<T> list) {
		for (T temp : list) {
			Session session = getSession();
			session.clear();
			session.persist(temp);
	        session.flush();
		}
		
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	@SuppressWarnings("deprecation")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	protected ProcedureCall createStoredProcedureCriteria(String sp){
		return getSession().createStoredProcedureCall(sp, persistentClass);
	}
	
	protected ProcedureCall createStoredProcedureCriteriaStr(String sp){
		return getSession().createStoredProcedureCall(sp);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<T> criteriaQuery(Map< String, String > res, Map< String, String > ord)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(res.size() > 0)
			res.forEach((k,v) -> listPred.add(builder.ge(root.get(k), Integer.parseInt(v))));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		List<Order> orderList = new ArrayList();
		
		if(ord.size() > 0)
			ord.forEach((k,v) -> orderList.add(builder.asc(root.get(v))));

		cq.orderBy(orderList.toArray(new Order[]{}));
		
		List<T> result = getSession().createQuery(cq).getResultList();
		
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object criteriaQuery(Map< String, String > res)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(res.size() > 0)
			res.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		Object result = getSession().createQuery(cq).uniqueResult();
		
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object criteriaQueryStrDate(Map< String, String > resS, Map< String, Date > resD)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(resS.size() > 0)
			resS.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
		
		if(resD.size() > 0)
			resD.forEach((k,v) -> listPred.add(builder.greaterThanOrEqualTo(root.<Date>get(k), v)));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		Object result = getSession().createQuery(cq).uniqueResult();
		
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object criteriaQueryStrDateEq(Map< String, String > resS, Map< String, Date > resD)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(resS.size() > 0)
			resS.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
		
		if(resD.size() > 0)
			resD.forEach((k,v) -> listPred.add(builder.equal(root.<Date>get(k), v)));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		Object result = getSession().createQuery(cq).uniqueResult();
		
		return result;
		
	}
	
}
