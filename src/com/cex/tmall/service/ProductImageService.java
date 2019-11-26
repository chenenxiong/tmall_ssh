package com.cex.tmall.service;

import com.cex.tmall.pojo.Product;
import com.cex.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService extends BaseService{
    public static final String type_single="type_single";
    public static final String type_detail="type_detail";

    public void setFirstProductImage(Product product);
}
