package com.cex.tmall.service.impl;

import com.cex.tmall.dao.impl.DAOImpl;
import com.cex.tmall.pojo.Category;
import com.cex.tmall.service.BaseService;
import com.cex.tmall.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {

    protected Class clazz;


    public BaseServiceImpl(){
        try{
            throw new Exception();
        }
        catch(Exception e){
            StackTraceElement stes[]= e.getStackTrace();
            String serviceImpleClassName=   stes[1].getClassName();
            try {
                Class  serviceImplClazz= Class.forName(serviceImpleClassName);
                String serviceImpleClassSimpleName = serviceImplClazz.getSimpleName();
                String pojoSimpleName = serviceImpleClassSimpleName.replaceAll("ServiceImpl", "");
                String pojoPackageName = serviceImplClazz.getPackage().getName().replaceAll(".service.impl", ".pojo");
                String pojoFullName = pojoPackageName +"."+ pojoSimpleName;
                clazz=Class.forName(pojoFullName);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    public Integer save(Object object) {
        return (Integer)super.save(object);
    }

//    @Override
//    public void delete(Object object) {
//        super.delete(object);
//    }
//
//    @Override
//    public void update(Object object) {
//        System.out.println(object.getClass().getName());
//        super.update(object);
//    }

    @Override
    public Object get(Class clazz, int id) {
        return super.get(clazz,id);
    }

    @Override
    public Object get(int id) {
        return super.get(clazz,id);
    }

    @Override
    public List list() {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.addOrder(Order.asc("id"));
        return findByCriteria(dc);
    }

    @Override
    public List listByPage(Page page) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.addOrder(Order.asc("id"));
        return findByCriteria(dc,page.getStart(),page.getCount());
    }

    @Override
    public int total() {
        String hql="select count(*) from " + clazz.getName();
        List<Long> l = find(hql);
        return l.get(0).intValue();
    }

    @Override
    public List listByParent(Object parent) {
        String parentName = parent.getClass().getSimpleName();
        String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq(parentNameWithFirstLetterLower,parent));
        dc.addOrder(Order.asc("id"));
        return findByCriteria(dc);
    }

    @Override
    public List list(Page page, Object parent) {
        String parentName = parent.getClass().getSimpleName();
        String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq(parentNameWithFirstLetterLower,parent));
        dc.addOrder(Order.asc("id"));
        return findByCriteria(dc,page.getStart(),page.getCount());
    }

    @Override
    public int total(Object parentObject) {
        String parentName= parentObject.getClass().getSimpleName();
        String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);

        String sql = "select count(*) from %s bean where bean.%s =? ";
        String hql = String.format(sql, clazz.getName(), parentNameWithFirstLetterLower);

        List<Long> l= find(hql,parentObject);
        if(l.isEmpty())
            return 0;
        Long result= l.get(0);
        return result.intValue();
    }

    public List list(Object ...pairParms) {
        HashMap<String, Object> m = new HashMap<>();
        for (int i = 0; i < pairParms.length; i = i + 2) {
            m.put(pairParms[i].toString(), pairParms[i + 1]);
        }
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        for (Map.Entry entry : m.entrySet()) {
            dc.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
        }
        dc.addOrder(Order.asc("id"));
        return findByCriteria(dc);
    }
}































