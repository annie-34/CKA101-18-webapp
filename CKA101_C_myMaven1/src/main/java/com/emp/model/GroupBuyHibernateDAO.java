package com.emp.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;
public class GroupBuyHibernateDAO implements GroupBuyDAO_interface {
	private SessionFactory factory;
	public GroupBuyHibernateDAO() {
		factory=HibernateUtil.getSessionFactory();
	}
	public void updateRejectReason(
	        Integer groupBuyId,
	        String rejectReason) {
	    Session session =
	        factory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        GroupBuyVO groupBuy =session.get(GroupBuyVO.class,groupBuyId);
	        if(groupBuy != null) {
	        	groupBuy.setRejectReason(rejectReason);}
	        session.getTransaction().commit();
	    } catch(Exception e) {
	    	session.getTransaction().rollback();
	    e.printStackTrace();
	    }
	}
	@Override
    public void insert(GroupBuyVO groupBuy) {
    	Session session=factory.getCurrentSession();
    	try {
    		session.beginTransaction();
    		session.persist(groupBuy);
    		session.getTransaction().commit();
    	}catch(Exception c) {
    		c.printStackTrace();
    	}
    }
	@Override
    public void update(GroupBuyVO groupBuy) {
		Session session=factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(groupBuy);
			session.getTransaction().commit();
			
		}catch(Exception c) {
			c.printStackTrace();
		}
		
    	
    }
	@Override
    public void delete(Integer groupBuyId) {
		Session session=factory.getCurrentSession();
		try {
			session.beginTransaction();
			GroupBuyVO groupBuy=session.get(GroupBuyVO.class, groupBuyId);
			if(groupBuy!=null) {
				session.remove(groupBuy);
			}
			session.getTransaction().commit();
		}catch(Exception c) {
			c.printStackTrace();
		}
    	
    }
	@Override
    public GroupBuyVO findByPrimaryKey(Integer groupBuyId) {
		Session session=factory.getCurrentSession();
		GroupBuyVO groupBuy=null;
		try {
			session.beginTransaction();
			groupBuy=session.get(GroupBuyVO.class, groupBuyId);
			session.getTransaction().commit();
			
		}catch(Exception c) {
			c.printStackTrace();
		}
		return groupBuy;
    	
    }
	@Override
    public List<GroupBuyVO> getAll(){
		Session session=factory.getCurrentSession();
		List<GroupBuyVO> list=null;
		try {
			session.beginTransaction();
			list=session.createQuery("from GroupBuyVO", GroupBuyVO.class).list();
			session.getTransaction().commit();
		}catch(Exception c) {
			session.getTransaction().rollback();
			c.printStackTrace();
		}
		 return list;
    	
    }
	public List<ProductOptionVO> getProductOptions() {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try {
	        session.beginTransaction();
	        String sql = "select product_id, product_name from product_detail";
	        List<Object[]> rows = session.createNativeQuery(sql).getResultList();
	        List<ProductOptionVO> list = new ArrayList<>();
	        for (Object[] row : rows) {
	            ProductOptionVO vo = new ProductOptionVO();
	            vo.setProductId(((Number) row[0]).intValue());
	            vo.setProductName((String) row[1]);
	            list.add(vo);
	        }

	        session.getTransaction().commit();
	        return list;

	    } catch (RuntimeException e) {
	        if (session.getTransaction() != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        throw e;
	    }
	}
}
