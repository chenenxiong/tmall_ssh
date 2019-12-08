package com.cex.tmall.comparator;

import com.cex.tmall.pojo.Product;

import java.util.Comparator;

public class ProductReviewCommparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()-p1.getReviewCount();
    }
}
