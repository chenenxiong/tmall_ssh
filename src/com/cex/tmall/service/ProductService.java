package com.cex.tmall.service;

import com.cex.tmall.pojo.Category;
import com.cex.tmall.pojo.Product;

import java.util.List;

public interface ProductService  extends BaseService{
    public void fill(List<Category> categorys);
    public void fill(Category category);
    public void fillByRow(List<Category> categorys);

    public void setSaleAndReviewNumber(Product product);
    public void setSaleAndReviewNumber(List<Product> products);
    public List<Product> search(String keyword,int start,int count);
}
