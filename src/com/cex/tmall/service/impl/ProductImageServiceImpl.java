package com.cex.tmall.service.impl;

import com.cex.tmall.pojo.Product;
import com.cex.tmall.pojo.ProductImage;
import com.cex.tmall.service.ProductImageService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl extends BaseServiceImpl implements ProductImageService {
//    @Override
//    public List<ProductImage> list(String key_product, Product product, String key_type, String type) {
//        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//        dc.add(Restrictions.eq(key_product,product));
//        dc.add(Restrictions.eq(key_type,type));
//        dc.addOrder(Order.asc("id"));
//        return findByCriteria(dc);
//    }

    @Override
    public void setFirstProductImage(Product product) {
        if(null!=product.getFirstProductImage()) return;
        List<ProductImage> list = list("product",product,"type",ProductImageService.type_single);
        if(!list.isEmpty()) product.setFirstProductImage(list.get(0));
    }
}
