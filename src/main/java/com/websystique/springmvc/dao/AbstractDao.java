package com.websystique.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;

import com.websystique.springmvc.model.ParamsGeneral;

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
			session.save(temp);
	        session.flush();
		}
		
	}
	
	public void delete(List<T> list) {
		for (T temp : list) {
			Session session = getSession();
			session.clear();
			session.delete(temp);
	        session.flush();
		}
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}
	
	public Integer save_entity(T entity) {
		Integer id =  (Integer) getSession().save(entity);
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public T save_entityObj(T entity) {
				
		return (T) getSession().save(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
		
	protected ProcedureCall createStoredProcedureCriteria(String sp){
		return getSession().createStoredProcedureCall(sp, persistentClass);
	}
	
	protected ProcedureCall createStoredProcedureCriteriaStr(String sp){
		return getSession().createStoredProcedureCall(sp);
	}
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
	} */
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<T> criteriaQueryEqInt(Map< String, Integer > res, Map< String, String > ord)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(res.size() > 0)
			res.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		List<Order> orderList = new ArrayList();
		
		if(ord.size() > 0)
			ord.forEach((k,v) -> orderList.add(builder.asc(root.get(v))));

		cq.orderBy(orderList.toArray(new Order[]{}));
		
		List<T> result = getSession().createQuery(cq).getResultList();
		
		return result;
		
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<T> criteriaQueryEqStrInt(Map< String, String > res,Map< String, Integer > resint, Map< String, String > ord)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(res.size() > 0)
			res.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
		
		if(resint.size() > 0)
			resint.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		List<Order> orderList = new ArrayList();
		
		if(ord.size() > 0)
			ord.forEach((k,v) -> orderList.add(builder.asc(root.get(v))));

		cq.orderBy(orderList.toArray(new Order[]{}));
		
		List<T> result = getSession().createQuery(cq).getResultList();
		
		return result;
		
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object criteriaQueryEqObj(Map< String, String > res)
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
		
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })//Para buscar detalle especifico (COTIZADOR)
	protected Object criteriaQueryIntEqObj(Map< String, Integer > res)
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
		
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<T> criteriaQueryEqList(Map< String, String > res)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		if(res.size() > 0)
			res.forEach((k,v) -> listPred.add(builder.equal(root.get(k), v)));
			
		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		List<T> result = getSession().createQuery(cq).getResultList();
		
		return result;
		
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
	} */
	
	@SuppressWarnings("unchecked")
	protected List<Object> criteriaQueryStr(String query,Map<Integer,Integer> paramsInt, Map<Integer,String> paramStr)
	{
		Query queryQ = getSession().createNativeQuery(query);
		
		if(paramsInt.size() > 0)
			paramsInt.forEach((k,v) -> queryQ.setParameter(k, v));
		
		if(paramStr.size() > 0)
			paramStr.forEach((k,v) -> queryQ.setParameter(k, v));
		
		List<Object> Lista = queryQ.getResultList();
		return Lista;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	protected List<T> criteriaGeneralList(List<ParamsGeneral> paramsGeneral, Map<String,String> ord)
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);		
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		paramsGeneral.stream().forEach(params -> {
			switch(params.getOperador()) 
			{				
				case "EQ": 
				{
					if(params.getParamstringV() != null)
						  listPred.add(builder.equal(root.get(params.getParamstringN()), params.getParamstringV()));
					else
					{
						if(params.getParamintegerV() != null)
							  listPred.add(builder.equal(root.get(params.getParamintegerN()), params.getParamintegerV()));
						else
						{
							if(params.getParamdoubleV() != null)
								  listPred.add(builder.equal(root.get(params.getParamdoubleN()), params.getParamdoubleV()));
							else
							{
								if(params.getParambooleanV() != null)
									  listPred.add(builder.equal(root.get(params.getParambooleanN()), params.getParambooleanV()));
								else
								{
									if(params.getParamdateV() != null)
										  listPred.add(builder.equal(root.get(params.getParamdateN()), params.getParamdateV()));
									else
									{	
										if(params.getParamnullN() != null)
											  listPred.add(builder.isNull(root.get(params.getParamnullN())));
									}
								}
							}
						}
					}
					break;
				}
				case "NE":
				{	
					if(params.getParamstringV() != null)
						  listPred.add(builder.notEqual(root.get(params.getParamstringN()), params.getParamstringV()));
					else
					{
						if(params.getParamintegerV() != null)
							  listPred.add(builder.notEqual(root.get(params.getParamintegerN()), params.getParamintegerV()));
						else
						{
							if(params.getParamdoubleV() != null)
								  listPred.add(builder.notEqual(root.get(params.getParamdoubleN()), params.getParamdoubleV()));
							else
							{
								if(params.getParambooleanV() != null)
									  listPred.add(builder.notEqual(root.get(params.getParambooleanN()), params.getParambooleanV()));
								else
								{
									if(params.getParamdateV() != null)
										  listPred.add(builder.notEqual(root.get(params.getParamdateN()), params.getParamdateV()));
									else
									{
										if(params.getParamnullN() != null)
											  listPred.add(builder.isNotNull(root.get(params.getParamnullN())));
									}
								}
							}
						}
					}
					break;
				}
						  
				case "LIKE":
				{
					if(params.getParamstringV() != null)
						listPred.add(builder.like(root.get(params.getParamstringN()), "%" + params.getParamstringV() + "%"));
					break;
				}
						    
				case "LT":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.lessThan(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.lessThan(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.lessThan(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
						  
				case "GT":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.greaterThan(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.greaterThan(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.greaterThan(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
						  
				case "LTE":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.lessThanOrEqualTo(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.lessThanOrEqualTo(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else 
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.lessThanOrEqualTo(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
						   
				case "GTE":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.greaterThanOrEqualTo(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.greaterThanOrEqualTo(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.greaterThanOrEqualTo(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
			}	
			
		});

		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		List<Order> orderList = new ArrayList();
		if(ord.size() > 0)
			ord.forEach((k,v) -> orderList.add(builder.asc(root.get(v))));
		cq.orderBy(orderList.toArray(new Order[]{}));
		
		List<T> result = getSession().createQuery(cq).getResultList();
		
		return result;

	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	protected Object criteriaGeneralObj(List<ParamsGeneral> paramsGeneral)
	{		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery cq = builder.createQuery();
		Root<T> root = cq.from(persistentClass);		
		
		List<Predicate> listPred = new ArrayList<Predicate>();
		paramsGeneral.stream().forEach(params -> {
			switch(params.getOperador()) 
			{				
				case "EQ": 
				{
					if(params.getParamstringV() != null)
						  listPred.add(builder.equal(root.get(params.getParamstringN()), params.getParamstringV()));
					else
					{
						if(params.getParamintegerV() != null)
							  listPred.add(builder.equal(root.get(params.getParamintegerN()), params.getParamintegerV()));
						else
						{
							if(params.getParamdoubleV() != null)
								  listPred.add(builder.equal(root.get(params.getParamdoubleN()), params.getParamdoubleV()));
							else
							{
								if(params.getParambooleanV() != null)
									  listPred.add(builder.equal(root.get(params.getParambooleanN()), params.getParambooleanV()));
								else
								{
									if(params.getParamdateV() != null)
										  listPred.add(builder.equal(root.get(params.getParamdateN()), params.getParamdateV()));
									else
									{	
										if(params.getParamnullN() != null)
											  listPred.add(builder.isNull(root.get(params.getParamnullN())));
									}
								}
							}
						}
					}
					break;
				}
				case "NE":
				{	
					if(params.getParamstringV() != null)
						  listPred.add(builder.notEqual(root.get(params.getParamstringN()), params.getParamstringV()));
					else
					{
						if(params.getParamintegerV() != null)
							  listPred.add(builder.notEqual(root.get(params.getParamintegerN()), params.getParamintegerV()));
						else
						{
							if(params.getParamdoubleV() != null)
								  listPred.add(builder.notEqual(root.get(params.getParamdoubleN()), params.getParamdoubleV()));
							else
							{
								if(params.getParambooleanV() != null)
									  listPred.add(builder.notEqual(root.get(params.getParambooleanN()), params.getParambooleanV()));
								else
								{
									if(params.getParamdateV() != null)
										  listPred.add(builder.notEqual(root.get(params.getParamdateN()), params.getParamdateV()));
									else
									{
										if(params.getParamnullN() != null)
											  listPred.add(builder.isNotNull(root.get(params.getParamnullN())));
									}
								}
							}
						}
					}
					break;
				}
						  
				case "LIKE":
				{
					if(params.getParamstringV() != null)
						listPred.add(builder.like(root.get(params.getParamstringN()), "%" + params.getParamstringV() + "%"));
					break;
				}
						    
				case "LT":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.lessThan(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.lessThan(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.lessThan(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
						  
				case "GT":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.greaterThan(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.greaterThan(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.greaterThan(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
						  
				case "LTE":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.lessThanOrEqualTo(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.lessThanOrEqualTo(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else 
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.lessThanOrEqualTo(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
						   
				case "GTE":
				{
					if(params.getParamintegerV() != null)
						listPred.add(builder.greaterThanOrEqualTo(root.get(params.getParamintegerN()), (Comparable) params.getParamintegerV()));
					else
					{
						if(params.getParamdoubleV() != null)
							listPred.add(builder.greaterThanOrEqualTo(root.get(params.getParamdoubleN()), (Comparable) params.getParamdoubleV()));
						else
						{
							if(params.getParamdateV() != null)
								listPred.add(builder.greaterThanOrEqualTo(root.get(params.getParamdateN()), (Comparable) params.getParamdateV()));
						}
					}
					break;
				}
			}		
			
		});

		cq.select(root).where(listPred.toArray(new Predicate[]{}));
		
		Object result = getSession().createQuery(cq).uniqueResult();
		
		return result;
	}
	
	
}
