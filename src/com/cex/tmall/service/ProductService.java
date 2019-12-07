package com.cex.tmall.service;

import com.cex.tmall.pojo.Category;

import java.util.List;

public interface ProductService  extends BaseService{
    public void fill(List<Category> categorys);
    public void fill(Category category);
    public void fillByRow(List<Category> categorys);
}
