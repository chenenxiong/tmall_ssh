package com.cex.tmall.service.impl;

import com.cex.tmall.pojo.Category;
import com.cex.tmall.pojo.Product;
import com.cex.tmall.service.OrderItemService;
import com.cex.tmall.service.ProductImageService;
import com.cex.tmall.service.ProductService;
import com.cex.tmall.service.ReviewService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService{

    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    //为多个分类填充产品集合
    @Override
    public void fill(List<Category> categorys) {
        for(Category category:categorys){
            fill(category);
        }
    }

    //为分类填充产品集合
    @Override
    public void fill(Category category) {
        List<Product> products = listByParent(category);
        category.setProducts(products);
        for(Product p:products){
            productImageService.setFirstProductImage(p);
        }
        category.setProducts(products);
    }

    //为多个分类填充推荐产品集合，8个一行
    @Override
    public void fillByRow(List<Category> categorys) {
        for(Category category:categorys){
            List<Product> productList = listByParent(category);
            List<List<Product>> listProductByRow = new ArrayList();
            for(int i=0; i<productList.size();i+=8){
                int size=i+8;
                size=productList.size()<8?productList.size():size;
                List<Product> eachRowProduct = productList.subList(i,size);
                listProductByRow.add(eachRowProduct);
            }
            category.setProductsByRow(listProductByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.total(product);
        product.setSaleCount(saleCount);
        int reviewCount = reviewService.total(product);
        product.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for(Product p : products){
            setSaleAndReviewNumber(p);
        }
    }

    public List<Product> search(String keyword, int start, int count) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.like("name","%"+keyword+"%"));
        dc.addOrder(Order.asc("id"));
        return findByCriteria(dc,start,count);
    }
}
